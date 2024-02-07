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

    public SpotLight setKc(double Kc) {
        super.setKc(Kc);
        return this;
    }

    public SpotLight setKl(double Kl) {
        super.setKl(Kl);
        return this;
    }

    public SpotLight setKq(double Kq) {
        super.setKq(Kq);
        return this;
    }

    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(Math.max(0, this.spotDirection.dotProduct(this.getL(point))));
    }

}
