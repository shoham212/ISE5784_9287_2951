package primitives;

//import static primitives.Util.isZero;

//import java.util.Objects;

/**
 * @author Shoham&Efrat
 * Point class represent a point in a 3D coordinate system
 */

public class Point
{
    /**
     * The Double3 object that represents our point has 3 doubles with x, y, z coordinates
     */
    final protected Double3 xyz;
    /**
     * A Point object that represent a point with all zero coordinates
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Point constructor to initialize a new Point object using 3 doubles
     * @param d1 double that represent the x value in a point
     * @param d2 double that represent the y value in a point
     * @param d3 double that represent the z value in a point
     */
    public Point(double d1,double d2, double d3){
        xyz = new Double3(d1,d2,d3);
    }

    /**
     * Point constructor to initialize a new Point object with Double3
     * @param d Double3 object that represent an x, y, z coordinate
     */
    Point(Double3 d){
        xyz=d;
    }

    /**
     * @return The X-coordinate of this point.
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * @return The Y-coordinate of this point.
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * @return The Z-coordinate of this point.
     */
    public double getZ() {
        return xyz.d3;
    }


    @Override
    public String toString() {
        return "Point [xyz=" + xyz + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && xyz.equals(other.xyz);
    }

    /**
     * Add function that takes a vector (uses the point behind the vector class)
     * and add it to a point by adding their corresponding coordinates (using Double3 add function)
     * @param v the vector that's being added to the point
     * @return A new Point object of the result point
     */
    public Point add(Vector v) {return new Point(xyz.add(v.xyz)); }
    /**
     * Subtract function that takes a point and subtract with other point by using their corresponding coordinates
     * (using Double3 subtract function)
     * @param p the point that's being subtracted
     * @return A new Vector object of the result point
     */
    public Vector subtract(Point p) {return new Vector(this.xyz.subtract(p.xyz));}

    /**
     * distanceSquared function that's calculate the distance between two points
     * @param p Point object that its distance has being measured from the current point
     * @return The squared distance between the two point (type: double)
     */
    public double distanceSquared(Point p) {
        double dx = xyz.d1 - p.xyz.d1;
        double dy = xyz.d2 - p.xyz.d2;
        double dz = xyz.d3 - p.xyz.d3;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * distanceSquared function that's calculate the distance between two points (using distanceSquared squared root)
     * @param p Point object that its distance has being measured from the current point
     * @return The distance between the two point (type: double)
     */
    public double distance(Point p) { return Math.sqrt(distanceSquared(p));}
}