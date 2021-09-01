package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Powers1ToN {
    private static final int MAX = 1000000000;
    private static final List<Integer> POWERS = new ArrayList<>(33000);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int q, n, prev = 0;
        List<Integer> allPowers = new ArrayList<>(33000);

        for (int i = 2; i * i <= MAX; i++) {
            for (int j = i; j <= MAX / i; j *= i) {
                allPowers.add(j * i);
            }
        }

        Collections.sort(allPowers);
        for (int power : allPowers) {
            if (power != prev) {
                POWERS.add(power);
                prev = power;
            }
        }

        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            q = Integer.parseInt(br.readLine());
            for (int i = 0; i < q; i++) {
                n = Integer.parseInt(br.readLine());
                sb.append(find(n)).append("\n");
            }
            tc--;
        }
        System.out.println(sb);
    }

    private static int find(int item) {
        int mid, res1, res2;
        int left = 0, right = POWERS.size() - 1;

        while (right - left > 1) {
            mid = (left + right) / 2;

            if (POWERS.get(mid) == item) {
                return 0;
            } else if (POWERS.get(mid) < item) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (item < POWERS.get(left)) {
            res1 = POWERS.get(left) - item;
        } else {
            res1 = item - POWERS.get(left);
        }

        if (item < POWERS.get(right)) {
            res2 = POWERS.get(right) - item;
        } else {
            res2 = item - POWERS.get(right);
        }

        return Math.min(res1, res2);
    }
}