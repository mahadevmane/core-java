package org.open.corejava.important;

public class EulersTotient {
    public int phiNative(int n) {
        int result = 1;

        for (int i = 2; i < n; i++) {
            if (gcd(i, n) == 1) {
                result++;
            }
        }

        return result;
    }

    public int phi(int n) {
        float result = n;

        for (int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                while (n % p == 0) {
                    n /= p;
                }

                result *= (1.0 - (1.0 / p));
            }
        }

        if (n > 1) {
            result *= (1.0 - (1.0 / n));
        }

        return (int) result;
    }

    public int phiOptimal(int n) {
        int result = n;

        for (int p = 2; p * p <= n; p++) {
            if (n % p == 0) {
                while (n % p == 0) {
                    n /= p;
                }

                result -= result / p;
            }
        }

        if (n > 1) {
            result -= result / n;
        }

        return result;
    }

    private int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }


}
