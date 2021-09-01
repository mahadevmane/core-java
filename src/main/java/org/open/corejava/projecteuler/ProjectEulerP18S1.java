package org.open.corejava.projecteuler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectEulerP18S1 {
    private static final Map<Integer, List<Integer>> data = new HashMap<>();
    private static int result = 0;

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputs;

            System.out.print("Enter number of Lines: ");
            int n = Integer.parseInt(br.readLine());

            System.out.println("Enter data-");
            for (int i = 1; i <= n; i++) {
                data.put(i, new ArrayList<>());

                inputs = br.readLine().split(" ");
                for (String str : inputs) {
                    data.get(i).add(Integer.parseInt(str));
                }
            }
        }

        getMaxPathSum(1, 0, 0);

        System.out.println(result);
    }

    private static void getMaxPathSum(int key, int i, int sum) {
        if (key == data.size()) {
            sum += data.get(key).get(i);
            if (sum > result) {
                result = sum;
            }
        } else {
            sum += data.get(key).get(i);
            getMaxPathSum(key + 1, i, sum);
            getMaxPathSum(key + 1, i + 1, sum);
        }
    }
}