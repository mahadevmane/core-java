package org.open.corejava.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinHeap<T> {
    private final Map<T, Integer> map;
    private final List<Vertex<T>> heap;
    private int index;

    public MinHeap() {
        this.index = -1;
        this.map = new HashMap<>();
        this.heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public boolean contains(T node) {
        return map.containsKey(node);
    }

    public int getWeight(T vertex) {
        int pos = map.get(vertex);

        return heap.get(pos).getWeight();
    }

    public Vertex<T> min() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public void add(T node, int weight) {
        this.index++;
        map.put(node, this.index);
        heap.add(this.index, new Vertex<T>(node, weight));

        /* Maintain heap property */
        maintainHeap(node);
    }

    public void replace(T node, int weight) {
        int pos = map.get(node);
        heap.get(pos).setWeight(weight);

        /* Maintain heap property */
        maintainHeap(node);
    }

    private void maintainHeap(T node) {
        int cp = map.get(node);
        int pp = (cp - 1) / 2;

        Vertex<T> cv = heap.get(cp);
        Vertex<T> pv = heap.get(pp);

        while (pv.getWeight() > cv.getWeight()) {
            heap.set(cp, pv);
            heap.set(pp, cv);

            map.put(pv.getVertex(), cp);
            map.put(cv.getVertex(), pp);

            cp = pp;
            pp = (cp - 1) / 2;

            cv = heap.get(cp);
            pv = heap.get(pp);
        }
    }

    public Vertex<T> pollMin() {
        if (heap.isEmpty()) {
            return null;
        }

        /* Remove max and maintain heap property */
        Vertex<T> min = heap.get(0);
        Vertex<T> last = heap.get(this.index);
        heap.set(0, last);
        map.put(last.getVertex(), 0);

        heap.remove(this.index);
        map.remove(min.getVertex());
        this.index--;

        if (this.index > 0) {
            int pp = 0, cp, c1p = (2 * pp) + 1, c2p = c1p + 1;

            Vertex<T> c1 = c1p <= this.index ? heap.get(c1p) : null;
            Vertex<T> c2 = c2p <= this.index ? heap.get(c2p) : null;

            Vertex<T> pv = heap.get(pp);
            Vertex<T> cv = c2 == null ? c1 : (c1.getWeight() < c2.getWeight() ? c1 : c2);

            while (cv != null && pv.getWeight() > cv.getWeight()) {
                cp = map.get(cv.getVertex());
                heap.set(cp, pv);
                heap.set(pp, cv);

                map.put(pv.getVertex(), cp);
                map.put(cv.getVertex(), pp);

                pp = cp;
                c1p = (2 * pp) + 1;
                c2p = c1p + 1;

                c1 = c1p <= this.index ? heap.get(c1p) : null;
                c2 = c2p <= this.index ? heap.get(c2p) : null;

                pv = heap.get(pp);
                cv = c2 == null ? c1 : (c1.getWeight() < c2.getWeight() ? c1 : c2);
            }
        }

        return min;
    }
}