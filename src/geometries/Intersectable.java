package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The {@code Intersectable} interface represents geometrical objects that can intersect with a ray.
 * Implementing classes must provide a method to find intersections between the object and a given ray.
 * @author Shoham&Efrat
 */
public interface Intersectable {

    List<Point> findIntersections(Ray ray);
}
