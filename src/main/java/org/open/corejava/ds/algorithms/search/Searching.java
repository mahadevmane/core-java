package org.open.corejava.ds.algorithms.search;

import java.util.Scanner;

public class Searching {

    public static void main(String[] args) throws Exception {
        int n, item;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        item = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(linearSearch(arr, n, item));
        System.out.println(sequentialSearch(arr, n, item));

        System.out.println(binarySearchIterative(arr, n, item));
        System.out.println(binarySearchRecursive(arr, 0, n - 1, item));

        System.out.println(numberOfOccurances(arr, n, item));

        sc.close();
    }

    private static boolean linearSearch(int[] arr, int n, int item) {
        for (int i = 0; i < n; i++) {
            if (item == arr[i])
                return true;
        }

        return false;
    }

    private static int sequentialSearch(int[] arr, int n, int item) {
        for (int i = 0; i < n; i++) {
            if (item == arr[i])
                return i;
        }

        return -1;
    }

    private static int binarySearchIterative(int[] arr, int length, int item) {
        int mid, left = 0, right = length - 1;

        while (left <= right) {
            mid = (left + right) / 2;

            if (item == arr[mid])
                return mid;
            else if (item < arr[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }

        return -1;
    }

    private static int binarySearchRecursive(int[] arr, int left, int right, int item) {
        if (left <= right) {
            int mid = (left + right) / 2;

            if (item == arr[mid])
                return mid;
            else if (item < arr[mid])
                return binarySearchRecursive(arr, left, mid - 1, item);
            else
                return binarySearchRecursive(arr, mid + 1, right, item);
        } else {
            return -1;
        }
    }

    private static int firstOccurance(int[] arr, int left, int right, int item) {
        int mid;

        while (right - left > 1) {
            mid = (left + right) / 2;

            if (arr[mid] >= item)
                right = mid;
            else
                left = mid;
        }

        return right;
    }

    private static int lastOccurance(int[] arr, int left, int right, int item) {
        int mid;

        while (right - left > 1) {
            mid = (left + right) / 2;

            if (arr[mid] <= item)
                left = mid;
            else
                right = mid;
        }

        return left;
    }

    private static int numberOfOccurances(int[] arr, int length, int item) {
        int left = firstOccurance(arr, -1, length - 1, item);
        int right = lastOccurance(arr, 0, length, item);

        if (arr[left] == item && arr[right] == item)
            return (right - left + 1);

        return 0;
    }
}
