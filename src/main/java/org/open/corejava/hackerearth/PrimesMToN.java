package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class PrimesMToN {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int m, n, j;
        boolean[] arr;
        String[] inputs;

        int tc = Integer.parseInt(br.readLine());
        while (--tc >= 0) {
            inputs = br.readLine().split(" ");
            m = Integer.parseInt(inputs[0]);
            n = Integer.parseInt(inputs[1]);

            arr = new boolean[n - m + 1];
            Arrays.fill(arr, true);

            if (m == 1) {
                arr[0] = false;
            }

            for (int i = 2; i * i <= n; i++) {
                if (m < 4) {
                    j = i * i;
                } else {
                    if (m % i == 0) {
                        j = m;
                    } else {
                        j = (m / i) * i + i;
                    }
                }

                for (; j <= n; j += i) {
                    arr[j - m] = false;
                }
            }

            for (int i = m; i <= n; i++) {
                if (arr[i - m]) {
                    sb.append(i).append("\n");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}