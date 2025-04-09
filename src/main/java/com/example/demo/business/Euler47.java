package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler47 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The first two consecutive numbers to have two distinct prime factors are:</p>\r\n"
				+ "\\begin{align}\r\n" + "14 &amp;= 2 \\times 7\\\\\r\n" + "15 &amp;= 3 \\times 5.\r\n"
				+ "\\end{align}\r\n"
				+ "<p>The first three consecutive numbers to have three distinct prime factors are:</p>\r\n"
				+ "\\begin{align}\r\n" + "644 &amp;= 2^2 \\times 7 \\times 23\\\\\r\n"
				+ "645 &amp;= 3 \\times 5 \\times 43\\\\\r\n" + "646 &amp;= 2 \\times 17 \\times 19.\r\n"
				+ "\\end{align}\r\n"
				+ "<p>Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(2);
			solution = solveProblem(3);
		} else {
			solution = solveProblem(4);
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

		int currentNumber = 2;
		boolean found = false;
		while (!found && currentNumber < 1000000) {
			boolean areComposite = true;

			for (int i = currentNumber; i < currentNumber + limit; i++) {
				HashSet<Integer> primeFactors = new HashSet<>();
				if (!primesSet.contains(i)) {
					primeFactorization(i, primes, primeFactors);
					if (primeFactors.size() == limit) {
						areComposite &= true;
					} else {
						areComposite = false;
					}
				} else {
					areComposite = false;
				}
			}

			if (areComposite) {
				ArrayList<ArrayList<Integer>> primeFactors = new ArrayList<>();
				for (int i = 0; i < limit; i++) {
					primeFactors.add(new ArrayList<>());
					primeFactorization(currentNumber + i, primes, primeFactors.get(i));
					System.out.printf("%d & ", currentNumber + i);
				}
				System.out.printf(" are composite numbers\nTheir prime factors are: %s respectively\n\n",
						primeFactors.toString());
				found = true;
			} else {
				currentNumber++;
			}
		}

		ArrayList<String> distinct = new ArrayList<>();
		for (int i = 0; i < limit; i++) {
			distinct.add(String.valueOf(currentNumber + i));
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(currentNumber);
		System.out.printf("The first %d consecutive integers to have %d distinct prime factors each are\n%s\n-----\n",
				limit, limit, distinct.toString());

		return solution;
	}

	@SuppressWarnings("unused")
	private static void primeFactorization(int number, ArrayList<Integer> primes, ArrayList<Integer> primeFactors) {
		if (number > 1) {
			for (int prime : primes) {
				if (number % prime == 0) {
					primeFactors.add(prime);
					primeFactorization(number / prime, primes, primeFactors);
					break;
				}
			}
		}
	}

	private static void primeFactorization(int number, ArrayList<Integer> primes, HashSet<Integer> primeFactors) {
		if (number > 1) {
			for (int prime : primes) {
				if (number % prime == 0) {
					primeFactors.add(prime);
					primeFactorization(number / prime, primes, primeFactors);
					break;
				}
			}
		}
	}
}
