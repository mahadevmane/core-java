package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class ProjectEulerP1S2 {
    private static final int TARGET = 999;

    public static void main(String[] args) {
        int num1 = 3, num2 = 5;
        long result = sumDivisibleBy(num1) + sumDivisibleBy(num2) - sumDivisibleBy(num1 * num2);
        System.out.println("Result: " + result);
    }

    private static long sumDivisibleBy(int num) {
        int p = TARGET / num;
        return num * (p * (p + 1)) / 2;
    }
}