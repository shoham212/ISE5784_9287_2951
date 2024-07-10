package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The {@code Intersectable} interface represents geometrical objects that can intersect with a ray.
 * Implementing classes must provide a method to find intersections between the object and a given ray.
 *
 * author Shoham&Efrat
 */
public abstract class Intersectable {

    /**
     * Finds the intersection points of a ray with the geometry.
     *
     * @param ray the ray that intersects the geometry.
     * @return a list of intersection points of the ray with the geometry.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * GeoPoint class is a helper class that holds a geometry and a point.
     */
    public static class GeoPoint {
        /**
         * The geometry that the point belongs to.
         */
        public Geometry geometry;

        /**
         * The point of intersection.
         */
        public Point point;

        /**
         * Constructs a GeoPoint object with the specified geometry and point.
         *
         * @param geometry the geometry.
         * @param point    the point.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public String toString() {
            return "GeoPoint [geometry=" + geometry + " point=" + point + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof GeoPoint other)
                    && (this.geometry == other.geometry)
                    && point.equals(other.point);
        }
    }

    /**
     * Finds all intersection points between a given ray and the geometries in the scene.
     *
     * @param ray the ray to check for intersections
     * @return a list of intersection points (GeoPoint) or {@code null} if there are no intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds all intersection points between a given ray and the geometries in the scene, within a specified maximum distance.
     *
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance to check for intersections
     * @return a list of intersection points (GeoPoint) or {@code null} if there are no intersections within the specified distance
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Helper method to find all intersection points between a given ray and the geometries in the scene, within a specified maximum distance.
     * This method should be implemented by subclasses to provide the actual intersection logic.
     *
     * @param ray the ray to check for intersections
     * @param maxDistance the maximum distance to check for intersections
     * @return a list of intersection points (GeoPoint) or {@code null} if there are no intersections within the specified distance
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);


}



