package primitives;

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


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return (other instanceof Ray obj)
                && this.head.equals(obj.head)
                && this.direction.equals(obj.direction);
    }
}
