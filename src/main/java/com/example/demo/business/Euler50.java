package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler50 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The prime $41$, can be written as the sum of six consecutive primes:</p>\r\n"
				+ "$$41 = 2 + 3 + 5 + 7 + 11 + 13.$$\r\n"
				+ "<p>This is the longest sum of consecutive primes that adds to a prime below one-hundred.</p>\r\n"
				+ "<p>The longest sum of consecutive primes below one-thousand that adds to a prime, contains $21$ terms, and is equal to $953$.</p>\r\n"
				+ "<p>Which prime, below one-million, can be written as the sum of the most consecutive primes?</p>";
		//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(100);
			solution = solveProblem(1000);
		} else {
			solution = solveProblem(1000000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();
		HashSet<Integer> primesSet = new HashSet<>(primes);

		int maxLength = 0;
		int maxPrime = 0;

		for (int i = 0; i < primes.size(); i++) {
			ListIterator<Integer> iter = primes.listIterator(i);
			int sum = 0;
			int count = 0;

			while (iter.hasNext()) {
				sum += iter.next();
				count++;

				if (sum >= limit) {
					break;
				}

				if (primesSet.contains(sum) && count > maxLength) {
					maxLength = count;
					maxPrime = sum;
				}
			}
		}

		String solution = String.valueOf(maxPrime);

		return solution;
	}
}
