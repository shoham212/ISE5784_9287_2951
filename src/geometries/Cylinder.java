package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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
    public Vector getNormal(Point point) {
        // if the given point collides with the base point of the axis ray, just return the normal vector (dir)
        if (point.equals(this.axis.getHead())) return this.axis.getDirection();

        //calculating distance of the given point from base point of the axis ray
        double t = this.axis.getDirection().dotProduct(point.subtract(this.axis.getHead()));
        //if the given point is on one of the bases of the cylinder, we just return a normal vector to the base (dir)
        if (isZero(t) || isZero(t - this.height)) return this.axis.getDirection();
        return super.getNormal(point);
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }
}
