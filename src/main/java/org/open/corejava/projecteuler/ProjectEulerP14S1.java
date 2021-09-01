package org.open.corejava.projecteuler;

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