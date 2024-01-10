package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {
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
}