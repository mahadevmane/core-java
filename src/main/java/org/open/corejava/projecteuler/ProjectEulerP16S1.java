package org.open.corejava.projecteuler;

import java.math.BigInteger;

public class ProjectEulerP16S1 {
	private final static int MAX = 1000;

	public static void main(String[] args) {
		BigInteger num = new BigInteger("2");
		BigInteger temp = new BigInteger("1");

		for (int i = 0; i < MAX; i++) {
			temp = temp.multiply(num);
		}

		int result = 0;
		char[] chs = temp.toString().toCharArray();
		for (int i = 0; i < chs.length; i++) {
			result += Integer.parseInt(String.valueOf(chs[i]));
		}

		System.out.println(result);
	}
}