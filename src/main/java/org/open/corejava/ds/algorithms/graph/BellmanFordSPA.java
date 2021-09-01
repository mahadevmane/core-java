package org.open.corejava.ds.algorithms.graph;

import org.open.corejava.ds.Edge;
import org.open.corejava.ds.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BellmanFordSPA<T> {
    public ShortestPath<T> shortestPath(Graph<T> graph, T source) {
        Map<T, T> path = new HashMap<>();
        Map<T, Integer> distance = new HashMap<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            distance.put(vertex, Integer.MAX_VALUE);
        }

        distance.put(source, 0);
        path.put(source, null);

        Set<Edge<T>> edges = graph.getEdges();
        for (int i = 1; i < vertices.size(); i++) {
            for (Edge<T> edge : edges) {
                if (distance.get(edge.getU()) != Integer.MAX_VALUE) {
                    int weight = distance.get(edge.getU()) + edge.getWeight();
                    if (distance.get(edge.getV()) > weight) {
                        distance.put(edge.getV(), weight);
                        path.put(edge.getV(), edge.getU());
                    }
                }
            }
        }

        return new ShortestPath<>(path, distance);
    }
}