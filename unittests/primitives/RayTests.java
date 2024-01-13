package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTests {

    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(1,1,-1), new Vector(1,2,-2));

        //TC01: t is positive
        double t = 3;
        assertEquals(new Point(2,3,-3), ray.getPoint(t), "ERROR: t is positive but point was not correct");


        //TC02: t is negative
        t = -3;
        assertEquals(new Point(0,-1,1), ray.getPoint(t), "ERROR: t is negative but point was not correct");

        // =============== Boundary Values Tests ==================
        //TC10: t is 0
        t = 0;
        assertEquals(new Point(1,1,-1), ray.getPoint(t), "ERROR: t is zero but point was not correct");

    }
}
