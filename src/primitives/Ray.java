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

}