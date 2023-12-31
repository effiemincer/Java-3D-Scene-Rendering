package primitives;

public class Vector extends Point {

    final double ZERO = 0;

    Vector(double d1, double d2, double d3) {

        super(d1, d2, d3);
        checkForZeroVector(d1, d2, d3);

    }

    Vector(Double3 point) {
        super(point);
    }

    private void checkForZeroVector(double d1, double d2, double d3) {
        // TODO: Check for syntax of new Double3(d1, d2, d3)
        if (new Double3(d1, d2, d3).equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector Zero is not allowed");
    }

    private void checkForZero(double value) {
        if (value == ZERO)
            throw new IllegalArgumentException("Dividing by Zero is not allowed");
    }

    Vector add(Vector other) {
        return new Vector(this.point.add(other.point));
    }

    Vector scale(double scalingValue) {
        return new Vector(this.point.scale(scalingValue));
    }

    double dotProduct(Vector other) {
        double xProduct = this.point.d1 * other.point.d1;
        double yProduct = this.point.d2 * other.point.d2;
        double zProduct = this.point.d3 * other.point.d3;
        return xProduct + yProduct + zProduct;
    }

    public Vector crossProduct(Vector other) {
        double x = this.point.d2 * other.point.d3 - this.point.d3 * other.point.d2;
        double y = this.point.d3 * other.point.d1 - this.point.d1 * other.point.d3;
        double z = this.point.d1 * other.point.d2 - this.point.d2 * other.point.d1;
        return new Vector(x, y, z);
    }

    double lengthSquared() {
        return this.dotProduct(this);
    }

    double length() {
        return Math.sqrt(this.lengthSquared());
    }

    Vector normalize() {
        double length = this.length();
        this.checkForZero(length);

        double xNormalized = this.point.d1 / length;
        double yNormalized = this.point.d2 / length;
        double zNormalized = this.point.d3 / length;

        return new Vector(xNormalized, yNormalized, zNormalized);
    }
}
