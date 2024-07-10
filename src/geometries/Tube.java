package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Tube class represents a three-dimensional cylindrical geometric shape
 * with a constant radius along its length. It extends the RadialGeometry abstract class
 * and inherits its radius property. A tube is characterized by its axis, which is a ray
 * representing the direction and origin of the cylinder.
 *
 * author Shoham&Efrat
 */
public class Tube extends RadialGeometry {
    /**
     * The axis of the tube, represented by a ray.
     */
    protected final Ray axis;

    /**
     * Constructs a tube with the specified axis and radius.
     *
     * @param axis   The axis of the tube, represented by a ray.
     * @param radius The radius of the tube.
     */
    public Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        double t = (point.subtract(axis.getHead())).dotProduct(axis.getDirection());
        if (isZero(t)) {
            return (point.subtract(axis.getHead())).normalize();
        } else {
            Point o = axis.getPoint(t);
            return (point.subtract(o)).normalize();
        }
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {return null;}
}
