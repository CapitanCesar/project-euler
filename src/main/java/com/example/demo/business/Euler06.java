package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler06 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The sum of the squares of the first ten natural numbers is,</p>\r\n"
				+ "$$1^2 + 2^2 + ... + 10^2 = 385.$$\r\n"
				+ "<p>The square of the sum of the first ten natural numbers is,</p>\r\n"
				+ "$$(1 + 2 + ... + 10)^2 = 55^2 = 3025.$$\r\n"
				+ "<p>Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is $3025 - 385 = 2640$.</p>\r\n"
				+ "<p>Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(100);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int sumOfSquares = 0;

		for (int i = 1; i <= limit; i++) {
			sumOfSquares += (i * i);
		}

		int totalSum = (1 + limit) * (limit / 2);
		int squareOfSums = totalSum * totalSum;

		int totalDifference = squareOfSums - sumOfSquares;

		// Print the interval and the smallest multiple that is evenly divided by all
		String solution = String.valueOf(totalDifference);
//		System.out.printf("The difference between the [sum of the squares (%d)] and\nthe [square of the sum (%d)]\nis: %d\n-----\n",
//				sumOfSquares, squareOfSums, totalDifference);
		return solution;
	}
}
