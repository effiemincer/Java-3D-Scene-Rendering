package renderer;

import primitives.*;

import java.awt.image.ColorModel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Blackboard class is a helper class for generating points on a square or a circle
 */
public class Blackboard {

    /**
     * Generate points on a square
     *
     * @param center       the center of the square
     * @param size         the size of the square
     * @param vUp          the up vector
     * @param vRight       the right vector
     * @param numberOfRays the number of rays to generate
     * @return a list of points on the square
     */
    public static LinkedList<Point> generatePointsSquare(Point center, double size, Vector vUp, Vector vRight, int numberOfRays) {
        LinkedList<Point> points = new LinkedList<>();

        //2x2, 3x3, 9x9, 17x17, 33x33
        int sqrt = (int) Math.sqrt(numberOfRays);
        double offset = 1d / ((double) sqrt - 1d);

        //starts at the top left point of the square
        Point heightPoint = center.add(vUp.scale(size / 2).add(vRight.scale(-size / 2)));
        Point widthPoint;
        //moves through the square in a grid pattern row by row
        for (int i = 0; i < sqrt; i++) {
            widthPoint = heightPoint;
            for (int j = 0; j < sqrt; j++) {
                points.add(widthPoint);
                widthPoint = widthPoint.add(vRight.scale(size * offset));
            }
            heightPoint = heightPoint.add(vUp.scale(-size * offset));
        }

        return points;
    }

    /**
     * Generate points on a circle
     *
     * @param originalRay  the original ray
     * @param center       the center of the circle
     * @param radius       the radius of the circle
     * @param numberOfRays the number of rays to generate
     * @return a list of points on the circle
     */
    public static LinkedList<Point> generatePointsCircle(Ray originalRay, Point center, double radius, int numberOfRays) {
        Vector vUp = originalRay.getDirection().findOrthogonal();
        Vector vRight = calcVRight(vUp, originalRay.getDirection());

        //LinkedList<Point> points = generatePointsSquare(center, radius * 2, vUp, vRight, numberOfRays);
        LinkedList<Point> points = generatePointsSquare(center, radius, vUp, vRight, numberOfRays);

        //exclude the point if it's not in the circle
        points.removeIf(p -> p.distance(center) > radius);

        return points;
    }


    /**
     * Calculate the right vector that is perpendicular to both vUp and direction
     *
     * @param vUp       the up vector
     * @param direction the direction vector
     * @return the right vector
     */
    private static Vector calcVRight(Vector vUp, Vector direction) {
        //create third perpendicular vector
        return direction.crossProduct(vUp).normalize();
    }


    /**
     * Adaptive super sampling
     * @param points the 4 points of the square
     * @param center the center of the square
     * @param centerColor the color of the center
     * @param size the size of the square
     * @param vUp the up vector
     * @param vRight    the right vector
     * @param camera the camera
     * @param depth the depth of the recursion
     * @return the average color of the 4 quadrants
     */
    public static Color adaptiveSuperSampling(LinkedList<Point> points, Point center, Color centerColor, double size, Vector vUp, Vector vRight, Camera camera, int depth) {

        //stop the recursion
        if (depth == 0) {
            return centerColor;
        }

        Color avgColor = Color.BLACK;

        //for each quadrant
        for (int i=0; i<4; i++) {
            LinkedList<Point> newPoints = new LinkedList<>();
            Ray ray = new Ray(camera.getLocation(), points.get(i).subtract(camera.getLocation()));
            Color color = camera.getRayTracer().traceRay(ray);

            // if the color is not similar, we need to split the square into 4 smaller squares
            if (!Color.isColorSimilar(centerColor, color) ) {
            //if (!centerColor.equals(color)) {
                // Quadrant 1
                if (i == 0) {
                    newPoints.add(points.get(0));
                    newPoints.add(center.add(vUp.scale(size / 2)));
                    newPoints.add(center.add(vRight.scale(-size / 2)));
                    newPoints.add(center);

                    Point newCenter = center.add(vUp.scale(size / 4).add(vRight.scale(-size / 4)));
                    centerColor = camera.getRayTracer().traceRay(new Ray(camera.getLocation(), newCenter.subtract(camera.getLocation())));
                    avgColor = avgColor.add(adaptiveSuperSampling(newPoints, newCenter, centerColor, size / 2, vUp, vRight, camera, depth - 1));
                }

                // Quadrant 2
                else if (i == 1) {
                    newPoints.add(center.add(vUp.scale(size / 2)));
                    newPoints.add(points.get(1));
                    newPoints.add(center);
                    newPoints.add(center.add(vRight.scale(size / 2)));

                    Point newCenter = center.add(vUp.scale(size / 4).add(vRight.scale(size / 4)));
                    centerColor = camera.getRayTracer().traceRay(new Ray(camera.getLocation(), newCenter.subtract(camera.getLocation())));
                    avgColor = avgColor.add(adaptiveSuperSampling(newPoints, newCenter, centerColor, size / 2, vUp, vRight, camera, depth - 1));
                }

                // Quadrant 3
                else if (i == 2) {
                    newPoints.add(center.add(vRight.scale(-size / 2)));
                    newPoints.add(center);
                    newPoints.add(points.get(2));
                    newPoints.add(center.add(vUp.scale(-size / 2)));
                    Point newCenter = center.add(vUp.scale(-size / 4).add(vRight.scale(-size / 4)));
                    centerColor = camera.getRayTracer().traceRay(new Ray(camera.getLocation(), newCenter.subtract(camera.getLocation())));
                    avgColor = avgColor.add(adaptiveSuperSampling(newPoints, newCenter, centerColor, size / 2, vUp, vRight, camera, depth - 1));

                }

                // Quadrant 4
                else {
                    newPoints.add(center);
                    newPoints.add(center.add(vRight.scale(size / 2)));
                    newPoints.add(center.add(vUp.scale(-size / 2)));
                    newPoints.add(points.get(3));

                    Point newCenter = center.add(vUp.scale(-size / 4).add(vRight.scale(size / 4)));
                    centerColor = camera.getRayTracer().traceRay(new Ray(camera.getLocation(), newCenter.subtract(camera.getLocation())));
                    avgColor = avgColor.add(adaptiveSuperSampling(newPoints, newCenter, centerColor, size / 2, vUp, vRight, camera, depth - 1));

                }
            }
            // if the color is similar, we can just add the color to the average
            else
                avgColor = avgColor.add(color);
        }

        // return the average color of the 4 quadrants
        return avgColor.reduce(4);
    }

}
