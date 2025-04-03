package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler37 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The number $3797$ has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: $3797$, $797$, $97$, and $7$. Similarly we can work from right to left: $3797$, $379$, $37$, and $3$.</p>\r\n"
				+ "<p>Find the sum of the only eleven primes that are both truncatable from left to right and right to left.</p>\r\n"
				+ "<p class=\"smaller\">NOTE: $2$, $3$, $5$, and $7$ are not considered to be truncatable primes.</p>";
		String solution;

		if (isTest) {
			solution = solveProblem(3797, 3798);
		} else {
			solution = solveProblem(11, 1000000);
		}

		return new Euler(problem, solution);
	}

	private static String solveProblem(int start, int limit) {
		Set<Integer> primeSet = new HashSet<>(PrimeGenerator.readFile());
		ArrayList<Integer> truncatablePrimes = new ArrayList<>();
		int sum = 0;
		int count = 0;
		int number = start;

		while (count < 11 && number < limit) {
			if (isTruncatablePrime(number, primeSet)) {
				truncatablePrimes.add(number);
				sum += number;
				count++;
			}
			number += 2; // Skip even numbers for efficiency
		}

		return String.valueOf(sum);
	}

	private static boolean isTruncatablePrime(int num, Set<Integer> primeSet) {
		if (!primeSet.contains(num)) {
			return false;
		}

		// Check truncation from left
		String numStr = Integer.toString(num);
		for (int i = 1; i < numStr.length(); i++) {
			if (!primeSet.contains(Integer.parseInt(numStr.substring(i)))) {
				return false;
			}
		}

		// Check truncation from right
		for (int i = numStr.length() - 1; i > 0; i--) {
			if (!primeSet.contains(Integer.parseInt(numStr.substring(0, i)))) {
				return false;
			}
		}

		return true;
	}
}
