package geometries;

import primitives.*;

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
        return null;
    }
}
