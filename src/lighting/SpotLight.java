package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {

    private Vector spotDirection;

    public SpotLight(Color color, Point point, Vector vector) {
        super(color, point);
        this.spotDirection = vector.normalize();
    }

    public SpotLight setKc(Double Kc) {
        super.setKc(Kc);
        return this;
    }

    public SpotLight setKl(Double Kl) {
        super.setKl(Kl);
        return this;
    }

    public SpotLight setKq(Double Kq) {
        super.setKq(Kq);
        return this;
    }

    public Color getIntensity(Point point) {
        this.Il = super.getIntensity(point).scale(Math.max(0, this.spotDirection.dotProduct(this.getL(point))));
        return this.Il;
    }

}
