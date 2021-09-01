package org.open.corejava.important;

public class NumberTheory {
	private static final int MOD = 1000000007;

	// d - GCD, x & y are coefficients: ax + by = GCD(a, b)
	@SuppressWarnings("unused")
	private int d, x, y;

	public int GCDNative(int a, int b) {
		int m = a < b ? a : b;

		while (m > 1) {
			if (a % m == 0 && b % m == 0)
				return m;

			m--;
		}

		return 1;
	}

	public int GCDEuclid(int a, int b) {
		if (b == 0)
			return a;

		return GCDEuclid(b, a % b);
	}

	public void GCDEuclidExt(int a, int b) {
		if (b == 0) {
			d = a;
			x = 1;
			y = 0;
		} else {
			GCDEuclidExt(b, a % b);
			int temp = x;
			x = y;
			y = temp - (a / b) % y;
		}
	}

	public void checkPrime(int n) {
		int count = 0;
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				if (i * i == n)
					count++;
				else
					count += 2;
			}
		}

		if (count == 2)
			System.out.println(n + " is a prime number.");
		else
			System.out.println(n + " is not a prime number.");
	}

	public boolean isPrime(int n) {
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public void sieve(int n) {
		boolean[] isPrime = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			isPrime[i] = true;
		}

		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i * i <= n; i++) {
			if (isPrime[i] == true) {
				for (int j = i * i; j <= n; j += i)
					isPrime[j] = false;
			}
		}
	}

	public boolean isPrimePrime(int n) {
		boolean[] isPrime = new boolean[n + 1];

		for (int i = 0; i <= n; ++i) {
			isPrime[i] = true;
		}

		isPrime[0] = false;
		isPrime[1] = false;
		int cnt = 1;

		for (int i = 2; i * i <= n; ++i) {
			if (isPrime[i] == true) {
				for (int j = i * i; j <= n; j += i) {
					if (isPrime[j]) {
						isPrime[j] = false;
						cnt++;
					}
				}
			}
		}

		return isPrime[n - cnt];
	}

	public long exponentiationNative(long a, long b, long c) {
		long ans = 1;
		for (int i = 1; i <= b; i++) {
			ans *= a;
			ans %= c;
		}
		return ans;
	}

	public long exponentiation(long a, long b, long c) {
		long ans = 1;

		while (b != 0) {
			if (b % 2 == 1) {
				ans = ans * a;
				ans = ans % c;
			}
			a = a * a;
			a %= c;

			b /= 2;
		}
		return ans;
	}

	public long nCr(int n, int r) {
		long result = 1;

		result = fact(n) / (fact(r) * fact(n - r));

		return result;
	}

	public long fact(int n) {
		long result = 1;

		for (int i = 2; i <= n; i++) {
			result = (result * i) % MOD;
		}

		return result;
	}
}
