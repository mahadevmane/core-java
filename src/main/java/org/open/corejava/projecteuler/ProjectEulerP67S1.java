package org.open.corejava.projecteuler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProjectEulerP67S1 {
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs;

            System.out.print("Enter number of Lines: ");
            int n = Integer.parseInt(br.readLine());

            // Jagged Arrays
            int[][] data = new int[n][];
            int[][] res = new int[n][];

            System.out.println("Enter data-");
            for (int i = 0; i < n; i++) {
                inputs = br.readLine().split(" ");
                data[i] = new int[inputs.length];
                res[i] = new int[inputs.length];

                for (int j = 0; j < inputs.length; j++) {
                    data[i][j] = Integer.parseInt(inputs[j]);
                }
            }

            res[0][0] = data[0][0];
            for (int i = 1; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (j == 0) {
                        res[i][j] = res[i - 1][j] + data[i][j];
                    } else if (j == data[i].length - 1) {
                        res[i][j] = res[i - 1][j - 1] + data[i][j];
                    } else {
                        if (res[i - 1][j - 1] > res[i - 1][j]) {
                            res[i][j] = res[i - 1][j - 1] + data[i][j];
                        } else {
                            res[i][j] = res[i - 1][j] + data[i][j];
                        }
                    }
                }
            }

            n = n - 1;
            int result = res[0][0];
            for (int i = 0; i < res[n].length; i++) {
                if (result < res[n][i]) {
                    result = res[n][i];
                }
            }

            System.out.println(result);
        }
    }
}