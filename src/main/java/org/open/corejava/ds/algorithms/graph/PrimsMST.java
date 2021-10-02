package org.open.corejava.ds.algorithms.graph;

import org.open.corejava.ds.Edge;
import org.open.corejava.ds.Graph;
import org.open.corejava.ds.MinHeap;
import org.open.corejava.ds.Vertex;

import java.util.*;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class PrimsMST<T> {
    public List<Edge<T>> mst(Graph<T> graph) {
        MinHeap<T> mh = new MinHeap<>();
        List<Edge<T>> result = new ArrayList<>();
        Map<T, Edge<T>> map = new HashMap<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            mh.add(vertex, Integer.MAX_VALUE);
        }

        mh.replace(vertices.iterator().next(), 0);

        Vertex<T> vertex;
        while (!mh.isEmpty()) {
            vertex = mh.pollMin();
            Edge<T> edge = map.get(vertex.getVertex());
            if (edge != null) {
                result.add(edge);
            }

            for (Vertex<T> next : graph.getNeighbors(vertex.getVertex())) {
                if (mh.contains(next.getVertex()) && mh.getWeight(next.getVertex()) > next.getWeight()) {
                    edge = new Edge<>(vertex.getVertex(), next.getVertex(), next.getWeight());
                    mh.replace(next.getVertex(), next.getWeight());
                    map.put(next.getVertex(), edge);
                }
            }
        }

        return result;
    }
}