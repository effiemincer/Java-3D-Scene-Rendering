package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.MissingResourceException;
import java.util.Random;

/**
 * Represents a camera used for rendering images.
 */
public class Camera implements Cloneable {
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private Point location;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width = 0d;
    private double height = 0d;
    private double distance = 0d;
    private Point ViewPlaneCenter;

    /**
     * The total number of rays to cast for each pixel.
     */
    private int totalRays = 1;

    private Camera() {
    }

    /**
     * Gets a new builder instance for creating a camera.
     *
     * @return A new builder for constructing a camera.
     */
    public static Builder getBuilder() {
        return new Builder();
    }



    /**
     * Clones the camera object.
     *
     * @return A cloned instance of the camera.
     * @throws CloneNotSupportedException If cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Gets the location of the camera
     * @return The location of the camera.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Gets the view direction vector of the camera
     * @return The view direction vector.
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets the up vector of the camera.
     * @return The up vector.
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets the right vector of the camera.
     * @return The right vector.
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     * @param interval The interval between grid lines.
     * @param color    The color of the grid lines.
     */
    public Camera printGrid(int interval, Color color) {
        //vertical line coloring
        for (int j = 0; j < imageWriter.getNx(); j += interval) {
            for (int i = 0; i < imageWriter.getNy(); i++) {
                imageWriter.writePixel(j, i, color);
            }
        }
        //horizontal line coloring
        for (int j = 0; j < imageWriter.getNx(); j++) {
            for (int i = 0; i < imageWriter.getNy(); i += interval) {
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * Writes the image to the output file.
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }

    /**
     * Renders the image by casting rays through each pixel and tracing them in the scene.
     */
    public Camera renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int j = 0; j < nX; j++) {
            for (int i = 0; i < nY; i++) {
                castRay(nX, nY, j, i);
            }
        }
        return this;
    }

    /**
     * Casts a ray through a specific pixel and calculates the color based on ray tracing.
     *
     * @param Nx The width of the image.
     * @param Ny The height of the image.
     * @param j  The y-coordinate of the pixel.
     * @param i  The x-coordinate of the pixel.
     */
    private void castRay(int Nx, int Ny, int j, int i) {
        Color avgColor = Color.BLACK;
        //super sample for the total number of rays
        for (int k = 0; k < totalRays; k++) {
            avgColor = avgColor.add(rayTracer.traceRay(constructRay(Nx, Ny, j, i, this.totalRays)));
        }

        //divide the total color by the number of rays to get the average color
        imageWriter.writePixel(j, i, avgColor.reduce(this.totalRays));
    }

