package geometries;
import primitives.*;

/**
 * Represents a tube in a 3D space, a type of radial geometry defined by a radius and an axis (Ray).
 * The tube extends the RadialGeometry abstract class.
 */
public class Tube extends RadialGeometry {
    protected final Ray axis; // The axis of the tube

    /**
     * Constructs a Tube object with a specified radius and axis.
     * NEEDED TO IMPLEMENT A CONSTRUCTOR TO AVOID ERRORS
     *
     * @param r    The radius of the tube.
     * @param axis The axis (Ray) of the tube.
     */
    Tube(double r, Ray axis) {
        super(r); // Initializes the tube with the specified radius using the RadialGeometry superclass
        this.axis = axis; // Initializes the axis of the tube
    }


    @Override
    public Vector GetNormal(Point p) {
        return null; // Returns null as the normal calculation is not implemented
    }
}
