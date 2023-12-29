package geometries;
import primitives.*;

public class Triangle extends Polygon {
    /**
     * Triangle Constructor using parent ctor from Polygon, takes 3 points and makes a triangle
     * @param a 1st point of triangle
     * @param b 2nd point on triangle
     * @param c 3rd point of triangle
     */
    public Triangle(Point a, Point b, Point c) {
        super(a, b, c);
    }
}

