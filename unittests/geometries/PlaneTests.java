package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    /**
     * Test method for ctor.
     */
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================


        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(2, 0, 0);
        Point p4 = new Point(0, 0, 0);

        //TC10: tests that points are not all on same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3), "ERROR: points are all on the same line");

        //TC11: tests that no 2 points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p4, p2), "ERROR: 2 Duplicate points in plane ctor");
    }

    /**
     * Test method for {@link Plane#getNormal()}  and {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: testing the normal to the plane is correct for both getNormal() and getNormal(point)
        Plane plane = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector normal = plane.getNormal(new Point(0, 0, 0));

        //TC02: tests to see that normal is calculated correctly, as per instructions, we can't know direction of vector so test for both directions
        assertTrue(normal.equals(new Vector(0, 0, 1)) || normal.equals(new Vector(0, 0, -1)), "ERROR: getNormal(Point) does not return correct normal");
        assertTrue(normal.equals(new Vector(0, 0, 1)) || normal.equals(new Vector(0, 0, -1)), "ERROR: getNormal() does not return correct normal");

        //TC03: test to see if normal was normalized
        assertEquals(1, normal.length(), "ERROR: normal vector is not normalized");
    }

    @Test
    void testFindIntersections() {

    }
}