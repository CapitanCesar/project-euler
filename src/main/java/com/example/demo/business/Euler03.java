package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler03 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The prime factors of $13195$ are $5, 7, 13$ and $29$.</p>\r\n"
				+ "<p>What is the largest prime factor of the number $600851475143$?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem((long) 13195);
		} else {
			solution = solveProblem(600851475143L);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(long bigNumber) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();

		ArrayList<Integer> allFactors = new ArrayList<>();
		long largestFactor = 0;
		long remainder = bigNumber;

		for (int prime : primes) {
			if (prime < bigNumber && bigNumber % prime == 0) {
				allFactors.add(prime);
				largestFactor = prime;
				remainder /= prime;
			}
		}

		if (remainder > largestFactor) {
			System.out.println(
					"The provided list of primes isn't big enough to find the largest prime factor.\nA non-prime (biggest) factor is returned instead.");
			largestFactor = remainder;
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(largestFactor);
//		System.out.printf("Largest factor:\t%d\n-----\nAll prime factors:\n", largestFactor);
//		PrimeGenerator.printList(allFactors);

		return solution;
	}
}
