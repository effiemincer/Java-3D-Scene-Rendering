package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;

/**
 * Blackboard class is a helper class for generating points on a square or a circle
 */
public class Blackboard {

    /**
     * Generate points on a square
     *
     * @param center the center of the square
     * @param size the size of the square
     * @param vUp the up vector
     * @param vRight the right vector
     * @param numberOfRays the number of rays to generate
     * @return a list of points on the square
     */
    public static LinkedList<Point> generatePointsSquare(Point center, double size, Vector vUp, Vector vRight, int numberOfRays) {
        LinkedList<Point> points = new LinkedList<>();

        //2x2, 3x3, 9x9, 17x17, 33x33
        int sqrt = (int) Math.sqrt(numberOfRays);
        double offset = 1d / ((double) sqrt + 1d);

        //starts at the top left point of the square
        Point heightPoint = center.add(vUp.scale(size /2).add(vRight.scale(-size /2)));
        Point widthPoint;
        //moves through the square in a grid pattern row by row
        for (int i = 0; i < sqrt; i++) {
            heightPoint = heightPoint.add(vUp.scale(-size * offset));
            widthPoint = heightPoint;
            for (int j = 0; j < sqrt; j++) {
                widthPoint = widthPoint.add(vRight.scale(size * offset));
                points.add(widthPoint);
            }
        }

            return points;
    }

    /**
     * Generate points on a circle
     *
     * @param originalRay the original ray
     * @param center the center of the circle
     * @param radius the radius of the circle
     * @param numberOfRays the number of rays to generate
     * @return a list of points on the circle
     */
    public static LinkedList<Point> generatePointsCircle(Ray originalRay, Point center, double radius, int numberOfRays){
        Vector vUp = originalRay.getDirection().findOrthogonal();
        Vector vRight = calcVRight(vUp, originalRay.getDirection());

        LinkedList<Point> points = generatePointsSquare(center, radius*2, vUp, vRight, numberOfRays);

        //exclude the point if it's not in the circle
        points.removeIf(p -> p.distance(center) > radius);

        return points;
    }


    /**
     * Calculate the right vector that is perpendicular to both vUp and direction
     *
     * @param vUp the up vector
     * @param direction the direction vector
     * @return the right vector
     */
    private static Vector calcVRight(Vector vUp, Vector direction){
        //create third perpendicular vector
        return direction.crossProduct(vUp).normalize();
    }


}
