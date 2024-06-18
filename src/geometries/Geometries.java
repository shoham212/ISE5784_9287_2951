package geometries;

import primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

/**
 * composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries implements Intersectable {
    public final List<Intersectable> geometries = new LinkedList<Intersectable>();

    /**
     * empty geometries constructor
     */
    public Geometries(){};

    /**
     * Constructs a new Geometries object with the specified intersectable geometries.
     * @param geometries The intersectable geometries to add to this collection.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds one or more geometries to this collection.
     * @param geometries The geometries to add to this collection.
     */
    public void add(Intersectable... geometries){
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        LinkedList<Point> points=null;
        for(var geometry: geometries){
            var geometryList = geometry.findIntersections(ray);
            if(geometryList !=null){
                if(points==null){
                    points=new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
