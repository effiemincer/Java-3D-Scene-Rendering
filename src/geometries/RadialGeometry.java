package geometries;

import primitives.*;

/**
 * Abstract class representing a radial geometry in a geometric context, implementing the Geometry interface.
 * This class serves as a base for geometrical shapes that have radial properties, such as circles or spheres.
 */
abstract class RadialGeometry implements Geometry {
    protected final double radius; // The radius of the radial geometry

    /**
     * Constructs a RadialGeometry object with a specified radius.
     *
     * @param r The radius value of the radial geometry.
     */
    RadialGeometry(double r) {
        this.radius = r; // Initializes the radius of the radial geometry
    }
}
