package renderer;
import static java.awt.Color.*;
import geometries.*;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class MINIP1 {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));


    @Test
    public void testBlurryGlass() {

        Vector vTo = new Vector(0, 1, 0);
        Camera.Builder camera =Camera.getBuilder().setLocation(new Point(0,-230,0).add(vTo.scale(-13)))
                .setDirection(vTo,new Vector(0,0,1))
                .setVpSize(200, 200).setVpDistance(1000);
        ;

        scene.setAmbientLight(new AmbientLight(new Color(gray).reduce(2), new Double3(0.15)));


        for (int i = -4; i < 6; i += 2) {
            scene.geometries.add(
                    new Sphere(2, new Point(5.2 * i, -1.5, 0)).setEmission(new Color(red).reduce(4).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(1, new Point(5.5 * i, -3, -6)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(1.5, new Point(4.3 * i, -8, -8)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(1, new Point(5 * i, -1.5, 2)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(1, new Point(5.6 * i, -3, -6)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(0.5, new Point(4.6 * i, -8, -4)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(0.5, new Point(4 * i, -1.5, 1)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),
                    new Sphere(1, new Point(4.5 * i, -3, 1)).setEmission(new Color(red).reduce(2))
                            .setMaterial(new Material().setKd(0.2).setKs(1).setShininess(80).setKt(0d)),

                     new Polygon(new Point(5 * i - 4, -5, -11), new Point(5 * i - 4, -5, 5), new Point(5 * i + 4, -5, 5),
                            new Point(5 * i + 4, -5, -11)).setEmission(new Color(250, 235, 215).reduce(2))
                            .setMaterial(new Material().setKd(0.001).setKs(0.002).setShininess(1).setKt(0.95)
                                     .setBlurGlass(i == 4 ? 1 : 1000, 0.9 * (i + 15), 10)) //

            );
        }

        scene.geometries.add(new Plane(new Point(1, 10, 1), new Point(2, 10, 1), new Point(5, 10, 0))
                .setEmission(new Color(white).reduce(3))
                .setMaterial(new Material().setKd(0.2).setKs(0).setShininess(0).setKt(0d))

        );

        // scene.lights.add(new PointLight(new Color(100, 100, 150), new Point(0, 6,
        // 0)));
        scene.lights.add(new DirectionalLight(new Color(white).reduce(1), new Vector(-0.4, 1, 0)));
        scene.lights.add(new SpotLight(new Color(white).reduce(2), new Point(20.43303, -7.37104, 13.77329),
                new Vector(-20.43, 7.37, -13.77)).setKl(0.6));

        ImageWriter imageWriter = new ImageWriter("blurryGlass", 500, 500);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new SimpleRayTracer(scene))
                .setMultithreading(3)
                .setDebugPrint(1)
                .build()
                .renderImage()
                .writeToImage();
    }
}
