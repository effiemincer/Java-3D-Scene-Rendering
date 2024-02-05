package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * A simple ray tracer that calculates the color of a point in a scene based on ray intersections.
 */
public class SimpleRayTracer extends RayTracerBase {

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
        return intersectionPoints == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersectionPoints));

    }

    /**
     * Calculates the color of a given point in the scene.
     *
     * @param gp The point for which to calculate the color.
     * @return The color of the specified point in the scene.
     */
    public Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
    }

}
