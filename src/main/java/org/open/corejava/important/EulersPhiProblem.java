package org.open.corejava.important;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EulersPhiProblem {
    private static final int MOD = 1000000007;
    private static final int MAX1 = 500001;
    private static final int MAX2 = 1000001;

    private static final int[] phi = new int[MAX1];
    private static final long[] phiSum = new long[MAX1];
    private static final int[] arr = new int[MAX2];
    private static final long[] tree = new long[MAX2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        compute();

        char ch;
        int temp, x, y;
        long result = 0;
        int n = Integer.parseInt(br.readLine());
        assert n >= 1 && n < MAX2;

        String[] inputs = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            temp = Integer.parseInt(inputs[i - 1]);
            assert temp >= 1 && temp < MAX1;

            arr[i] = temp;
            update(i, phiSum[temp], n);
        }

        int q = Integer.parseInt(br.readLine());
        assert q >= 1 && q <= 100000;
        for (int i = 1; i <= q; i++) {
            inputs = br.readLine().split(" ");

            ch = inputs[0].charAt(0);
            x = Integer.parseInt(inputs[1]);
            y = Integer.parseInt(inputs[2]);

            if (ch == 'C') {
                assert (y >= 1 && y <= n);

                result = (sum(y) - sum(x - 1)) % MOD;
                if (result < 0) {
                    result += MOD;
                }

                System.out.println(result);
            } else {
                assert (y >= 1 && y < MAX1);

                update(x, -phiSum[arr[x]], n);
                update(x, phiSum[y], n);

                arr[x] = y;
            }
        }
    }

    private static long sum(int i) {
        long result = 0;

        while (i > 0) {
            result = (result + tree[i]) % MOD;
            i -= (i & -i);
        }

        return result;
    }

    private static void compute() {
        for (int i = 0; i < MAX1; i++) {
            phi[i] = i;
        }

        for (int i = 2; i < MAX1; i++) {
            if (phi[i] == i) {
                phi[i] = i - 1;

                for (int j = 2 * i; j < MAX1; j += i) {
                    phi[j] -= (phi[j] / i);
                }
            }
        }

        for (int i = 1; i < MAX1; i++) {
            for (int j = i, k = 1; j < MAX1; j += i, k++) {
                phiSum[j] += i * phi[k];
            }
        }
    }

    private static void update(int i, long val, int n) {
        val %= MOD;

        while (i <= n) {
            tree[i] = (tree[i] + val) % MOD;
            i += (i & -i);
        }
    }
}
