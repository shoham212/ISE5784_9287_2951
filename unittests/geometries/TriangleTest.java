package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}