package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing Point
 * @author Shoham&Efrat
 */
class PointTest {
    private final double DELTA = 1e-10;

    /**
     * Test case for the {@link Point#add(Vector)} method of Point class.
     */
    @Test
    void add() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test add Standard

        Point p = new Point (1,2,3);
        Vector v = new Vector (1,2,3);
        Point result = new Point (2,4,6);
        assertEquals (p.add(v) ,result,
                "ERROR: (point + vector) = other point does not work correctly");

        //TC02: test add with negative components

        Point p2 = new Point (-1,-2,-3);
        Vector v2 = new Vector (2,4,6);
        assertNotEquals (p.add(v),p2.add(v2),
                "ERROR: (Point + negative Vector) does not work correctly");

        //TC03: test add with negative components

        Point p3 = new Point (-3,-3,-3);
        Vector v3 = new Vector (-4,-5,-6);
        assertEquals (p.add(v3) ,p3,
                "ERROR: (negative Point + negative Point) does not work correctly");

        //TC04: test add connecting a point with its negative

        Point p4=new Point (0,0,0);
        Vector v4 = new Vector (-1,-2,-3);
        assertEquals (p.add(v4) ,p4,
                "ERROR: (point + vector) = center of coordinates does not work correctly");

        //============ Boundary Partitions Tests==============
    }
    /**
     * Test case for the {@link Point#subtract(Point)} method of Point class.
     */
    @Test
    void subtract() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test subtract Standard
        Point p1 = new Point (2,4,6);
        Point p2 = new Point (1,2,3);
        Vector result = new Vector (1,2,3);
        assertEquals (p1.subtract(p2) ,result,
                "ERROR: (point2 - point1) does not work correctly");


        //============ Boundary Partitions Tests ==============
        //TC10: test subtract for point with itself

        assertThrows(IllegalArgumentException.class, ()->p1.subtract(p1),
                "ERROR: zero vector does not throw an exception");
    }


    /**
     * Test case for the {@link Point#distanceSquared(Point)} method of Point class.
     */
    @Test
    void distanceSquared() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test distanceSquared for point with itself

        Point p = new Point (2,4,5);
        assertEquals (p.distanceSquared(p) ,0,DELTA,
                "ERROR: point squared distanceSquared to itself is not zero");

        //TC02: test distanceSquared Standard

        Point p1 = new Point(1, 2, 3);
        assertEquals (p1.distanceSquared(p) ,9,DELTA,
                "ERROR: squared distanceSquared between points is wrong");

        //TC03: test distanceSquared for negative point with itself

        Point p3 = new Point (-3,-3,-3);
        assertEquals (p3.distanceSquared(p3) ,0,DELTA,
                "ERROR: negative point squared distanceSquared to itself is not zero");

        //TC04: test negative distanceSquared

        Point p4 = new Point (-1,-1,-1);
        assertEquals (p3.distanceSquared(p4) ,12,DELTA,
                "ERROR: negative squared distanceSquared between points is wrong");

        //============ Boundary Partitions Tests==============
    }
    /**
     * Test case for the {@link Point#distance(Point)} method of Point class.
     */
    @Test
    void distance() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test distanceSquared for point with itself

        Point p = new Point (2,4,5);
        assertEquals (p.distance(p) ,0,DELTA,
                "ERROR: point squared distance to itself is not zero");

        //TC02: test distanceSquared Standard

        Point p1 = new Point(1, 2, 3);
        assertEquals (p1.distance(p) ,Math.sqrt(9),DELTA,
                "ERROR: squared distance between points is wrong");

        //TC03: test distanceSquared for negative point with itself

        Point p3 = new Point (-3,-3,-3);
        assertEquals (p3.distance(p3) ,0,DELTA,
                "ERROR: negative point squared distance to itself is not zero");

        //TC04: test negative distanceSquared

        Point p4 = new Point (-1,-1,-1);
        assertEquals (p3.distance(p4) ,Math.sqrt(12),DELTA,
                "ERROR: negative squared distance between points is wrong");

        //============ Boundary Partitions Tests==============
    }
}