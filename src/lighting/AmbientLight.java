package lighting;

import primitives.Color;
import primitives.Double3;


public class AmbientLight {

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    public AmbientLight(Color color, Double d) {
    }

    public AmbientLight(Color color, Double3 d) {
    }

    public Color getIntensity() {
        return null;
    }
}
