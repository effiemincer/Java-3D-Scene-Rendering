package geometries;

import primitives.*;

import java.util.List;

/**
 * Represents a Triangle, a type of Polygon, defined by three points.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle object with three specified points.
     *
     * @param a The first point of the triangle.
     * @param b The second point of the triangle.
     * @param c The third point of the triangle.
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);
    }

    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}


