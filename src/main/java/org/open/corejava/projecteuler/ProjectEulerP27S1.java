package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * Find the product of the coefficients, a and b, for the quadratic
 * expression (n*n + an + b, where |a| <= 1000 and |b| <= 1000) that
 * produces the maximum number of primes for consecutive values of n,
 * starting with n=0.
 *
 * <a href="https://projecteuler.net/problem=27">P27</a>
 */

public class ProjectEulerP27S1 {
    private static final int MAX = 1000;

    public static void main(String[] args) {
        int aCoef = 0, bCoef = 0, nMax = 0, n;

        for (int i = -MAX; i <= MAX; i++) {
            for (int j = -MAX; j <= MAX; j++) {
                n = 0;
                while (isPrime(Math.abs((n * n) + (i * n) + j))) {
                    n++;
                }

                if (n > nMax) {
                    aCoef = i;
                    bCoef = j;
                    nMax = n;
                }
            }
        }

        String result = "N: " + nMax + "\nA: " + aCoef + ", B: " + bCoef + "\nProduct: " + (aCoef * bCoef);
        System.out.println(result);
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}