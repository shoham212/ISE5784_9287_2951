package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}