package org.open.corejava.ds.algorithms.search;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public interface Search<T> {
    boolean search(T[] arr, T item);

    int find(T[] arr, T item);
}
