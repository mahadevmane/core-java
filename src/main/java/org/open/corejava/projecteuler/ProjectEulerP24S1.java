package org.open.corejava.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectEulerP24S1 {
    private static final List<String> lst = new ArrayList<String>();

    public static void main(String[] args) {
        String str = "0123456789";
        permute(0, str.length() - 1, str.toCharArray());

        Collections.sort(lst);
        System.out.println(lst.get(999999));
    }

    private static void permute(int start, int len, char[] chars) {
        if (start == len) {
            lst.add(String.valueOf(chars));
        } else {
            for (int i = start; i <= len; i++) {
                swap(start, i, chars);
                permute(start + 1, len, chars);
                swap(start, i, chars);
            }
        }
    }

    private static void swap(int i, int j, char[] chars) {
        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }
}