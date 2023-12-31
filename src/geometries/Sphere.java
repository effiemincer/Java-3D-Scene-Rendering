package geometries;
import primitives.*;

/**
 * Represents a sphere in a three-dimensional (3D) space, a type of radial geometry defined by a radius and a center point.
 * The sphere extends the RadialGeometry abstract class.
 */
public class Sphere extends RadialGeometry {
    private final Point center; // The center point of the sphere

    /**
     * Constructs a Sphere object with a specified radius and center point.
     *
     * @param r      The radius of the sphere.
     * @param center The center point of the sphere.
     */
    Sphere(double r, Point center) {
        super(r); // Initializes the sphere with the specified radius using the RadialGeometry superclass
        this.center = center; // Initializes the center point of the sphere
    }

    @Override
    public Vector GetNormal(Point p){
        // To be implemented
        return null; // Returns null as the normal calculation is not implemented
    }
}
