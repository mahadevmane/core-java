package org.open.corejava.projecteuler;

public class ProjectEulerP28S1 {
	private static final int N = 1001;

	public static void main(String[] args) {
		int min, max, cnt = 1;
		int[][] arr = new int[N][N];

		min = max = N / 2;
		arr[min][max] = cnt++;

		while (min >= 0 && max < N) {
			/* Top to Bottom */
			for (int i = min + 1; i <= max; i++) {
				arr[i][max] = cnt++;
			}

			/* Right to Left */
			for (int i = max - 1; i >= min; i--) {
				arr[max][i] = cnt++;
			}

			/* Bottom to Top */
			for (int i = max - 1; i >= min; i--) {
				arr[i][min] = cnt++;
			}

			/* Left to Right */
			for (int i = min + 1; i <= max; i++) {
				arr[min][i] = cnt++;
			}

			min--;
			max++;
		}

		min = 0;
		max = N - 1;
		int sum = -3;

		while (min <= max) {
			sum += (arr[min][min] + arr[max][max] + arr[min][max] + arr[max][min]);

			min++;
			max--;
		}

		System.out.println(sum);
	}
}