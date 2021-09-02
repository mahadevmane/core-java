package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP9S1 {
    public static void main(String[] args) {
        int total = 1000, a, b, c;

        a = b = c = 0;
        for (a = 2; a < total / 3; a++) {
            for (b = 2, c = total - a - b; b < c; b++, c--) {
                if (isPythagorianTriplet(a, b, c)) {
                    break;
                }
            }
            if (b < c) {
                break;
            }
        }

        System.out.println(a + " " + b + " " + c);
        System.out.println(a * b * c);
    }

    private static boolean isPythagorianTriplet(int a, int b, int c) {
        return (a * a) + (b * b) == (c * c);
    }
}