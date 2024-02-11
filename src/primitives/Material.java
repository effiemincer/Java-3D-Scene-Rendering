package primitives;

/**
 * Represents material properties for shading.
 */
public class Material {
    /** The diffuse reflection coefficient. */
    public Double3 kD = Double3.ZERO;

    /** The specular reflection coefficient. */
    public Double3 kS = Double3.ZERO;

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
}
