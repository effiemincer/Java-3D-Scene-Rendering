package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testSubtract() {

        Point point1 = new Point(2, 2, 2);
        Point point2 = new Point(1, 1, 1);

        Vector result = point1.subtract(point2);

        assertEquals(result, new Vector(1, 1, 1));
    }

    @Test
    void testSubtractResultsZero() {

        Point point = new Point(1, 2, 3);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> point.subtract(point));

        // Assert the exception message if needed
        assertEquals("Can't subtract a point from itself", exception.getMessage());
    }

    // TODO: Check for P(1, 1, 1).add(V(-1, -1, -1)) ???
    @Test
    void testAdd() {
        Point point = new Point(1, 2, 3);
        Vector vector = new Vector(3, 2, 1);

        Point point_result = point.add(vector);

        assertEquals(point_result, new Point(4, 4, 4));
    }


}