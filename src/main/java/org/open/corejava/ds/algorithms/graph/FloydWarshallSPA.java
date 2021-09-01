package org.open.corejava.ds.algorithms.graph;

import org.open.corejava.ds.Graph;
import org.open.corejava.ds.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FloydWarshallSPA<T> {
    private static final int INF = Integer.MAX_VALUE;

    public AllPairShortestPath<T> shortestPath(Graph<T> graph) {
        Map<T, Map<T, T>> path = new HashMap<>();
        Map<T, Map<T, Integer>> distance = new HashMap<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            path.put(vertex, new HashMap<>());
            distance.put(vertex, new HashMap<>());

            for (T next : vertices) {
                path.get(vertex).put(next, null);
                if (vertex == next) {
                    distance.get(vertex).put(next, 0);
                } else {
                    distance.get(vertex).put(next, INF);
                }
            }
        }

        for (T vertex : vertices) {
            for (Vertex<T> next : graph.getNeighbors(vertex)) {
                path.get(vertex).put(next.getVertex(), vertex);
                distance.get(vertex).put(next.getVertex(), next.getWeight());
            }
        }

        for (T k : vertices) {
            for (T i : vertices) {
                for (T j : vertices) {
                    if (distance.get(i).get(k) != INF && distance.get(k).get(j) != INF) {
                        int weight = distance.get(i).get(k) + distance.get(k).get(j);

                        if (distance.get(i).get(j) > weight) {
                            distance.get(i).put(j, weight);
                            path.get(i).put(j, path.get(k).get(j));
                        }
                    }
                }
            }
        }

        return new AllPairShortestPath<T>(path, distance);
    }
}