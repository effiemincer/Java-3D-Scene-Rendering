package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        //TODO: I don't know how to calculate the normal of a tube
        // ============ Equivalence Partitions Tests ==============

        Ray axis = new Ray(new Point(0,0,0), new Vector(1,0,0));
        double radius = 1;
        Tube tube = new Tube(radius, axis);

        //TC01: testing the calculation for getNormal
        assertEquals(tube.getNormal(new Point(0,1,0)), (new Vector(0,1,0)).normalize(), "ERROR: calculation of normal is incorrect");

        // =============== Boundary Values Tests ==================
        //TC10: when the connection between the point on the body and the ray’s head creates a 90 degrees with the ray
        //Todo
    }
}