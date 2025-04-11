package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler41 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>We shall say that an $n$-digit number is pandigital if it makes use of all the digits $1$ to $n$ exactly once. For example, $2143$ is a $4$-digit pandigital and is also prime.</p>\r\n"
				+ "<p>What is the largest $n$-digit pandigital prime that exists?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(4);
		} else {
			/*
			 * Recall that if the sum of the digits is divisible by 3, the number is
			 * divisible by 3. Therefore, it can be guaranteed that the pandigital primes
			 * must have 1, 4 or 7 digits (since all pandigital numbers with 2, 3, 5, 6, 8,
			 * and 9 digits are divisible by 3, so they cannot be prime).
			 */
//			solution = solveProblem(9);
//			solution = solveProblem(8);
			solution = solveProblem(7);
//			solution = solveProblem(6);
//			solution = solveProblem(5);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		ArrayList<Integer> primes = PrimeGenerator.readFile();
		ArrayList<Integer> pandigitalPrimes = new ArrayList<>();
		StringBuilder digits = new StringBuilder();

		for (int i = 1; i <= limit; i++) {
			digits.append(i);
		}

		ArrayList<String> permutations = new ArrayList<>();
		Euler24.permute(digits.toString(), 0, digits.length() - 1, permutations);

		int biggestPandigitalPrime = 0;
		for (String perm : permutations) {
			int num = Integer.parseInt(perm);
			if (isPrime(num, primes)) {
				pandigitalPrimes.add(num);
				biggestPandigitalPrime = Math.max(biggestPandigitalPrime, num);
			}
		}

		String solution = String.valueOf(biggestPandigitalPrime);
//	    System.out.printf("The largest %d-digit pandigital prime is:\t%d\n-----\n", limit, biggestPandigitalPrime);
//	    PrimeGenerator.printList(pandigitalPrimes);

		return solution;
	}

	private static boolean isPrime(int num, ArrayList<Integer> primes) {
		if (num < 2) {
			return false;
		}
		int sqrtNum = (int) Math.sqrt(num);
		for (int prime : primes) {
			if (prime > sqrtNum) {
				break;
			}
			if (num % prime == 0) {
				return false;
			}
		}
		return true;
	}

}
