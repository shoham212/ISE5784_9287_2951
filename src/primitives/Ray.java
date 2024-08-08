package primitives;

import java.util.LinkedList;
import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.*;

/**
 * A class representing a ray, which is a set of points on a straight line that are on one side of a given point called the head of the ray.
 *
 * author Shoham&Efrat
 */
public class Ray {

    private final double DELTA = 1e-10;

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

    /**
     * constructor for Ray
     * set the ray with the sliding of
     * the initial point in the delta on the normal
     *
     * @param p the initial point
     * @param v the direction of the ray - must already be normalized
     * @param n the normal
     */
    public Ray(Point p, Vector v, Vector n) {
        //point + normal.scale(±DELTA)
        double nv = n.dotProduct(v);
        Vector normalEpsilon = n.scale((nv > 0 ? DELTA : -DELTA));
        head = p.add(normalEpsilon);
        direction = v;
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

    /**
     *
     * @param n         normal to the geometry
     * @param radius    radius of the beam circle
     * @param distance  distance of the eam circle
     * @param numOfRays num of rays in the beam
     * @return list of beam rays
     */
    public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
        List<Ray> rays = new LinkedList<Ray>();
        rays.add(this);// Including the main ray
        if (numOfRays == 1 || isZero(radius))// The component (glossy surface /diffuse glass) is turned off
            return rays;

        // the 2 vectors that create the virtual grid for the beam
        Vector nX = direction.createNormal();
        Vector nY = direction.crossProduct(nX);

        Point centerCircle = this.getPoint(distance);
        Point randomPoint;
        Vector v12;

        double rand_x, rand_y, delta_radius = radius / (numOfRays - 1);
        double nv = n.dotProduct(direction);

        for (int i = 1; i < numOfRays; i++) {
            randomPoint = centerCircle;
            rand_x = random(-radius, radius);
            rand_y = randomSign() * Math.sqrt(radius * radius - rand_x * rand_x);

            try {
                randomPoint = randomPoint.add(nX.scale(rand_x));
            } catch (Exception ex) {
            }

            try {
                randomPoint = randomPoint.add(nY.scale(rand_y));
            } catch (Exception ex) {
            }

            v12 = randomPoint.subtract(head).normalize();

            double nt = alignZero(n.dotProduct(v12));

            if (nv * nt > 0) {
                rays.add(new Ray(head, v12));
            }
            radius -= delta_radius;
        }
        return rays;
    }
}

