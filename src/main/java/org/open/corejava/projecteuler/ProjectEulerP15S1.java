package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 * <p>
 * How many such routes are there through a 20×20 grid?
 */

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