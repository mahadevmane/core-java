package org.open.corejava.important;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

public class StringCombinations {
    private final Set<String> list = new TreeSet<String>();
    private final Deque<String> stack = new ArrayDeque<String>();
    private final int add;
    private final int shift;
    private final int length;

    public StringCombinations(String str, int add, int shift) {
        this.length = str.length();
        this.add = add;
        this.shift = shift;
    }

    public void search(String str) {
        String temp;
        list.add(str);
        stack.push(str);

        while (!stack.isEmpty()) {
            str = stack.pop();

            temp = rotate(str);
            if (!list.contains(temp)) {
                list.add(temp);
                stack.push(temp);
            }

            temp = updateEvenPos(str);
            if (!list.contains(temp)) {
                list.add(temp);
                stack.push(temp);
            }
        }
    }

    public void searchRecursive(String str) {
        System.out.println(str);
        list.add(str);

        String temp = rotate(str);
        if (!list.contains(temp)) {
            searchRecursive(temp);
        }

        temp = updateEvenPos(str);
        if (!list.contains(temp)) {
            searchRecursive(temp);
        }
    }

    private String rotate(String str) {
        char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            result[(i + shift) % length] = str.charAt(i);
        }

        return new String(result);
    }

    private String updateEvenPos(String str) {
        char[] result = str.toCharArray();

        for (int i = 1; i < length; i += 2) {
            result[i] = (char) ('0' + (result[i] - '0' + add) % 10);
        }

        return new String(result);
    }
}
