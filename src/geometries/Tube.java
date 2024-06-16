package geometries;

import  primitives.Ray;
import primitives.Point;
import primitives.Vector;

/**
 * The Tube class represents a three-dimensional cylindrical geometric shape
 * with a constant radius along its length. It extends the RadialGeometry abstract class
 * and inherits its radius property. A tube is characterized by its axis, which is a ray
 * representing the direction and origin of the cylinder.
 */
public class Tube extends RadialGeometry {
    /** The axis of the tube, represented by a ray. */
    protected final Ray axis;

    /**
     * Constructs a tube with the specified axis and radius.
     * @param a The axis of the tube, represented by a ray.
     * @param r The radius of the tube.
     */
    public Tube(Ray a,double r){
        super(r);
        axis=a;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
