package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in a three-dimensional space.
 * Any class implementing this interface must provide methods to calculate various
 * properties of the shape.
 */
public interface Geometry {
    /**
     *An unrealized function is to calculate a normal for any geometric surface.
     */
    public Vector getNormal(Point p);
}
