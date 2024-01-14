package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The {@code Geometries} class represents a collection of intersectable geometries.
 * It implements the {@code Intersectable} interface, allowing it to find intersections
 * with a given ray.
 */
public class Geometries implements Intersectable {

    // List to store intersectable geometries
    private final List<Intersectable> list = new LinkedList<>();

    /**
     * Default constructor for the Geometries class.
     */
    public Geometries() { }

    /**
     * Constructor that initializes the Geometries with an array of intersectable geometries.
     *
     * @param geometries Array of intersectable geometries to be added.
     */
    public Geometries(Intersectable... geometries) {
        this.add(geometries);
    }

    /**
     * Adds intersectable geometries to the collection.
     *
     * @param geometries Array of intersectable geometries to be added.
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(list, geometries);
    }

    /**
     * Finds intersections of a given ray with the collection of geometries.
     *
     * @param ray The ray for which intersections are to be found.
     * @return A list of intersection points, or {@code null} if no intersections occur.
     */
    public List<Point> findIntersections(Ray ray) {
        List<Point> res = null;
        for (Intersectable in : list) {
            List<Point> l = in.findIntersections(ray);
            if (l != null) {
                if (res == null) res = new LinkedList<>();
                res.addAll(l);
            }
        }
        return res;
    }
}
