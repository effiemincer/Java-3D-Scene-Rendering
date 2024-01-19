package renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import geometries.Sphere;
import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;

import java.util.ArrayList;
import java.util.List;
//import scene.Scene;

/**
 * Testing Camera Class
 *
 * @author Dan
 */
class CameraTest {
    /**
     * Camera builder for the tests
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
//            .setRayTracer(new SimpleRayTracer(new Scene("Test")))
//            .setImageWriter(new ImageWriter("Test", 1, 1))
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpDistance(10);

    /**
     * Test method for
     * {@link Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testConstructRay() {
        final String badRay = "Bad ray";

        // ============ Equivalence Partitions Tests ==============
        // EP01: 4X4 Inside (1,1)
        Camera camera1 = cameraBuilder.setVpSize(8, 8).build();
        assertEquals(new Ray(Point.ZERO, new Vector(1, -1, -10)),
                camera1.constructRay(4, 4, 1, 1), badRay);

        // =============== Boundary Values Tests ==================
        // BV01: 4X4 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(3, -3, -10)),
                camera1.constructRay(4, 4, 0, 0), badRay);

        // BV02: 4X4 Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(1, -3, -10)),
                camera1.constructRay(4, 4, 1, 0), badRay);

        // BV03: 3X3 Center (1,1)
        Camera camera2 = cameraBuilder.setVpSize(6, 6).build();
        assertEquals(new Ray(Point.ZERO, new Vector(0, 0, -10)),
                camera2.constructRay(3, 3, 1, 1), badRay);

        // BV04: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(0, -2, -10)),
                camera2.constructRay(3, 3, 1, 0), badRay);

        // BV05: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(Point.ZERO, new Vector(2, 0, -10)),
                camera2.constructRay(3, 3, 0, 1), badRay);

        // BV06: 3X3 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(2, -2, -10)),
                camera2.constructRay(3, 3, 0, 0), badRay);

    }

    @Test
    void testCameraRaysIntersectSphere() {

        // TC01: Just the central ray hits (intersects) the sphere
//        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
//        Camera camera = Camera.getBuilder()
//                .setLocation(Point.ZERO)
//                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
//                .setVpSize(3, 3)
//                .setVpDistance(1).build();
//
//        List<Point> result;
//        for (int i = 0; i <= 2; i++) {
//            for (int j = 0; j <= 2; j++) {
//
//                result = sphere.findIntersections(camera.constructRay(3, 3, j, i));
//                if (i == 1 && j == 1)
//                    assertEquals(2, result.size(), "Wrong number of points");
//                else
//                    assertNull(result, "Ray's line out of sphere");
//            }
//        }

        // TC01: Just the central ray intersects the sphere. 2 intersection points
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
        Camera camera = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();
        helperMethod(camera, sphere, 2);

        // TC02: All rays hit the sphere. 18 intersection points
        sphere = new Sphere(2.5, new Point(0, 0, -2.5));
        camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();
        helperMethod(camera, sphere, 18);

    }

    // TODO: FIX THIS METHOD TO BE GENERAL, NOT ONLY SPHERE
    void helperMethod(Camera camera, Sphere sphere, int expectedIntersections) {
        List<Point> finalResults = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                List<Point> result = sphere.findIntersections(camera.constructRay(3, 3, j, i));
                if (result != null)
                    finalResults.addAll(sphere.findIntersections(camera.constructRay(3, 3, j, i)));
            }
        }
        assertEquals(expectedIntersections, finalResults.size(), "Wrong number of points");
    }
}
