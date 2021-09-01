package org.open.corejava.projecteuler;

public class ProjectEulerP21S1 {
    private final static int MAX = 10001;
    private static final int[] res = new int[MAX];

    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            res[i] = 1;
        }

        for (int i = 2; i <= MAX / 2; i++) {
            for (int j = i + i; j < MAX; j += i) {
                res[j] += i;
            }
        }

        int result = 0;
        for (int i = 1; i < MAX; i++) {
            if (i != res[i]) {
                if (res[i] < MAX && i == res[res[i]]) {
                    System.out.println(i + " - " + res[i]);
                    result += (i + res[i]);
                }
            }
        }

        System.out.println(result / 2);
    }
}