package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.MissingResourceException;

public class Camera implements Cloneable {
    private Point location;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double width = 0d;
    private double height = 0d;
    private double distance = 0d;

    private Camera() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray for a given pixel in the image.
     *
     * @param Nx      The width of the image.
     * @param Ny      The height of the image.
     * @param j       The index by width.
     * @param i       The index by height.
     * @return        The constructed ray.
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        // Calculate the center point of the image plane
        Point center = location.add(vTo.scale(distance));

        // Calculate the pixel dimensions
        double Rx = height / Nx;
        double Ry = width / Ny;

        // Calculate the position of the pixel on the image plane
        double xJ = (j - (Nx - 1) / 2d) * Rx;
        double yI = -(i - (Ny - 1) / 2d) * Ry;

        // Initialize the point in 3D space corresponding to the pixel
        Point pIJ = center;

        // Adjust the point based on the horizontal position of the pixel
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));

        // Adjust the point based on the vertical position of the pixel
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        // Calculate the direction vector from the camera location to the pixel
        Vector Vij = pIJ.subtract(location);

        // Return the constructed ray
        return new Ray(location, Vij);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Point getLocation() {
        return location;
    }

    public Vector getVTo() {
        return vTo;
    }

    public Vector getVUp() {
        return vUp;
    }

    public Vector getVRight() {
        return vRight;
    }

    public static class Builder {
        private final Camera camera;

        public Builder() {
            this.camera = new Camera();
        }

        public Builder(Camera camera) {
            this.camera = camera;
        }

        public Builder setLocation(Point point) {
            this.camera.location = point;
            return this;
        }

        public Builder setDirection(Vector towards, Vector upwards) {

            // ! I'm not sure what vertical means in the instructions.
            if (towards.dotProduct(upwards) != 0)
                throw new IllegalArgumentException("Vectors are not orthogonal");

            this.camera.vTo = towards.normalize();
            this.camera.vUp = upwards.normalize();

            return this;
        }

        //set view plane size
        public Builder setVpSize(double w, double h) {

            //checking both doubles are positive
            if (w <= 0 || h <= 0)
                throw new IllegalArgumentException("Width and height of view plane must both be greater than zero.");

            this.camera.width = w;
            this.camera.height = h;

            return this;
        }

        //set view plane distance
        public Builder setVpDistance(double d) {

            //checks that distance is greater than zero
            if (d < 0 )
                throw new IllegalArgumentException("Distance from viewplane must be at least zero.");
            this.camera.distance = d;

            return this;
        }

        public Camera build() {
            final String renderDataMissing = "Rendering data is missing";
            final String cameraClass = "Camera class";

            if (this.camera.width == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Width of viewplane is zero");

            if (this.camera.height == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Height of viewplane is zero");

            if (this.camera.distance == 0)
                throw new MissingResourceException(renderDataMissing, cameraClass, "Distance to viewplane is zero");

            // ! INCORRECT EXCEPTION VALUES?

            this.camera.vRight = this.camera.vTo.crossProduct(this.camera.vUp).normalize();

            // Does this work?
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Camera cloning failed", e);
            }
        }
    }
}
