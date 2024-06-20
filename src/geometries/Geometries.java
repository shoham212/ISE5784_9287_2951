package geometries;

import primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Composite class for all geometries object implementing {@link Intersectable}.
 */
public class Geometries extends Intersectable {
    public final List<Intersectable> geometries = new LinkedList<Intersectable>();

    /**
     * Empty geometries constructor.
     */
    public Geometries() {}

    /**
     * Constructs a new Geometries object with the specified intersectable geometries.
     *
     * @param geometries The intersectable geometries to add to this collection.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        LinkedList<GeoPoint> points = null;
        for (var geometry : geometries) {
            var geometryList = geometry.findGeoIntersectionsHelper(ray);
            if (geometryList != null) {
                if (points == null) {
                    points = new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
