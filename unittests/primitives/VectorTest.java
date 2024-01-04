package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void testVector() {

    }

    @Test
    void testAdd() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(3, 2, 1);
        Vector inverseVector1 = new Vector(-1, -2, -3);

        Vector resultVector = vector1.add(vector2);

        assertEquals(resultVector, new Vector(4, 4, 4), "ERROR: Vector + itself throws wrong exception");
        assertThrows(IllegalArgumentException.class, () -> vector1.add(inverseVector1), "Cannot add a vector + -itself!");
    }

    // ! Create subtract method in class
    @Test
    void testSubtract() {
    }

    @Test
    void testScale() {
        Vector vector = new Vector(1, 1, 1);
        double scalingValue = 2;

        Vector resultVector = new Vector(vector.xyz.scale(scalingValue));

        assertEquals(resultVector, new Vector(2, 2, 2));
    }

    @Test
    void testDotProduct() {
        Vector vector1 = new Vector(1, -1, 1);
        Vector vector2 = new Vector(2, 2, 2);
        Vector vector3 = new Vector(0, 1, -1);
        Vector vector4 = new Vector(1, 0, 0);

        // Check for different vectors
        assertEquals(vector1.dotProduct(vector2), 2, "ERROR: dotProduct() wrong value");
        // Check for same vector
        assertEquals(vector1.dotProduct(vector1), 3, "ERROR: dotProduct() wrong value");

        // Check for ortholinearity
        assertThrows(IllegalArgumentException.class, () -> vector3.dotProduct(vector4), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    void testCrossProduct() {
        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = new Vector(2, 2, 2);
        Vector vector3 = new Vector(1, 0, 0);

        assertEquals(vector1.crossProduct(vector3), new Vector(0, 1, -1), "ERROR: crossProduct() wrong result length");


    }

    @Test
    void testLengthSquared() {
        Vector vector = new Vector(0, 3, 4);

        assertEquals(vector.lengthSquared(), 25, "ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
        Vector vector = new Vector(0, 3, 4);

        assertEquals(vector.length(), 5, "ERROR: length() wrong value");
    }

    @Test
    void testNormalize() {
    }
}