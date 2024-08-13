package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Scene {

    /** The name of the scene. */
    public String name;

    /** The background color of the scene. */
    public Color background = Color.BLACK;

    /** The ambient light in the scene. */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The geometries present in the scene. */
    public Geometries geometries = new Geometries();

    /** The light sources present in the scene. */
    public List<LightSource> lights = new LinkedList<>();

    private int kSize;

    public Scene setKMeans(int kSize) {
        this.kSize = kSize;
        this.geometries.setBvh();
        return this;
    }
    /**
     * Constructs a new Scene with the specified name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the background color to set
     * @return this Scene object for method chaining
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light to set
     * @return this Scene object for method chaining
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries The geometries to be set.
     * @return The Scene object with the updated geometries.
     */

    public Scene setGeometries(Geometries geometries) {
        if (this.geometries.getBvh() == false) {
            this.geometries = geometries;
            return this;
        }

        List<Intersectable> geometriesList = geometries.geometries;

        // calculate centers of objects
        List<Point> centers = new ArrayList<>();
        for (Intersectable i : geometriesList) {
            Point avg = new Point((i.getMinPoint().getX() + i.getMaxPoint().getX()) / 2,
                    (i.getMinPoint().getY() + i.getMaxPoint().getY()) / 2,
                    (i.getMinPoint().getZ() + i.getMaxPoint().getZ()) / 2);
            centers.add(avg);
        }

        KMeans kMeans = new KMeans();

        // Perform k-means clustering
        List<List<Integer>> clusters = kMeans.cluster(centers, kSize);

        // Build hierarchy based on clusters
        Geometries hierarchies = new Geometries();
        for (List<Integer> cluster : clusters) {
            Geometries clusterObjects = new Geometries();
            for (int index : cluster) {
                clusterObjects.add(geometriesList.get(index));
            }
            if (clusterObjects.geometries.size() == 1) {
                hierarchies.add(new Geometries(clusterObjects.geometries.get(0)));
            } else {
                hierarchies.add(new Geometries(clusterObjects.geometries.toArray(new Intersectable[0])));
            }
        }
        this.geometries = hierarchies;
        return this;
    }

    /**
     * Sets the light sources present in the scene.
     *
     * @param lights the light sources to set
     * @return this Scene object for method chaining
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    private static class KMeans {
        private List<List<Integer>> cluster(List<Point> points, int k) {
            List<List<Integer>> clusters = new ArrayList<>();

            // Step 1: Initialize centroids randomly
            List<Point> centroids = initializeCentroids(points, k);

            // Iterate until convergence
            boolean converged = false;
            while (!converged) {
                // Step 2: Assign each point to the nearest centroid
                List<List<Integer>> newClusters = assignPointsToCentroids(points, centroids);

                // Step 3: Update centroids based on the mean of the points in each cluster
                List<Point> newCentroids = updateCentroids(points, newClusters);

                // Step 4: Check for convergence
                converged = centroids.equals(newCentroids);

                centroids = newCentroids;
                clusters = newClusters;
            }

            return clusters;
        }

        private List<Point> initializeCentroids(List<Point> points, int k) {
            // Randomly select k points as initial centroids
            List<Point> centroids = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < k; i++) {
                int index = rand.nextInt(points.size());
                centroids.add(points.get(index));
            }
            return centroids;
        }

        private List<List<Integer>> assignPointsToCentroids(List<Point> points, List<Point> centroids) {
            // Assign each point to the nearest centroid
            List<List<Integer>> clusters = new ArrayList<>();
            for (int i = 0; i < centroids.size(); i++) {
                clusters.add(new ArrayList<>());
            }
            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                double minDistance = Double.POSITIVE_INFINITY;
                int clusterIndex = -1;
                for (int j = 0; j < centroids.size(); j++) {
                    double distance = point.distance(centroids.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        clusterIndex = j;
                    }
                }
                clusters.get(clusterIndex).add(i);
            }
            return clusters;
        }

        private List<Point> updateCentroids(List<Point> points, List<List<Integer>> clusters) {
            // Update centroids based on the mean of the points in each cluster
            List<Point> centroids = new ArrayList<>();
            for (List<Integer> cluster : clusters) {
                if (cluster.isEmpty()) {
                    // If a cluster is empty, keep the centroid unchanged
                    centroids.add(new Point(0, 0, 0)); // Add dummy point, not used in distance calculation
                    continue;
                }
                double sumX = 0, sumY = 0, sumZ = 0;
                for (int index : cluster) {
                    Point point = points.get(index);
                    sumX += point.getX();
                    sumY += point.getY();
                    sumZ += point.getZ();
                }
                double meanX = sumX / cluster.size();
                double meanY = sumY / cluster.size();
                double meanZ = sumZ / cluster.size();
                centroids.add(new Point(meanX, meanY, meanZ));
            }
            return centroids;
        }
    }
}
