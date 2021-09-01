package org.open.corejava.important;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IcelandGraph {
	private static final List<String> bridges = new ArrayList<>();
	private static final Map<String, List<String>> graph = new HashMap<>();
	private static final Map<String, Boolean> visited = new HashMap<>();
	private static int count = 0;
	private static String firstIceland;

	public static void main(String[] args) throws Exception {
		System.out.println(criticalBridges(1, "({A,B,C,D,E,F},{(A,C),(B,C),(C,E),(B,E),(B,D),(E,F)})"));
		System.out.println(criticalBridges(2, "({A,B,C},{(A,B),(B,C),(C,A)}),({A,B,C,D,E},{(A,B),(B,C),(C,A),(E,D),(D,A)})"));
	}

	private static String criticalBridges(int n, String str) {
		StringBuffer sb = new StringBuffer();

		String[] inputs = str.substring(2, str.length() - 2).replace("}),({", "#").split("#");
		for (String input : inputs) {
			bridges.clear();
			graph.clear();
			visited.clear();

			String[] temp = input.replace("},{", "#").split("#");
			String[] icelands = temp[0].split(",");
			firstIceland = icelands[0];
			for (String il : icelands) {
				visited.put(il, false);
				graph.put(il, new ArrayList<>());
			}

			String[] brs = temp[1].replace("),(", ")#(").split("#");
			for (String br : brs) {
				br = br.substring(1, br.length() - 1);

				bridges.add(br);
				String[] ils = br.split(",");

				graph.get(ils[0]).add(ils[1]);
				graph.get(ils[1]).add(ils[0]);
			}

			// Check all edges
			sb.append("{");
			boolean isCritical = false;
			for (String br : bridges) {
				String[] ils = br.split(",");

				if (graph.get(ils[0]).size() > 1 && graph.get(ils[1]).size() > 1) {
					graph.get(ils[0]).remove(ils[1]);
					graph.get(ils[1]).remove(ils[0]);

					if (!isConnected()) {
						isCritical = true;
						sb.append("(" + br + "),");
					}

					graph.get(ils[0]).add(ils[1]);
					graph.get(ils[1]).add(ils[0]);
				} else {
					isCritical = true;
					sb.append("(" + br + "),");
				}
			}

			if (!isCritical) {
				sb.append("NA},");
			} else {
				sb.deleteCharAt(sb.length() - 1).append("},");
			}
		}

		sb.deleteCharAt(sb.length() - 1);
		if (n > 1) {
			sb.insert(0, "{").append("}");
		}

		return sb.toString();
	}

	private static boolean isConnected() {
		count = 0;
		for (Entry<String, Boolean> entry : visited.entrySet()) {
			entry.setValue(false);
		}

		dfs(firstIceland);

		return count == visited.size();
	}

	private static void dfs(String iceland) {
		count++;
		visited.put(iceland, true);

		for (String il : graph.get(iceland)) {
			if (!visited.get(il)) {
				dfs(il);
			}
		}
	}
}