package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Represents ambient light in a scene, which provides overall illumination regardless of direction.
 */
public class AmbientLight {

    /** A constant representing no ambient light. */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    private final Color intensity;

    /**
     * Constructs an ambient light with the specified color and intensity factor.
     * @param color The color of the ambient light.
     * @param d     The intensity factor of the ambient light as a scalar.
     */
    public AmbientLight(Color color, Double d) {
        intensity = color.scale(d);
    }

    /**
     * Constructs an ambient light with the specified color and intensity.
     * @param color The color of the ambient light.
     * @param d     The intensity of the ambient light as a vector.
     */
    public AmbientLight(Color color, Double3 d) {
        this.intensity = color.scale(d);
    }

    /**
     * Gets the intensity of the ambient light.
     * @return The intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
