package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents a cylinder in three-dimensional space, extending the Tube class.
 */

public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a Cylinder object with the specified height, axis, and radius.
     * @param h double The height of the cylinder.
     * @param a Ray The axis of the cylinder.
     * @param r double The radius of the cylinder.
     */
    public Cylinder(double h,Ray a,double r){
        super(a,r);
        height=h;
    }

    @Override
    public Vector getNormal(Point p){
        return null;
    }
}
