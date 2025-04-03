package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler36 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The decimal number, $585 = 1001001001_2$ (binary), is palindromic in both bases.</p>\r\n"
				+ "<p>Find the sum of all numbers, less than one million, which are palindromic in base $10$ and base $2$.</p>\r\n"
				+ "<p class=\"smaller\">(Please note that the palindromic number, in either base, may not include leading zeros.)</p>";
		String solution;

		if (isTest) {
			solution = solveProblem(585, 586);
		} else {
			solution = solveProblem(1, 1000000);
		}

		return new Euler(problem, solution);
	}

	private static String solveProblem(int start, int limit) {
		int totalSum = 0;

		for (int i = start; i < limit; i++) {
			if (isPalindrome(Integer.toString(i)) && isPalindrome(Integer.toBinaryString(i))) {
				totalSum += i;
			}
		}

		return String.valueOf(totalSum);
	}

	private static boolean isPalindrome(String str) {
		int left = 0, right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
