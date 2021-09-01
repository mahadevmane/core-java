package org.open.corejava.jbasics.enums;

public class EnumDemo {

    public static void main(String[] args) {
        Months m = Months.JAN;
        System.out.println(m.month);
    }

    public enum Months {
        JAN("January"),
        FEB("February"),
        MAR("March"),
        APR("April"),
        MAY("May"),
        JUN("June"),
        JUL("July"),
        AUG("August"),
        SEP("September"),
        OCT("October"),
        NOV("November"),
        DEC("December");

        public final String month;

        Months(final String month) {
            this.month = month;
        }

        public static Months getEnum(String code) {
            return null;
        }
    }
}
