package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler30 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:\r\n"
				+ "\\begin{align}\r\n" + "1634 &amp;= 1^4 + 6^4 + 3^4 + 4^4\\\\\r\n"
				+ "8208 &amp;= 8^4 + 2^4 + 0^4 + 8^4\\\\\r\n" + "9474 &amp;= 9^4 + 4^4 + 7^4 + 4^4\r\n"
				+ "\\end{align}\r\n" + "</p><p class=\"smaller\">As $1 = 1^4$ is not a sum it is not included.</p>\r\n"
				+ "<p>The sum of these numbers is $1634 + 8208 + 9474 = 19316$.</p>\r\n"
				+ "<p>Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(4);
		} else {
			solution = solveProblem(5);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		ArrayList<Integer> NthPowers = new ArrayList<>();

		HashMap<Character, Integer> Nths = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			Nths.put(String.valueOf(i).charAt(0), (int) Math.pow(i, limit));
		}

		int totalSum = 0;
		int endingNumber = (int) 1e6;

		for (int number = 2; number < endingNumber; number++) {
			String numberAsString = String.valueOf(number);

			int sumOfDigits = 0;
			for (char digit : numberAsString.toCharArray()) {
				sumOfDigits += Nths.get(digit);
			}

			if (sumOfDigits == number) {
				NthPowers.add(number);
				totalSum += number;
			}
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(totalSum);
		System.out.printf("Sum of Nth powers:\t%d\n-----\n", totalSum);
		PrimeGenerator.printList(NthPowers);

		return solution;
	}
}
