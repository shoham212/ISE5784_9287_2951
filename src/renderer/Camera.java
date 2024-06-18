package renderer;

import java.util.MissingResourceException;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import primitives.*;

/**
 * The Camera class represents a camera in a 3D space.
 * It defines the camera's position, orientation, and properties of the view plane.
 */
public class Camera implements Cloneable{

    private static final String MISSING_RENDERING_DATA = "missing rendering data";
    private static final String CAMERA_CLASS_NAME = "Camera";
    private Point location;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width = 0;
    private double height = 0;
    private double distance = 0;

    /**
     * Default constructor for the Camera class.
     * This is a private constructor to prevent direct instantiation.
     */
    private Camera() {
    }

    /**
     * Returns the width of the view plane.
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the distance from the camera to the view plane.
     *
     * @return the distance from the camera to the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Creates a new Builder for constructing Camera instances.
     *
     * @return a new Builder instance
     */
    public static Builder getBuilder() {
        return new Builder();
    }
    /**
     * Constructs a ray through a specific pixel on the view plane.
     *
     * @param nX the number of horizontal pixels in the view plane
     * @param nY the number of vertical pixels in the view plane
     * @param j  the horizontal index of the pixel (0-based)
     * @param i  the vertical index of the pixel (0-based)
     * @return the constructed ray through the specified pixel
     * @throws IllegalArgumentException if nX or nY is zero
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        Point Pc = location.add(vTo.scale(distance));
        double Ry = height / nY;
        double Rx = width / nX;

        double Yi = -1 * (i - (nY - 1) / 2.0) * Ry;
        double Xj = (j - (nX - 1) / 2.0) * Rx;

        Point Pij = Pc;
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }

        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        return new Ray(location, Pij.subtract(location));
    }

    /**
     * The Builder class for constructing Camera instances.
     */
    public static class Builder {
        private final Camera camera = new Camera();
        /**
         * Sets the location of the camera.
         *
         * @param location the location of the camera
         * @return this Builder instance
         */
        public Builder setLocation(Point location) {
            camera.location = location;
            return this;
        }

        /**
         * Sets the direction of the camera.
         *
         * @param toVector the vector pointing forward
         * @param upVector the vector pointing upwards
         * @return this Builder instance
         * @throws IllegalArgumentException if the vectors are not perpendicular or not normalized
         */
        public Builder setDirection(Vector toVector, Vector upVector) {
            if (toVector.dotProduct(upVector) != 0) {
                throw new IllegalArgumentException("The vectors must be orthogonal");
            }
            camera.vTo = toVector.normalize();
            camera.vUp = upVector.normalize();
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return this Builder instance
         * @throws IllegalArgumentException if width or height is not positive
         */
        public Builder setVpSize(double width, double height) {
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Sets the distance between the camera and the view plane.
         *
         * @param distance the distance between the camera and the view plane
         * @return this Builder instance
         * @throws IllegalArgumentException if the distance is not positive
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be a positive value.");
            }
            camera.distance = distance;
            return this;
        }

        /**
         * Builds the Camera instance, ensuring all necessary fields are initialized.
         *
         * @return a clone of the Camera object with calculated fields
         * @throws MissingResourceException if any required field is missing
         * @throws IllegalArgumentException if any field has an invalid value
         */
        public Camera build() {
            if (camera.location == null) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "p0");
            }
            if (camera.vUp == null) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "vUp");
            }
            if (camera.vTo == null) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "vTo");
            }
            if (!isZero(camera.vTo.dotProduct(camera.vUp))) {
                throw new MissingResourceException("", CAMERA_CLASS_NAME, "the vTo and vUp are not orthogonal");
            }
            if (alignZero(camera.width) == 0) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "width");
            }
            if (alignZero(camera.height) == 0) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "height");
            }
            if (alignZero(camera.distance) == 0) {
                throw new MissingResourceException(MISSING_RENDERING_DATA, CAMERA_CLASS_NAME, "distance");
            }
            if (alignZero(camera.width) < 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }
            if (alignZero(camera.height) < 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }
            if (alignZero(camera.distance) < 0) {
                throw new IllegalArgumentException("Distance must be greater than 0");
            }

            camera.vUp = camera.vUp.normalize();
            camera.vTo = camera.vTo.normalize();
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
