package org.open.corejava.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectEulerP22S1 {
    private static final List<String> names = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Project Euler\\Inputs\\Problem 22.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] inputs = line.replace("\"", "").split(",");

                for (String name : inputs) {
                    names.add(name);
                }
            }
        }

        Collections.sort(names);
        char[] chs;
        int cnt = 0, temp;
        long result = 0;

        for (String name : names) {
            chs = name.toCharArray();

            temp = 0;
            for (char ch : chs) {
                if (ch >= 65 && ch <= 90) {
                    temp += (ch - 64);
                }
            }

            cnt++;
            temp *= cnt;
            result += temp;
        }

        System.out.println(result);
    }
}