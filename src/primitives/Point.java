package primitives;

/**
 * Point class represents a point in a 3D coordinate system.
 *
 * author Shoham&Efrat
 */
public class Point {
    /**
     * The Double3 object that represents our point has 3 doubles with x, y, z coordinates.
     */
    final protected Double3 xyz;

    /**
     * A Point object that represents a point with all zero coordinates.
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Point constructor to initialize a new Point object using 3 doubles.
     *
     * @param d1 double that represents the x value in a point
     * @param d2 double that represents the y value in a point
     * @param d3 double that represents the z value in a point
     */
    public Point(double d1, double d2, double d3) {
        xyz = new Double3(d1, d2, d3);
    }

    /**
     * Point constructor to initialize a new Point object with Double3.
     *
     * @param d Double3 object that represents x, y, z coordinates
     */
    Point(Double3 d) {
        xyz = d;
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
        return (obj instanceof Point other) && xyz.equals(other.xyz);
    }

    /**
     * Adds a vector to this point.
     *
     * @param v the vector to add to the point
     * @return A new Point object resulting from the addition
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * Subtracts another point from this point.
     *
     * @param p the point to subtract
     * @return A new Vector object resulting from the subtraction
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param p Point object to measure the distance from the current point
     * @return The squared distance between the two points
     */
    public double distanceSquared(Point p) {
        double dx = xyz.d1 - p.xyz.d1;
        double dy = xyz.d2 - p.xyz.d2;
        double dz = xyz.d3 - p.xyz.d3;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param p Point object to measure the distance from the current point
     * @return The distance between the two points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
}
