package geometries;

import primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import static primitives.Util.alignZero;

/**
 * Composite class for all geometries object implementing {@link Intersectable}.
 */
public class Geometries extends Intersectable {

    public List<Intersectable> geometries = new LinkedList<Intersectable>();

    public void setBvh() {
        this.isBvh = true;
    }
    /**
     * Constructs a new Geometries object with the specified intersectable geometries.
     *
     * @param geometries The intersectable geometries to add to this collection.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
        this.maxPoint = findMaxPoint(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        this.minPoint = findMinPoint(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private Point findMaxPoint(double x, double y, double z) {
        for (Intersectable intersectable : geometries) {
            if (intersectable.maxPoint == null) continue;

            if (intersectable.maxPoint.getX() > x) x = intersectable.maxPoint.getX();
            if (intersectable.maxPoint.getY() > y) y = intersectable.maxPoint.getY();
            if (intersectable.maxPoint.getZ() > z) z = intersectable.maxPoint.getZ();
        }
        return new Point(x, y, z);
    }

    private Point findMinPoint(double x, double y, double z) {
        for (Intersectable intersectable : geometries) {
            if (intersectable.minPoint == null) continue;

            if (intersectable.minPoint.getX() < x) x = intersectable.minPoint.getX();
            if (intersectable.minPoint.getY() < y) y = intersectable.minPoint.getY();
            if (intersectable.minPoint.getZ() < z) z = intersectable.minPoint.getZ();
        }
        return new Point(x, y, z);
    }
    /**
     * Adds one or more geometries to this collection.
     *
     * @param geometries The geometries to add to this collection.
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        LinkedList<GeoPoint> points = null;
        for (var geometry : geometries) {
            if (!geometry.isRayIntersectingBoundingBox(ray, maxDistance) && isBvh ) continue; // && isBvh
            var geometryList = geometry.findGeoIntersectionsHelper(ray, maxDistance);
            if (geometryList != null) {
                for (GeoPoint gp : geometryList) {
                    if (alignZero(gp.point.distance(ray.getHead()) - maxDistance) <= 0) {
                        if (points == null) {
                            points = new LinkedList<>();
                        }
                        points.add(gp);
                    }
                }
            }
        }
        return points;
    }

    public boolean getBvh() {
        return isBvh;
    }
}
