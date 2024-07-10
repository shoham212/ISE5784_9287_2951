package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Sphere
 * @author Shoham&Efrat
 */
class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests =============

        Sphere sph = new Sphere(1.0, new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point(0, 0, 2)), "ERROR: Wrong normal to sphere");

        //TC01: Test normal is proper

        Sphere sp = new Sphere(1, new Point(0, 0, 0));
        assertEquals(sp.getNormal(new Point(0, 0, 1)), new Vector(0, 0, 1), "ERROR: getNormal() gives wrong normal.");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        var sphere = new Sphere(2, new Point(2, 2, 2));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Normal intersection with 2 points
        assertEquals(List.of(new Point(2, 0, 2), new Point(0, 2, 2)),
                sphere.findIntersections(new Ray(new Point(3, -1, 2), new Vector(-1, 1, 0))),
                "findIntersections does not work correctly for normal 2 points intersection");

        // TC02: Normal intersection with 2 points
        assertEquals(List.of(new Point(2, 0, 2)),
                sphere.findIntersections(new Ray(new Point(3, 2, 2), new Vector(-1, -2, 0))),
                "findIntersections does not work correctly when the point is inside the sphere");

        // TC03: The ray's tail hits the sphere, no intersection points
        assertNull(sphere.findIntersections(new Ray(new Point(5, 5, 0), new Vector(1, 1, 1))),
                "findIntersections does not work correctly when back of the ray intersects with the sphere");

        // TC04: The ray doesn't hit the sphere, no intersection points
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 10), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray doesn't hit the sphere");

        // =============== Boundary Values Tests ==================

        // involving the center
        // TC10: The ray starts outside the tube and goes through the center, 2
        // intersection
        // points
        assertEquals(List.of(new Point(4, 2, 2), new Point(0, 2, 2)),
                sphere.findIntersections(new Ray(new Point(10, 2, 2), new Vector(-1, 0, 0))),
                "findIntersections does not work correctly for normal 2 points intersection when the ray goes through the center");

        // TC11: The ray starts in the center, 1 intersection point
        assertEquals(List.of(new Point(0, 2, 2)),
                sphere.findIntersections(new Ray(new Point(2, 2, 2), new Vector(-1, 0, 0))),
                "findIntersections does not work correctly when the ray starts in the center");

        // TC12: The ray starts in the sphere but the ray's back goes through the
        // center, no intersection points
        assertEquals(List.of(new Point(4, 2, 2)),
                sphere.findIntersections(new Ray(new Point(3, 2, 2), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray's back hits the center but start at the middle");

        // TC13: The ray starts and the edge and it's tail goes through the center, no
        // intersection points
        assertNull(sphere.findIntersections(new Ray(new Point(4, 2, 2), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray starts at the edge but the ray's back goes through the center");

        // TC14: The ray starts in the edge and goes through the center, 1 intersection
        // point
        assertEquals(List.of(new Point(0, 2, 2)),
                sphere.findIntersections(new Ray(new Point(4, 2, 2), new Vector(-1, 0, 0))),
                "findIntersections does not work correctly when the ray  starts at the edge but goes through the center");
        // TC15: The ray starts outside and goes through the center, 2 intersection
        // points
        assertNull(sphere.findIntersections(new Ray(new Point(5, 2, 2), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray starts outside but the ray's back goes through the center");

        // ************
        // on the edge without involving the center
        // TC16: The ray is on the edge but goes through the sphere, 1 intersection
        // point
        assertEquals(List.of(new Point(2, 2, 0)),
                sphere.findIntersections(new Ray(new Point(2, 0, 2), new Vector(0, 1, -1))),
                "findIntersections does not work correctly when the ray start at the edge but goes through the sphere");
        // TC14: The ray is on the edge but doesn't go through the sphere, no
        // intersection points
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 2), new Vector(1, 0, 1))),
                "findIntersections does not work correctly when the ray start at the edge but the back goes through the sphere");

        // ************
        // point of contact
        // TC18: The ray start on the edge but doesn't go through the sphere, 1
        // intersection
        // point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 2, 4), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray start at the edge");

        // TC19: The ray starts before the point of contact, 1 intersection point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 1))),
                "findIntersections does not work correctly when the ray starts before the contact with the edge");

        // TC20: The ray tail's has point of contact with the sphere, no intersection
        // points
        assertNull(sphere.findIntersections(new Ray(new Point(5, 2, 2), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray's contacts the edge");

        // ************
        // the ray's start is orthogonal to the center
        // TC21: The ray is orthogonal to the sphere center minus ray origin
        assertNull(sphere.findIntersections(new Ray(new Point(5, 2, 2), new Vector(1, 0, 0))),
                "findIntersections does not work correctly when the ray is ortogonal to the sphere center");
    }
}