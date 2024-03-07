package renderer;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class GlossDiffuseTests {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(Point.ZERO, Vector.Y)
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere and a triangle with a spot light testing gloss
     */
    @Test
    public void GlossTest() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, 0)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKr(0.85).setGloss(225)), //
                new Triangle(new Point(-150, 100, 400), new Point(150, 100, 400), new Point(0, 0, -750)) //
                        .setEmission(new Color(0, 100, 0)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.15)),
                new Triangle(new Point(-150, -100, 400), new Point(150, -100, 400), new Point(0, 0, -750)) //
                        .setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.15).setKr(0.85).setGloss(225))
        );
        //scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
//                new SpotLight(new Color(255, 255, 255), new Point(0, 100, 150), new Vector(0, -1, -1)) //
//                        .setKl(4E-4).setKq(2E-5));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(7500)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("GlossTest", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }


    /**
     * Produce a picture of a sphere and a triangle with a spot light testing diffuse
     */
    @Test
    public void DiffuseTest() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, 0)) //
                        .setEmission(new Color(BLUE)) ///
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Sphere(25d, new Point(-75, 0, 0)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point(-150, -100, 100), new Point(150, -100, 100), new Point(0, 100, 100)) //
                       // .setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.15).setKt(0.95).setDiff(150)) //
        );
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
        scene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(0, 0, 200), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(7500)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("DiffuseTest", 500, 500)).setTotalRays(10)
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere and a triangle with a spot light testing diffuse
     */
    @Test
    public void DiffuseTest2() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, 100)) //
                        .setEmission(new Color(BLUE)) ///
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.75).setDiff(250)), //
                new Sphere(25d, new Point(-25, 0, 0)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point(0, 0, 0), new Point(150, -100, 0), new Point(0, 100, 0)) //
                         //.setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.15).setKt(0.95).setDiff(250)), //
                new Sphere(25d, new Point(50, 0, -100)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
        scene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(0, 0, 200), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(7500)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("DiffuseTest2", 500, 500)).setTotalRays(10)
                .build()
                .renderImage()
                .writeToImage();
    }

}
