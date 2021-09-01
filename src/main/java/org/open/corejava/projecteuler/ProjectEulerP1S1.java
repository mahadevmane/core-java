package org.open.corejava.projecteuler;

public class ProjectEulerP1S1 {
    private static final int TARGET = 999;

    public static void main(String[] args) {
        int num1 = 3, num2 = 5;
        long result = 0;

        for (int i = 1; i <= TARGET; i++) {
            if (i % num1 == 0 || i % num2 == 0) {
                result += i;
            }
        }

        System.out.println("Result: " + result);
    }
}