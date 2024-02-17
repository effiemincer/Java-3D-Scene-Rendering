package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a point light in a scene, which provides illumination from a single point.
 */
public class PointLight extends Light implements LightSource {
    /**
     * The position of the light.
     */
    public Point position;
    /**
     * The constant attenuation factor,
     */
    public double Kc = 1d;
    /**
     * The linear attenuation factor,
     */
    public double Kl = 0d;
    /**
     * The quadratic attenuation factor.
     */
    public double Kq = 0d;

    /**
     * Constructs a point light with the specified color and position.
     *
     * @param color The color of the light.
     * @param point The position of the light.
     */
    public PointLight(Color color, Point point) {
        super(color);
        this.position = point;
    }

    /**
     * Sets the constant attenuation factor of the point light.
     *
     * @param Kc The constant attenuation factor.
     * @return The point light with the specified constant attenuation factor.
     */
    public PointLight setKc(double Kc) {
        this.Kc = Kc;
        return this;
    }

    /**
     * Sets the linear attenuation factor of the point light.
     *
     * @param Kl The linear attenuation factor.
     * @return The point light with the specified linear attenuation factor.
     */
    public PointLight setKl(double Kl) {
        this.Kl = Kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the point light.
     *
     * @param Kq The quadratic attenuation factor.
     * @return The point light with the specified quadratic attenuation factor.
     */
    public PointLight setKq(double Kq) {
        this.Kq = Kq;
        return this;
    }

    /**
     * Gets the intensity of the light at the given point.
     *
     * @param point The point to which the intensity is calculated.
     * @return The intensity of the light at the given point.
     */
    public Color getIntensity(Point point) {
        double d = point.distance(this.position);
        Color I0 = this.getIntensity();
        return I0.scale(1 / (Kc + Kl * d + Kq * d * d));
    }

    /**
     * Gets the vector from the light source to the given point.
     *
     * @param point The point to which the vector is calculated.
     * @return The vector from the light source to the given point.
     */
    public Vector getL(Point point) {
        return point.subtract(this.position).normalize();
    }

    public double getDistance(Point point) {
        return point.distance(this.position);
    }
}
