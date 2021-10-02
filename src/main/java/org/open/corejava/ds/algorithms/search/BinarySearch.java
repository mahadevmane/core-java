package org.open.corejava.ds.algorithms.search;

/**
 * @param <T>
 * @author Mahadev Mane
 */
public class BinarySearch<T extends Comparable<T>> implements Search<T> {
    @Override
    public boolean search(T[] sortedArr, T item) {
        return find(sortedArr, item) != -1;
    }

    @Override
    public int find(T[] sortedArr, T item) {
        int start = 0, end = sortedArr.length - 1, mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (item.compareTo(sortedArr[mid]) == 0) {
                return mid;
            } else if (item.compareTo(sortedArr[mid]) > 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

}
