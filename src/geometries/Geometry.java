package geometries;

import primitives.*;

/**
 * The {@code Geometry} interface represents a geometric shape that can be intersected by a ray.
 * It extends the {@code Intersectable} interface and provides a method to retrieve the normal vector
 * at a specified point on the shape.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color of the geometry
     */
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * @return the color of the geometry
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the color of the geometry.
     *
     * @param emission the color of the geometry
     * @return the geometry itself
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material the material of the geometry
     * @return the geometry itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the material of the geometry.
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * takes a point on a shape and returns the vector normal
     * @param p is a point
     * @return Vector normal to the point on the shape
     */
    public abstract Vector getNormal(Point p);

}
