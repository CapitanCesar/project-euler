package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler05 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>$2520$ is the smallest number that can be divided by each of the numbers from $1$ to $10$ without any remainder.</p>\r\n"
				+ "<p>What is the smallest positive number that is <strong class=\"tooltip\">evenly divisible<span class=\"tooltiptext\">divisible with no remainder</span></strong> by all of the numbers from $1$ to $20$?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(20);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int bigNumber = 1;

		for (int i = 2; i <= limit; i++) {
			bigNumber *= i;
			if (bigNumber < 0) {
				bigNumber = Integer.MAX_VALUE;
				break;
			}
		}
		
		int smallestMultiple = bigNumber;
		boolean isEvenlyDivisible;
		for (int i = 2; i <= bigNumber; i++) {
			isEvenlyDivisible = true;
			for (int j = 1; j <= limit; j++) {
				if (i % j != 0) {
					isEvenlyDivisible = false;
					break;
				}
			}
			if (isEvenlyDivisible) {
				smallestMultiple = i;
				break;
			}
		}

		// Print the interval and the smallest multiple that is evenly divided by all
		String solution = String.valueOf(smallestMultiple);
		System.out.printf("The smallest evenly divisible number between [1, %d] is: %d\n-----\n",
				limit, smallestMultiple);
		return solution;
	}
}
