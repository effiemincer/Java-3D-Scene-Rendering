package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    protected Point position;
    protected Double Kc = 1d, Kl = 0d, Kq = 0d;
    protected Color Il = null;

    public PointLight(Color color, Point Point) {
        super(color);
        this.position = position;
    }

    public PointLight setKc(Double Kc) {
        this.Kc = Kc;
        return this;
    }

    public PointLight setKl(Double Kl) {
        this.Kl = Kl;
        return this;
    }

    public PointLight setKq(Double Kq) {
        this.Kq = Kq;
        return this;
    }

    public Color getIntensity(Point point) {
        if (this.Il == null) {
            double d = this.position.distance(point);
            Color I0 = this.getIntensity();
            this.Il = I0.scale(1 / (Kc + Kl * d + Kq * d * d));
        }
        return this.Il;
    }

    public Vector getL(Point point) {
        return this.position.subtract(point).normalize();
    }
}
