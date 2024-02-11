package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The {@code Intersectable} interface represents an object that can be intersected by a ray.
 * Implementing classes must provide a method to find intersections with a given ray.
 */
public abstract class Intersectable {

    /**
     * Finds intersections of the object with a given ray.
     *
     * @param ray The ray for which intersections are to be found.
     * @return A list of intersection points, or an empty list if no intersections occur.
     */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Uses the helper function to return the intersections for an array and a geometry
     *
     * @param ray The ray for which intersections are to be found.
     * @return A list of intersection points, or an empty list if no intersections occur.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * A class to represent a point in a 3D space.
     * It contains a {@code Geometry} object and a {@code Point} object.
     * This class is used to store the geometry and the point of intersection of a ray with the geometry.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor that initializes the {@code Geometry} and the {@code Point} of intersection.
         *
         * @param geometry The geometry of intersection.
         * @param point    The point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Equals method to compare between two {@code GeoPoint} objects.
         *
         * @param obj The object to compare to
         * @return {@code true} if the object is equal to this {@code GeoPoint}, {@code false} otherwise
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof GeoPoint other)
                    && this.geometry.equals(other.geometry)
                    && this.point.equals(other.point);
        }

        /**
         * ToString method to use for debugging.
         *
         * @return The {@code GeoPoint} object as a string
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

    }

}

