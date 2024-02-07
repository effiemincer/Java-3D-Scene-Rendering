package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    public Point position;
    public double Kc = 1d, Kl = 0d, Kq = 0d;

    public PointLight(Color color, Point point) {
        super(color);
        this.position = point;
    }

    public PointLight setKc(double Kc) {
        this.Kc = Kc;
        return this;
    }

    public PointLight setKl(double Kl) {
        this.Kl = Kl;
        return this;
    }

    public PointLight setKq(double Kq) {
        this.Kq = Kq;
        return this;
    }

    public Color getIntensity(Point point) {
        double d = point.distance(this.position);
        Color I0 = this.getIntensity();
        return I0.scale(1 / (Kc + Kl * d + Kq * d * d));
    }

    public Vector getL(Point point) {
        return point.subtract(this.position).normalize();
    }
}
