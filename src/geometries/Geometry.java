package geometries;

import java.awt.*;
import primitives.*;

/**
 *
 */
public interface Geometry {
    /**
     * takes a point on a shape and returns the vector normal
     * @param p is a point
     * @return Vector normal to the point on the shape
     */
    Vector GetNormal(Point p);

}
