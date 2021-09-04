package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * <p>
 * What is the 10 001st prime number?
 */

public class ProjectEulerP7S1 {
    private final static int RANK = 10001;

    public static void main(String[] args) {
        long num = 1, cnt = 0;

        while (cnt != RANK) {
            if (isPrime(++num)) {
                cnt++;
            }
        }

        System.out.println(num);
    }

    private static boolean isPrime(long num) {
        for (int i = 2; (long) i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}