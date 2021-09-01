package org.open.corejava.projecteuler;

import java.math.BigInteger;

public class ProjectEulerP20S1 {
    public static void main(String[] args) {
        BigInteger fact = new BigInteger("1");

        for (int i = 2; i <= 100; i++) {
            fact = fact.multiply(new BigInteger(String.valueOf(i)));
        }

        int result = 0;
        char[] chs = fact.toString().toCharArray();
        for (char ch : chs) {
            result += Integer.parseInt(String.valueOf(ch));
        }

        System.out.println(result);
    }
}