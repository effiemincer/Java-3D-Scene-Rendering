package renderer;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.Random;

public class Blackboard {
    /**
     * Constructs a beam of rays for a given pixel in the image.
     *
     * @param Nx       The width of the image.
     * @param Ny    The height of the image.
     * @param j       The y-coordinate of the pixel.
     * @param i      The x-coordinate of the pixel.
     * @param totalRays The total number of rays to cast for each pixel.
     * @return A list of rays for the pixel.
     */
    public LinkedList<Ray> constructRayBeam(int Nx, int Ny, int j, int i, int totalRays) {
        // Calculate the pixel dimensions (quadrupling resolution for super sampling)
        double Rx = height / Nx;
        double Ry = width / Ny;

        double randomX = 0d;
        double randomY = 0d;

        // Add random jitter to the pixel position for super sampling
        double minX = -(Rx);
        double minY = -(Ry);

        LinkedList<Ray> rays = new LinkedList<>();

        for (int k = 0; k < totalRays; k++) {
            if (totalRays != 1) {
                Random rand = new Random();

                randomX = rand.nextDouble() * (-2 * minX) + minX;
                randomY = rand.nextDouble() * (-2 * minY) + minY;
            }

            // Calculate the position of the pixel on the image plane
            double xJ = (j - (Nx - 1) / 2d) * Rx + randomX;
            double yI = -(i - (Ny - 1) / 2d) * Ry + randomY;

            // Initialize the point in 3D space corresponding to the pixel
            Point pIJ = Camera.getViewPlaneCenter();

            // Adjust the point based on the horizontal position of the pixel
            if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));

            // Adjust the point based on the vertical position of the pixel
            if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

            rays.add(new Ray(location, pIJ.subtract(location)));
        }
        return rays;
    }
}
