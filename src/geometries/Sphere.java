package geometries;
import primitives.*;

/**
 * Represents a sphere in a three-dimensional (3D) space, a type of radial geometry defined by a radius and a center point.
 * The sphere extends the RadialGeometry abstract class.
 */
public class Sphere extends RadialGeometry {
    private Point center; // The center point of the sphere


    @Override
    public Vector getNormal(Point p){
        // To be implemented
        return null; // Returns null as the normal calculation is not implemented
    }
}
