package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Plane
 * @author Shoham&Efrat
 */
class PlaneTest {

    /**
     * Test case for the {@link Plane#Plane(Point p1,Point p2,Point p3)} method of Plane class.
     */
    @Test
    void Plane() {
        //============ Boundary Partitions Tests==============

        //TC10: test all points on the same line

        Point p1=new Point(1,1,1);
        Point p2=new Point(2,2,2);
        Point p3=new Point(3,3,3);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3), "ERROR: all points on the same line");

        //TC11: test two points are equals

        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p3), "ERROR: two points are equals");

    }

    /**
     * Test case for the {@link Plane#getNormal(Point p)} method of Plane class.
     */
    @Test
    void GetNormal() {
        //============ Equivalence Partitions Tests ==============

        //TC01: test GetNormal Standard
        Plane pl=new Plane(new Point (0, 0, 1),new Point (1, 0, 0),new Point (0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector norm = pl.getNormal(new Point(0, 0, 1));
        boolean normal1 = new Vector(sqrt3, sqrt3, sqrt3).equals(norm);
        boolean normal2 = new Vector(-sqrt3, -sqrt3, -sqrt3).equals(norm);
        assertTrue(normal1 || normal2, "ERROR: test GetNormal does not work correctly");
    }
}