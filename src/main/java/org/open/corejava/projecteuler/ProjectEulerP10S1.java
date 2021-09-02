package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP10S1 {
    private final static int MAX = 2000000;

    public static void main(String[] args) {
        boolean[] isPrime = new boolean[MAX];

        for (int i = 2; i < MAX; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}