package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * The base class for ray tracing algorithms.
 */
public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with the given scene.
     * @param s The scene to be rendered.
     */
    public RayTracerBase(Scene s) {
        scene = s;
    }

    /**
     * Traces a ray through the scene and calculates the color at the intersection point.
     * @param r The ray to be traced.
     * @return The color at the intersection point of the ray with the scene.
     */
    public abstract Color traceRay(Ray r);
}

