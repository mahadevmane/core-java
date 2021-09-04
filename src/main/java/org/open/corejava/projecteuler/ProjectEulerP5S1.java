package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * <p>
 * What is the smallest positive number that is evenly divisible by all the numbers from 1 to 20?
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