package geometries;
import primitives.*;

/**
 * Represents a tube in a 3D space, a type of radial geometry defined by a radius and an axis (Ray).
 * The tube extends the RadialGeometry abstract class.
 */
public class Tube extends RadialGeometry {
    protected Ray axis; // The axis of the tube

    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point p) {
        //I don't know how to implement this
        //Vector vectorToSurface = p.subtract(axis.direction);

        //todo
        return null; // Returns null as the normal calculation is not implemented
    }
}
