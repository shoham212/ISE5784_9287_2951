package primitives;
import static primitives.Util.isZero;
import java.util.List;
/**
 * @author Shoham&Efrat
 * A class of Ray represents a set of points on a straight line that are on one side of a given point called the head of the ray.
 */

public class Ray {
    /**
     * The start of the line
     */
    private final Point head;
    /**
     * The direction vector that's represent the direction of the line
     */
    private final Vector direction;

    /**
     * Getter method for head
     * @return the head point of the ray
     */
    public Point getHead() {
        return head;
    }

    /**
     * Getter method for direction
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Ray constructor to initialize a new Ray object with head and direction
     * (direction should be normalized)
     * @param p a new head point to assign head with
     * @param v a new direction vector to assign direction with
     */
    public Ray(Point p, Vector v){
        head= p;
        direction= v.normalize();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    @Override
    public String toString() {
        return "Ray [head=" + head + ", direction=" + direction + "]";
    }

    /**
     * Ray function for calculating a point on the beam line at a given distance from the beam head
     * @param t distance from the beam head
     * @return a point on the beam line
     */
    public Point getPoint(double t) {
        if (isZero(t))
        {return  head;}
        return head.add(direction.scale((t)));
    }

    /**
     * Finds the closest point to the starting point of the ray
     * from a collection of points.
     * @param points a collection of points
     * @return the point closest to the starting point of the ray
     */
    public Point findClosestPoint(List<Point> points) {

        if (points==null || points.isEmpty()){
            return null;
        }

        Point closesP = points.get(0);
        double closesD = head.distance(closesP);

        for(Point point: points){
            double distance = head.distance(point);
            if(distance<closesD){
                closesD=distance;
                closesP=point;
            }
        }
        return closesP;
    }
}


