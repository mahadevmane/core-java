package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChanduGurudeva {
    private static final int MOD = 1000000007;
    private static final int MAX = 200001;

    private static final List<GNode> list = new ArrayList<GNode>(MAX);
    private static final int[] id = new int[MAX];
    private static final long[] size = new long[MAX];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n, a, b;
        long result, w;
        String[] inputs;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());

            initialize(n);

            for (int i = 0; i < n - 1; i++) {
                inputs = br.readLine().split(" ");
                a = Integer.parseInt(inputs[0]);
                b = Integer.parseInt(inputs[1]);
                w = Long.parseLong(inputs[2]);

                list.add(new GNode(a, b, w));
            }

            Collections.sort(list);

            result = 0;
            for (int i = 0; i < n - 1; i++) {
                a = root(list.get(i).x);
                b = root(list.get(i).y);

                result = (result + (list.get(i).w * size[a] * size[b])) % MOD;
                union(a, b);
            }

            list.clear();
            System.out.println(result);
        }
    }

    private static void initialize(int n) {
        for (int i = 0; i <= n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private static int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    private static void union(int x, int y) {
        if (size[x] < size[y]) {
            id[x] = y;
            size[y] += size[x];
        } else {
            id[y] = x;
            size[x] += size[y];
        }
    }
}

class GNode implements Comparable<GNode> {
    int x, y;
    long w;

    public GNode(int x, int y, long w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    @Override
    public int compareTo(GNode obj) {
        if (this.w == obj.w) {
            return this.x - obj.x;
        }

        return (int) (this.w - obj.w);
    }
}