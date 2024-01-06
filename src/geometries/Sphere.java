package geometries;
import primitives.*;

/**
 * Represents a sphere in a three-dimensional (3D) space, a type of radial geometry defined by a radius and a center point.
 * The sphere extends the RadialGeometry abstract class.
 */
public class Sphere extends RadialGeometry {
    private final Point center; // The center point of the sphere

    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point p){
        // To be implemented
        // If it intersects at the point (x,y,z) and the centre of the sphere is at (l,m,n) then I am reading that the normal
        // to the point of intersection on the sphere is: N = ( (x-l)/r , (y-m)/r , (z-n)/r ) where r is the radius of the circle.
        return null; // Returns null as the normal calculation is not implemented
    }
}
