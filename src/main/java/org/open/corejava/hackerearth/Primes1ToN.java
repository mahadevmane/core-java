package org.open.corejava.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Primes1ToN {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total;
        System.out.print("Enter number: ");
        int n = Integer.parseInt(br.readLine());
        boolean[] primes = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            primes[i] = true;
        }

        total = n - 1;
        for (int i = 2; i * i <= n; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    if (primes[j]) {
                        total--;
                        primes[j] = false;
                    }
                }
            }
        }

        System.out.println("\nNumber of prime numbers from 1 to " + n + " are " + total);
    }
}
