package org.open.corejava.projecteuler;

public class ProjectEulerP4S1 {
    private final static int LL3Digit = 100;
    private final static int UL3Digit = 999;

    public static void main(String[] args) {
        int result = 0, temp;

        for (int i = LL3Digit; i <= UL3Digit; i++) {
            for (int j = LL3Digit; j <= UL3Digit; j++) {
                temp = i * j;
                if (isPalindrome(temp)) {
                    if (temp > result) {
                        result = temp;
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static boolean isPalindrome(int num) {
        char[] chars = String.valueOf(num).toCharArray();

        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }

        return true;
    }
}