package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * @author Shoham&Efrat
 * The Sphere class represents a three-dimensional spherical geometric shape.
 * It extends the RadialGeometry abstract class and inherits its radius property.
 * A sphere is characterized by its radius and center point.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a sphere with the specified radius and center point.
     * @param r The radius of the sphere.
     * @param c The center point of the sphere.
     */
    public Sphere(double r,Point c){
        super(r);
        center=c;
    }

    @Override
    public Vector getNormal(Point p){
        Vector normal = (p.subtract(center)).normalize();
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        if (ray.getHead().equals(center)) {
            return List.of(ray.getPoint(radius));
        }
        //check if there is intersection between them
        Vector v = center.subtract(ray.getHead());

        double tm = alignZero(ray.getDirection().dotProduct(v));

        //check if the ray is tangent to the sphere
        double d = alignZero(Math.sqrt(v.lengthSquared() - tm * tm));
        if (d >= radius) return null;
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 > 0 && t2 > 0) {
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        }
        if (t1 > 0) {
            return List.of(ray.getPoint(t1));
        }
        if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        }
        return null;
    }


}
