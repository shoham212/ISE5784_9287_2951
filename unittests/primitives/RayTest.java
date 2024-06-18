package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Ray
 * @author Shoham&Efrat
 */
class RayTest {


    /**
     * Test case for the {@link Ray#getPoint(double)} method of Ray class.
     */
    @Test
    void getPoint() {
        //============ Equivalence //Partitions Tests ==============

        //TC01: test getPoint Standard

        Point p1=new Point(1,1,1);
        Vector v1=new Vector(1,0,0);
        Ray r= new Ray(p1,v1);
        assertEquals (r.getPoint(2) ,new Point (3,1,1),
                "ERROR: getPoint does not work correctly");

        //TC02: test negative getPoint
        assertEquals (r.getPoint(-2) ,new Point (-1,1,1),
                "ERROR: getPoint for negative distance does not work correctly");

        //============ Boundary Partitions Tests==============

        //TC10: test getPoint for distance=0

        assertEquals (r.getPoint(0) ,p1,
                "ERROR: getPoint for distance=0 does not work correctly");

    }

    /**
     * Test case for the {@link Ray#findClosestPoint(java.util.List)} method of Ray class.
     */
    @Test
    void findClosestPoint(){
        //============ Equivalence //Partitions Tests ==============

        //TC01: test point in the middle of the list is the one closest to the beginning of the foundation
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        assertEquals(ray.findClosestPoint( List.of ( new Point(10, 10, 10),new Point(2, 2, 2), new Point(20, 20, 20))),
                new Point(2, 2, 2),"ERROR: test point in the middle of the list is the one closest to the beginning of the foundation");

        //============ Boundary Partitions Tests==============

        //TC10: test empty list
        assertNull(ray.findClosestPoint(List.of()), "Error: test empty list");

        //TC11: test The first point is closest to the beginning of the ray
        assertEquals(ray.findClosestPoint(List.of(new Point(1, 1, 1), new Point(5, 5, 5), new Point(10, 10, 10))),
                new Point(1, 1, 1),"ERROR: test The first point is closest to the beginning of the ray");

        //TC12: test The last point is closest to the beginning of the ray
        assertEquals(ray.findClosestPoint(List.of(new Point(10, 10, 10), new Point(5, 5, 5), new Point(1, 1, 1))),
                new Point(1, 1, 1),"ERROR: test The last point is closest to the beginning of the ray");
    }
}