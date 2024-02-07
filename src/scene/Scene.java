package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.*;

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
     * The lights present in the scene.
     */
    public List<LightSource> lights = new LinkedList<LightSource>();

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

    /**
     * Sets the lights present in the scene.
     *
     * @param lights The lights to be set.
     * @return The updated Scene object.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
