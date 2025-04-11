package com.example.demo.business;

import java.math.BigInteger;

import com.example.demo.classes.Combinatorics;
import com.example.demo.records.Euler;

public class Euler53 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>There are exactly ten ways of selecting three from five, 12345:</p>\r\n"
				+ "<p class=\"center\">123, 124, 125, 134, 135, 145, 234, 235, 245, and 345</p>\r\n"
				+ "<p>In combinatorics, we use the notation, $\\displaystyle \\binom 5 3 = 10$.</p>\r\n"
				+ "<p>In general, $\\displaystyle \\binom n r = \\dfrac{n!}{r!(n-r)!}$, where $r \\le n$, $n! = n \\times (n-1) \\times ... \\times 3 \\times 2 \\times 1$, and $0! = 1$.\r\n"
				+ "</p>\r\n"
				+ "<p>It is not until $n = 23$, that a value exceeds one-million: $\\displaystyle \\binom {23} {10} = 1144066$.</p>\r\n"
				+ "<p>How many, not necessarily distinct, values of $\\displaystyle \\binom n r$ for $1 \\le n \\le 100$, are greater than one-million?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(23);
		} else {
			solution = solveProblem(100);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		Combinatorics calculator = new Combinatorics();
		int nCombinations = 0;
		BigInteger oneMillion = BigInteger.valueOf(1000000), combinations;

		for (int n = 1; n <= limit; n++) {
			for (int r = 1; r <= n; r++) {
				combinations = calculator.getCombinations(n, r);
				if (combinations.compareTo(oneMillion) > 0) {
//					System.out.printf("(%d, %d) = %s\n", n, r, combinations.toString());
					nCombinations++;
				}
			}
		}

		String solution = String.valueOf(nCombinations);
		System.out.printf("How many, not necessarily distinct, values of (n, r)"
				+ " are greater than one-million:\t%d\n-----\n", nCombinations);

//		for (int i = 0; i < limit; i++) {
//			System.out.printf("%d! = %s\n", i, calculator.factorial(i));
//		}

		return solution;
	}
}
