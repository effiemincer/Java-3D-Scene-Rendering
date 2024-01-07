package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {


    /**
     * Test method for constructing a zero vector.
     */
    @Test
    void testZeroVectorConstructor() {
        // =============== Boundary Values Tests ==================

        //TC01: not letting a zero vector be created
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: zero vector does not throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), "ERROR: zero vector does not throw an exception");
    }


    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void testAdd() {

        Vector v1 = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing that a vector plus a vector is the correct calculation
        assertEquals(new Vector(-1, -2, -3), v1.add(v2), "ERROR: Vector + Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        //TC02: testing that getting a result that would return a zero vector throws an exception
        assertThrows(IllegalArgumentException.class, () -> v1.add(v1Opposite), "ERROR: Vector + -itself does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}.
     */
    @Test
    void testSubtract() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);
        // ============ Equivalence Partitions Tests ==============

        //TC01: testing that a vector minus a vector is the correct calculation
        assertEquals(new Vector(1, 1, 1), vector2.subtract(vector1), "ERROR: Vector - Vector does not work correctly");

        // =============== Boundary Values Tests ==================

        //TC02: testing that getting a result that would return a zero vector throws an exception
        assertThrows(IllegalArgumentException.class, () -> vector1.subtract(vector1), "ERROR: Vector - itself does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        Vector vector = new Vector(1, 1, 1);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing that a vector is properly scaled
        assertEquals(new Vector(2, 2, 2), new Vector(vector.xyz.scale(2)), "ERROR: The vector is not scaling correctly");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing orthogonal vectors (whose dot product is 0)
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");

        //TC02: checking a regular dot product produces the correct value
        assertEquals(0, v1.dotProduct(v2) + 28, "ERROR: dotProduct() wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing normal cross product works correctly
        assertTrue(Util.isZero(vr.length() - v1.length() * v3.length()), "ERROR: crossProduct() wrong result length");

        //TC02: cross product result is orthogonal to its operands
        assertEquals(0, vr.dotProduct(v1), "ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0, vr.dotProduct(v3), "ERROR: crossProduct() result is not orthogonal to its operands");

        // =============== Boundary Values Tests ==================

        //TC01: testing that cross product for parallel vectors throws exception
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "ERROR: crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector vector = new Vector(0, 3, 4);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing length squared works correctly
        assertEquals(25, vector.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        Vector vector = new Vector(0, 3, 4);

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing length works correctly
        assertEquals(5, vector.length(), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(-1, -2, -3);
        Vector u = v.normalize();

        // ============ Equivalence Partitions Tests ==============

        //TC01: testing that the vector is a unit vector
        assertEquals(0, u.length() - 1, "ERROR: the normalized vector is not a unit vector");

        //TC02: testing that the dot product of the normalized vector is just a unit vector of the 1st vector
        assertTrue(v.dotProduct(u) >= 0, "ERROR: the normalized vector is opposite to the original one");

        //TC03: the normalized vector is parallel to the original (which will throw an exception in the ctor when trying to make a zero vector from the cross product)
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
    }
}
