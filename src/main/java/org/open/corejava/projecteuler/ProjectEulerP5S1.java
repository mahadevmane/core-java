package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP5S1 {
    public static void main(String[] args) {
        int lLimit = 2, uLimit = 20;
        int i = lLimit, result = uLimit - 1;

        while (i <= uLimit) {
            result++;

            for (i = lLimit; i <= uLimit; i++) {
                if (result % i != 0) {
                    break;
                }
            }
        }

        System.out.println(result);
    }
}