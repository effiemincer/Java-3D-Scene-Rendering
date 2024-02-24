package primitives;

/**
 * Represents material properties for shading.
 */
public class Material {
    /** The diffuse reflection coefficient. */
    public Double3 kD = Double3.ZERO;

    /** The specular reflection coefficient. */
    public Double3 kS = Double3.ZERO;

    /**
     * The transparency coefficient.
     */
    public Double3 kT = Double3.ZERO;

    /**
     *  The reflection coefficient.
     */
    public Double3 kR = Double3.ZERO;

    /** The shininess coefficient. */
    public int nShininess = 1;

    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param kD The new diffuse reflection coefficient
     * @return This Material instance
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient using a single value.
     *
     * @param d The new diffuse reflection coefficient
     * @return This Material instance
     */
    public Material setKd(double d) {
        this.kD = new Double3(d);
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param kS The new specular reflection coefficient
     * @return This Material instance
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the specular reflection coefficient using a single value.
     *
     * @param d The new specular reflection coefficient
     * @return This Material instance
     */
    public Material setKs(double d) {
        this.kS = new Double3(d);
        return this;
    }

    /**
     * Sets the shininess coefficient.
     *
     * @param nShininess The new shininess coefficient
     * @return This Material instance
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the transparency coefficient.
     *
     * @param kT The new transparency coefficient
     * @return This Material instance
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the transparency coefficient using a single value.
     *
     * @param d The new transparency coefficient
     * @return This Material instance
     */
    public Material setKt(double d) {
        this.kT = new Double3(d);
        return this;
    }

    /**
     * Sets the reflection coefficient.
     *
     * @param kR The new reflection coefficient
     * @return This Material instance
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the reflection coefficient using a single value.
     *
     * @param d The new reflection coefficient
     * @return This Material instance
     */
    public Material setKr(double d) {
        this.kR = new Double3(d);
        return this;
    }
}
