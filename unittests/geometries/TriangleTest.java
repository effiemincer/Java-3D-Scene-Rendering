package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
        //TC01: testing the normal to the triangle is correct
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);

        Triangle tri = new Triangle(p1,p2,p3);

        Vector normal = tri.getNormal(new Point(0,0,0));

        //tests to see that normal is calculated correctly, as per instructions, we can't know direction of vector so test for both directions
        assertTrue(normal.equals(new Vector(0,0,1)) || normal.equals(new Vector(0,0,-1)), "ERROR: getNormal(Point) does not return correct normal");
        assertTrue(normal.equals(new Vector(0,0,1)) ||  normal.equals(new Vector(0,0,-1)), "ERROR: getNormal() does not return correct normal");

    }
}