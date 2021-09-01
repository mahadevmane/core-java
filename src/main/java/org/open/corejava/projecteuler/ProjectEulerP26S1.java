package org.open.corejava.projecteuler;

public class ProjectEulerP26S1 {
	public static void main(String[] args) {
		int divisor = 0, sequenceLen = 0, tempSquenceLen;

		for (int i = 999; i > 1; i--) {
			if (sequenceLen >= i) {
				break;
			}

			int[] foundRemainders = new int[i];
			int divident = 1;
			int position = 0;

			while (foundRemainders[divident] == 0 && divident != 0) {
				foundRemainders[divident] = position;
				divident *= 10;
				divident %= i;
				position++;
			}

			tempSquenceLen = position - foundRemainders[divident];
			if (tempSquenceLen > sequenceLen) {
				divisor = i;
				sequenceLen = tempSquenceLen;
			}
		}

		System.out.println(divisor + " - " + sequenceLen);
	}
}