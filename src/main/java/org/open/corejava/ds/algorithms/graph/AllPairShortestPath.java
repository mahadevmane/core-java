package org.open.corejava.ds.algorithms.graph;

import java.util.Map;
import java.util.Set;

public class AllPairShortestPath<T> {
    public Map<T, Map<T, T>> path;
    public Map<T, Map<T, Integer>> distance;

    public AllPairShortestPath(Map<T, Map<T, T>> path, Map<T, Map<T, Integer>> distance) {
        this.path = path;
        this.distance = distance;
    }

    @Override
    public String toString() {
        Set<T> keys = path.keySet();

        StringBuilder sb = new StringBuilder("Path Matrix:\n");
        for (T key : keys) {
            sb.append("\t").append(key);
        }

        for (T key : keys) {
            sb.append("\n").append(key).append("=>\t");
            for (T parent : path.get(key).values()) {
                sb.append(parent).append("\t");
            }
        }

        sb.append("\n\nDistance Matrix:\n");
        for (T key : keys) {
            sb.append("\t").append(key);
        }

        for (T key : keys) {
            sb.append("\n").append(key).append("=>\t");
            for (int weight : distance.get(key).values()) {
                sb.append(weight).append("\t");
            }
        }

        return sb.toString();
    }
}
