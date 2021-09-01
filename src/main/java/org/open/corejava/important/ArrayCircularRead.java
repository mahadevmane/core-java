package org.open.corejava.important;

import java.util.Scanner;

public class ArrayCircularRead {

    public static void main(String[] args) {
        int[][] inArr;
        int[] outArr;
        int re, ce;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Row and Column: ");
        re = sc.nextInt();
        ce = sc.nextInt();
        inArr = new int[re][ce];
        outArr = new int[re * ce + 1];

        System.out.println("\nEnter Data-");
        for (int i = 0; i < re; i++) {
            for (int j = 0; j < ce; j++)
                inArr[i][j] = sc.nextInt();
        }

        sc.close();

        System.out.println("\nEntered Data Is-");
        for (int i = 0; i < re; i++) {
            for (int j = 0; j < ce; j++)
                System.out.print(inArr[i][j] + "\t");
            System.out.println();
        }

        System.out.println("\nAfter Circular Read Data Is-");
        arrayCircularRead(inArr, outArr, 0, re - 1, 0, ce - 1, 0);
        for (int i = 0, cnt = 0; i < re; i++) {
            for (int j = 0; j < ce; j++)
                System.out.print(outArr[cnt++] + "\t");
            System.out.println();
        }
    }

    private static void arrayCircularRead(int[][] inArr, int[] outArr, int rs, int re, int cs, int ce, int cnt) {
        for (int i = cs; i <= ce; i++)
            outArr[cnt++] = inArr[rs][i];

        for (int i = rs + 1; i <= re; i++)
            outArr[cnt++] = inArr[i][ce];

        for (int i = ce - 1; i >= cs; i--)
            outArr[cnt++] = inArr[re][i];

        for (int i = re - 1; i > rs; i--)
            outArr[cnt++] = inArr[i][cs];

        if (rs <= re && cs <= ce)
            arrayCircularRead(inArr, outArr, ++rs, --re, ++cs, --ce, cnt);
    }
}
