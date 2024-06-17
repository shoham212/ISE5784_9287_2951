package geometries;

import  primitives.Ray;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * @author Shoham&Efrat
 * The Tube class represents a three-dimensional cylindrical geometric shape
 * with a constant radius along its length. It extends the RadialGeometry abstract class
 * and inherits its radius property. A tube is characterized by its axis, which is a ray
 * representing the direction and origin of the cylinder.
 */
public class Tube extends RadialGeometry {
    /**
     * The axis of the tube, represented by a ray.
     */
    protected final Ray axis;

    /**
     * Constructs a tube with the specified axis and radius.
     *
     * @param a The axis of the tube, represented by a ray.
     * @param r The radius of the tube.
     */
    public Tube(Ray a, double r) {
        super(r);
        axis = a;
    }

    @Override
    public Vector getNormal(Point p) {
        double t = (p.subtract(axis.getHead())).dotProduct(axis.getDirection());
        if (isZero(t)) {
            return (p.subtract(axis.getHead())).normalize();
        } else {
            Point o = axis.getHead().add(axis.getDirection().scale(t));
            return (p.subtract(o)).normalize();
        }
    }
}
