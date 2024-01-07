package geometries;

import primitives.*;

/**
 * Represents a cylinder in a three-dimensional (3D) space, inheriting from the Tube class.
 * The cylinder is defined by its radius, axis, and height.
 */
public class Cylinder extends Tube {
    private final double height; // The height of the cylinder

    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p) {
        //find out where the point is
        //get vector from base to the head and see if it's orthogonal to a vector to the point
        //if it's on the base then return the positive or negative
        //otherwise use getNormal from tube

        return null; // Currently returns null as the normal calculation is not implemented for a cylinder
    }
}
