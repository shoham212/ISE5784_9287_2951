package lighting;

import primitives.Color;

/**
 * The abstract class Light represents a source of light with a specific intensity.
 */
abstract class Light {
    /**
     * The intensity of the light.
     */
    protected Color intensity;

    /**
     * Constructs a Light object with the specified intensity.
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
