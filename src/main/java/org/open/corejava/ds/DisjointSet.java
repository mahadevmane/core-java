package org.open.corejava.ds;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
    private final Map<T, Node> map;

    public DisjointSet() {
        this.map = new HashMap<>();
    }

    public void makeSet(T vertex) {
        map.put(vertex, new Node(vertex));
    }

    public T findSet(T vertex) {
        Node node = map.get(vertex);
        if (node == null) {
            return null;
        }

        node = findSet(node);
        return node.vertex;
    }

    private Node findSet(Node node) {
        if (node == node.parent) {
            return node;
        }

        node.parent = findSet(node.parent);
        return node.parent;
    }

    public void union(T u, T v) {
        Node node1 = map.get(u);
        Node node2 = map.get(v);

        if (node1 == null || node2 == null) {
            return;
        }

        node1 = findSet(node1);
        node2 = findSet(node2);

        if (node1 != node2) {
            if (node1.rank == node2.rank) {
                node1.rank += 1;
                node2.parent = node1;
            } else {
                if (node1.rank > node2.rank) {
                    node2.parent = node1;
                } else {
                    node1.parent = node2;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Disjoint Sets:");

        for (T vertex : map.keySet()) {
            sb.append("\n" + vertex + " => " + map.get(vertex));
        }

        return sb.toString();
    }

    private class Node {
        int rank;
        T vertex;
        Node parent;

        public Node(T vertex) {
            this.rank = 0;
            this.vertex = vertex;
            this.parent = this;
        }

        @Override
        public String toString() {
            return "[" + vertex + ", " + rank + ", " + parent.vertex + "]";
        }
    }
}