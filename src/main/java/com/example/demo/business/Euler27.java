package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler27 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Euler discovered the remarkable quadratic formula:</p>\r\n"
				+ "<p class=\"center\">$n^2 + n + 41$</p>\r\n"
				+ "<p>It turns out that the formula will produce $40$ primes for the consecutive integer values $0 \\le n \\le 39$. However, when $n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41$ is divisible by $41$, and certainly when $n = 41, 41^2 + 41 + 41$ is clearly divisible by $41$.</p>\r\n"
				+ "<p>The incredible formula $n^2 - 79n + 1601$ was discovered, which produces $80$ primes for the consecutive values $0 \\le n \\le 79$. The product of the coefficients, $-79$ and $1601$, is $-126479$.</p>\r\n"
				+ "<p>Considering quadratics of the form:</p>\r\n"
				+ "<blockquote>\r\n"
				+ "$n^2 + an + b$, where $|a| &lt; 1000$ and $|b| \\le 1000$<br><br><div>where $|n|$ is the modulus/absolute value of $n$<br>e.g. $|11| = 11$ and $|-4| = 4$</div>\r\n"
				+ "</blockquote>\r\n"
				+ "<p>Find the product of the coefficients, $a$ and $b$, for the quadratic expression that produces the maximum number of primes for consecutive values of $n$, starting with $n = 0$.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(41);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();
		HashSet<Integer> primesSet = new HashSet<Integer>(primes);

		int [] longestConsecutivePrimes = {1, 41, 40};
		int a, b, n, consecutivePrimes, primeCandidate;
		for (a = -limit+1; a < limit; a++) {
			for (b = -limit; b <= limit; b++) {
				consecutivePrimes = 0;
				for (n = 0; n < 1000; n++) {
					primeCandidate = n * n + a * n + b;
					if (primesSet.contains(primeCandidate)) {
						consecutivePrimes++;
					} else {
						break;
					}
				}
				if (consecutivePrimes > 2) {
//					System.out.printf("n^2 + %dn + %d produces %d consecutive primes\n", a, b, consecutivePrimes);
				}
				if (consecutivePrimes > longestConsecutivePrimes[2]) {
					longestConsecutivePrimes[0] = a;
					longestConsecutivePrimes[1] = b;
					longestConsecutivePrimes[2] = consecutivePrimes;
				}
			}
		}

		// Print the consecutivePrimes
		String solution = String.valueOf(longestConsecutivePrimes[0] * longestConsecutivePrimes[1]);
//		System.out.printf("a * b = %d\n-----\n", longestConsecutivePrimes[0] * longestConsecutivePrimes[1]);
//		System.out.printf("a=%d, b=%d, consecutivePrimes=%d",
//				longestConsecutivePrimes[0], longestConsecutivePrimes[1], longestConsecutivePrimes[2]);

		return solution;
	}
}
