package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * Author: [Your Name or Information]
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(Point)} ()}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point point1 = new Point(2, 2, 2);
        Point point2 = new Point(1, 1, 1);

        //TC01: testing the regular subtraction works correctly
        assertEquals(new Vector(1, 1, 1), point1.subtract(point2), "ERROR: (point1 - point2) does not work correctly");

        // =============== Boundary Values Tests ==================
        //TC02: testing that a point minus itself throws an exception
        assertThrows(IllegalArgumentException.class, () -> point1.subtract(point1), "ERROR: (point - itself) does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point point = new Point(1, 2, 3);
        Vector vector = new Vector(3, 2, 1);
        Vector v1Opposite = new Vector(-1, -2, -3);

        //TC01: point + a vector calculates correctly
        assertEquals(new Point(4, 4, 4), point.add(vector), "ERROR: (point + vector) = other point does not work correctly");

        //TC02: point + the opposite of itself is 0
        assertTrue(point.add(v1Opposite).equals(Point.ZERO), "ERROR: (point + vector) = center of coordinates does not work correctly"); //I don't think it can be simplified because of isZero in .equals() in Double3
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p3 = new Point(2, 4, 5);

        //TC01: checking that the distance of a point to itself is zero
        assertTrue(Util.isZero(p1.distance(p1)), "ERROR: point distance to itself is not zero");

        //TC02:checking that distance is being calculated correctly
        assertTrue(Util.isZero(p1.distance(p3) - 3), "ERROR: distance between points to itself is wrong");
        assertTrue(Util.isZero(p3.distance(p1) - 3), "ERROR: distance between points to itself is wrong");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);
        Point p3 = new Point(2, 4, 5);

        //TC01: checking that the distance squared of a point to itself is zero
        assertTrue(Util.isZero(p1.distanceSquared(p1)), "ERROR: point squared distance to itself is not zero");

        //TC02:checking that distance squared is being calculated correctly
        assertTrue(Util.isZero(p3.distanceSquared(p1) - 9), "ERROR: squared distance between points is wrong");
        assertTrue(Util.isZero(p1.distanceSquared(p3) - 9), "ERROR: squared distance between points is wrong");
    }
}
