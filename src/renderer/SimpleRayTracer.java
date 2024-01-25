package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase {

    public SimpleRayTracer(Scene s) {
        super(s);
    }

    @Override
    public Color traceRay(Ray r) {
        List<Point> points = scene.geometries.findIntersections(r);
        if (points == null)
            return scene.background;
        Point closestPoint = r.findClosestPoint(points);
        return calcColor(closestPoint);
    }

    public Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
