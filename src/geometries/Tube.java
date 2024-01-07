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

        // t = direction (dot product) point - head
        //centerOfTube = point + (direction * t)
        double t = this.axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        if (t == 0) throw new IllegalArgumentException("Your point and the head of the ray are orthogonal to the axis");

        Point centerOfTube = (axis.getHead()).add((axis.getDirection()).scale(t));
        return p.subtract(centerOfTube).normalize();
    }
}
