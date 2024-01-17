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

    /**
     * Finds intersections of a ray with a sphere using the formulas provided from the slides.
     *
     * @param ray The ray for which intersections with the sphere are to be found.
     * @return A list of intersection points, or {@code null} if there are no intersections.
     */
    public List<Point> findIntersections(Ray ray) {
        //following the formulas given on the slides

        List<Point> res = new LinkedList<>();

        //if ray starts at the center point
        if (ray.getHead().equals(this.center)) {
            res.add(ray.getPoint(radius));
            return res;
        }

        //a normalized version of the ray
        Vector rayDirection = ray.getDirection();

        Vector headOfRayToOrigin = this.center.subtract(ray.getHead());
        double tm = rayDirection.dotProduct(headOfRayToOrigin);
        double d = Math.sqrt(headOfRayToOrigin.lengthSquared() - (tm * tm));

        if (d >= radius) return null;       //if (d >= r) then there are no intersections

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;

        //take only if t > 0
        if (t1 > 0)
            res.add(ray.getPoint(t1));

        if (t2 > 0) {
            //puts the closer point to the head of the ray first in the list
            double distance1 = ray.getPoint(t1).distance(ray.getHead());
            double distance2 = ray.getPoint(t2).distance(ray.getHead());
            if (distance1 > distance2 && t1 > 0)
                res.addFirst(ray.getPoint(t2));
            else
                res.add(ray.getPoint(t2));
        }

        if (!res.isEmpty())
            return res;
        return null;
    }
}
