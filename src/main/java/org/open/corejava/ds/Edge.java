package org.open.corejava.ds;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class Edge<T> implements Comparable<Edge<T>>, Cloneable {
    private T u, v;
    private int weight;

    public Edge(T u, T v, int w) {
        this.u = u;
        this.v = v;
        this.weight = w;
    }

    public T getU() {
        return u;
    }

    public void setU(T u) {
        this.u = u;
    }

    public T getV() {
        return v;
    }

    public void setV(T v) {
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<T> obj) {
        if (this.weight == obj.weight) {
            if (this.u.equals(obj.u))
                return this.v.hashCode() - obj.v.hashCode();
            return this.u.hashCode() - obj.u.hashCode();
        }

        return this.weight - obj.weight;
    }

    @Override
    public String toString() {
        return u + "-" + v + ":" + weight;
    }
}
