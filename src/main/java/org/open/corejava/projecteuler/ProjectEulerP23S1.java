package org.open.corejava.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectEulerP23S1 {
    private final static int MAX = 28124;
    private static final int[] sumOfDivisors = new int[MAX];
    private static final boolean[] isSumOf2AbundantNumbers = new boolean[MAX];
    private static final List<Integer> abundantNumbers = new ArrayList<>();

    public static void main(String[] args) {
        Arrays.fill(sumOfDivisors, 1);
        for (int i = 2; i <= MAX / 2; i++) {
            for (int j = i + i; j < MAX; j += i) {
                sumOfDivisors[j] += i;
            }
        }

        for (int i = 1; i < MAX; i++) {
            if (i < sumOfDivisors[i]) {
                abundantNumbers.add(i);
            }
        }

        int temp;
        for (int a1 : abundantNumbers) {
            for (int a2 : abundantNumbers) {
                temp = a1 + a2;
                if (temp >= MAX) {
                    break;
                }
                isSumOf2AbundantNumbers[temp] = true;
            }
        }

        long result = 0;
        for (int i = 1; i < MAX; i++) {
            if (!isSumOf2AbundantNumbers[i]) {
                result += i;
            }
        }

        System.out.println(result);
    }
}