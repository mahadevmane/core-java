package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class VasyaAndParty {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int u, v;
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        String[] kls = br.readLine().split(" ");
        Graph g = new Graph(n + 1, kls);

        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");

            u = Integer.parseInt(line[0]);
            v = Integer.parseInt(line[1]);

            g.addEdge(u, v);
        }

        long result = g.numberOfGroups();
        System.out.println(result);
    }
}

class Graph {
    private static final int MOD = 1000000007;
    private static final Deque<Integer> stack = new ArrayDeque<>();

    private final int v;
    private int mkl;
    private int cnt;
    private final int[] kls;
    private final Set<Integer>[] adjList;
    private final boolean[] visited;

    public Graph(int v, String[] kls) {
        this.v = v;

        this.kls = new int[v];
        this.adjList = new HashSet[v];

        for (int i = 0; i < v - 1; i++) {
            this.kls[i + 1] = Integer.parseInt(kls[i]);
            this.adjList[i] = new HashSet<>();
        }

        this.adjList[v - 1] = new HashSet<>();
        this.visited = new boolean[v];
    }

    public void addEdge(int x, int y) {
        adjList[x].add(y);
        adjList[y].add(x);
    }

    public void recDFS(int v) {
        if (mkl < kls[v]) {
            mkl = kls[v];
            cnt = 0;
        }

        if (!this.visited[v] && mkl == kls[v]) {
            cnt++;
        }

        this.visited[v] = true;

        Integer next;
        for (Integer integer : adjList[v]) {
            next = integer;

            if (!visited[next]) {
                recDFS(next);
            }
        }
    }

    public void DFS(int v) {
        Integer next;
        Iterator<Integer> itr;

        stack.push(v);
        while (!stack.isEmpty()) {
            v = stack.pop();

            if (mkl < kls[v]) {
                mkl = kls[v];
                cnt = 0;
            }

            if (!this.visited[v] && mkl == kls[v]) {
                cnt++;
            }

            visited[v] = true;

            itr = adjList[v].iterator();
            while (itr.hasNext()) {
                next = itr.next();

                if (!visited[next]) {
                    stack.push(next);
                }
            }
        }
    }

    public long numberOfGroups() {
        long result = 1;

        for (int i = 1; i < this.v; i++) {
            if (!visited[i]) {
                mkl = kls[i];
                cnt = 0;

                DFS(i);

                result = (result * cnt) % MOD;
            }
        }

        return result;
    }
}