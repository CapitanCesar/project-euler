package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler10 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The sum of the primes below $10$ is $2 + 3 + 5 + 7 = 17$.</p>\r\n"
				+ "<p>Find the sum of all the primes below two million.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem((int) 2e6);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();

		ArrayList<Integer> smallPrimes = new ArrayList<>();
		long totalSum = 0L;

		for (int prime : primes) {
			if (prime < limit) {
				smallPrimes.add(prime);
				totalSum += prime;
			}
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(totalSum);
		System.out.printf("Sum of primes:\t%d\n-----\n", totalSum);
//		PrimeGenerator.printList(smallPrimes);

		return solution;
	}
}
