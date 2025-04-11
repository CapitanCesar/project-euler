package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler49 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The arithmetic sequence, $1487, 4817, 8147$, in which each of the terms increases by $3330$, is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the $4$-digit numbers are permutations of one another.</p>\r\n"
				+ "<p>There are no arithmetic sequences made up of three $1$-, $2$-, or $3$-digit primes, exhibiting this property, but there is one other $4$-digit increasing sequence.</p>\r\n"
				+ "<p>What $12$-digit number do you form by concatenating the three terms in this sequence?</p>";
//		System.out.println(problem);
		String solution = solveProblem(isTest);

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(boolean stopEarly) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile();
		HashSet<Integer> primesSet = new HashSet<>(primes);

		String stage1 = findThreePermutations(1111, primesSet);
		
		if (stopEarly) {
			return stage1;
		}

		int stage2 = Integer.parseInt(stage1.substring(0, 4));
		String solution = findThreePermutations(stage2 + 1, primesSet);

		return solution;
	}

	private static String findThreePermutations(int startNumber, HashSet<Integer> primesSet) {
		int[] numbers = new int[3];
		int step;

		for (numbers[0] = startNumber; numbers[0] < 10000; numbers[0]++) {
			if (!isPrime(numbers[0], primesSet)) {
				continue;
			}

			for (numbers[1] = numbers[0] + 1; numbers[1] < 10000; numbers[1]++) {
				if (!isPrime(numbers[1], primesSet) || !isPermutation(numbers[0], numbers[1])) {
					continue;
				}

				step = numbers[1] - numbers[0];
				numbers[2] = numbers[1] + step;

				if (numbers[2] < 10000 && isPrime(numbers[2], primesSet) && isPermutation(numbers[1], numbers[2])) {
//					System.out.printf("%d, %d, %d\n", numbers[0], numbers[1], numbers[2]);
					return String.valueOf(numbers[0]) + String.valueOf(numbers[1]) + String.valueOf(numbers[2]);
				}
			}
		}
		
		return String.valueOf(numbers[0]) + String.valueOf(numbers[1]) + String.valueOf(numbers[2]);
	}

	private static boolean isPrime(int number, HashSet<Integer> primesSet) {
		return primesSet.contains(number);
	}

	private static boolean isPermutation(int first, int second) {
		String firstString = String.valueOf(first), secondString = String.valueOf(second), firstHalf, secondHalf;
		int splittingIndex;
		for (char digit : firstString.toCharArray()) {
			splittingIndex = secondString.indexOf(digit);
			if (splittingIndex == -1) {
				return false;
			}
			firstHalf = secondString.substring(0, splittingIndex);
			secondHalf = secondString.substring(Math.min(secondString.length(), splittingIndex + 1));
			secondString = firstHalf + secondHalf;
		}
		return true;
	}
}
