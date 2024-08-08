package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

public class baulingTests {
    private final Scene scene = new Scene("Test scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector (0,0,-1), new Vector (0,1,0))
            .setRayTracer(new SimpleRayTracer(scene));


    @Test
    public void restrictedTrianglesWithCylinders() {
        Camera.Builder camera = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene));

        scene.geometries.add(
                new Triangle(new Point(-150, -200, -115),  new Point(150, -200, -135),new Point(0, 78, -11))
                        .setEmission(new Color(218,218,88))
                        .setMaterial(new Material().setKs(0.8).setShininess(60)),
                new Triangle(new Point(-200, -200, -115), new Point(200, -200, -100), new Point(0, 90, -150))
                        .setEmission(new Color(138,138,58))
                        .setMaterial(new Material().setKs(0.8).setShininess(60)),
                new Triangle(new Point(-300, -200, -115), new Point(300, -200, -135), new Point(0, 90, -150))
                        .setEmission(new Color(218,218,88))
                        .setMaterial(new Material().setKs(0.8).setShininess(60)),

                //the boll

                new Sphere(15d, new Point(0, -80, -30))
                        .setEmission(new Color(black))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Sphere(2d, new Point(0, -76, 0))
                        .setEmission(new Color(black)),
                new Sphere(2d, new Point(3, -70, 0))
                        .setEmission(new Color(black)),
                new Sphere(2d, new Point(-3, -70, 0))
                        .setEmission(new Color(black)),




                new Triangle(new Point(-2.5, 20, -20), new Point(2.5, 20, -20), new Point(0, 30, 115))
                        .setEmission(new Color(white)),
                new Polygon(new Point(-2.5, 37.3, 3),new Point(2.5, 37.3, 3),new Point(2.5, 38, 3),new Point(-2.5, 38, 3))
                        .setEmission(new Color(red)),
                new Polygon(new Point(-2.4, 38.5, 3),new Point(2.4, 38.5, 3),new Point(2.4, 39.2, 3),new Point(-2.4, 39.2, 3))
                        .setEmission(new Color(red)),
                new Sphere(3d, new Point(0, 23, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(0, 24, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(0, 25, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(0, 26, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.0d, new Point(0, 27, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.25d, new Point(0, 28, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.5d, new Point(0, 29, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.25d, new Point(0, 30, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.25d, new Point(0, 31, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(0, 32, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(0, 33, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(0, 34, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(0, 35, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(0, 36, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(0, 37, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(0, 38, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(0, 39, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(0, 40, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(0, 42, 0))
                        .setEmission(new Color(white)),




                new Triangle(new Point(-11.3, 23, -19), new Point(-6.7, 23, -19), new Point(-9, 27, 100))
                        .setEmission(new Color(white)),
                new Polygon(new Point(-11.55, 39.85, 3),new Point(-6.25, 39.85, 3),new Point(-6.25, 40.5, 3),new Point(-11.55, 40.5, 3))
                   .setEmission(new Color(red)),
                new Polygon(new Point(-11.8, 38.5, 3),new Point(-6.4, 38.5, 3),new Point(-6.4, 39.2, 3),new Point(-11.8, 39.2, 3))
                        .setEmission(new Color(red)),
                new Sphere(2.75d, new Point(-9, 26, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-9, 27, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-9, 28, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-9, 29, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-9, 30, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(-9, 31, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.25d, new Point(-9, 32, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(-9, 33, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(-9, 34, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-9, 35, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-9, 36, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-9, 37, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-9, 38, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(-9, 39, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(-9, 40, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(-9, 41, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(-9, 42, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(-9, 43, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(-9, 44, 0))
                        .setEmission(new Color(white)),




                new Triangle(new Point(11.3, 23, -19), new Point(6.7, 23, -19), new Point(9, 27, 100))
                        .setEmission(new Color(white)),
                new Polygon(new Point(11.45, 39.85, 3),new Point(6.2, 39.85, 3),new Point(6.2, 40.5, 3),new Point(11.45, 40.5, 3))
                        .setEmission(new Color(red)),
                new Polygon(new Point(11.8, 38.5, 3),new Point(6.4, 38.5, 3),new Point(6.4, 39.2, 3),new Point(11.8, 39.2, 3))
                        .setEmission(new Color(red)),
                new Sphere(2.75d, new Point(9, 26, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(9, 27, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(9, 28, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(9, 29, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(9, 30, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(9, 31, 0))
                        .setEmission(new Color(white)),
                new Sphere(4.25d, new Point(9, 32, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d,  new Point(9, 33, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(9, 34, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(9, 35, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(9, 36, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(9, 37, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(9, 38, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(9, 39, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(9, 40, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(9, 41, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(9, 42, 0))
                        .setEmission(new Color(white)),
                new Sphere(2d, new Point(9, 43, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(9, 44, 0))
                        .setEmission(new Color(white)),




                new Triangle(new Point(-20.1, 26.5, -19), new Point(-15.9, 26.5, -19), new Point(-18, 30, 120))
                        .setEmission(new Color(white)),
                new Polygon(new Point(-20, 43.9, 3),new Point(-16, 43.9, 3),new Point(-16, 44.5, 3),new Point(-20, 44.5, 3))
                        .setEmission(new Color(red)),
                new Polygon(new Point(-20.2, 42.2, 3),new Point(-15.7, 42.2, 3),new Point(-15.7, 42.8, 3),new Point(-20.1, 42.8, 3))
                        .setEmission(new Color(red)),
                new Sphere(2.5d, new Point(-18, 29, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(-18, 30, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-18, 31, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-18, 32, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-18, 33, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-18, 34, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(-18, 35, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-18, 36, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-18, 37, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-18, 38, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-18, 39, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-18, 40, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(-18, 41, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(-18, 42, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-18, 43, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-18, 44, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-18, 45, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-18, 46, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(-18, 47, 0))
                        .setEmission(new Color(white)),


                new Triangle(new Point(15.9, 26.5, -19), new Point(20.3, 26.5, -19), new Point(18, 30, 120))
                        .setEmission(new Color(white)),
                new Polygon(new Point(20, 43.9, 3),new Point(16, 43.9, 3),new Point(16, 44.5, 3),new Point(20, 44.5, 3))
                        .setEmission(new Color(red)),
                new Polygon(new Point(20.3, 42.2, 3),new Point(15.7, 42.2, 3),new Point(15.7, 42.8, 3),new Point(20., 42.8, 3))
                        .setEmission(new Color(red)),
                new Sphere(2.5d, new Point(18, 29, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(18, 30, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(18, 31, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(18, 32, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(18, 33, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(18, 34, 0))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(18, 35, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(18, 36, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(18, 37, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(18, 38, 0))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(18, 39, 0))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(18, 40, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(18, 41, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(18, 42, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(18, 43, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(18, 44, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(18, 45, 0))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(18, 46, 0))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(18, 47, 0))
                        .setEmission(new Color(white)),





                new Polygon(new Point(-9, 44, 2),new Point(-4.7, 44, 2),new Point(-4.7, 44.6, 2),new Point(-9, 44.6, 2))
                        .setEmission(new Color(red)),
                new Polygon(new Point(-9, 42.5, 2),new Point(-4.7, 42.5, 2),new Point(-4.7, 43.1, 2),new Point(-9, 43.1, 2))
                        .setEmission(new Color(red)),
                new Sphere(2.5d, new Point(-6.5, 29, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(-6.5, 30, -5))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-6.5, 31, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-6.5, 32, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-6.5, 33, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-6.5, 34, -5))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(-6.5, 35, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.7d, new Point(-6.5, 36, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(-6.5, 37, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(-6.5, 38, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(-6.5, 39, -5))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(-6.5, 40, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(-6.5, 41, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(-6.5, 42, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75, new Point(-6.5, 43, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-6.5, 44, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-6.5, 45, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(-6.5, 46, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(-6.5, 47, -5))
                        .setEmission(new Color(white)),




                new Polygon(new Point(9, 44, 2),new Point(4.7, 44, 2),new Point(4.7, 44.6, 2),new Point(9, 44.6, 2))
                        .setEmission(new Color(red)),
                new Polygon(new Point(9, 42.5, 2),new Point(4.7, 42.5, 2),new Point(4.7, 43.1, 2),new Point(9, 43.1, 2))
                        .setEmission(new Color(red)),
                new Sphere(2.5d, new Point(6.5, 29, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(6.5, 30, -5))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(6.5, 31, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(6.5, 32, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(6.5, 33, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(6.5, 34, -5))
                        .setEmission(new Color(white)),
                new Sphere(4d, new Point(6.5, 35, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.7d, new Point(6.5, 36, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.75d, new Point(6.5, 37, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.5d, new Point(6.5, 38, -5))
                        .setEmission(new Color(white)),
                new Sphere(3.25d, new Point(6.5, 39, -5))
                        .setEmission(new Color(white)),
                new Sphere(3d, new Point(6.5, 40, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.75d, new Point(6.5, 41, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.5d, new Point(6.5, 42, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75, new Point(6.5, 43, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(6.5, 44, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(6.5, 45, -5))
                        .setEmission(new Color(white)),
                new Sphere(1.75d, new Point(6.5, 46, -5))
                        .setEmission(new Color(white)),
                new Sphere(2.25d, new Point(6.5, 47, -5))
                        .setEmission(new Color(white)),


                new Polygon(new Point(-300, -300, -150), new Point(300, -300, -150), new Point(300, 200, -150), new Point(-300, 200, -150))
                        .setEmission(new Color(white))
                        .setMaterial(new Material().setKs(0.8).setShininess(60))

                );



        scene.lights.add(
                new SpotLight(new Color(white), new Point(50, 50, 50), new Vector(-1, -1, -4))
                       .setKl(4E-4).setKq(2E-5)
        );

        camera.setImageWriter(new ImageWriter("myBaulingImage", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }

}
