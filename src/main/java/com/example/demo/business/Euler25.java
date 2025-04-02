package com.example.demo.business;

import java.math.BigInteger;
import java.util.ArrayList;

import com.example.demo.classes.FibonacciGenerator;
import com.example.demo.records.Euler;

public class Euler25 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The Fibonacci sequence is defined by the recurrence relation:</p>\r\n"
				+ "<blockquote>$F_n = F_{n - 1} + F_{n - 2}$, where $F_1 = 1$ and $F_2 = 1$.</blockquote>\r\n"
				+ "<p>Hence the first $12$ terms will be:</p>\r\n" + "\\begin{align}\r\n" + "F_1 &amp;= 1\\\\\r\n"
				+ "F_2 &amp;= 1\\\\\r\n" + "F_3 &amp;= 2\\\\\r\n" + "F_4 &amp;= 3\\\\\r\n" + "F_5 &amp;= 5\\\\\r\n"
				+ "F_6 &amp;= 8\\\\\r\n" + "F_7 &amp;= 13\\\\\r\n" + "F_8 &amp;= 21\\\\\r\n" + "F_9 &amp;= 34\\\\\r\n"
				+ "F_{10} &amp;= 55\\\\\r\n" + "F_{11} &amp;= 89\\\\\r\n" + "F_{12} &amp;= 144\r\n" + "\\end{align}\r\n"
				+ "<p>The $12$th term, $F_{12}$, is the first term to contain three digits.</p>\r\n"
				+ "<p>What is the index of the first term in the Fibonacci sequence to contain $1000$ digits?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(3);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		ArrayList<BigInteger> fibonacciSequence = FibonacciGenerator.readFile();

		String chosenNumber = "0";
		int chosenNumberIndex = 0;
		for (int i = 0; i < fibonacciSequence.size(); i++) {
			String numberAsString = fibonacciSequence.get(i).toString();
			if (numberAsString.length() >= limit) {
				chosenNumber = numberAsString;
				chosenNumberIndex = i + 1;
				break;
			}
		}

		// Print the Nth Fibonacci number to contain limit digits
		String solution = String.valueOf(chosenNumberIndex);
		System.out.printf("The %dth Fibonacci number:\t%s\n-----\n", chosenNumberIndex, chosenNumber);
//		FibonacciGenerator.printList(fibonacciSequence);

		return solution;
	}
}
