package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.*;

/**
 * A simple ray tracer that calculates the color of a point in a scene based on ray intersections.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final double DELTA = 0.1;

    /**
     * Constructs a SimpleRayTracer object with the given scene.
     *
     * @param scene The scene to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces the given ray through the scene and returns the color of the closest intersection point.
     *
     * @param ray The ray to be traced.
     * @return The color of the closest intersection point, or the background color if no intersection is found.
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionPoints == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color of a given point in the scene.
     *
     * @param gp The point for which to calculate the color.
     * @return The color of the specified point in the scene.
     */
    public Color calcColor(GeoPoint gp, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(gp, ray));
    }

    /**
     * Calculates the color of a given point in the scene, based on local effects only.
     *
     * @param gp  The point for which to calculate the color.
     * @param ray The ray that hit the point
     * @return The color of the specified point in the scene.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (isZero(nv)) return Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if ((nl * nv > 0) && unshaded(gp, lightSource, l, n, nl)) {    // sign(nl) == sign(nv)
                Color lightIntensity = lightSource.getIntensity(gp.point);
                color = color.add(
                        lightIntensity.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }


    /**
     * Calculates the diffusive color of a point on a geometry.
     *
     * @param material The material of the geometry
     * @param nl       The dot product of n and l
     * @return The diffusive color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Calculates the specular color of a point on a geometry.
     *
     * @param material The material of the geometry
     * @param n        The normal vector
     * @param l        The light vector
     * @param nl       The dot product of n and l
     * @param v        The view vector
     * @return The specular color
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = alignZero(v.dotProduct(r));
        return material.kS.scale(Math.pow(Math.max(0, -vr), material.nShininess));
    }


    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray ray = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return true;


        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint gpoint : intersections) {
            if (alignZero(gpoint.point.distance(gp.point) - lightDistance) <= 0)
                return false;
        }
        return true;
    }
}
