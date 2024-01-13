package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sphere in a three-dimensional (3D) space, a type of radial geometry defined by a radius and a center point.
 * The sphere extends the RadialGeometry abstract class.
 */
public class Sphere extends RadialGeometry {
    private final Point center; // The center point of the sphere

    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point p) {
        // Assuming the point lies on the surface of the sphere
        // formula for normal is normal = (point - center) normalized
        return p.subtract(center).normalize();
    }

    public List<Point> findIntersections(Ray ray) {
        //following the formulas given on the slides

        //a normalized version of the ray
        Vector rayDirection = ray.getDirection();
        Vector headOfRayToOrigin = this.center.subtract(ray.getHead());
        double tm = rayDirection.dotProduct(headOfRayToOrigin);
        double d = Math.sqrt(headOfRayToOrigin.lengthSquared() - (tm*tm));

        if (d >= radius) return null;       //if (d <= r) then there are no intersections

        List<Point> res = new LinkedList<>();
        double th = Math.sqrt(radius*radius - d*d);
        double t1 = tm + th;
        double t2 = tm - th;

        //take only if t > 0
        if (t1 > 0 )
            res.add(ray.getPoint(t1));

        if (t2 > 0 )
            res.add(ray.getPoint(t2));

        return res;
    }
}
