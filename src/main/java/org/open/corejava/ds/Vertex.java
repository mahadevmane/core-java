package org.open.corejava.ds;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class Vertex<T> {
    private T vertex;
    private int weight;

    public Vertex(T v, int w) {
        this.vertex = v;
        this.weight = w;
    }

    public T getVertex() {
        return vertex;
    }

    public void setVertex(T vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return vertex + ":" + weight;
    }
}
