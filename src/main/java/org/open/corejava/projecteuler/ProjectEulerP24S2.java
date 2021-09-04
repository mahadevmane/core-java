package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * <a href="https://projecteuler.net/problem=24">P24</a>
 */

public class ProjectEulerP24S2 {
    public static void main(String[] args) {
        int n = 999999, tmp;
        int[] fact = new int[10];
        StringBuilder sb = new StringBuilder("0123456789");

        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i;
        }

        for (int i = 9; i >= 0; i--) {
            tmp = n / fact[i];
            System.out.print(sb.charAt(tmp));
            sb.deleteCharAt(tmp);
            n = n % fact[i];
        }
    }
}