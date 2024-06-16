package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Plane class represents a geometric plane in a three-dimensional space.
 * A plane is defined by a point on the plane and a normal vector perpendicular to it.
 */
public class Plane implements Geometry {

    /** A point on the plane. */
    private final Point q;

    /** The normal vector to the plane. */
    private final Vector normal;

    /**
     * Constructs a plane from three points lying on it.
     * @param p1 The first point on the plane.
     * @param p2 The second point on the plane.
     * @param p3 The third point on the plane.
     */
    public Plane (Point p1,Point p2,Point p3){
        q=p1;
        normal=null;
    }

    /**
     * Constructs a plane from a point on the plane and its normal vector.
     * @param p The point on the plane.
     * @param n The normal vector to the plane.
     */
    public Plane (Point p,Vector n ){
        q=p;
        normal= n.normalize();
    }

    /**
     * Gets the normal vector to the plane.
     * @return The normal vector to the plane.
     */
    public Vector getNormal(){
        return normal;
    }
    @Override
    public Vector getNormal(Point p){
        return null;
    }
}

