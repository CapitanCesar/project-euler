package com.example.demo.business;

import java.math.BigInteger;
import java.util.ArrayList;

import com.example.demo.classes.FibonacciGenerator;
import com.example.demo.records.Euler;

public class Euler02 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with $1$ and $2$, the first $10$ terms will be:\r\n"
				+ "$$1, 2, 3, 5, 8, 13, 21, 34, 55, 89, \\dots$$</p>\r\n"
				+ "<p>By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.</p>\r\n";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem((int) 4e6);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param limit
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<BigInteger> fibSequence = FibonacciGenerator.readFile();

		ArrayList<BigInteger> evenFibNumbers = new ArrayList<>();
		BigInteger totalSum = BigInteger.ZERO;
		BigInteger bigLimit = new BigInteger(String.valueOf(limit));

		for (BigInteger fibNumber : fibSequence) {
			if (fibNumber.compareTo(bigLimit) == -1 && fibNumber.mod(BigInteger.TWO) == BigInteger.ZERO) {
				evenFibNumbers.add(fibNumber);
				totalSum = totalSum.add(fibNumber);
			}
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(totalSum);
//		System.out.printf("Sum of even fibonacci numbers:\t%d\n-----\n", totalSum);
//		FibonacciGenerator.printList(evenFibNumbers);

		return solution;
	}
}
