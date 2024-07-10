package primitives;

/**
 * Represents the material properties of a surface.
 */
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
     * The transparency coefficient of the material.
     */
    public Double3 kT = Double3.ZERO;

    /**
     * The reflection coefficient of the material.
     */
    public Double3 kR = Double3.ZERO;

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

    /**
     * Setter for kT.
     *
     * @param kT the kT to set
     * @return the material itself for method chaining
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Setter for kT with regular double.
     *
     * @param kT the kT to set
     * @return the material itself for method chaining
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Setter for kR.
     *
     * @param kR the kR to set
     * @return the material itself for method chaining
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Setter for kR with regular double.
     *
     * @param kR the kR to set
     * @return the material itself for method chaining
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
}
