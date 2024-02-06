package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

public interface LightSource {

    public Color getIntensity(Point point);

    public Vector getL(Point point);
}
