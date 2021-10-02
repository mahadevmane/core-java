package org.open.corejava.ds;

import java.util.ArrayList;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class DirectedGraph<T> extends Graph<T> {
    @Override
    public void addEdge(T u, T v) {
        addEdge(u, v, 0);
    }

    @Override
    public void addEdge(T u, T v, int w) {
        edges.add(new Edge<>(u, v, w));

        if (!adjList.containsKey(u)) {
            adjList.put(u, new ArrayList<>());
        }

        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
        }

        adjList.get(u).add(new Vertex<>(v, w));
    }
}
