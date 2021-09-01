package org.open.corejava.important;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Permutation {
    private static long cnt;

    public static void main(String[] args) {
        int ch = 0;
        char[] chars;
        do {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print("Enter Anything: ");
                chars = br.readLine().toCharArray();

                cnt = 0;
                System.out.println("\nThe permutations are-");
                permute(0, chars.length - 1, chars);

                System.out.print("\nDo you want to try again (Y/N)? ");
                ch = br.read();
            } catch (IOException e) {
                System.out.println("\nSorry, your input is wrong...");
            }
        } while (ch == 'Y' || ch == 'y');
        System.out.println("\n\n\nThanks, for using me!!!");
    }

    private static void permute(int start, int end, char[] chars) {
        if (start == end) {
            System.out.print(++cnt + "\t");
            System.out.println(chars);
        } else {
            for (int i = start; i <= end; i++) {
                swap(start, i, chars);
                permute(start + 1, end, chars);
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
