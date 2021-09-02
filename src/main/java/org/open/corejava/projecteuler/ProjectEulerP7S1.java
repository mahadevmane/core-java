package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
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
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}