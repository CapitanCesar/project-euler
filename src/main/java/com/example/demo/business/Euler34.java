package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.demo.records.Euler;

public class Euler34 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>$145$ is a curious number, as $1! + 4! + 5! = 1 + 24 + 120 = 145$.</p>\r\n"
				+ "<p>Find the sum of all numbers which are equal to the sum of the factorial of their digits.</p>\r\n"
				+ "<p class=\"smaller\">Note: As $1! = 1$ and $2! = 2$ are not sums they are not included.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(145);
		} else {
			solution = solveProblem(1000000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		HashMap<Character, Integer> factorials = new HashMap<>();
		for (int digit = 0; digit < 10; digit++) {
			factorials.put(String.valueOf(digit).charAt(0), Euler15.factorial(digit).intValue());
		}

		ArrayList<Integer> numbers = new ArrayList<>();

		for (int number = 3; number <= limit; number++) {
			int sum = 0;
			String numberAsString = String.valueOf(number);

			for (char digit : numberAsString.toCharArray()) {
				sum += factorials.get(digit);
			}

			if (sum == number) {
				numbers.add(number);
			}
		}

		int totalSum = 0;
		for (int number : numbers) {
			totalSum += number;
		}
		// Print the sum of numbers and the list itself
		String solution = String.valueOf(totalSum);
//		System.out.printf("Sum of numbers which are equal to the sum of the factorial of their digits:\t%d\n-----\n",
//				totalSum);
//		PrimeGenerator.printList(numbers);

		return solution;
	}
}
