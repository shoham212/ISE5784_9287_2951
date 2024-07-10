package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * SimpleRayTracer class represents a simple ray tracer
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * A small delta value used for numerical stability and precision in calculations.
     */
    private static final double DELTA = 0.1;

    /**
     * Determines if a given point on a geometry is unshaded by checking for intersections between
     * the point and a light source.
     *
     * @param gp the geometric point on the geometry being tested
     * @param l the direction vector from the point to the light source
     * @param n the normal vector at the point on the geometry
     * @param nl the dot product of the normal vector and the light direction vector
     * @param ls the light source being considered
     * @return {@code true} if the point is unshaded (i.e., no objects block the light); {@code false} otherwise
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, double nl, LightSource ls) {
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(gp.point));

        return intersections == null || intersections.isEmpty();
    }

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if (points == null) {
            return scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(points);
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color of a point in the scene.
     *
     * @param geoPoint The point on the geometry in the scene.
     * @param ray The ray from the camera to the intersection.
     * @return The color of the point.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {

        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * Calculates the effect of different light sources on a point in the scene
     * according to the Phong model.
     *
     * @param intersection The point on the geometry in the scene.
     * @param ray The ray from the camera to the intersection.
     * @return The color of the point affected by local light sources.
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;

        int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().kd;
        Double3 ks = intersection.geometry.getMaterial().ks;

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0 && unshaded(intersection, l, n, nl, lightSource)) {
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffuse(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse component of light reflection.
     *
     * @param kd The diffuse reflection coefficient.
     * @param nl The dot product between the normal vector and the light vector.
     * @param lightIntensity The intensity of the light source.
     * @return The color contribution from the diffuse reflection.
     */
    private Color calcDiffuse(Double3 kd, double nl, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(nl)));
    }

    /**
     * Calculates the specular component of light reflection.
     *
     * @param ks The specular reflection coefficient.
     * @param l The light vector.
     * @param n The normal vector.
     * @param nl The dot product between the normal vector and the light vector.
     * @param v The view vector.
     * @param nShininess The shininess coefficient.
     * @param lightIntensity The intensity of the light source.
     * @return The color contribution from the specular reflection.
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess,
                               Color lightIntensity) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK; // View from direction opposite to r vector
        }
        return lightIntensity.scale(ks.scale(Math.pow(minusVR, nShininess)));
    }
}
