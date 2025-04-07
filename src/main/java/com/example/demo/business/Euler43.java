package com.example.demo.business;

import java.math.BigInteger;
import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler43 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The number, $1406357289$, is a $0$ to $9$ pandigital number because it is made up of each of the digits $0$ to $9$ in some order, but it also has a rather interesting sub-string divisibility property.</p>\r\n"
				+ "<p>Let $d_1$ be the $1$<sup>st</sup> digit, $d_2$ be the $2$<sup>nd</sup> digit, and so on. In this way, we note the following:</p>\r\n"
				+ "<ul><li>$d_2d_3d_4=406$ is divisible by $2$</li>\r\n"
				+ "<li>$d_3d_4d_5=063$ is divisible by $3$</li>\r\n"
				+ "<li>$d_4d_5d_6=635$ is divisible by $5$</li>\r\n"
				+ "<li>$d_5d_6d_7=357$ is divisible by $7$</li>\r\n"
				+ "<li>$d_6d_7d_8=572$ is divisible by $11$</li>\r\n"
				+ "<li>$d_7d_8d_9=728$ is divisible by $13$</li>\r\n"
				+ "<li>$d_8d_9d_{10}=289$ is divisible by $17$</li>\r\n"
				+ "</ul><p>Find the sum of all $0$ to $9$ pandigital numbers with this property.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem("1406357289");
		} else {
			solution = solveProblem(null);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(String startNumber) {
		BigInteger sum = BigInteger.ZERO;

		ArrayList<String> permutations = new ArrayList<>();
		if (startNumber != null) {
			permutations.add(startNumber);
		} else {
			Euler24.permute("0123456789", 0, 9, permutations);
		}

		for (String perm : permutations) {
			if (isInteresting(perm)) {
				BigInteger interestingPandigitial = new BigInteger(perm);
				sum = sum.add(interestingPandigitial);
			}
		}

		String solution = sum.toString();
//		System.out.printf("the sum of all 0 to 9 pandigital numbers with this property is:\t%d\n-----\n", sum);

		return solution;
	}

	private static boolean isInteresting(String pandigitalNumber) {
		int[] primeNumbers = { 2, 3, 5, 7, 11, 13, 17 };
		int i = 0;
		boolean isInteresting = true;

		while (isInteresting && i + 3 < pandigitalNumber.length()) {
			String subString = pandigitalNumber.substring(i + 1, i + 4);
			int number = Integer.parseInt(subString);
//			System.out.printf("%d is divisible by %d\n", number, primeNumbers[i]);

			isInteresting &= (number % primeNumbers[i] == 0);
			i++;
		}

		return isInteresting;
	}
}
