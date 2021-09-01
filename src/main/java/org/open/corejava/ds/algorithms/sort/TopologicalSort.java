package org.open.corejava.ds.algorithms.sort;

import org.open.corejava.ds.Graph;
import org.open.corejava.ds.Vertex;

import java.util.*;

public class TopologicalSort<T> {
    private final Deque<T> stack;

    public TopologicalSort() {
        this.stack = new ArrayDeque<>();
    }

    public Deque<T> sort(Graph<T> graph) {
        stack.clear();
        Map<T, Boolean> visited = new HashMap<>();

        Set<T> vertices = graph.getVertices();
        for (T vertex : vertices) {
            visited.put(vertex, false);
        }

        for (T vertex : vertices) {
            if (!visited.get(vertex)) {
                dfs(vertex, graph, visited);
            }
        }

        return stack;
    }

    private void dfs(T vertex, Graph<T> graph, Map<T, Boolean> visited) {
        visited.put(vertex, true);

        for (Vertex<T> next : graph.getNeighbors(vertex)) {
            if (!visited.get(next.getVertex())) {
                dfs(next.getVertex(), graph, visited);
            }
        }
        stack.push(vertex);
    }
}
