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

    /*
    nX: width, nY: height, j: index by width,i: index by height
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        Point center = location.add(vTo.scale(distance));

        double Rx = height / Nx;
        double Ry = width / Ny;

        double xJ = (j - (Nx - 1) / 2d) * Rx;
        double yI = -(i - (Ny - 1) / 2d) * Ry;

        Point pIJ = center;

        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));

        Vector Vij = pIJ.subtract(location);

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

        public Builder setVpSize(double w, double h) {
            this.camera.width = w;
            this.camera.height = h;

            return this;
        }

        public Builder setVpDistance(double d) {
            this.camera.distance = d;

            return this;
        }

        public Camera build() {
            if (this.camera.width == 0)
                throw new MissingResourceException("Rendering width data is missing", "Camera", "width");

            if (this.camera.height == 0)
                throw new MissingResourceException("Rendering height data is missing", "Camera", "height");

            if (this.camera.distance == 0)
                throw new MissingResourceException("Rendering distance data is missing", "Camera", "distance");

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
