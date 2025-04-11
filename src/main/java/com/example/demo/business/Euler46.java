package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler46 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.</p>\r\n"
				+ "\\begin{align}\r\n"
				+ "9 = 7 + 2 \\times 1^2\\\\\r\n"
				+ "15 = 7 + 2 \\times 2^2\\\\\r\n"
				+ "21 = 3 + 2 \\times 3^2\\\\\r\n"
				+ "25 = 7 + 2 \\times 3^2\\\\\r\n"
				+ "27 = 19 + 2 \\times 2^2\\\\\r\n"
				+ "33 = 31 + 2 \\times 1^2\r\n"
				+ "\\end{align}\r\n"
				+ "<p>It turns out that the conjecture was false.</p>\r\n"
				+ "<p>What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(34);
		} else {
			solution = solveProblem(10000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();

		int i = 2, j, integerSQRT;
		boolean found = false;
		while (!found && i < limit) {
			// Ignore primes and even composite numbers
			if (!primes.contains(i) && i % 2 != 0) {
				for (int prime : primes) {
					j = i - prime;

					// End when no prime satisfies the formula
					if (j < 0) {
						found = true;
						break;
					}

					integerSQRT = hasIntegerSquareRoot(j);
					if (integerSQRT > 0) {
//						System.out.printf("%d = %d + 2 x %d^2\n", i, prime, integerSQRT);
						break;
					}
				}
			}
			i++;
		}

		// Print the sum of multiples and the list itself
		String solution;
		if (found) {
			solution = String.valueOf(i - 1);
		} else {
			solution = String.valueOf(found);
		}
//		System.out.printf("found=%b\n-----\n", found);

		return solution;
	}

	private static int hasIntegerSquareRoot(double d) {
		// Negative numbers don't have real square roots
		if (d < 0 && d % 2 == 0) {
			return -1;
		}
		// Get the integer part of the square root
		int sqrt = (int) Math.sqrt(d / 2.0);
		// Check if the square of the integer part equals the original number
		if (sqrt * sqrt == d / 2) {
			return sqrt;
		}
		return -1;
	}
}
