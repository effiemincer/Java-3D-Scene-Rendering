/**
 *
 */
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
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
     * Produce a picture of a sphere lighted by a spot-light
     */
    @Test
    public void twoSpheres() {
        scene.geometries.add(
                new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot-light
     */
    @Test
    public void twoSpheresOnMirrors() {
        scene.geometries.add(
                new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(200d, new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of two triangles lighted by a spot-light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        scene.geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(30d, new Point(60, 50, -50)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture designed by us that shows off the new features  of reflection and refraction
     */
    @Test
    public void buildYourOwnPicture() {
        scene.geometries.add(
                new Sphere(400d, new Point(0, 0, -1000)).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(50)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(600d, new Point(100, -250, -2000)).setEmission(new Color(0, 100, 0))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKr(new Double3(.3)).setGloss(10)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, -1500, -1500),
                        new Point(670, -670, 1500))
                        .setEmission(new Color(255, 255, 255))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(-1500, 1500, -1500), new Point(1500, 1500, -1500),
                        new Point(-670, 670, 1500))
                        .setEmission(new Color(25, 25, 25))
                        .setMaterial(new Material().setKt(0.5)),
                new Plane(new Point(0, 0, -2000), new Vector(0, 0, 1))
                        .setEmission(new Color(0, 100, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60))
        );
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new DirectionalLight(new Color(1020, 400, 400), new Vector(-1, -4, -4)));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("buildYourOwnPictureGLOSS", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot-light
     */
    @Test
    public void bonusPicture() {
        scene.geometries.add(
                new Sphere(400d, new Point(0, 0, -1000)).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(50)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(600d, new Point(100, -250, -2000)).setEmission(new Color(0, 100, 0))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKr(new Double3(.3))),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, -1500, -1500),
                        new Point(670, -670, 1500))
                        .setEmission(new Color(255, 255, 255))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(-1500, 1500, -1500), new Point(1500, 1500, -1500),
                        new Point(-670, 670, 0))
                        .setEmission(new Color(25, 25, 25))
                        .setMaterial(new Material().setKt(0.5)),
                new Plane(new Point(0, 0, -2000), new Vector(0, 0, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(200d, new Point(-1500, 0, -1000)).setEmission(new Color(0, 0, 100))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(25).setKt(0.5)),
                new Sphere(200d, new Point(1500, 0, -1000)).setEmission(new Color(100, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(1)),
                new Triangle(new Point(1500, 1500, 1500), new Point(1500, 0, -1500),
                        new Point(1500, 1000, 0))
                        .setEmission(new Color(20, 120, 20))
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))),
                new Sphere(350d, new Point(0, 1500, -100)).setEmission(new Color(75, 0, 100))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.5)),
                new Sphere(350d, new Point(0, -1500, -100)).setEmission(new Color(100, 100, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.5))
        );
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.05));
        scene.lights.add(new DirectionalLight(new Color(100, 50,  100), new Vector(-1, -4, -4)));
        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(-1500, 0, -1000), new Vector(0,1, -1))
                .setKl(0.00001).setKq(0.000005).setNarrowBeam(20));
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(0, 100, 0)).setKl(0.0001).setKq(0.000001));
        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(0, 1500, -100), new Vector(0,1, -1))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(7500)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("Bonus Picture", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
