package org.open.corejava.projecteuler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Mahadev Mane
 *
 * <b>Maximum Path Sum:</b> By starting at the top of the triangle below
 * and moving to adjucent numbers on the row below. Find the maximum total from top to the bottom.
 * <p>
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 *
 * <a href="https://projecteuler.net/problem=67">P67</a>
 */

public class ProjectEulerP67S1 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter number of Lines: ");
            int noOfRows = Integer.parseInt(br.readLine());

            // Jagged Arrays
            int[][] data = new int[noOfRows][];

            System.out.println("Enter data-");
            String[] inputs;
            for (int row = 0; row < noOfRows; row++) {
                inputs = br.readLine().split(" ");
                data[row] = new int[inputs.length];

                for (int col = 0; col < inputs.length; col++) {
                    data[row][col] = Integer.parseInt(inputs[col]);
                }
            }

            int prevRow, prevCol;
            for (int row = 1; row < data.length; row++) {
                prevRow = row - 1;
                for (int col = 0; col < data[row].length; col++) {
                    prevCol = col - 1;
                    if (col == 0) {
                        data[row][col] = data[prevRow][col] + data[row][col];
                    } else if (col == data[row].length - 1) {
                        data[row][col] = data[prevRow][prevCol] + data[row][col];
                    } else {
                        data[row][col] = Math.max(data[prevRow][prevCol], data[prevRow][col]) + data[row][col];
                    }
                }
            }

            int lastRow = noOfRows - 1;
            int result = data[0][0];
            for (int col = 0; col < data[lastRow].length; col++) {
                if (result < data[lastRow][col]) {
                    result = data[lastRow][col];
                }
            }

            System.out.println(result);
        }
    }
}