package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RayTests {

    /**
     * Test method for {@link Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(1, 1, -1), new Vector(1, 2, -2));

        //TC01: t is positive
        double t = 3;
        assertEquals(new Point(2, 3, -3), ray.getPoint(t), "ERROR: t is positive but point was not correct");


        //TC02: t is negative
        t = -3;
        assertEquals(new Point(0, -1, 1), ray.getPoint(t), "ERROR: t is negative but point was not correct");

        // =============== Boundary Values Tests ==================
        // TC10: t is 0
        t = 0;
        assertEquals(new Point(1, 1, -1), ray.getPoint(t), "ERROR: t is zero but point was not correct");

    }

    @Test
    void testFindClosestPoint() {

        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 2, -2));
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(0, 0, 1);
        Point p3 = new Point(2, 2, 2);

// ============ Equivalence Partitions Tests ==============
        // EP01: point is somewhere in the list
        LinkedList<Point> list = new LinkedList<Point>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        assertEquals(p2, ray.findClosestPoint(list), "ERROR: Incorrect closest point");

// =============== Boundary Values Tests ==================
        // BV01: empty list. return null
        list = new LinkedList<Point>();

        assertNull(ray.findClosestPoint(list), "ERROR: List is not empty");

        // BV02: element 1 is the closest point. return point
        list = new LinkedList<Point>();
        list.add(p2);
        list.add(p1);
        list.add(p3);

        assertEquals(p2, ray.findClosestPoint(list), "ERROR: Element 1 is not the closest point");

        // BV02: last element is the closest point. return point
        list = new LinkedList<Point>();
        list.add(p3);
        list.add(p1);
        list.add(p2);

        assertEquals(p2, ray.findClosestPoint(list), "ERROR: Last element is not the closest point");
    }
}
