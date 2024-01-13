package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeometriesTests {

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane = new Plane(new Point(1,1,0), new Point(1,3,0), new Point(3,2,1));
        Sphere sphere = new Sphere(1, new Point(3,0,0));
        Triangle tri = new Triangle(new Point(-2,-2,0), new Point(-2,-4,1), new Point(-4,-2,-3));

        //TC01: several but not all geometries intersect (only the plane and 2 points on sphere)
        Ray ray = new Ray(new Point(1.5,0,1), new Vector(2,0,-4));
        Geometries geometries = new Geometries(plane, sphere, tri);
        List<Point> res1 = new LinkedList<>();
        res1.add(new Point(1.8,0,0.4));
        res1.add(new Point(2,0,0));
        res1.add(new Point(2.4,0,-0.8));
        assertEquals(res1, geometries.findIntersections(ray), "ERROR: Only 1 geometry intersects");

        // =============== Boundary Values Tests ==================
        //TC10: Empty Geometries Collection
        geometries = new Geometries();
        ray = new Ray(new Point(1.5,2,1), new Vector(-2,0,4));
        assertNull(geometries.findIntersections(ray), "ERROR: empty geometries collection");

        //TC11: No intersection points
        ray = new Ray(new Point(1.5,2,1), new Vector(-2,0,4));
        geometries = new Geometries(plane, sphere, tri);
        assertNull(geometries.findIntersections(ray), "ERROR: No intersection points");

        //TC12: Only 1 geometry intersects
        ray = new Ray(new Point(1.5,2,1), new Vector(2,0,-4));
        geometries = new Geometries(plane, sphere, tri);
        List<Point> res2 = new LinkedList<>();
        res2.add(new Point(1.8,2,0.4));
        assertEquals(res2, geometries.findIntersections(ray), "ERROR: Only 1 geometry intersects");

        //TC13: All Geometries intersect
        ray = new Ray(new Point(1.5,0,1), new Vector(2,0,-4));
        geometries = new Geometries(plane, sphere);
        List<Point> res3 = new LinkedList<>();
        res3.add(new Point(1.8,0,0.4));
        res3.add(new Point(2,0,0));
        res3.add(new Point(2.4,0,-0.8));
        assertEquals(res3, geometries.findIntersections(ray), "ERROR: Only 1 geometry intersects");

    }
}
