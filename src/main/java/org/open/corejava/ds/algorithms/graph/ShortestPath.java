package org.open.corejava.ds.algorithms.graph;

import java.util.Map;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class ShortestPath<T> {
    public Map<T, T> path;
    public Map<T, Integer> distance;

    public ShortestPath(Map<T, T> path, Map<T, Integer> distance) {
        this.path = path;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Path: " + path + "\nDistance: " + distance;
    }
}