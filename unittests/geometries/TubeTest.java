package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import  primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Tube
 * @author Shoham&Efrat
 */
class TubeTest {

    @Test
    void getNormal() {
// ============ Equivalence Partitions Tests ==============

        //TC01: same value get normal test

        Vector d = new Vector(0, 1, 0);
        Point h = new Point(0, 0, 0);
        Ray a = new Ray(h, d);
        Tube t = new Tube(a, 1);
        Point p = new Point(1, 5, 0);

        assertEquals(new Vector(1, 0, 0), t.getNormal(p),
                "ERROR: get normal Tube classic test");

        //TC02: test getNormal sphere returns normalized vector
        assertEquals(1.0, (t.getNormal(p)).length(),
                "ERROR: test getNormal Tube returns normalized vector");

        // =============== Boundary Values Tests ==================

        //TC10: point-pointO is orthogonal to axis
        Point p1= new Point(1, 0, 0);
        assertEquals(new Vector(1, 0, 0), t.getNormal(p1),
                "ERROR:  getNormal orthogonal test");
    }
}