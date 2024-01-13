package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

    private Triangle tri;

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point[] points =
                {new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)};

        Triangle tri = new Triangle(points[0], points[1], points[2]);

        //TC01: testing the normal to the triangle is correct using getNormal(Point)
        Vector normal = tri.getNormal(new Point(0, 0, 0));
        assertTrue(normal.equals(new Vector(0, 0, 1)) || normal.equals(new Vector(0, 0, -1)), "ERROR: getNormal(Point) does not return correct normal");

        //TC02: testing that the normal is a unit vector
        assertEquals(1, normal.length(), "ERROR: Normal is normalized to unit vector");

        //TC03: testing that the normal is orthogonal to the edges
        for (int i = 0; i < 3; i++) {
            assertEquals(0d, points[i].subtract(points[i == 0 ? 2 : i - 1]).dotProduct(normal), "ERROR: Triangle's normal is not orthogonal to one of the edges");
        }
    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        tri = new Triangle(new Point(1,1,0), new Point(1,3,0), new Point(3,2,1));

        //TC01: Ray starts before Triangle and intersects with it
        Ray ray = new Ray(new Point(1.5,2,1), new Vector(2,0,-3));
        assertEquals(new Point(1.88,2,0.44), tri.findIntersections(ray), "ERROR: Ray intersects with triangle once");

        //TC02: Ray starts before triangle and does not intersect
        ray = new Ray(new Point(2,1,1), new Vector(0,0,-1));
        assertNull(tri.findIntersections(ray), "ERROR: ray does not intersect with triangle");

        //TC03: Ray starts before triangle and does not intersect (in-between two lines)
        ray = new Ray(new Point(0.782,0.66874,0.5), new Vector(0,0,-1));
        assertNull(tri.findIntersections(ray), "ERROR: ray does not intersect with triangle");


        // =============== Boundary Values Tests ==================
        //TC10: Ray intersects with line the extension of a line from A to B
        ray = new Ray(new Point(1,4,1), new Vector(0,0,-1));
        assertNull(tri.findIntersections(ray), "ERROR: ray intersects with extension of a line from A to B");

        //TC11: Ray intersect on edge of triangle
        ray = new Ray(new Point(1,2,1), new Vector(0,0,-1));
        assertNull(tri.findIntersections(ray), "ERROR: ray intersects with edge of triangle");

        //TC12: Ray starts on corner of triangle
        ray = new Ray(new Point(1,3,1), new Vector(0,0,-1));
        assertNull(tri.findIntersections(ray), "ERROR: ray intersects with vertex of triangle");


    }
}