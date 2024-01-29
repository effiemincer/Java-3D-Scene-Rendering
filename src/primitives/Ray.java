package primitives;

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
     * Finds the point closest to the head point in the given list of points.
     * @param pointList The list of points from which to find the closest point.
     * @return The point closest to the head point, or {@code null} if the list is empty.
     */
    public Point findClosestPoint(List<Point> pointList) {

        if (pointList.isEmpty())
            return null;

        Point closestPoint = pointList.getFirst();
        for (Point p : pointList)
            if (p.distance(getHead()) < closestPoint.distance(getHead())) {
                closestPoint = p;
            }

        return closestPoint;
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
