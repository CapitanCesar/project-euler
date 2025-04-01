package com.example.demo.business;

import java.util.HashSet;

import com.example.demo.records.Euler;

public class Euler21 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Let $d(n)$ be defined as the sum of proper divisors of $n$ (numbers less than $n$ which divide evenly into $n$).<br>\r\n"
				+ "If $d(a) = b$ and $d(b) = a$, where $a \\ne b$, then $a$ and $b$ are an amicable pair and each of $a$ and $b$ are called amicable numbers.</p>\r\n"
				+ "<p>For example, the proper divisors of $220$ are $1, 2, 4, 5, 10, 11, 20, 22, 44, 55$ and $110$; therefore $d(220) = 284$. The proper divisors of $284$ are $1, 2, 4, 71$ and $142$; so $d(284) = 220$.</p>\r\n"
				+ "<p>Evaluate the sum of all the amicable numbers under $10000$.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(220);
		} else {
			solution = solveProblem(10000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int sumOfAmicableNumbers = 0, currentDivisors = 0, amicableCandidate;
		HashSet<Integer> amicableNumbers = new HashSet<>();

		for (int currentNumber = 2; currentNumber <= limit; currentNumber++) {
			if (amicableNumbers.contains(currentNumber)){
				continue;
			}

			// Gather the prime list from file
			currentDivisors = 0;
			// Loop from 1 to sqrt(n)
			for (int i = 1; i * i <= currentNumber; i++) {
				if (currentNumber % i == 0) {
					if (i == 1) {
						// Don't count the currentNumber itself
						currentDivisors++;
					} else {
						// Count both divisors: i and n/i
						currentDivisors += (i + currentNumber / i);
					}
				}
			}

			amicableCandidate = currentDivisors;
			if (amicableCandidate == currentNumber) {
				continue;
			}

			currentDivisors = 0;
			// Loop from 1 to sqrt(n)
			for (int i = 1; i * i <= amicableCandidate; i++) {
				if (amicableCandidate % i == 0) {
					if (i == 1) {
						// Don't count the currentNumber itself
						currentDivisors++;
					} else {
						// Count both divisors: i and n/i
						currentDivisors += (i + amicableCandidate / i);
					}
				}
			}

			if (currentDivisors == currentNumber) {
				System.out.printf("Amicable numbers: %d, %d\n", currentNumber, amicableCandidate);
				amicableNumbers.add(currentNumber);
				amicableNumbers.add(amicableCandidate);
			}
		}

		for (int number : amicableNumbers) {
			sumOfAmicableNumbers += number;
		}
		// Print the sum of all the amicable numbers under limit
		String solution = String.valueOf(sumOfAmicableNumbers);

		return solution;
	}
}
