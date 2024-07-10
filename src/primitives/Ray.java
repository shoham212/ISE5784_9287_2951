package primitives;

import static primitives.Util.isZero;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * A class representing a ray, which is a set of points on a straight line that are on one side of a given point called the head of the ray.
 *
 * author Shoham&Efrat
 */
public class Ray {
    /**
     * The start of the line.
     */
    private final Point head;

    /**
     * The direction vector that represents the direction of the line.
     */
    private final Vector direction;

    /**
     * Getter method for head.
     *
     * @return the head point of the ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Getter method for direction.
     *
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Ray constructor to initialize a new Ray object with head and direction (direction should be normalized).
     *
     * @param head a new head point to assign head with
     * @param direction a new direction vector to assign direction with
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    @Override
    public String toString() {
        return "Ray [head=" + head + ", direction=" + direction + "]";
    }

    /**
     * Ray function for calculating a point on the beam line at a given distance from the beam head.
     *
     * @param t distance from the beam head
     * @return a point on the beam line
     */
    public Point getPoint(double t) {
        return isZero(t) ? head : head.add(direction.scale(t));
        //if (isZero(t)) {
        //    return head;
        //}
       // return head.add(direction.scale(t));
    }

    /**
     * Finds the closest point to the starting point of the ray from a collection of points.
     *
     * @param points a collection of points
     * @return the point closest to the starting point of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint to the starting point of the ray from a collection of GeoPoints.
     *
     * @param points a collection of GeoPoints
     * @return the GeoPoint closest to the starting point of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null) {
            return null;
        }
        GeoPoint closest = points.get(0);
        double distance = head.distance(closest.point);
        for (GeoPoint p : points) {
            double currentDistance = head.distance(p.point);
            if (currentDistance < distance) {
                closest = p;
                distance = currentDistance;
            }
        }
        return closest;
    }
}

