package geometries;
import primitives.*;

/**
 * Represents a tube in a 3D space, a type of radial geometry defined by a radius and an axis (Ray).
 * The tube extends the RadialGeometry abstract class.
 */
public class Tube extends RadialGeometry {
    protected Ray axis; // The axis of the tube


    @Override
    public Vector getNormal(Point p) {
        return null; // Returns null as the normal calculation is not implemented
    }
}
