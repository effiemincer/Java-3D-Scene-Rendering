package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;

public class Blackboard {

    public LinkedList<Point> generatePointsSquare(Point center, double size, Vector vUp, Vector vRight, int numberOfRays){
        LinkedList<Point> points = new LinkedList<>();
        return;
    }
    public LinkedList<Point> generatePointsCircle(Ray originalRay, Point center, double radius, int numberOfRays){
        Vector vUp = originalRay.getDirection().findOrthogonal();
        Vector vRight = calcVRight();
        LinkedList<Point> points = new LinkedList<>();
        return;
    }

    private Vector calcVRight() {
    }


}
