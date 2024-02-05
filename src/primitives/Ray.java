package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Represents a ray in a three-dimensional (3D) space defined by a starting point and a direction vector.
 * This class models the behavior and attributes of a ray used in 3D graphics and geometry.
 */
public class Ray {
    private final Point head; // The starting point of the ray
    private final Vector direction; // The direction vector of the ray

    /**
     * Constructs a Ray object initialized with a specified starting point and direction vector.
     *
     * @param startingPoint   The starting point of the ray.
     * @param directionVector The direction vector of the ray.
     */
    public Ray(Point startingPoint, Vector directionVector) {
        this.head = startingPoint;
        this.direction = directionVector.normalize(); // Normalizes the direction vector
    }

    /**
     * Finds the closest intersection point of the ray with a list of intersection points.
     *
     * @param intersections A list of intersection points.
     * @return The closest intersection point to the head of the ray, or {@code null} if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {

        if (intersections.isEmpty())
            return null;

        GeoPoint closestPoint = intersections.getFirst();
        for (GeoPoint p : intersections)
            if (p.point.distance(getHead()) < closestPoint.point.distance(getHead())) {
                closestPoint = p;
            }

        return closestPoint;
    }


    /**
     * Finds the closest point to the head of the ray from a list of points.
     *
     * @param intersections list of points
     * @return the closest point to the head of the ray
     */
    public Point findClosestPoint(List<Point> intersections) {
        return intersections == null ? null
                : findClosestGeoPoint(intersections.stream()
                .map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Gets the head point of this ray.
     * @return The head point of this ray.
     */
    public Point getHead() {
        return this.head;
    }

    /**
     * Gets the direction vector of this ray.
     * @return The direction vector of this ray.
     */
    public Vector getDirection() {
        return this.direction;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param other The reference object with which to compare.
     * @return {@code true} if this object is the same as the other object; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return (other instanceof Ray obj)
                && this.head.equals(obj.head)
                && this.direction.equals(obj.direction);
    }

    /**
     * Gets the point along the ray at a given parameter value.
     * @param t The parameter value indicating the distance along the ray.
     * @return The point along the ray at the specified parameter value.
     */
    public Point getPoint(double t) {
        if (t == 0) return getHead();
        return getHead().add(this.getDirection().scale(t));
    }

}
