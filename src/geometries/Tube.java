package geometries;

import primitives.*;

import java.util.List;

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
        // centerOfTube = point + (direction * t)
        double t = this.axis.getDirection().dotProduct(p.subtract(axis.getHead()));

        //P-P0 is orthogonal to the direction of the axis
        if (t ==0)
            throw new IllegalArgumentException("P-P0 is orthogonal to the direction of the axis");

        // Add If t==0?
        return p.subtract(axis.getPoint(t)).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
