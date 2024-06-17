package geometries;

import primitives.Point;
import primitives.Vector;

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

}
