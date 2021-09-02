package org.open.corejava.projecteuler;

import java.math.BigInteger;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP16S1 {
    private final static int MAX = 1000;

    public static void main(String[] args) {
        BigInteger num = new BigInteger("2");
        BigInteger temp = new BigInteger("1");

        for (int i = 0; i < MAX; i++) {
            temp = temp.multiply(num);
        }

        int result = 0;
        char[] chs = temp.toString().toCharArray();
        for (char ch : chs) {
            result += Integer.parseInt(String.valueOf(ch));
        }

        System.out.println(result);
    }
}