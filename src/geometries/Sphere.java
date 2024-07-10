package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Sphere class represents a three-dimensional spherical geometric shape.
 * It extends the RadialGeometry abstract class and inherits its radius property.
 * A sphere is characterized by its radius and center point.
 *
 * author Shoham&Efrat
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a sphere with the specified radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector normal = (point.subtract(center)).normalize();
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (ray.getHead().equals(center)) {
            double distanceToRadiusPoint = alignZero(ray.getPoint(radius).distance(ray.getHead()));
            if (distanceToRadiusPoint <= maxDistance) {
                return List.of(new GeoPoint(this, ray.getPoint(radius)));
            }
            return null;
        }

        // Check if there is intersection between them
        Vector v = center.subtract(ray.getHead());

        double tm = alignZero(ray.getDirection().dotProduct(v));

        // Check if the ray is tangent to the sphere
        double d = alignZero(v.lengthSquared() - tm * tm);
        if (alignZero(radius * radius - d) <= 0) return null;

        double th = Math.sqrt(radius * radius - d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        List<GeoPoint> result = new ArrayList<>();

        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            result.add(new GeoPoint(this, ray.getPoint(t1)));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            result.add(new GeoPoint(this, ray.getPoint(t2)));
        }

        return result.isEmpty() ? null : result;
    }

}

