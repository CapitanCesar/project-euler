package com.example.demo.business;

import java.math.BigInteger;

import com.example.demo.records.Euler;

public class Euler16 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>$2^{15} = 32768$ and the sum of its digits is $3 + 2 + 7 + 6 + 8 = 26$.</p>\r\n"
				+ "<p>What is the sum of the digits of the number $2^{1000}$?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(15);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int n) {
		// Compute the Power Digit Sum
		BigInteger powerOfTwo = BigInteger.TWO.pow(n);

		char[] digits = powerOfTwo.toString().toCharArray();

		short digit;
		int totalSum = 0;
		for (char dChar : digits) {
			digit = Short.valueOf(dChar + "");
			totalSum += digit;
		}

		// Print the amount of combinations
		String solution = String.valueOf(totalSum);
//		System.out.printf("The Power Digit Sum of 2^%d is :\t%d\n-----\n", n, totalSum);

		return solution;
	}
}