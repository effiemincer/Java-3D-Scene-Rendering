package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Represents a scene in a ray tracing environment.
 */
public class Scene {

    /** The name of the scene. */
    public String name;

    /** The background color of the scene. */
    public Color background = Color.BLACK;

    /** The ambient light of the scene. */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The geometries present in the scene. */
    public Geometries geometries = new Geometries();

    /**
     * Constructs a new Scene object with the given name.
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     * @param background The background color to be set.
     * @return The updated Scene object.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the geometries present in the scene.
     * @param geometries The geometries to be set.
     * @return The updated Scene object.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     * @param ambientLight The ambient light to be set.
     * @return The updated Scene object.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
}
