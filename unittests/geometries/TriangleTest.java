package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Triangle
 * @author Shoham&Efrat
 */
class TriangleTest {
    @Test
    void getNormal() {
// ============ Equivalence Partitions Tests ==============

        //TC01: same value get normal test
        Triangle t=new Triangle(new Point (0, 0, 1),new Point (1, 0, 0),new Point (0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector norm = t.getNormal(new Point(0, 0, 1));
        boolean normal1 = new Vector(sqrt3, sqrt3, sqrt3).equals(norm);
        boolean normal2 = new Vector(-sqrt3, -sqrt3, -sqrt3).equals(norm);
        assertTrue(normal1 || normal2, "ERROR: test GetNormal does not work correctly");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0, 1, 0), new Point(0, 5, 0), new Point(0, 3, 5));

        // ============ Equivalence Partitions Tests ==============
        // TC01:The intersection with the contained plane is inside the triangle
        assertEquals(List.of(new Point(0, 3, 1)), triangle.findIntersections(new Ray(new Point(1, 3, 0), new Vector(-1, 0, 1))), "The point supposed to be in the triangle");

        // TC02:The intersection with the contained plane is outside the triangle - facing one the ribs
        assertNull(triangle.findIntersections(new Ray(new Point(0, 6, 0), new Vector(0, 1, 0))), "ERROR: intersection with the contained plane facing one the ribs");
        // TC03:The intersection with the contained plane is outside the triangle - facing one the vertices
        assertNull(triangle.findIntersections(new Ray(new Point(1, 2, 1), new Vector(-1, 0, -1))), "ERROR: intersection with the contained plane facing one the vertices");

        // =============== Boundary Values Tests ==================
        // TC10:The point of intersection with the contained plane you invented on one of the sides
        assertNull(triangle.findIntersections(new Ray(new Point(1, 2, 1), new Vector(-1, 1, -1))), "ERROR: intersection with the contained plane you invented on one of the sides");
        // TC11:The point of intersection with the contained plane is inside one of the vertices
        assertNull(triangle.findIntersections(new Ray(new Point(1, 2, 1), new Vector(-1, -1, -1))), "ERROR: intersection with the contained plane is inside one of the vertices");
        // TC12:The point of intersection with the contained plane is on a continuation of one of the sides
        assertNull(triangle.findIntersections(new Ray(new Point(1, 2, 1), new Vector(-1, 4, 0))), "ERROR: intersection with the contained plane is on a continuation of one of the sides");
    }
}