package geometries;

/**
 * Abstract class representing a radial geometry in a geometric context, implementing the Geometry interface.
 * This class serves as a base for geometrical shapes that have radial properties, such as circles or spheres.
 */
abstract class RadialGeometry extends Geometry {
    protected double radius; // The radius of the radial geometry

    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
