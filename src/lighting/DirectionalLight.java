package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

/**
 * Represents a directional light in a scene, which provides illumination from a single direction.
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     * The direction of the light.
     */
    private final Vector direction;

    /**
     * Constructs a directional light with the specified color and direction.
     *
     * @param color     The color of the light.
     * @param direction The direction of the light.
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction.normalize();
    }

    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param point The point to which the intensity is calculated.
     * @return The intensity of the light at the specified point.
     */
    public Color getIntensity(Point point) {
        return this.intensity;
    }

    /**
     * Returns the direction of the light at the specified point.
     *
     * @param point The point to which the vector is calculated.
     * @return The direction of the light at the specified point.
     */
    public Vector getL(Point point) {
        return this.direction;
    }

    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
