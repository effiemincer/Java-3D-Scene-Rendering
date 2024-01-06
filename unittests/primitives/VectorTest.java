package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 * @author Effie Mincer and Yehuda Gurovich
 */
class VectorTest {


    /**
     * Test method for constructing a zero vector.
     */
    @Test
    void testZeroVectorConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: zero vector does not throw an exception");
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), "ERROR: zero vector does not throw an exception");
    }


    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void testAdd() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2         = new Vector(-2, -4, -6);

        assertThrows(IllegalArgumentException.class, () -> v1.add(v1Opposite),"ERROR: Vector + -itself does not throw an exception" );
        assertEquals(v1.add(v2), v1Opposite,"ERROR: Vector + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Vector)}.
     */
    @Test
    void testSubtract() {
        Vector vector1 = new Vector(1, 2, 3);
        Vector vector2 = new Vector(2, 3, 4);

        //TODO: add exception to subtract function
        assertThrows(IllegalArgumentException.class, () -> vector1.subtract(vector1), "ERROR: Vector - itself does not throw an exception");
        assertEquals(vector2.subtract(vector1), new Vector(1, 1, 1), "ERROR: Vector - Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        Vector vector = new Vector(1, 1, 1);
        double scalingValue = 2;
        double zero = 0;


        Vector resultVector = new Vector(vector.xyz.scale(scalingValue));

        assertEquals(resultVector, new Vector(2, 2, 2));
        assertThrows(IllegalArgumentException.class, () -> vector.scale(0), "ERROR: the scaling value cannot be zero");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {

        Vector vector1 = new Vector(1, -1, 1);
        Vector vector2 = new Vector(2, 2, 2);
        Vector vector3 = new Vector(0, 1, -1);
        Vector vector4 = new Vector(1, 0, 0);


        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
        Vector v3         = new Vector(0, 3, -2);

        //test cases for dotProduct
        assertEquals(v1.dotProduct(v3), 0, "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(v1.dotProduct(v2) + 28, 0,"ERROR: dotProduct() wrong value");

        /*
        // Check for different vectors
        assertEquals(vector1.dotProduct(vector2), 2, "ERROR: dotProduct() wrong value");
        // Check for same vector
        assertEquals(vector1.dotProduct(vector1), 3, "ERROR: dotProduct() wrong value");
*/
        // Check for ortholinearity
        //assertThrows(IllegalArgumentException.class, () -> vector3.dotProduct(vector4), "ERROR: dotProduct() for orthogonal vectors is not zero");


    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        /*
        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = new Vector(2, 2, 2);
        Vector vector3 = new Vector(1, 0, 0);
         */

        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
        Vector v3         = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        //test cases for crossProduct based on main from stage0
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "ERROR: crossProduct() for parallel vectors does not throw an exception");
        assertTrue(Util.isZero(vr.length() - v1.length() * v3.length()), "ERROR: crossProduct() wrong result length");
        assertEquals(vr.dotProduct(v1), 0,"ERROR: crossProduct() result is not orthogonal to its operands" );
        assertEquals(vr.dotProduct(v3), 0, "ERROR: crossProduct() result is not orthogonal to its operands");

        //assertEquals(vector1.crossProduct(vector3), new Vector(0, 1, -1), "ERROR: crossProduct() wrong result length");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector vector = new Vector(0, 3, 4);

        //test cases for lengthSquared
        assertEquals(vector.lengthSquared(), 25, "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        Vector vector = new Vector(0, 3, 4);

        //test case for length
        assertEquals(vector.length(), 5, "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(-1, -2, -3);
        Vector u = v.normalize();

        // Test cases for vector normalization
        assertEquals(u.length() -1, 0 , "ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
        assertTrue(v.dotProduct(u) >= 0, "ERROR: the normalized vector is opposite to the original one");
    }
}
