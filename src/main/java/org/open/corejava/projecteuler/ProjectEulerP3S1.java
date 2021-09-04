package org.open.corejava.projecteuler;

/**
 * @author Mahadev Mane
 * <p>
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 */

public class ProjectEulerP3S1 {
    public static void main(String[] args) {
        long num = 600851475143L;
        int primeFactor = 2;

        while (num > primeFactor) {
            if (num % primeFactor == 0) {
                System.out.println(primeFactor);
                num = num / primeFactor;
                continue;
            }
            primeFactor++;
        }

        System.out.println(primeFactor);
    }
}