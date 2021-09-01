package org.open.corejava.jbasics.serilization.otherpkg;

import java.math.BigDecimal;

public class Tester {

    public static void main(String[] args) {
        System.out.println("" + null);
        System.out.println(true);
        System.out.println(false);

        String lgNumber = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        System.out.println("First No. : " + lgNumber);
        System.out.println("Second No.: " + lgNumber);

        BigDecimal d = new BigDecimal(lgNumber);
        System.out.println("Addition:  " + d.add(d));

        String str = "dfsgdsf";
        String strr = "dfsgdsf";
        System.out.println(str == strr);
    }
}
