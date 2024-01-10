package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: testing the normal to the sphere is correct
        Point center = new Point(0, 0, 0);
        double r = 2;
        Sphere sphere = new Sphere(r, center);
        Vector normal = sphere.getNormal(new Point(2, 0, 0));

        assertEquals(new Vector(2, 0, 0).normalize(), normal, "ERROR: normal calculation is incorrect");
    }
}