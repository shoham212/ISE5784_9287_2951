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
     * Finds the intersection points of a ray with the geometry.
     *
     * @param ray the ray that intersects the geometry.
     * @return a list of GeoPoint objects representing the intersections of the ray with the geometry.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return this.findGeoIntersectionsHelper(ray);
    }

    /**
     * A helper method to find the intersection points of a ray with the geometry.
     *
     * @param ray the ray that intersects the geometry.
     * @return a list of GeoPoint objects representing the intersections of the ray with the geometry.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}



