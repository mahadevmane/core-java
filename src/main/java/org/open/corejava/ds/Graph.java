package org.open.corejava.ds;

import java.util.*;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class Graph<T> {
    protected Set<Edge<T>> edges;
    protected Map<T, List<Vertex<T>>> adjList;

    public Graph() {
        this.edges = new TreeSet<>();
        this.adjList = new HashMap<>();
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Set<T> getVertices() {
        return adjList.keySet();
    }

    public List<Vertex<T>> getNeighbors(T vertex) {
        return adjList.get(vertex);
    }

    public void addEdge(T u, T v) {
        addEdge(u, v, 0);
    }

    public void addEdge(T u, T v, int w) {
        edges.add(new Edge<>(u, v, w));

        if (!adjList.containsKey(u)) {
            adjList.put(u, new ArrayList<>());
        }

        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
        }

        adjList.get(u).add(new Vertex<>(v, w));
        adjList.get(v).add(new Vertex<>(u, w));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Edges => ");

        for (Edge<T> edge : edges) {
            sb.append(edge).append("; ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n\nAdjacency List\n");

        for (T vertex : adjList.keySet()) {
            sb.append(vertex).append(" =>  ");
            for (Vertex<T> adjVertices : adjList.get(vertex)) {
                sb.append(adjVertices).append("; ");
            }

            sb.delete(sb.length() - 2, sb.length());
            sb.append("\n");
        }

        return sb.toString();
    }
}