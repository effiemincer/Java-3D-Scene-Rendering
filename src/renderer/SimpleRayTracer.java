package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
        List<Point> intersectionPoints = scene.geometries.findIntersections(ray);
        if (intersectionPoints == null)
            return scene.background;
        Point closestPoint = ray.findClosestPoint(intersectionPoints);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color of a given point in the scene.
     *
     * @param point The point for which to calculate the color.
     * @return The color of the specified point in the scene.
     */
    public Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
