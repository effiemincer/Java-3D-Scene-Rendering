package geometries;

import primitives.*;

/**
 * Radial Geometry is an abstract class of the interface Geometry
 * @author Effie
 */
abstract class RadialGeometry implements Geometry{
    protected double radius;

    /** ctor that initializes RadialGeometry
     * @param r which is the radius
     * */
    RadialGeometry(double r) { radius = r; }
}
