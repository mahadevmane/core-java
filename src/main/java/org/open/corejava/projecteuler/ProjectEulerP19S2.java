package org.open.corejava.projecteuler;

import java.util.Calendar;

/**
 * @author Mahadev Mane
 * <p>
 * You are given the following information, but you may prefer to do some research for yourself.
 * <p>
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * <p>
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
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