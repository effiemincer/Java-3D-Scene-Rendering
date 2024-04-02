package special;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;

public class FinalPresentation  {

    private final Scene scene = new Scene("Test scene");

    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(Point.ZERO, Vector.Y)
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void finalPresentation() {
        scene.geometries.add(
                new Triangle(new Point(30, -35, 300), new Point(-30, -35, 300),
                        new Point(30, 40, 300))
                        //.setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.5).setKs(0.05).setKt(0.95).setDiff(250)),
                new Triangle(new Point(-30,40, 300), new Point(30, 40, 300), new Point(-30,-35,300))
                        //.setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.5).setKs(0.05).setKt(0.95).setDiff(250)),
                new Sphere(25d, new Point(0, 0, 250))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.15).setKr(0.5).setShininess(30)),
                new Sphere(10d, new Point(0, -30, 350))
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.05).setKs(0.15).setKr(0.85).setGloss(225)),
//                new Sphere(10d, new Point(0, 40, 350))
//                        .setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.15).setKr(0.6)),
                new Plane(new Point(0, -40, 0), new Vector(0,1, 0))
                        .setEmission(new Color(45, 100, 210))
                        .setMaterial(new Material().setKd(0.5).setKs(0.15)));

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.1));
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(0, -30, 370), new Vector(0, 0, -1)).setKl(4E-5).setKq(2E-7));
        scene.lights.add(new DirectionalLight(new Color(100, 100, 100), new Vector(1, 0, 0)));
        scene.lights.add(new PointLight(new Color(GREEN), new Point(0, 0, 360)).setKl(0.001).setKq(0.0005));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpSize(200, 200).setVpDistance(1000)
                .setDebugPrint(0.1).setMultiThreading(3)
                .setAdaptiveDepth(2)
                //.setTotalRays(9)
                .setImageWriter(new ImageWriter("Final Presentation", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
