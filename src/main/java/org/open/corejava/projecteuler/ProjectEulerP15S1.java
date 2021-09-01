package org.open.corejava.projecteuler;

public class ProjectEulerP15S1 {
    private final static int MAX = 20;

    public static void main(String[] args) {
        System.out.println(count(MAX, MAX));
    }

    private static long count(int row, int col) {
        long[][] arr = new long[row + 1][col + 1];

        for (int i = 0; i <= row; i++) {
            arr[i][0] = 1;
        }

        for (int i = 0; i <= col; i++) {
            arr[0][i] = 1;
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
            }
        }

        return arr[row][col];
    }
}