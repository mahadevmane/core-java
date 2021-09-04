package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * The sum of the squares of the first ten natural numbers is, 385
 * <p>
 * The square of the sum of the first ten natural numbers is, 3025
 * <p>
 * However, the difference between the sum of the squares of the
 * first ten natural numbers and the square of the sum is 3025 - 385 = 2640.
 * <p>
 * Find the difference between the sum of the squares of the
 * first one hundred natural numbers and the square of the sum.
 */

public class ProjectEulerP6S1 {
    public static void main(String[] args) {
        int lLimit = 1, uLimit = 100;
        long sumOfSqr = 0, sqrOfSum;

        for (int i = lLimit; i <= uLimit; i++) {
            sumOfSqr += ((long) i * i);
        }

        sqrOfSum = (uLimit * (uLimit + 1)) / 2;
        sqrOfSum = sqrOfSum * sqrOfSum;

        System.out.println(sqrOfSum - sumOfSqr);
    }
}