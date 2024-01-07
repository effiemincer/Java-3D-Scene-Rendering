package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
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
        assertEquals(1, normal.length(), "Triangle's normal is not orthogonal to one of the edges");


        //TC03: testing that the normal is orthogonal to the edges
        Vector v1 = points[0].subtract(points[1]);
        Vector v2 = points[0].subtract(points[2]);
        Vector v3 = points[1].subtract(points[2]);

        assertEquals(0, v1.dotProduct(normal), "Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0, v2.dotProduct(normal), "Triangle's normal is not orthogonal to one of the edges");
        assertEquals(0, v3.dotProduct(normal), "Triangle's normal is not orthogonal to one of the edges");
    }
}