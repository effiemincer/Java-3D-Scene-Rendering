package geometries;

import primitives.*;

import java.util.LinkedList;
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

        //First check if there are any intersections with the plane the triangle is sitting on
        List<Point> res = plane.findIntersections(ray);
        if (res == null)
            return null;

        Vector rayDirection = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(ray.getHead());
        Vector v2 = vertices.get(1).subtract(ray.getHead());
        Vector v3 = vertices.get(2).subtract(ray.getHead());

        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();

        double res1 = rayDirection.dotProduct(n1);
        double res2 = rayDirection.dotProduct(n2);
        double res3 = rayDirection.dotProduct(n3);

        if (res1 == 0 || res2 == 0 || res3 == 0)
            return null;

        if ((res1 > 0 && res2 > 0 && res3 > 0) || (res1<0 && res2 < 0 && res3 < 0))
            return res;
        else
            return null;
    }
}


