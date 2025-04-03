package com.example.demo.business;

import java.math.BigInteger;

import com.example.demo.records.Euler;

public class Euler15 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Starting in the top left corner of a $2 \\times 2$ grid, and only being able to move to the right and down, there are exactly $6$ routes to the bottom right corner.</p>\r\n"
				+ "<div class=\"center\">\r\n"
				+ "<img src=\"resources/images/0015.png?1678992052\" class=\"dark_img\" alt=\"\"></div>\r\n"
				+ "<p>How many such routes are there through a $20 \\times 20$ grid?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(2);
		} else {
			solution = solveProblem(20);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int n) {
		// Central binomial coefficient
		BigInteger twoNFactorial = factorial(2 * n); // (2n)!

		BigInteger nFactorial = factorial(n);
		BigInteger nFactorialSquared = nFactorial.pow(2); // (n!)^2

		BigInteger numberOfCombinations = twoNFactorial.divide(nFactorialSquared);

		// Print the amount of combinations
		String solution = String.valueOf(numberOfCombinations);
//		System.out.printf("#Permutations:\t%d\n-----\n", numberOfCombinations);

		return solution;
	}

	public static BigInteger factorial(int n) {
		if (n <= 0) {
			// Base case: factorial of 0 is 1
			return BigInteger.ONE;
		} else {
			// Recursive case
			BigInteger nMinusOneFactorial = factorial(n - 1);
			BigInteger nAsBigInteger = BigInteger.valueOf(n);
			return nAsBigInteger.multiply(nMinusOneFactorial);
		}
	}
}
