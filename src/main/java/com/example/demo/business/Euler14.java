package com.example.demo.business;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.demo.records.Euler;

public class Euler14 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The following iterative sequence is defined for the set of positive integers:</p>\r\n"
				+ "<ul style=\"list-style-type:none;\">\r\n" + "<li>$n \\to n/2$ ($n$ is even)</li>\r\n"
				+ "<li>$n \\to 3n + 1$ ($n$ is odd)</li></ul>\r\n"
				+ "<p>Using the rule above and starting with $13$, we generate the following sequence:\r\n"
				+ "$$13 \\to 40 \\to 20 \\to 10 \\to 5 \\to 16 \\to 8 \\to 4 \\to 2 \\to 1.$$</p>\r\n"
				+ "<p>It can be seen that this sequence (starting at $13$ and finishing at $1$) contains $10$ terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at $1$.</p>\r\n"
				+ "<p>Which starting number, under one million, produces the longest chain?</p>\r\n"
				+ "<p class=\"note\"><b>NOTE:</b> Once the chain starts the terms are allowed to go above one million.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem((int) 2e5);
		} else {
			solution = solveProblem((int) 1e6);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		int startingNumber = 1, currentCollatz = 0, biggestCollatz = 1;
//		ArrayList<Integer> collatzSequence = new ArrayList<>();
		HashMap<BigInteger, Integer> collatzMapping = new HashMap<>();
		collatzMapping.put(BigInteger.ONE, 1);

		for (int i = 1; i < limit; i++) {
			currentCollatz = getCollatzSequenceSize(BigInteger.valueOf(i), collatzMapping);
			collatzMapping.put(BigInteger.valueOf(i), currentCollatz);
			if (currentCollatz > biggestCollatz) {
				startingNumber = i;
				biggestCollatz = currentCollatz;
			}
		}

//		System.out.println(collatzMapping.toString());
//		collatzSequence = iterateCollatzSequence(startingNumber);

		// Print the starting number that produces the biggest Collatz sequence and the
		// list itself
		String solution = String.valueOf(startingNumber);
//		System.out.printf("Starting number:\t%d\n-----\nCollatz sequence of length (%d)\n",
//				startingNumber, biggestCollatz);
//		PrimeGenerator.printList(collatzSequence);

		return solution;
	}

	@SuppressWarnings("unused")
	private static int getCollatzSequenceSize(int currentNumber, HashMap<Integer, Integer> collatzMapping) {
		int size = 0;
		while (!collatzMapping.containsKey(currentNumber)) {
			size++;
			if (currentNumber % 2 == 0) {
				currentNumber /= 2;
			} else {
				currentNumber = 3 * currentNumber + 1;
			}
		}
		return size + collatzMapping.get(currentNumber);
	}

	private static int getCollatzSequenceSize(BigInteger currentNumber, HashMap<BigInteger, Integer> collatzMapping) {
		BigInteger[] division = new BigInteger[2]; // quotient, remainder
		BigInteger three = BigInteger.ONE.add(BigInteger.TWO);
		int size = 0;
		while (!collatzMapping.containsKey(currentNumber)) {
			size++;
			division = currentNumber.divideAndRemainder(BigInteger.TWO);
			if (division[1].equals(BigInteger.ZERO)) {
				currentNumber = division[0];
			} else {
				currentNumber = currentNumber.multiply(three).add(BigInteger.ONE);
			}
		}
		return size + collatzMapping.get(currentNumber);
	}

	@SuppressWarnings("unused")
	private static ArrayList<Integer> iterateCollatzSequence(int currentNumber) {
		ArrayList<Integer> collatzSequence = new ArrayList<>();
		while (currentNumber != 1) {
			collatzSequence.add(currentNumber);
			if (currentNumber % 2 == 0) {
				currentNumber /= 2;
			} else {
				currentNumber = 3 * currentNumber + 1;
			}
		}
		collatzSequence.add(currentNumber);
		return collatzSequence;
	}
}
