package primitives;

public class Material {
    /**
     * The diffuse reflection coefficient of the material.
     */
    public Double3 kd = Double3.ZERO;

    /**
     * The specular reflection coefficient of the material.
     */
    public Double3 ks = Double3.ZERO;

    /**
     * The shininess of the material.
     */
    public int nShininess = 0;

    /**
     * Setter for kd.
     *
     * @param kd the kd to set
     * @return the material itself for method chaining
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    /**
     * Setter for kd with regular double.
     *
     * @param kd the kd to set
     * @return the material itself for method chaining
     */
    public Material setKd(double kd) {
        this.kd = new Double3(kd);
        return this;
    }

    /**
     * Setter for ks.
     *
     * @param ks the ks to set
     * @return the material itself for method chaining
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }

    /**
     * Setter for ks with regular double.
     *
     * @param ks the ks to set
     * @return the material itself for method chaining
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    /**
     * Setter for nShininess.
     *
     * @param nShininess the nShininess to set
     * @return the material itself for method chaining
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
