package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler07 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>By listing the first six prime numbers: $2, 3, 5, 7, 11$, and $13$, we can see that the $6$th prime is $13$.</p>\r\n"
				+ "<p>What is the $10\\,001$st prime number?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(6);
		} else {
			solution = solveProblem(10001);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int index) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();

		int chosenPrime;
		if (index < 1) {
			System.out.println("The provided index to search isn't available ¿Maybe a negative number?.\n"
					+ "The biggest available prime is returned instead.");
			chosenPrime = primes.getLast();
		}
		else if (primes.size() <= index) {
			System.out.println("The provided list of primes isn't big enough to find the largest prime.\n"
					+ "The biggest available prime is returned instead.");
			chosenPrime = primes.getLast();
		} else {
			chosenPrime = primes.get(index - 1);
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(chosenPrime);
		System.out.printf("The %dth prime is: %d\n-----\n", index, chosenPrime);

		return solution;
	}
}
