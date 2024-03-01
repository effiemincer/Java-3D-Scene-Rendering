package renderer;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
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

    @Test
    public void GlossTest() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, 0)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKr(0.75).setGloss(100)), //
                new Plane(new Point(0, -100, 0), new Vector(0, 1, 0)) //
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Plane(new Point(0, 100, 0), new Vector(0, -1, 0)) //
                        .setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        //scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
        scene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(0, 0, 200), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

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
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Sphere(25d, new Point(-75, 0, 0)) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point(-150, -100, 100), new Point(150, -100, 100), new Point(0, 100, 100)) //
                        .setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setKd(0.05).setKs(0.15).setKt(0.95).setDiff(150)) //
        );
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.05));
        scene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(0, 0, 200), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(7500)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("DiffuseTest", 500, 500)).setTotalRays(300)
                .build()
                .renderImage()
                .writeToImage();
    }

}
