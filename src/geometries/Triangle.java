package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Triangle class represents a three-dimensional triangular geometric shape.
 * It extends the Polygon class and inherits its properties.
 *
 * author Shoham&Efrat
 */
public class Triangle extends Polygon  {
    /**
     * Constructs a triangle with the specified three points.
     *
     * @param p1 coordinate value for X axis
     * @param p2 coordinate value for Y axis
     * @param p3 coordinate value for Z axis
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);

        this.minPoint = new Point(
                Math.min(p1.getX(), Math.min(p2.getX(), p3.getX())),
                Math.min(p1.getY(), Math.min(p2.getY(), p3.getY())),
                Math.min(p1.getZ(), Math.min(p2.getZ(), p3.getZ()))
        );
        this.maxPoint = new Point(
                Math.max(p1.getX(), Math.max(p2.getX(), p3.getX())),
                Math.max(p1.getY(), Math.max(p2.getY(), p3.getY())),
                Math.max(p1.getZ(), Math.max(p2.getZ(), p3.getZ()))
        );
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Check if the ray intersects the bounding box before proceeding with further calculations
        if (!isRayIntersectingBoundingBox(ray, maxDistance)&& isBvh) {
            return null;
        }
        if (plane.findGeoIntersections(ray, maxDistance) == null)
            return null;

        List<Point> intersections = plane.findIntersections(ray);
        // if there are no intersections with the plane, there are no intersections with the triangle
        if (intersections == null) {
            return null;
        }

        // if the ray intersects the plane at the triangle's plane
        Vector v1 = vertices.get(0).subtract(ray.getHead());
        Vector v2 = vertices.get(1).subtract(ray.getHead());
        Vector v3 = vertices.get(2).subtract(ray.getHead());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double s1 = ray.getDirection().dotProduct(n1);
        double s2 = ray.getDirection().dotProduct(n2);
        double s3 = ray.getDirection().dotProduct(n3);

        // if the ray is parallel to the triangle's plane
        if (isZero(s1) || isZero(s2) || isZero(s3)) {
            return null;
        }

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            Point intersection = intersections.get(0);
            double distance = alignZero(intersection.distance(ray.getHead()));
            if (distance <= maxDistance) {
                return List.of(new GeoPoint(this, intersection));
            }
        }
        // if the ray intersects the plane but not the triangle or is beyond maxDistance
        return null;
    }

}
