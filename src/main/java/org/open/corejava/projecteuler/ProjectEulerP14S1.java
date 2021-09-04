package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * The following iterative sequence is defined for the set of positive integers:
 * <p>
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * <p>
 * Using the rule above and starting with 13, we generate the following sequence:
 * <p>
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * <p>
 * Which starting number, under one million, produces the longest chain?
 * <p>
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */

public class ProjectEulerP14S1 {
    private final static int MAX = 1000000;

    public static void main(String[] args) {
        int result = 0, count = 0, cnt;
        long temp;

        for (int i = 1; i < MAX; i++) {
            cnt = 1;
            temp = i;

            while (temp > 1) {
                if (temp % 2 == 0) {
                    temp = temp / 2;
                } else {
                    temp = (3 * temp) + 1;
                }

                cnt++;
            }

            if (temp < 1) {
                System.out.println(temp);
            }

            if (cnt > count) {
                result = i;
                count = cnt;
            }
        }

        System.out.println(result + " - " + count);
    }
}