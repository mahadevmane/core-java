package org.open.corejava.jbasics.enums;

public class EnumNextLevel {

    public static void main(String[] args) {
        Operations plus = Operations.PLUS;
        System.out.println(plus.operate(12, 32));

        Operations minus = Operations.MINUS;
        System.out.println(minus.operate(12, 32));
    }

    enum Operations {
        PLUS("+") {
            @Override
            public double operate(double first, double second) {
                return first + second;
            }
        },
        MINUS("-") {
            @Override
            public double operate(double first, double second) {
                return first - second;
            }
        },
        MULTIPLY("+") {
            @Override
            public double operate(double first, double second) {
                return first * second;
            }
        },
        DIVIDE("+") {
            @Override
            public double operate(double first, double second) {
                return first / second;
            }
        };

        final String op;

        Operations(final String op) {
            this.op = op;
        }

        abstract public double operate(double first, double second);
    }
}
