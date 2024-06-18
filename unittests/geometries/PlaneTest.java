package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Point p = new Point(1, 1, 1);
        Vector n = new Vector(0, 0, 1);
        Plane plane = new Plane(p, n);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line cuts the plane (1 point)
        assertEquals(plane.findIntersections(new Ray(new Point(1,1,3),new Vector(0,0,-1))), List.of(new Point(1,1,1)) ,"ERROR: findIntersections Ray's line is outside the Plane");
        // TC02: Ray's line is outside the Plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0,0,2),new Vector(1,1,0))) ,"ERROR: A ray is not perpendicular, not parallel and does not cut the plane");

        // =============== Boundary Values Tests ==================

        // **** Group: The ray is parallel to the plane
        // TC10: Ray's line on the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1,1,1),new Vector(1,1,0))), "ERROR: Ray's line on of plane");
        // TC11: Ray's line out of plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0,0,2),new Vector(1,1,0))), "ERROR: Ray's line out of plane");

        // **** Group: The ray is perpendicular to the plane
        // TC12: Ray's line starts before the plane (1 point)
        assertEquals(plane.findIntersections (new Ray(new Point(1, 1, 0), new Vector(0, 0, 1))),List.of(new Point(1,1,1)), "ERROR: Ray's line starts before the plane");
        // TC13: Ray's line starts inside the plane (0 points)
        assertNull(plane.findIntersections (new Ray(new Point(1, 1, 1), new Vector(0, 0, 1))), "ERROR: Ray's line starts inside the plane");
        // TC14: Ray's line starts after the plane (1 point)
        assertEquals(plane.findIntersections(new Ray(new Point(1, 1, 2), new Vector(0, 0, -1))),List.of(new Point(1,1,1)), "ERROR: Ray's line starts after the plane");

        // **** Group: The ray is neither perpendicular nor parallel
        // TC15: The head of the Ray's line starts exactly at the "reference point" (1 point)
        assertNull(plane.findIntersections (new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))), "ERROR: The head of the Ray's line starts exactly at the reference point");
        // TC16: Ray's line starts on the plane (1 point)
        assertNull(plane.findIntersections (new Ray(new Point(2, 3, 1), new Vector(-1, -1, 1))), "ERROR: Ray's line starts on the plane");

    }
}