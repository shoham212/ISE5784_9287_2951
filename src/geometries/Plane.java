package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a geometric plane in a three-dimensional space.
 * A plane is defined by a point on the plane and a normal vector perpendicular to it.
 *
 * author Shoham&Efrat
 */
public class Plane extends Geometry {

    /**
     * A point on the plane.
     */
    private final Point q;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane from three points lying on it.
     *
     * @param p1 The first point on the plane.
     * @param p2 The second point on the plane.
     * @param p3 The third point on the plane.
     */
    public Plane(Point p1, Point p2, Point p3) {
        q = p1;
        Vector v1 = p3.subtract(p1);
        Vector v2 = p2.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a plane from a point on the plane and its normal vector.
     *
     * @param p The point on the plane.
     * @param n The normal vector to the plane.
     */
    public Plane(Point p, Vector n) {
        q = p;
        normal = n.normalize();
    }

    /**
     * Gets the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // The beginning of the beam is exactly at the reference point of the plane. A given point inside the plane.
        if (ray.getHead().equals(q)) {
            return null;
        }

        double nv = normal.dotProduct(ray.getDirection());
        // A beam parallel to the plane
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(normal.dotProduct(q.subtract(ray.getHead())) / nv);

        // Neither parallel nor perpendicular but starts on a plane
        if (isZero(t)) {
            return null;
        }

        // Perpendicular to the plane
        if (t > 0 && alignZero(t - maxDistance) <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        return null;
    }

}


