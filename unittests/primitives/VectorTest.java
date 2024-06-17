package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Vector
 * @author Shoham&Efrat
 */
class VectorTest {
    private final double DELTA = 1e-10;
    /**
     * Test case for the {@link Vector#Vector(double v1,double v2,double v3)} method of Vector class.
     */
    @Test
    void Vector() {
        //============ Boundary Partitions Tests ==============

        assertThrows(IllegalArgumentException.class, () -> new Vector(0,0,0),
                "ERROR: zero vector does not throw an exception");
    }

    /**
     * Test case for the {@link Vector#Vector(Double3 c)} method of Vector class.
     */
    @Test
    void Vector2() {
        //============ Boundary Partitions Tests ==============
        assertThrows(IllegalArgumentException.class, () -> new Vector (Double3.ZERO),
                "ERROR: zero vector does not throw an exception");

    }


    /**
     * Test case for the {@link Vector#lengthSquared()} method of Vector class.
     */
    @Test
    void lengthSquared() {
//============ Equivalence Partitions Tests ==============

        //TC01: test Length Squared Standard

        Vector v1 = new Vector(1, 2, 2);
        assertEquals(9, v1.lengthSquared(), DELTA,
                "ERROR: lengthSquared() wrong value");

        //TC02: test length squared with negative components
        Vector v2 = new Vector(-1, -2, -2);
        assertEquals(9, v1.lengthSquared(), DELTA,
                "ERROR: lengthSquared() wrong value with negative components");

        //============ Boundary Partitions Tests==============
    }
    /**
     * Test case for the {@link Vector#length()} method of Vector class.
     */
    @Test
    void length() {
//============ Equivalence Partitions Tests ==============

        //TC01: test Length Squared Standard

        Vector v1 = new Vector(1, 2, 2);
        assertEquals(3, v1.length(), DELTA,
                "ERROR: length() wrong value");

        //TC02: test length squared with negative components
        Vector v2 = new Vector(-1, -2, -2);
        assertEquals(3, v1.length(), DELTA,
                "ERROR: length() wrong value with negative components");

        //============ Boundary Partitions Tests==============
    }
    /**
     * Test case for the {@link Vector#add(Vector)} method of Vector class.
     */
    @Test
    void add() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test add standard

        Vector v = new Vector (1,2,3);
        Vector v1 = new Vector (2,4,6);
        assertEquals (v.add(v) ,v1,
                "ERROR: Vector + Vector does not work correctly");

        //TC02: test add with negative components

        Vector v2 = new Vector(-1,-2,-3);
        assertNotEquals (v2.add(v1),v1,
                "ERROR: Vector + negative Vector does not work correctly");

        //TC03: test add with negative components

        Vector v4 = new Vector (-3,-3,-3);
        Vector v5 = new Vector (-4,-5,-6);
        assertEquals (v.add(v5) ,v4,
                "ERROR: negative Vector + negative Vector does not work correctly");

        //============ Boundary Partitions Tests ==============

        //TC10: test add product with orthogonal Vectors

        assertThrows(IllegalArgumentException.class, ()->v.add(v2),
                "ERROR: Vector + -itself does not throw an exception");
    }
    /**
     * Test case for the {@link Vector#scale(double)} method of Vector class.
     */
    @Test
    void scale() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test scale standard

        Vector v = new Vector (1,2,3);
        assertEquals (v.scale(1) ,v,
                "ERROR: scale() wrong value");
        ;
        //TC02: test scale with negative components

        Vector v1 = new Vector (-1,-2,-3);
        assertEquals (v.scale(-1) ,v1,
                "ERROR: scale() negative wrong value");

        //============ Boundary Partitions Tests ==============

        //TC10: test scale product with orthogonal Vectors

        assertThrows(IllegalArgumentException.class, ()->v.scale(0),
                "ERROR: zero vector does not throw an exception");
    }
    /**
     * Test case for the {@link Vector#dotProduct(Vector)} method of Vector class.
     */
    @Test
    void dotProduct() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test dotProduct standard

        Vector v = new Vector (1,2,3);
        Vector v1 = new Vector (1,1,1);
        assertEquals (v.dotProduct(v1) , 6,
                "ERROR: dotProduct() wrong value");

        //TC02: test dotProduct with negative components

        Vector v2 = new Vector(-1,-2,-3);
        assertEquals (v1.dotProduct(v2),-6,
                "ERROR: dotProduct() negative wrong value");

        //TC03: test dotProduct with negative components

        Vector v4 = new Vector (-1,-1,-1);
        Vector v5 = new Vector (-4,-5,-6);
        assertEquals (v4.dotProduct(v5) ,15,
                "ERROR: dotProduct() negative wrong value");

        //============ Boundary Partitions Tests ==============

        //TC10: test dotProduct between perpendicular vectors

        Vector v6 = new Vector(1, 2, 3);
        Vector v7 = new Vector(0, 3, -2);
        assertTrue (isZero(v6.dotProduct(v7)),"ERROR: dotProduct() for orthogonal vectors is not zero");
    }
    /**
     * Test case for the {@link Vector#crossProduct(Vector)} method of Vector class.
     */
    @Test
    void crossProduct() {
//============ Equivalence Partitions Tests ==============

        //TC01: test cross product standard

        Vector vectorA = new Vector(1, 2, 3);
        Vector vectorB = new Vector(4, 5, 6);
        assertEquals(new Vector(-3, 6, -3), vectorA.crossProduct(vectorB),
                "ERROR: crossProduct() wrong result length");

        //TC02: test cross product with negative components

        Vector vectorNegative = new Vector(1, -2, 3);
        Vector vectorNegativeA = new Vector(-4, 5, -6);
        assertEquals(new Vector(-3, -6, -3),
                vectorNegative.crossProduct(vectorNegativeA),
                "ERROR: crossProduct() wrong result for orthogonal Vectors");

        //============ Boundary Partitions Tests ==============

        //TC10: test cross product with orthogonal Vectors

        Vector vectorX = new Vector(1, 0, 0);
        Vector vectorY = new Vector(0, 1, 0);
        assertEquals(new Vector(0, 0, 1), vectorX.crossProduct(vectorY),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");

        //TC11: test cross product with self

        assertThrows(IllegalArgumentException.class, () -> vectorX.crossProduct(vectorX), "ERROR: crossProduct() for parallel vectors does not throw an exception");
    }
    /**
     * Test case for the {@link Vector#normalize()} method of Vector class.
     */
    @Test
    void normalize() {
//============ Equivalence Partitions Tests ==============

        //TC01: test normalize standard

        Vector v = new Vector(4,3, 0);
        assertEquals(new Vector(4/v.length(), 3/v.length() , 0), v.normalize(),
                "ERROR: normalize() wrong result");

        //TC02: test normalize with negative components

        Vector v1 = new Vector(-4, 3, 0);
        assertEquals(new Vector(-4 / v1.length(), 3 / v1.length(), 0 / v1.length()), v1.normalize(),
                "ERROR: normalize() negative wrong result");


        //============ Boundary Partitions Tests ==============

        //TC10: test normalize unit Vector

        Vector v2 = new Vector(1, 0, 0);
        assertEquals(v2, v2.normalize(),
                "ERROR: normalize() negative wrong result UnitVector");
    }

}