package primitives;

public class Point {

    final double ZERO = 0;
    protected Double3 point;

    protected Point(Double3 point) {
        this.point = point;
    }

    public Point(double d1, double d2, double d3) {
        this.point = new Double3(d1, d2, d3);
    }

    Vector subtract(Point other) {
        return new Vector(this.point.subtract(other.point));
    }

    Point add(Vector vector) {
        return new Vector(this.point.subtract(vector.point));
    }

    double distance(Point other) {
        double xDistance = other.point.d1 - this.point.d1;
        double yDistance = other.point.d2 - this.point.d2;
        double zDistance = other.point.d3 - this.point.d3;

        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance) + (zDistance * zDistance));
        return distance;
    }

    double distanceSquared(Point other) {
        double distance = this.distance(other);
        return distance * distance;
    }
}