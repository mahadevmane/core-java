package org.open.corejava.important;

public class PossiblePathInMatrix {
    private static int cnt = 0;

    public int count(int row, int col) {
        int[][] arr = new int[row][col];

        for (int i = 0; i < row; i++) {
            arr[i][0] = 1;
        }

        for (int i = 0; i < col; i++) {
            arr[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
            }
        }

        return arr[row - 1][col - 1];
    }

    public int countRecursive(int row, int col, int no) {
        count(row, col, no);

        return cnt;
    }

    private void count(int row, int col, int no) {
        if (row == no || col == no) {
            cnt++;
            return;
        }
        count(row + 1, col, no);
        count(row, col + 1, no);
    }
}
