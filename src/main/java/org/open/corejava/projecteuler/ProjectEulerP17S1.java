package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP17S1 {

    public static void main(String[] args) {
        int result = 0, temp;

        for (int i = 1; i <= 1000; i++) {
            if (i == 1000) {
                result += 11;
                break;
            }

            temp = i;
            while (temp > 0) {
                if (temp >= 100 && temp <= 999) {
                    if (temp % 100 == 0) {
                        result += (getUnits(temp / 100) + 7);
                    } else {
                        result += (getUnits(temp / 100) + 10);
                    }
                    temp = temp % 100;
                } else if (temp >= 20 && temp <= 99) {
                    result += getTens(temp / 10);
                    temp = temp % 10;
                } else {
                    result += getUnits(temp);
                    temp = 0;
                }
            }
        }

        System.out.println(result);
    }

    private static int getUnits(int num) {
        if (num == 1 || num == 2 || num == 6 || num == 10) {
            return 3;
        } else if (num == 0 || num == 4 || num == 5 || num == 9) {
            return 4;
        } else if (num == 3 || num == 7 || num == 8) {
            return 5;
        } else if (num == 11 || num == 12) {
            return 6;
        } else if (num == 15 || num == 16) {
            return 7;
        } else if (num == 13 || num == 14 || num == 18 || num == 19) {
            return 8;
        } else if (num == 17) {
            return 9;
        } else {
            return 0;
        }
    }

    private static int getTens(int num) {
        if (num == 4 || num == 5 || num == 6) {
            return 5;
        } else if (num == 2 || num == 3 || num == 8 || num == 9) {
            return 6;
        } else if (num == 7) {
            return 7;
        } else {
            return 0;
        }
    }
}