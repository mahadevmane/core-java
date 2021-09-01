package org.open.corejava.projecteuler;

public class ProjectEulerP2S1 {
	private static final int LIMIT = 4000000;

	public static void main(String[] args) {
		int nt = 2, t1 = 1, t2 = 2;
		int sum = 0;

		while (nt <= LIMIT) {
			nt = t1 + t2;

			if (nt % 2 == 0) {
				sum += nt;
			}

			t1 = t2;
			t2 = nt;
		}

		System.out.println(sum);
	}
}