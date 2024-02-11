package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

public interface LightSource {

    /**
     * Gets the intensity of the light at the given point.
     *
     * @param point The point to which the intensity is calculated.
     * @return The intensity of the light at the given point.
     */
    public Color getIntensity(Point point);

    /**
     * Gets the vector from the light source to the given point.
     *
     * @param point The point to which the vector is calculated.
     * @return The vector from the light source to the given point.
     */
    public Vector getL(Point point);
}
