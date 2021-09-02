package org.open.corejava.projecteuler;

import java.math.BigInteger;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP25S1 {
    public static void main(String[] args) {
        BigInteger f1 = new BigInteger("1");
        BigInteger f2 = new BigInteger("1");
        BigInteger fn;

        int index = 2;

        while (true) {
            index++;
            fn = f1.add(f2);

            if (fn.toString().length() >= 1000) {
                break;
            }

            f1 = f2;
            f2 = fn;
        }

        System.out.println(index);
    }
}