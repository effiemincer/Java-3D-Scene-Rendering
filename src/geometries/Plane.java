package geometries;

import primitives.*;

public class Plane implements Geometry{
    private final Point q;
    private final Vector normal;

    /**
     * ctor with 3 points, finds normal and saves 1 point as reference
     * @param a
     * @param b
     * @param c
     */
    Plane(Point a, Point b, Point c){
        normal = null;
        q = a;
    }

    /**
     * Ctor which accepts point and vector param
     * @param p reference point for plane
     * @param n normal vector
     */
    Plane(Point p, Vector n){
        q = p;
        normal = n;
    }

    /**
     * returns the normal of the plane
     * @return Vector normal
     */
    public Vector GetNormal() {
        return normal;
    }

    /**
     * returns the normal of the plane
     * @return a Vector normal
     */
    @Override
    public Vector GetNormal(Point p) {
        //must implement more I think
        return normal;
    }

}
