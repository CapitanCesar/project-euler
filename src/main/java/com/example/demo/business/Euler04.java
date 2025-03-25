package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler04 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>A palindromic number reads the same both ways. The largest palindrome made from the product of two $2$-digit numbers is $9009 = 91 \\times 99$.</p>\r\n"
				+ "<p>Find the largest palindrome made from the product of two $3$-digit numbers.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(2);
		} else {
			solution = solveProblem(3);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int maxNumber = (int) Math.pow(10, limit);
		int biggestPalindromicNumber = 0;
		int[] factors = { 0, 0 };

		int product;
		for (int i = 0; i < maxNumber; i++) {
			for (int j = 0; j < maxNumber; j++) {
				product = i * j;
				if (isPalindrome(product) && biggestPalindromicNumber < product) {
					biggestPalindromicNumber = product;
					factors[0] = i;
					factors[1] = j;
				}
			}
		}

		// Print the palindromic product and the list of factors
		String solution = String.valueOf(biggestPalindromicNumber);
		System.out.printf("Biggest palindromic product:\t%d\n-----\n[%d, %d]\n",
				biggestPalindromicNumber, factors[0], factors[1]);
		return solution;
	}

	public static boolean isPalindrome(int number) {
		int remainder = number, lastDigit;
		ArrayList<Integer> digits = new ArrayList<>();
		while (remainder > 0) {
			lastDigit = remainder % 10;
			remainder = (remainder - lastDigit) / 10;
			digits.add(lastDigit);
		}

		boolean flag = true;

		while (digits.size() > 1) {
			int first = digits.removeFirst(), last = digits.removeLast();
			if (first != last) {
				flag = false;
			}
		}

		return flag;
	}
}
