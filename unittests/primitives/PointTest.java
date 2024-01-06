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

        Point point_result = point.add(vector);

        assertEquals(point_result, new Point(4, 4, 4));
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // Test cases for distance calculation
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // Test cases for squared distance calculation
    }
}
