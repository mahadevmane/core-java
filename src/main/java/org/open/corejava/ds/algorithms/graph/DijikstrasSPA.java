package org.open.corejava.ds.algorithms.graph;

import org.open.corejava.ds.Graph;
import org.open.corejava.ds.MinHeap;
import org.open.corejava.ds.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DijikstrasSPA<T> {
    public ShortestPath<T> shortestPath(Graph<T> graph, T source) {
        MinHeap<T> mh = new MinHeap<>();
        Map<T, T> path = new HashMap<>();
        Map<T, Integer> distance = new HashMap<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            mh.add(vertex, Integer.MAX_VALUE);
        }

        mh.replace(source, 0);
        path.put(source, null);
        while (!mh.isEmpty()) {
            Vertex<T> current = mh.pollMin();
            distance.put(current.getVertex(), current.getWeight());

            source = current.getVertex();
            for (Vertex<T> vertex : graph.getNeighbors(source)) {
                if (mh.contains(vertex.getVertex())) {
                    int weight = distance.get(source) + vertex.getWeight();
                    if (mh.getWeight(vertex.getVertex()) > weight) {
                        mh.replace(vertex.getVertex(), weight);
                        path.put(vertex.getVertex(), source);
                    }
                }
            }
        }

        return new ShortestPath<>(path, distance);
    }
}