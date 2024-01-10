package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

// TODO: ADD JAVADOC
public interface Intersectable {

    List<Point> findIntersections(Ray ray);
}
