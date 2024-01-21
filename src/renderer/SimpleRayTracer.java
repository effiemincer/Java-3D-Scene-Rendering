package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public class SimpleRayTracer extends RayTracerBase {

    public SimpleRayTracer(Scene s) {
        super(s);
    }

    @Override
    public Color traceRay(Ray r) {
        return null;
    }

}
