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
        Point point1 = new Point(2, 2, 2);
        Point point2 = new Point(1, 1, 1);

        assertEquals(point1.subtract(point2), new Vector(1, 1, 1), "ERROR: (point1 - point2) does not work correctly");
        assertThrows(IllegalArgumentException.class, () -> point1.subtract(point1), "ERROR: (point - itself) does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        Point point = new Point(1, 2, 3);
        Vector vector = new Vector(3, 2, 1);
        Vector v1Opposite = new Vector(-1, -2, -3);

        Point point_result = point.add(vector);

        assertEquals(point_result, new Point(4, 4, 4), "ERROR: (point + vector) = other point does not work correctly");
        assertTrue(point.add(v1Opposite).equals(Point.ZERO), "ERROR: (point + vector) = center of coordinates does not work correctly"); //I don't think it can be simplified because of isZero in .equals() in Double3
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        Point  p1         = new Point(1, 2, 3);
        Point  p3         = new Point(2, 4, 5);
        // Test cases for distance calculation


        assertTrue(Util.isZero(p1.distance(p1)),"ERROR: point distance to itself is not zero" );
        assertTrue(Util.isZero(p1.distance(p3)-3), "ERROR: distance between points to itself is wrong");
        assertTrue(Util.isZero(p3.distance(p1) - 3), "ERROR: distance between points to itself is wrong");;
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        Point  p1         = new Point(1, 2, 3);
        Point  p3         = new Point(2, 4, 5);
        // Test cases for squared distance calculation
        assertTrue(Util.isZero(p1.distanceSquared(p1)), "ERROR: point squared distance to itself is not zero");
        assertTrue(Util.isZero(p3.distanceSquared(p1) - 9), "ERROR: squared distance between points is wrong");
        assertTrue(Util.isZero(p1.distanceSquared(p3) - 9), "ERROR: squared distance between points is wrong");
    }
}
