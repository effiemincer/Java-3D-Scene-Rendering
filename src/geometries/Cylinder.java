package geometries;

import primitives.*;

public class Cylinder extends Tube{
    private final double height ;

    /**
     * ctor that initializes RadialGeometry
     * JUST DID THIS TO AVOID ERRORS
     *
     * @param r    which is the radius
     * @param axis
     */
    Cylinder(double r, Ray axis, double height) {
        super(r, axis);
        this.height = height;
    }


    @Override
    public Vector GetNormal(Point p) {
        return null;
    }
}
