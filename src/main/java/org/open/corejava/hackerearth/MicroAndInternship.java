package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MicroAndInternship {
    private static final int MAX = 100001;
    private static final int[][] dp = new int[MAX][20];
    private static final int[][] arr = new int[MAX][101];
    private static final Deque<Node> stack = new ArrayDeque<>(30000);
    private static TreeNode[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int x, y, nodeValue, lca, temp, result;

        int n = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        tree = new TreeNode[n + 1];

        tree[0] = new TreeNode(0);
        for (int i = 1; i <= n; i++) {
            nodeValue = Integer.parseInt(inputs[i - 1]);
            tree[i] = new TreeNode(nodeValue);
        }

        for (int i = 1; i < n; i++) {
            inputs = br.readLine().split(" ");
            x = Integer.parseInt(inputs[0]);
            y = Integer.parseInt(inputs[1]);

            tree[x].adjList.add(y);
            tree[y].adjList.add(x);
        }

        dfs(1, 0);

        for (int i = 1; (1 << i) < n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
        }

        int qq = Integer.parseInt(br.readLine());
        for (int i = 1; i <= qq; i++) {
            inputs = br.readLine().split(" ");
            x = Integer.parseInt(inputs[0]);
            y = Integer.parseInt(inputs[1]);

            result = 0;
            lca = lca(x, y);
            for (int j = 1; j <= 100; j++) {
                temp = arr[x][j] + arr[y][j] - (2 * arr[lca][j]);

                if (temp != 0 || j == tree[lca].value) {
                    result++;
                }
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int current, int parent) {
        int next;
        List<Integer> lst;
        Node temp, node = new Node(current, parent);

        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();

            arr[node.current][tree[node.current].value]++;
            dp[node.current][0] = node.parent;

            lst = tree[node.current].adjList;
            for (int i = 0; i < lst.size(); i++) {
                next = lst.get(i);

                if (next != node.parent) {
                    tree[next].depth = tree[node.current].depth + 1;
                    for (int j = 1; j <= 100; j++) {
                        arr[next][j] = arr[node.current][j];
                    }

                    temp = new Node(next, node.current);
                    stack.push(temp);
                }
            }
        }
    }

    private static int lca(int u, int v) {
        if (tree[u].depth < tree[v].depth) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        for (int i = 18; i >= 0; i--) {
            if (tree[dp[u][i]].depth >= tree[v].depth)
                u = dp[u][i];
        }

        if (u == v)
            return u;

        for (int i = 18; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }

        return dp[u][0];
    }
}

class Node {
    int current, parent;

    public Node(int c, int p) {
        current = c;
        parent = p;
    }
}

class TreeNode {
    int value, depth;
    List<Integer> adjList;

    public TreeNode(int value) {
        this.value = value;
        this.depth = 0;
        this.adjList = new ArrayList<>();
    }
}