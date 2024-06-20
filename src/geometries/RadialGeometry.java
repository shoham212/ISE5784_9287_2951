package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The RadialGeometry class represents a geometric shape characterized by its radius.
 * This class serves as an abstract base class for various geometric shapes
 * such as spheres, cylinders, and cones.
 */
public abstract class RadialGeometry extends Geometry {
    /** The radius of the radial geometry. */
    final protected double radius;

    /**
     * Constructs a radial geometry with the specified radius.
     *
     * @param radius The radius of the radial geometry.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates and returns the normal vector to the radial geometry at the specified point.
     *
     * @param point The point on the radial geometry for which the normal vector is to be calculated.
     * @return The normal vector to the radial geometry at the specified point. Since the shape
     *         is radial, the normal vector is constant throughout and is returned as null.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
