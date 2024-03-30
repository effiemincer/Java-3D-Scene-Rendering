package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.LinkedList;
import java.util.MissingResourceException;
import java.util.stream.*;

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
    private int totalRays = 1;
    private int threads = 1;
    private double printInterval = 0;
    private PixelManager pixelManager;
    private boolean adaptive = false;

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
     * Gets the width of the view plane.
     * @return the width of the view plane
     */
    public int getTotalRays() {
        return totalRays;
    }

    /**
     * Gets the number of threads used for rendering.
     * @return The number of threads used for rendering.
     */
    public int getThreads() {
        return threads;
    }

    /**
     * Gets the print interval for debugging.
     * @return The print interval for debugging.
     */
    public double getPrintInterval() {
        return printInterval;
    }

    /**
     * Gets the image writer used for rendering.
     * @return The image writer used for rendering.
     */
    public RayTracerBase getRayTracer() {
        return rayTracer;
    }

    /**
     * Gets the image writer used for rendering.
     * @return The image writer used for rendering.
     */
    public boolean getAdaptive() {
        return adaptive;
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

        //single thread
        if (threads == 1) {
            for (int j = 0; j < nX; j++) {
                for (int i = 0; i < nY; i++) {
                    castRay(nX, nY, j, i);
                }
            }
        }

        //multithreading
        else{
            pixelManager = new PixelManager(nX, nY, printInterval);
//            while (threads-- > 0){
//                new Thread(() -> {
//                    for (PixelManager.Pixel pixel = pixelManager.nextPixel(); pixel != null; pixel = pixelManager.nextPixel()){
//                        castRay(nX, nY, pixel.col, pixel.row);
//                        pixelManager.pixelDone();
//                    }
//                }).start();
//            }

            IntStream.range(0,nY).parallel().forEach(i ->{
                IntStream.range(0,nX).forEach(j ->{
                    castRay(nX, nY, j, i);
                    pixelManager.pixelDone();
                    //pixelManager.printPixel();
                });
            });
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
        Ray r = constructRay(Nx, Ny, j, i);

        //if totalRays is one then don't do super Sampling
        if (totalRays == 1 && !adaptive) {
            imageWriter.writePixel(j, i, rayTracer.traceRay(r));
            return;
        }

        // Calculate the pixel dimensions
        double Rx = height / Nx;
        double Ry = width / Ny;

        // Calculate the position of the pixel on the image plane
        double xJ = (j - (Nx - 1) / 2d) * Rx;
        double yI = -(i - (Ny - 1) / 2d) * Ry;

        // Initialize the point in 3D space corresponding to the pixel
        Point pIJ = ViewPlaneCenter;

        // Adjust the point based on the horizontal position of the pixel
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));

        // Adjust the point based on the vertical position of the pixel
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        //if there is adaptive super sampling
        if (adaptive) {
            //center of the first pixel
            Color prevAvgColor = rayTracer.traceRay(r);
            //Ray centerRay = new Ray(location, pIJ.subtract(location));
            Color avgColor = Blackboard.adaptiveSuperSampling(pIJ, Rx, vUp, vRight, prevAvgColor, this, 3);
            imageWriter.writePixel(j, i, avgColor);
        }
        //No adaptive super sampling
        else {
            Color avgColor = Color.BLACK;
            LinkedList<Point> points = Blackboard.generatePointsSquare(pIJ, Rx, vUp, vRight, totalRays);
            for (Point p : points) {
                Ray ray = new Ray(location, p.subtract(location));
                avgColor = avgColor.add(rayTracer.traceRay(ray));
            }
            imageWriter.writePixel(j, i, avgColor.reduce(points.size()));
        }

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
    public Ray constructRay(int Nx, int Ny, int j, int i) {

        // Calculate the pixel dimensions
        double Rx = height / Nx;
        double Ry = width / Ny;

        // Calculate the position of the pixel on the image plane
        double xJ = (j - (Nx - 1) / 2d) * Rx;
        double yI = -(i - (Ny - 1) / 2d) * Ry;

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

        /**
         * Sets the image writer to use for rendering.
         *
         * @param iw The image writer to use.
         * @return The builder instance.
         */
        public Builder setImageWriter(ImageWriter iw) {
            this.camera.imageWriter = iw;

            return this;
        }

        /**
         * Sets the ray tracer to use for rendering.
         *
         * @param rtb The ray tracer to use.
         * @return The builder instance.
         */
        public Builder setRayTracer(RayTracerBase rtb) {
            this.camera.rayTracer = rtb;

            return this;
        }
        public Builder setViewPlaneCenter(Point p) {
            this.camera.ViewPlaneCenter = p;
            return this;
        }

        /**
         * Sets the number of rays to cast through each pixel.
         *
         * @param totalRays The number of rays to cast.
         * @return The builder instance.
         */
        public Builder setTotalRays(int totalRays) {
            this.camera.totalRays = totalRays;
            return this;
        }

        /**
         * Sets the number of threads to use for rendering.
         *
         * @param nThreads The number of threads to use.
         * @return The builder instance.
         */
        public Builder setMultiThreading(int nThreads) {
            this.camera.threads = nThreads;
            return this;
        }

        /**
         * Sets the print interval for debugging.
         *
         * @param interval The interval for printing debug information.
         * @return The builder instance.
         */
        public Builder setDebugPrint(double interval) {
            this.camera.printInterval = interval;
            return this;
        }

        /**
         * Sets the adaptive super sampling flag.
         *
         * @param adaptive The adaptive super sampling flag.
         * @return The builder instance.
         */
        public Builder setAdaptive(boolean adaptive) {
            this.camera.adaptive = adaptive;
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

            this.camera.vRight = this.camera.vTo.crossProduct(this.camera.vUp).normalize();
            this.camera.ViewPlaneCenter = camera.location.add(camera.vTo.scale(camera.distance));

            if (this.camera.imageWriter == null)
                throw new MissingResourceException(renderDataMissing, cameraClass, "ImageWriter is null");

            if (this.camera.rayTracer == null)
                throw new MissingResourceException(renderDataMissing, cameraClass, "RayTracer is null");

            if (this.camera.threads < 1)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Number of threads is below zero");

            if (this.camera.totalRays < 1)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Number of rays is below one");

            if (this.camera.printInterval < 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Debug print is below zero");


            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Camera cloning failed", e);
            }
        }
    }
}