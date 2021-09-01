package org.open.corejava.projecteuler;

public class ProjectEulerP12S1 {
	private final static int MAX = 500;

	public static void main(String[] args) {
		int i, cnt = 0, term = 1;
		long triangle = 0;

		while (cnt <= MAX) {
			triangle = (term * (term + 1)) / 2;

			for (i = 1, cnt = 0; i * i <= triangle; i++) {
				if (triangle % i == 0) {
					if (i == triangle / i) {
						cnt++;
					} else {
						cnt += 2;
					}
				}
			}

			term++;
		}

		System.out.println(triangle);
	}
}