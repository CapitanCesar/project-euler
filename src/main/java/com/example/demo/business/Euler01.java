package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler01 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>If we list all the natural numbers below $10$ that are multiples of $3$ or $5$, we get $3, 5, 6$ and $9$. The sum of these multiples is $23$.</p>\r\n"
				+ "<p>Find the sum of all the multiples of $3$ or $5$ below $1000$.</p>";
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
	 * @param limit
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();
		ArrayList<Integer> smallPrimes = new ArrayList<>();
		for (int prime : primes) {
			if (prime < limit) {
				smallPrimes.add(prime);
			}
		}

		// Compute list of multiples of 3 or 5
		ArrayList<Integer> multiples = new ArrayList<>();
		int sumOfMultiples = 8;
		multiples.add(3);
		multiples.add(5);

		for (int i = 6; i < limit; i++) {
			// If the number is prime, ignore it
			if (!smallPrimes.contains(i)) {
				if (i % 3 == 0) {
					sumOfMultiples += i;
					multiples.add(i);
				}
				// If the number is multiple of 5, add it
				else if (i % 5 == 0) {
					sumOfMultiples += i;
					multiples.add(i);
				}
			}
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(sumOfMultiples);
//		System.out.printf("Sum of multiples: %d\nList:\t", sumOfMultiples);
//		PrimeGenerator.printList(multiples);

		return solution;
	}
}
