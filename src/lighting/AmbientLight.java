package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Represents ambient light in a scene, which provides overall illumination regardless of direction.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Empty constructor for AmbientLight.
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructs an ambient light with the specified color and intensity.
     *
     * @param color The color of the ambient light.
     * @param d     The intensity of the ambient light as a Double3.
     */
    public AmbientLight(Color color, Double3 d) {
        super(color.scale(d));
    }


}
