package org.open.corejava.ds.algorithms.search;

public class LinearSearch<T> implements Search<T> {

    @Override
    public boolean search(T[] arr, T item) {
        for (T t : arr) {
            if (t == item) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int find(T[] arr, T item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == item) {
                return i;
            }
        }

        return -1;
    }
}
