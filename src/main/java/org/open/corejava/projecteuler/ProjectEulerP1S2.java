package org.open.corejava.projecteuler;

public class ProjectEulerP1S2 {
    private static final int TARGET = 999;

    public static void main(String[] args) {
        int num1 = 3, num2 = 5;

        long result = sumDivisibleBy(num1) + sumDivisibleBy(num2) - sumDivisibleBy(num1 * num2);

        System.out.println("Result: " + result);
    }

    private static long sumDivisibleBy(int num) {
        int p = TARGET / num;
        long result = num * (p * (p + 1)) / 2;

        return result;
    }
}