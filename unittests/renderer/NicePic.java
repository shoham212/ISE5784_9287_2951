package renderer;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static java.awt.Color.pink;

import org.junit.jupiter.api.Test;

//import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import primitives.*;
import scene.Scene;

public class NicePic {

    private final Scene scene = new Scene("GlossysurfacesAndBlurryGlassTests");//.setKMeans(4);
    private final Camera.Builder cameraBuilder = Camera.getBuilder().setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void MyPicture() throws CloneNotSupportedException {
        cameraBuilder.setDirection(new Vector(-1,1,0), new Vector(0,0,1));
        
        scene.geometries.add(
                new Plane(new Point(1,1,0), new Point(1,0,0), new Point(0,1,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLUE)),

                new Triangle(new Point(0,100,0), new Point(-50,100,0), new Point(-50,100,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(-50,150,0), new Point(-50,100,0), new Point(-50,100,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(-50,150,0), new Point(-50,150,50), new Point(-50,100,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(-50,150,0), new Point(-50,150,50), new Point(0,150,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(0,100,50), new Point(-50,100,50), new Point(0,100,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(0,150,0), new Point(-50,150,50), new Point(0,150,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(0,100,0), new Point(0,150,0), new Point(0,150,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(0,100,0), new Point(0,100,50), new Point(0,150,50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(150,75,0)),

                new Triangle(new Point(0,100,50), new Point(0,150,50), new Point(-25,125,90))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(-50,100,50), new Point(-50,150,50), new Point(-25,125,90))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(-50,100,50), new Point(0,100,50), new Point(-25,125,90))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(0,150,50), new Point(-50,150,50), new Point(-25,125,90))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(RED)),

                new Triangle(new Point(-10,170,0), new Point(-40,170,0), new Point(-10,170,120))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-10,170,120), new Point(-40,170,120), new Point(-40,170,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-10,170,0), new Point(-10,200,0), new Point(-10,170,120))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-10,200,120), new Point(-10,200,0), new Point(-10,170,120))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-10,200,0), new Point(-10,200,120), new Point(-40,200,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-40,200,120), new Point(-10,200,120), new Point(-40,200,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-40,200,0), new Point(-40,170,0), new Point(-40,200,120))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Triangle(new Point(-40,170,120), new Point(-40,170,0), new Point(-40,200,120))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                        .setEmission(new Color(BLACK)),

                new Sphere(15, new Point(-25, 185, 120)).setEmission(new Color(RED.brighter()))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),

                new Sphere(30, new Point(-25, 185, 120)).setEmission(new Color(pink))
                        .setMaterial(new Material().setKd(0.001).setKs(0.001).setBlurGlass( 120, 0.9 * ( 15), 10).setShininess(30).setKt(0.2)),

                new Sphere(30,new Point(-300, 600, 300)).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(30).setKt(0.7)),

                new Triangle(new Point(-250, 400, 0), new Point(-400, 100, 0),
                        new Point(-250, 400, 200))
                        .setEmission(new Color(20,20,20))
                        .setMaterial(new Material().setKr(1).setBlurGlass( 100, 0.9 * ( 15), 10)),

                new Triangle(new Point(-400,100,200), new Point(-400, 100, 0),
                        new Point(-250, 400, 200))
                        .setEmission(new Color(20,20,20))
                        .setMaterial(new Material().setKr(1)),

                new Plane(new Point(-1000,0,0), new Point(0,1000,0), new Point(-0,1000,1000))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
                .setEmission(new Color(0,255,255)));

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.03));
        
        scene.lights.add(
                new PointLight(new Color(WHITE), new Point(-300, 600, 300)).setKl(4E-5).setKq(2E-7));

        cameraBuilder.setLocation(new Point(1200, -1100, 150)).setVpDistance(1000)
                .setVpSize(500, 500)
                .setImageWriter(new ImageWriter("MyPicture", 1000, 1000))
                .setMultithreading(6) //
                .setDebugPrint(0.7)
                .build()
                .renderImage()
                .writeToImage();
    }
}
