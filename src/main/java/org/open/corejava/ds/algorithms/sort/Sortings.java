package org.open.corejava.ds.algorithms.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Mahadev Mane
 */
public class Sortings {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] str = (br.readLine().split(" "));
            int[] arr = new int[N];

            if (N == str.length) {
                for (int j = 0; j < N; j++) {
                    arr[j] = Integer.parseInt(str[j]);
                }
            }

            System.out.println("Bubble sort result:");
            int[] result = bubbleSort(arr, N);
            printArray(result);

            System.out.println("\n\nSelection sort result:");
            result = selectionSort(arr, N);
            printArray(result);

            System.out.println("\n\nInsertion sort result:");
            result = insertionSort(arr, N);
            printArray(result);

            System.out.println("\n\nMerge sort result:");
            result = mergeSort(arr, 0, N - 1);
            printArray(result);

            System.out.println("\n\nRadix sort result:");
            result = radixSort(arr, N);
            printArray(result);
        }
    }

    private static void printArray(int[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static int[] bubbleSort(int[] arr, int n) {
        int temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        return arr;
    }

    private static int[] selectionSort(int[] arr, int n) {
        int min, temp;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[min] < arr[j]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

        return arr;
    }

    private static int[] insertionSort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i;

            while (j > 0 && temp > arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }

        return arr;
    }

    private static int[] mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }
        return arr;
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int p = start, q = mid + 1, k = 0;
        int[] temp = new int[end - start + 1];

        for (int i = start; i <= end; i++) {
            if (p > mid)
                temp[k++] = arr[q++];
            else if (q > end)
                temp[k++] = arr[p++];
            else if (arr[p] > arr[q])
                temp[k++] = arr[p++];
            else
                temp[k++] = arr[q++];
        }
        for (int x = 0; x < k; x++) {
            arr[start++] = temp[x];
        }
    }

    private static int[] radixSort(int[] arr, int n) {
        int i, m = arr[0], exp = 1;
        int[] b = new int[10];

        for (i = 1; i < n; i++)
            if (arr[i] > m)
                m = arr[i];

        while (m / exp > 0) {
            int[] bucket = new int[10];

            for (i = 0; i < n; i++)
                bucket[(arr[i] / exp) % 10]++;

            for (i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];

            for (i = n - 1; i >= 0; i--)
                b[--bucket[(arr[i] / exp) % 10]] = arr[i];

            for (i = 0; i < n; i++)
                arr[i] = b[i];

            exp *= 10;
        }

        return arr;
    }
}
