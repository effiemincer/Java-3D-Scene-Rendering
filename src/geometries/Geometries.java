package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private final List<Intersectable> list = new LinkedList<>();

    public Geometries() { }

    public Geometries(Intersectable... geometries) {
        this.add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(list, geometries);
    }

    public List<Point> findIntersections(Ray ray) {
        List<Point> res = null;
        for (Intersectable in : list) {
            List<Point> l = in.findIntersections(ray);
            if (l != null) {
                if (res == null) res = new LinkedList<>();
                res.addAll(l);
            }
        }
        return res;
    }
}
