package org.open.corejava.ds.algorithms.graph;

import org.open.corejava.ds.DisjointSet;
import org.open.corejava.ds.Edge;
import org.open.corejava.ds.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class KruskalsMST<T> {
    public List<Edge<T>> mst(Graph<T> graph) {
        List<Edge<T>> result = new ArrayList<>();
        DisjointSet<T> ds = new DisjointSet<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            ds.makeSet(vertex);
        }

        Set<Edge<T>> edges = graph.getEdges();
        for (Edge<T> edge : edges) {
            T set1 = ds.findSet(edge.getU());
            T set2 = ds.findSet(edge.getV());

            if (set1 != set2) {
                result.add(edge);
                ds.union(set1, set2);
            }
        }

        return result;
    }
}