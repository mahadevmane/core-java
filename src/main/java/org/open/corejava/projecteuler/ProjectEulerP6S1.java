package org.open.corejava.projecteuler;

public class ProjectEulerP6S1 {
    public static void main(String[] args) {
        int lLimit = 1, uLimit = 100;
        long sumOfSqr = 0, sqrOfSum;

        for (int i = lLimit; i <= uLimit; i++) {
            sumOfSqr += (i * i);
        }

        sqrOfSum = (uLimit * (uLimit + 1)) / 2;
        sqrOfSum = sqrOfSum * sqrOfSum;

        System.out.println(sqrOfSum - sumOfSqr);
    }
}