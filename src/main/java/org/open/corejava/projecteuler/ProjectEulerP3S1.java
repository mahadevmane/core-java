package org.open.corejava.projecteuler;

public class ProjectEulerP3S1 {
	public static void main(String[] args) {
		long num = 600851475143L;
		int primeFactor = 2;

		while (num > primeFactor) {
			if (num % primeFactor == 0) {
				System.out.println(primeFactor);
				num = num / primeFactor;
				continue;
			}

			primeFactor++;
		}

		System.out.println(primeFactor);
	}
}