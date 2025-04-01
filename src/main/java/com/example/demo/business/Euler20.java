package com.example.demo.business;

import java.math.BigInteger;

import com.example.demo.records.Euler;

public class Euler20 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>$n!$ means $n \\times (n - 1) \\times \\cdots \\times 3 \\times 2 \\times 1$.</p>\r\n"
				+ "<p>For example, $10! = 10 \\times 9 \\times \\cdots \\times 3 \\times 2 \\times 1 = 3628800$,<br>and the sum of the digits in the number $10!$ is $3 + 6 + 2 + 8 + 8 + 0 + 0 = 27$.</p>\r\n"
				+ "<p>Find the sum of the digits in the number $100!$.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(100);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int n) {
		// Compute n!
		BigInteger nFactorial = Euler15.factorial(n);
		String nFactorialString = nFactorial.toString();
		int sumOfDigits = 0;
		for (char digit : nFactorialString.toCharArray()) {
			sumOfDigits += Short.valueOf(digit + "");
		}

		// Print the total sum of digits in n!
		String solution = String.valueOf(sumOfDigits);
//		System.out.printf("Sum of Digits:\t%d in %s\n", sumOfDigits, nFactorialString);

		return solution;
	}
}
