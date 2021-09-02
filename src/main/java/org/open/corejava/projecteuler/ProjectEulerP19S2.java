package org.open.corejava.projecteuler;

import java.util.Calendar;

/**
 * @author Mahadev Mane
 */

public class ProjectEulerP19S2 {
    public static void main(String[] args) {
        int result = 0;
        Calendar cal = Calendar.getInstance();
        for (int i = 1901; i <= 2000; i++) {
            for (int j = 1; j <= 12; j++) {
                cal.set(Calendar.YEAR, i);
                cal.set(Calendar.MONTH, j);
                cal.set(Calendar.DATE, 1);

                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}