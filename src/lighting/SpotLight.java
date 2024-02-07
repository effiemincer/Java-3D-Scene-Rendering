package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *  Represents a spotlight in a scene, which provides illumination from a single point in a specific direction.
 */
public class SpotLight extends PointLight {

    /**
     * The direction of the spotlight.
     */
    private final Vector spotDirection;
    /**
     * The narrowness factor of the spotlight beam.
     */
    private int narrowBeam = 1;

    /**
     * Constructs a spotlight with the specified color, position, and direction.
     *
     * @param color The color of the light.
     * @param point The position of the light.
     * @param vector The direction of the light.
     */
    public SpotLight(Color color, Point point, Vector vector) {
        super(color, point);
        this.spotDirection = vector.normalize();
    }

    /**
     * Returns the direction of the spotlight at the specified point.
     *
     * @param Kc The constant attenuation factor.
     * @return The spotlight with the specified constant attenuation factor.
     */
    public SpotLight setKc(double Kc) {
        super.setKc(Kc);
        return this;
    }

    /**
     * Sets the linear attenuation factor of the spotlight.
     *
     * @param Kl The linear attenuation factor.
     * @return The spotlight with the specified linear attenuation factor.
     */
    public SpotLight setKl(double Kl) {
        super.setKl(Kl);
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the spotlight.
     *
     * @param Kq The quadratic attenuation factor.
     * @return The spotlight with the specified quadratic attenuation factor.
     */
    public SpotLight setKq(double Kq) {
        super.setKq(Kq);
        return this;
    }

    /**
     * Returns the direction of the spotlight at the specified point.
     *
     * @param n the narrowBeam to set
     * @return The spotlight with the specified narrowness factor.
     */
    public SpotLight setNarrowBeam(int n ){
        narrowBeam = n;
        return this;
    }

    /**
     * Returns the direction of the spotlight at the specified point.
     *
     * @param point The point to which the intensity is calculated.
     * @return The intensity of the light at the given point.
     */
    public Color getIntensity(Point point) {
        // Calculate the dot product of the spotlight direction and the vector towards the point
        double directionDotProduct = this.spotDirection.dotProduct(this.getL(point));

        // If the spotlight has a narrow beam, scale the intensity by the dot product raised to the power of the narrow beam
        double t = directionDotProduct > 0 ? Math.pow(directionDotProduct, narrowBeam) : 0;

        // Scale the intensity by the dot product and factor of the narrow beam
        return super.getIntensity(point).scale(Math.max(0, t));
    }
}
