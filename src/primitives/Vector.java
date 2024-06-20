package primitives;

/**
 * Vector class represents a fundamental object in the geometry of three dimensions with direction and size.
 *
 * author Shoham&Efrat
 */
public class Vector extends Point {
    /**
     * Constructor to initialize a new Vector with 3 doubles.
     * Throws an IllegalArgumentException if vector zero is passed.
     *
     * @param v1 double type that becomes the x coordinate
     * @param v2 double type that becomes the y coordinate
     * @param v3 double type that becomes the z coordinate
     */
    public Vector(double v1, double v2, double v3) {
        super(v1, v2, v3);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("can't create vector 0");
        }
    }

    /**
     * Constructor to initialize a new Vector using Double3 object that represents 3 coordinates.
     * Throws an IllegalArgumentException if vector zero is passed.
     *
     * @param c Double3 type object that represents 3 coordinates
     */
    public Vector(Double3 c) {
        super(c);
        if (c.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("can't create vector 0");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other) && super.equals(other);
    }

    @Override
    public String toString() {
        return "Vector []";
    }

    /**
     * Calculates the length squared of the current vector using dotProduct function.
     *
     * @return The length squared of the current vector
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculates the length of the current vector using the square root of lengthSquared function.
     *
     * @return The length of the current vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Adds a vector to the current vector by adding their Point object with the corresponding coordinates (using Double3 add function).
     *
     * @param v the vector that's being added to the vector
     * @return A new Vector object of the result
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Scales the vector by a scalar.
     *
     * @param s the scalar to multiply the vector with
     * @return A new vector of the scale result
     */
    public Vector scale(double s) {
        return new Vector(xyz.scale(s));
    }

    /**
     * Performs a dot product between two vectors (current vector + given vector).
     *
     * @param v the vector to perform dot product with the current vector
     * @return A double of the dot product result
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * Performs a cross product between two vectors (current vector + given vector).
     *
     * @param v the vector to perform cross product with the current vector
     * @return A new Vector of the cross product result
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                (xyz.d2 * v.xyz.d3) - (xyz.d3 * v.xyz.d2),
                (xyz.d3 * v.xyz.d1) - (xyz.d1 * v.xyz.d3),
                (xyz.d1 * v.xyz.d2) - (xyz.d2 * v.xyz.d1)
        );
    }

    /**
     * Normalizes the current vector (makes the vector length to 1).
     *
     * @return A new normalized Vector object
     */
    public Vector normalize() {
        double length = length();
        return new Vector(xyz.d1 / length, xyz.d2 / length, xyz.d3 / length);
    }
}
