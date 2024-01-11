package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: testing the normal to the sphere is correct
        Point center = new Point(0, 0, 0);
        double r = 2;
        Sphere sphere = new Sphere(r, center);
        Vector normal = sphere.getNormal(new Point(2, 0, 0));

        assertEquals(new Vector(2, 0, 0).normalize(), normal, "ERROR: normal calculation is incorrect");
    }

    @Test
    public void testFindIntersections() {
        final Point centerPoint = new Point(0, 4, 0);
        Sphere sphere = new Sphere(2, centerPoint);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        Point rayPoint = new Point(3, 0, 0);
        Vector rayVector = new Vector(0, 1, 0);
        Ray ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        rayPoint = new Point(4, 0, 0);
        rayVector = new Vector(0, 5, 0);
        ray = new Ray(rayPoint, rayVector);
        Point pIntersect1 = new Point(1.67, 2.91, 0);
        Point pIntersect2 = new Point(-0.7, 5.87, 0);
        var result = sphere.findIntersections(ray).stream().sorted(Comparator.comparingDouble(p -> p.distance(rayPoint))).toList();
        var exp = List.of(pIntersect1, pIntersect2);
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC03: Ray starts inside the sphere (1 point)
        rayPoint = new Point(0, 5, 0);
        rayVector = new Vector(4, 0, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(1.67, 2.91, 0);
        exp = List.of(pIntersect1);
        result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC04: Ray starts after the sphere (0 points)
        rayPoint = new Point(3, 0, 0);
        rayVector = new Vector(0, 1, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC05: Ray starts at sphere and goes inside (1 point)
        rayPoint = new Point(0, 2, 0);
        rayVector = new Vector(2, 4, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(2, 4, 0);
        exp = List.of(pIntersect1);
        result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC06: Ray starts at sphere and goes outside (0 points)
        rayPoint = new Point(0, 2, 0);
        rayVector = new Vector(2, 0, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        // TC07: Ray starts before the sphere (2 points)
        rayPoint = new Point(0, 1, 0);
        rayVector = new Vector(0, 7, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(0, 2, 0);
        pIntersect2 = new Point(0, 6, 0);
        result = sphere.findIntersections(ray).stream().sorted(Comparator.comparingDouble(p -> p.distance(rayPoint))).toList();
        exp = List.of(pIntersect1, pIntersect2);
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC08: Ray starts at sphere and goes inside (1 point)
        rayPoint = new Point(0, 2, 0);
        rayVector = new Vector(0, 7, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(0, 6, 0);
        exp = List.of(pIntersect1);
        result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC09: Ray starts inside (1 point)
        rayPoint = new Point(0, 3, 0);
        rayVector = new Vector(0, 2, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(0, 1, 0);
        exp = List.of(pIntersect1);
        result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC10: Ray starts at the center (1 point)
        rayPoint = centerPoint;
        rayVector = new Vector(0, 1, 0);
        ray = new Ray(rayPoint, rayVector);
        pIntersect1 = new Point(0, 2, 0);
        exp = List.of(pIntersect1);
        result = sphere.findIntersections(ray);
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(exp, result, "Ray crosses sphere in the incorrect place(s)");

        // TC11: Ray starts at sphere and goes outside (0 points)
        rayPoint = new Point(0, 2, 0);
        rayVector = new Vector(0, 1, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // TC12: Ray starts after sphere (0 points)
        rayPoint = new Point(0, 7, 0);
        rayVector = new Vector(0, 8, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC13: Ray starts before the tangent point
        rayPoint = new Point(2, 2, 0);
        rayVector = new Vector(-2, 2, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // TC14: Ray starts at the tangent point
        rayPoint = new Point(0, 2, 0);
        rayVector = new Vector(2, 2, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // TC15: Ray starts after the tangent point
        rayPoint = new Point(-2, 2, 0);
        rayVector = new Vector(-3, 2, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

        // **** Group: Special cases
        // TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        rayPoint = new Point(0, 1, 0);
        rayVector = new Vector(1, 1, 0);
        ray = new Ray(rayPoint, rayVector);
        assertNull(sphere.findIntersections(ray), "Ray's line out of sphere");

    }
}