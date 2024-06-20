package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry class represents a geometric shape in a three-dimensional space.
 * Any class implementing this class must provide methods to calculate various
 * properties of the shape.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Returns the emission color of the geometry.
     *
     * @return the emission color of the geometry.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Returns the material properties of the geometry.
     *
     * @return the material properties of the geometry.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the emission color of the geometry.
     * @return the geometry itself (for chaining calls).
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material properties of the geometry.
     *
     * @param material the material properties to set.
     * @return the geometry itself (for chaining calls).
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * An unrealized function to calculate a normal for any geometric surface.
     *
     * @param p the point on the geometric surface.
     * @return the normal vector to the surface at the given point.
     */
    public abstract Vector getNormal(Point p);
}
