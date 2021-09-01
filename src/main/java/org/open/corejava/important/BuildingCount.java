package org.open.corejava.important;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BuildingCount {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Please enter your inputs:");

            int rows = Integer.parseInt(br.readLine());
            int columns = Integer.parseInt(br.readLine());

            int marineCount = Integer.parseInt(br.readLine());
            int[][] marinePositions = new int[marineCount][2];

            for (int i = 0; i < marineCount; i++) {
                marinePositions[i][0] = Integer.parseInt(br.readLine());
                marinePositions[i][1] = Integer.parseInt(br.readLine());
            }

            System.out.println(GetBuildingCount(rows, columns, marineCount, marinePositions));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static int GetBuildingCount(int rows, int columns, int marineCount, int[][] marinePositions) {
        int result = 0, rowPos = 0, colPos = 0;
        int counter = rows > columns ? rows : columns;
        int[][] buildingArray = new int[rows][columns];

        for (int i = 0; i < marineCount; i++) {
            rowPos = marinePositions[i][0] - 1;
            colPos = marinePositions[i][1] - 1;

            if (rowPos >= 0 && colPos >= 0 && rowPos < rows && colPos < columns) {
                for (int j = 0; j < counter; j++) {
                    if (j < rows && buildingArray[j][colPos] != 1) {
                        buildingArray[j][colPos] = 1;
                        result++;
                    }

                    if (j < columns && buildingArray[rowPos][j] != 1) {
                        buildingArray[rowPos][j] = 1;
                        result++;
                    }
                }
            }
        }

        return result;
    }
}
