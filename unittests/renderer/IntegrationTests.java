package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for camera-ray intersection with different geometries.
 */
public class IntegrationTests {

    /**
     * Tests the intersection of camera rays with a plane.
     */
    @Test
    void testCameraRaysIntersectPlane() {

        Camera camera = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();

        // TC01: Plane is parallel to view plane. (9 Intersections)
        Plane plane = new Plane(new Point(-3,0,-3), new Point(0,-1,-3), new Point(1,3,-3));
        helperMethod(camera, plane, 9);

        // TC02: Plane is slightly tilted to view plane. (9 Intersections)
        plane = new Plane(new Point(-3,-1,-3), new Point(0,-1,-3), new Point(1,3,-1));
        helperMethod(camera, plane, 9);

        // TC03: Plane has large tilt to view plane (so lower pixels miss). (6 Intersections)
        plane = new Plane(new Point(-3,-3,-3), new Point(0,-1,-3), new Point(1,3,3));
        helperMethod(camera, plane, 6);

    }

    /**
     * Tests the intersection of camera rays with a triangle.
     */
    @Test
    void testCameraRaysIntersectTriangle() {
        Camera camera = Camera.getBuilder()
                .setLocation(Point.ZERO)
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();

        // TC01: Small Triangle, just central ray hits. (1 Intersection)
        // required that the normal of the triangle/plane is not the same as the ray coming in therefore
        // must insert points in specific order for correct calculation of normal
        Triangle tri = new Triangle(new Point(1,-1,-2), new Point(0,1,-2),new Point(-1,-1,-2));
        helperMethod(camera, tri, 1);

        // TC02: Large Triangle, middle and top middle pixel rays hit. (2 Intersection)
        tri = new Triangle(new Point(1,-1,-2), new Point(0,20,-2),new Point(-1,-1,-2));
        helperMethod(camera, tri, 2);

    }

    /**
     * Tests the intersection of camera rays with a sphere.
     */
    @Test
    void testCameraRaysIntersectSphere() {

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

        // TC03: All but corner rays hit the sphere. (10 intersections)
        sphere = new Sphere(2, new Point(0, 0, -2));
        camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();
        helperMethod(camera, sphere, 10);

        // TC04: Camera is inside the sphere (9 Intersections)
        sphere = new Sphere(4, new Point(0, 0, -0.5));
        camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();
        helperMethod(camera, sphere, 9);

        // TC05: Camera is behind the sphere (0 Intersections)
        sphere = new Sphere(0.5, new Point(0, 0, 1));
        camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3)
                .setVpDistance(1).build();
        helperMethod(camera, sphere, 0);
    }

    /**
     * Helper method for testing camera-ray intersections.
     *
     * @param camera                The camera used for generating rays.
     * @param in                    The intersectable geometry.
     * @param expectedIntersections The expected number of intersections.
     */
    void helperMethod(Camera camera, Intersectable in, int expectedIntersections) {
        List<Point> finalResults = new ArrayList<>();

        //creating rays for each pixel and finding the intersection
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                List<Point> result = in.findIntersections(camera.constructRay(3, 3, j, i));
                if (result != null)
                    finalResults.addAll(in.findIntersections(camera.constructRay(3, 3, j, i)));
            }
        }
        assertEquals(expectedIntersections, finalResults.size(), "Wrong number of intersections");
    }
}
