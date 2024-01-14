package geometries;

import primitives.*;

/**
 * The {@code Geometry} interface represents a geometric shape that can be intersected by a ray.
 * It extends the {@code Intersectable} interface and provides a method to retrieve the normal vector
 * at a specified point on the shape.
 */
public interface Geometry extends Intersectable {
    /**
     * takes a point on a shape and returns the vector normal
     * @param p is a point
     * @return Vector normal to the point on the shape
     */
    Vector getNormal(Point p);

}
