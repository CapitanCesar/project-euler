package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler40 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>An irrational decimal fraction is created by concatenating the positive integers:\r\n"
				+ "$$0.12345678910{\\color{red}\\mathbf 1}112131415161718192021\\cdots$$</p>\r\n"
				+ "<p>It can be seen that the $12$<sup>th</sup> digit of the fractional part is $1$.</p>\r\n"
				+ "<p>If $d_n$ represents the $n$<sup>th</sup> digit of the fractional part, find the value of the following expression.\r\n"
				+ "$$d_1 \\times d_{10} \\times d_{100} \\times d_{1000} \\times d_{10000} \\times d_{100000} \\times d_{1000000}$$</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(11);
		} else {
			solution = solveProblem(200000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		String champernowneConstant = "0.";

		for (int i = 1; i <= limit; i++) {
			champernowneConstant += String.valueOf(i);
		}

		String solution;
		if (limit == 11) {
			solution = champernowneConstant.charAt(12 + 1) + "";
		} else {
			int product = 1;
			for (int i = 1; i <= 1000000; i *= 10) {
				int digit = Integer.valueOf(champernowneConstant.charAt(i + 1) + "");
				product *= digit;
			}
			solution = String.valueOf(product);
			System.out.printf("Product of digits in powers of ten places:\t%d\n-----\n%s", product, champernowneConstant);
		}

		return solution;
	}
}
