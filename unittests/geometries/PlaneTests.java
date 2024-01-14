package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane = new Plane(new Point(0,0,1), new Point(1,0,0), new Point(0,1,0));

        //TC01: The ray is  parallel to the plane
        Ray ray = new Ray(new Point(2,0,0), new Vector(1,0,1));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is parallel to the plane");

        //TC02: The ray is orthogonal to the plane
        ray = new Ray(new Point(2,0,0), new Vector(1,1,1));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is orthogonal to the plane");

        //TC03: the ray intersects the plane
        ray = new Ray(new Point(2,0,0), new Vector(-5,0,1));
        List<Point> res = new LinkedList<>();
        res.add(new Point(.75,0,.25));
        assertEquals(res, plane.findIntersections(ray), "ERROR: the ray intersects the plane");

        //TC04: the ray does not intersect the plane
        ray = new Ray(new Point(2,0,0), new Vector(5,0,1));
        assertNull(plane.findIntersections(ray), "ERROR: the ray does not intersect the plane");

        // =============== Boundary Values Tests ==================
        plane = new Plane(new Point(1,1,1), new Point(2,2,0), new Point(3,0,3));
        //direction vector (1,-2,3), normal vector (1,-4,-3)

        //TC10: Ray is parallel to the plane and INCLUDED in the plane
        ray = new Ray(new Point(-1,-1,3), new Vector(1,-2,3));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is parallel to the plane");

        //TC11: Ray is parallel to the plane and NOT INCLUDED in the plane
        ray = new Ray(new Point(-1,1,3), new Vector(1,1,-1));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is parallel to the plane");

        //TC12: Ray is Orthogonal to Plane but starts before the plane (therefore they will intersect)
        ray = new Ray(new Point(-1,1,3), new Vector(1,-4,-3));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is orthogonal to the plane");

        //TC13: Ray is orthogonal to plane and starts inside the plane (starting point intersect)
        ray = new Ray(new Point(-1,-1,3), new Vector(1,-4,-3));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is orthogonal to the plane");

        //TC14: Ray is orthogonal to plane and starts after the plane (0 intersections)
        ray = new Ray(new Point(-1,-1,-1), new Vector(1,-4,-3));
        assertNull(plane.findIntersections(ray), "ERROR: the ray is orthogonal to the plane");

        //TC15: Ray is neither orthogonal nor parallel to plane and begins at plane (Point is in plane but not ray)
        ray = new Ray(new Point(-1,-1,3), new Vector(1,-3,-3));
        assertNull(plane.findIntersections(ray), "ERROR: head of ray is in plane but the ray does not intersect");

        //TC16: Ray is neither orthogonal nor parallel to plane and begins at reference point of plane
        ray = new Ray(new Point(0,0,1), new Vector(1,-3,-3));
        assertNull(plane.findIntersections(ray), "ERROR: head of ray is in plane (and on the reference point of the plane) but the ray does not intersect");
    }
}