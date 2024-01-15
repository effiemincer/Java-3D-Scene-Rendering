package primitives;

/**
 * Represents a three-dimensional point in space with x, y, and z coordinates.
 */
public class Point {

    /**
     * The zero point, which represents the origin (0, 0, 0) in three-dimensional space.
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * The coordinates of the point in three-dimensional space.
     */
    protected final Double3 xyz;

    /**
     * Constructs a Point object with the specified coordinates.
     *
     * @param point The three-dimensional coordinates of the point.
     */
    protected Point(Double3 point) {
        this.xyz = point;
    }

    /**
     * Constructs a Point object with three individual coordinates.
     *
     * @param d1 The x-coordinate.
     * @param d2 The y-coordinate.
     * @param d3 The z-coordinate.
     */
    public Point(double d1, double d2, double d3) {
        this.xyz = new Double3(d1, d2, d3);
    }

    public Double3 getXyz() {
        return xyz;
    }

    /**
     * Calculates the vector resulting from subtracting another point.
     *
     * @param other The point to subtract.
     * @return The resulting vector.
     */

    public Vector subtract(Point other) {
        if (this.equals(other)) throw new IllegalArgumentException("Can't subtract a point from itself");
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Calculates a new point by adding a vector to the current point.
     *
     * @param vector The vector to add.
     * @return The resulting point.
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point.
     * @return The distance between the points.
     */
    public double distance(Point other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param other The other point.
     * @return The squared distance between the points.
     */
    public double distanceSquared(Point other) {
        double xDistance = other.xyz.d1 - this.xyz.d1;
        double yDistance = other.xyz.d2 - this.xyz.d2;
        double zDistance = other.xyz.d3 - this.xyz.d3;
        return (xDistance * xDistance) + (yDistance * yDistance) + (zDistance * zDistance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

    /**
     * Returns a string representation of this Point object.
     *
     * @return A string representation of the coordinates of this Point object.
     */
    public String toString() {
        return xyz.toString();
    }
}