    /**
     * Constructs a ray for a given pixel in the image.
     *
     * @param Nx The width of the image.
     * @param Ny The height of the image.
     * @param j  The index by width.
     * @param i  The index by height.
     * @return The constructed ray.
     */
    public Ray constructRay(int Nx, int Ny, int j, int i, int totalRays) {

        // Calculate the pixel dimensions (quadrupling resolution for super sampling)
        double Rx = height / Nx;
        double Ry = width / Ny;

        double randomX = 0d;
        double randomY = 0d;

        if (totalRays != 1) {
            Random rand = new Random();

            // Add random jitter to the pixel position for super sampling
            double minX = -(Rx);
            double minY = -(Ry);

            randomX = rand.nextDouble() * (-2 * minX) + minX;
            randomY = rand.nextDouble() * (-2 * minY) + minY;
        }

        // Calculate the position of the pixel on the image plane
        double xJ = (j - (Nx - 1) / 2d) * Rx + randomX;
        double yI = -(i - (Ny - 1) / 2d) * Ry + randomY;

        // Initialize the point in 3D space corresponding to the pixel
        Point pIJ = ViewPlaneCenter;

        // Adjust the point based on the horizontal position of the pixel
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));

        // Adjust the point based on the vertical position of the pixel
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        // Return the constructed ray
        return new Ray(location, pIJ.subtract(location));
    }

    /**
     * Builder class for constructing a Camera object.
     */
    public static class Builder {
        private final Camera camera;

        public Builder() {
            this.camera = new Camera();
        }

        public Builder(Camera camera) {
            this.camera = camera;
        }

        /**
         * Sets the location of the camera.
         *
         * @param point The location of the camera.
         * @return The builder instance.
         */
        public Builder setLocation(Point point) {
            this.camera.location = point;
            return this;
        }

        /**
         * Sets the direction vectors of the camera.
         *
         * @param towards The view direction vector.
         * @param upwards The up vector.
         * @return The builder instance.
         * @throws IllegalArgumentException If the vectors are not orthogonal.
         */
        public Builder setDirection(Vector towards, Vector upwards) {
            if (towards.dotProduct(upwards) != 0)
                throw new IllegalArgumentException("Vectors are not orthogonal");

            this.camera.vTo = towards.normalize();
            this.camera.vUp = upwards.normalize();

            return this;
        }

        /**
         * Sets the direction vectors of the camera.
         *
         * @param p The location of the camera.
         * @param upwards The up vector.
         * @return The builder instance.
         * @throws IllegalArgumentException If the vectors are not orthogonal.
         */
        public Builder setDirection(Point p, Vector upwards) {
            Vector towards;
            if (!p.equals(Point.ZERO))
                towards = p.subtract(this.camera.location);
            else
                towards = new Vector(0,0,-1);

            if (towards.dotProduct(upwards) != 0)
                throw new IllegalArgumentException("Vectors are not orthogonal");

            this.camera.vTo = towards.normalize();
            this.camera.vUp = upwards.normalize();

            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param w The width of the view plane.
         * @param h The height of the view plane.
         * @return The builder instance.
         * @throws IllegalArgumentException If width or height is not greater than zero.
         */
        public Builder setVpSize(double w, double h) {
            if (w <= 0 || h <= 0)
                throw new IllegalArgumentException("Width and height of view plane must both be greater than zero.");

            this.camera.width = w;
            this.camera.height = h;

            return this;
        }

        /**
         * Sets the distance to the view plane.
         *
         * @param d The distance to the view plane.
         * @return The builder instance.
         * @throws IllegalArgumentException If the distance is less than zero.
         */
        public Builder setVpDistance(double d) {
            if (d < 0)
                throw new IllegalArgumentException("Distance from view plane must be at least zero.");
            this.camera.distance = d;

            return this;
        }

        public Builder setImageWriter(ImageWriter iw) {
            this.camera.imageWriter = iw;

            return this;
        }

        public Builder setRayTracer(RayTracerBase rtb) {
            this.camera.rayTracer = rtb;

            return this;
        }
        public Builder setViewPlaneCenter(Point p) {
            this.camera.ViewPlaneCenter = p;
            return this;
        }

        public Builder setTotalRays(int totalRays) {
            this.camera.totalRays = totalRays;
            return this;
        }

        /**
         * Builds the Camera object.
         *
         * @return The constructed Camera object.
         * @throws MissingResourceException If rendering data is missing (width, height, or distance).
         * @throws RuntimeException         If cloning the camera fails.
         */
        public Camera build() {
            //Builder c = new Builder();
            final String renderDataMissing = "Rendering data is missing";
            final String cameraClass = "Camera class";

            if (this.camera.width == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Width of view plane is zero");

            if (this.camera.height == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Height of view plane is zero");

            if (this.camera.distance == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Distance to view plane is zero");

            if (this.camera.totalRays == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Number of rays per pixel is zero");

            this.camera.vRight = this.camera.vTo.crossProduct(this.camera.vUp).normalize();
            this.camera.ViewPlaneCenter = camera.location.add(camera.vTo.scale(camera.distance));

            if (this.camera.imageWriter == null)
                throw new MissingResourceException(renderDataMissing, cameraClass, "ImageWriter is null");

            if (this.camera.rayTracer == null)
                throw new MissingResourceException(renderDataMissing, cameraClass, "RayTracer is null");

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Camera cloning failed", e);
            }
        }
    }
}
