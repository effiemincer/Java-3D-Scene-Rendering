package lighting;

import primitives.Color;

/**
 * Represents a light in a scene.
 */
abstract class Light {
    protected Color intensity;

    /**
     * Constructs a new Light object with the given intensity.
     *
     * @param intensity The intensity of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
