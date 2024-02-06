package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction.normalize();
    }

    public Color getIntensity(Point point) {
        return this.intensity;
    }

    public Vector getL(Point point) {
        return this.direction;
    }
}
