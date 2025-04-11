package com.example.demo.business;

import java.math.BigInteger;

import com.example.demo.records.Euler;

public class Euler48 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The series, $1^1 + 2^2 + 3^3 + \\cdots + 10^{10} = 10405071317$.</p>\r\n"
				+ "<p>Find the last ten digits of the series, $1^1 + 2^2 + 3^3 + \\cdots + 1000^{1000}$.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		BigInteger sumOfSelfPowers = BigInteger.ZERO;
		BigInteger selfPower;

		for (int number = 1; number <= limit; number++) {
			selfPower = BigInteger.valueOf(number).pow(number);
			sumOfSelfPowers = sumOfSelfPowers.add(selfPower);
		}

		String digits = sumOfSelfPowers.toString();
		String lastTenDigits = digits.substring(digits.length() - 10);

		// Print the sum of self powers up to limit
		String solution = lastTenDigits;
//		System.out.printf("1^1 + 2^2 + 3^3 + ... + %d^%d = %s.\n-----\n",
//				limit, limit, digits);

		return solution;
	}
}
