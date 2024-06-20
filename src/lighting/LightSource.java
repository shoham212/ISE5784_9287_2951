package lighting;

import primitives.*;

/**
 * The LightSource interface represents a source of light in a scene.
 * It provides methods to get the intensity of the light at a specific point
 * and to get the direction vector from the light source to a specific point.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param point the point at which to get the light intensity
     * @return the intensity of the light at the specified point
     */
    Color getIntensity(Point point);

    /**
     * Returns the direction vector from the light source to the specified point.
     *
     * @param point the point to which to get the direction vector
     * @return the direction vector from the light source to the specified point
     */
    Vector getL(Point point);
}
