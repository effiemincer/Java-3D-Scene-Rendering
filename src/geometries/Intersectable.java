package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The {@code Intersectable} interface represents an object that can be intersected by a ray.
 * Implementing classes must provide a method to find intersections with a given ray.
 */
public interface Intersectable {

    /**
     * Finds intersections of the object with a given ray.
     *
     * @param ray The ray for which intersections are to be found.
     * @return A list of intersection points, or an empty list if no intersections occur.
     */
    List<Point> findIntersections(Ray ray);
}

