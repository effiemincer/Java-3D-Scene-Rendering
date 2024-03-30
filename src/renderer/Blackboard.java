package renderer;

import primitives.*;

import java.awt.image.ColorModel;
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

        LinkedList<Point> points = generatePointsSquare(center, radius * 2, vUp, vRight, numberOfRays);

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

    public static Color adaptiveSuperSampling(Point center, double size, Vector vUp, Vector vRight, Color prevAvgColor, Camera camera, int depth) {
        //stop the recursion
        if (depth == 0)
            return camera.getRayTracer().traceRay(new Ray(camera.getLocation(), center.subtract(camera.getLocation())));

        LinkedList<Point> points = generatePointsSquare(center, size, vUp, vRight, 4);

        Color avgColor = prevAvgColor;
        for (int i=0; i<4; i++) {
            Ray ray = new Ray(camera.getLocation(), points.get(i).subtract(camera.getLocation()));
            Color color = camera.getRayTracer().traceRay(ray);
            if (prevAvgColor == color)
                avgColor = avgColor.add(color);
            else {
                if (i==0)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(-size / 4).add(vRight.scale(-size / 4))), size / 2, vUp, vRight, color, camera, depth - 1));
                else if (i==1)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(-size / 4).add(vRight.scale(size / 4))), size / 2, vUp, vRight, color, camera, depth - 1));
                else if (i==2)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(size / 4).add(vRight.scale(-size / 4))), size / 2, vUp, vRight, color, camera, depth - 1));
                else
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(size / 4).add(vRight.scale(size / 4))), size / 2, vUp, vRight, color, camera, depth - 1));
            }
        }

        return avgColor.reduce(5);




        //LinkedList<Point> quad1 = generatePointsSquare(center.add(vUp.scale(-size / 4).add(vRight.scale(-size / 4))), size, vUp, vRight, 4);
//            LinkedList<Point> quad4 = generatePointsSquare(center.add(vUp.scale(size / 4).add(vRight.scale(size / 4))), size, vUp, vRight, 4);
//
//            LinkedList<Point> quad2 = new LinkedList<>();
//            LinkedList<Point> quad3 = new LinkedList<>();
//
//            quad2.add(quad1.get(1));
//            quad2.add(quad1.get(3));
//            quad2.add(quad4.get(1));
//            quad2.add(quad1.getFirst().add(vUp.scale(-size)));
//
//            quad3.add(quad1.get(2));
//            quad3.add(quad1.get(3));
//            quad3.add(quad4.get(2));
//            quad2.add(quad1.getFirst().add(vRight.scale(size)));
//
//            LinkedList<LinkedList<Point>> quads = new LinkedList<>();
//            quads.add(quad1);
//            quads.add(quad2);
//            quads.add(quad3);
//            quads.add(quad4);
/*

        LinkedList<Point> points = generatePointsSquare(center, size, vUp, vRight, 9);
        Color quad1Color = Color.BLACK, quad2Color = Color.BLACK, quad3Color = Color.BLACK, quad4Color = Color.BLACK;


        int count = 0;
        for (Point p : points) {
            Ray ray = new Ray(camera.getLocation(), p.subtract(camera.getLocation()));
            Color color = camera.getRayTracer().traceRay(ray);
            if (count == 0 || count == 1 || count == 3 || count == 4)
                quad1Color = quad1Color.add(color);
            if (count == 1 || count == 2 || count == 4 || count == 5)
                quad2Color = quad2Color.add(color);
            if (count == 3 || count == 4 || count == 6 || count == 7)
                quad3Color = quad3Color.add(color);
            if (count == 4 || count == 5 || count == 7 || count == 8)
                quad4Color = quad4Color.add(color);
            count++;
        }
        LinkedList<Color> colors = new LinkedList<>();
        colors.add(quad1Color);
        colors.add(quad2Color);
        colors.add(quad3Color);
        colors.add(quad4Color);

        //if they're all the same color, return that color
//        if (colors.get(0) == colors.get(1) && colors.get(0) == colors.get(2) && colors.get(3) == colors.get(0))
//            return colors.get(0);

        //color of the center point
        Color avgColor = prevAvgColor;

        for (int i = 0; i < colors.size(); i++) {
            colors.set(i, colors.get(i).reduce(4));

            if (colors.get(i).equals(prevAvgColor))
                avgColor = avgColor.add(colors.get(i));
            else {
                //quad 1
                if (i == 0)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(-size / 4).add(vRight.scale(-size / 4))), size / 2, vUp, vRight, colors.get(i), camera, depth - 1));
                //quad2
                else if (i == 1)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(-size / 4).add(vRight.scale(size / 4))), size / 2, vUp, vRight, colors.get(i), camera, depth - 1));
                //quad3
                else if (i == 2)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(size / 4).add(vRight.scale(-size / 4))), size / 2, vUp, vRight, colors.get(i), camera, depth - 1));
                //quad4
                else if (i == 3)
                    avgColor = avgColor.add(adaptiveSuperSampling(center.add(vUp.scale(size / 4).add(vRight.scale(size / 4))), size / 2, vUp, vRight, colors.get(i), camera, depth - 1));

            }
        }
        return avgColor.reduce(5);
*/


//        //Color color,
//
//        for (LinkedList<Point> l : quads) {
//            //reset the color for each quad
//            Color quadColor = Color.BLACK;
//
//            for (Point p : l) {
//                Ray ray = new Ray(camera.getLocation(), p.subtract(camera.getLocation()));
//                quadColor = quadColor.add(camera.getRayTracer().traceRay(ray));
//                //color = camera.getRayTracer().traceRay(ray);
//
//                //if the color is the same as the previous average color, add the color to the average
////                if (color.equals(prevAvgColor))
////                    avgColor = avgColor.add(color);
////                    //if the color is different, split the square into 4 and recurse
////                else {
////                    //p = p.add(vUp.scale(-size / 4).add(vRight.scale(size / 4)));
////                    avgColor = avgColor.add(adaptiveSuperSampling(p, size / 2, vUp, vRight, color, ray, camera, depth - 1));
//               // }
//            }
//            quadColor = quadColor.reduce(4);
//
//            if (quadColor.equals(prevAvgColor))
//                avgColor = avgColor.add(quadColor);
//            else {
//                avgColor = avgColor.add(adaptiveSuperSampling(l.getFirst().add(vUp.scale(-size / 4).add(vRight.scale(-size / 4))), size / 2, vUp, vRight, quadColor, camera, depth - 1));
//            }
//        }
//
//        return avgColor.reduce(5);
    }

}
