package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler52 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>It can be seen that the number, $125874$, and its double, $251748$, contain exactly the same digits, but in a different order.</p>\r\n"
				+ "<p>Find the smallest positive integer, $x$, such that $2x$, $3x$, $4x$, $5x$, and $6x$, contain the same digits.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(125870, 2, 125875);
		} else {
			solution = solveProblem(1, 6);
		}

		return new Euler(problem, solution);
	}

	private static String solveProblem(int start, int multiplications) {
		return solveProblem(start, multiplications, Integer.MAX_VALUE / multiplications);
	}

	private static String solveProblem(int start, int multiplications, int end) {
		int number = start, chosenNumber = 0, product;
		ArrayList<Integer> numbers = new ArrayList<>();
		boolean found = false, isCandidate;

		while (!found && number < end) {
			isCandidate = true;
			product = 2;

			while (isCandidate && product <= multiplications) {
				isCandidate &= Euler49.isPermutation(number, product * number);
				product++;
			}

			if (isCandidate) {
				found = true;
				chosenNumber = number;
				numbers = new ArrayList<>();
				for (product = 1; product <= multiplications; product++) {
					numbers.add(product * number);
				}
			}
			number++;
		}

		String solution = String.valueOf(chosenNumber);
		System.out.printf("The smallest prime %d and its permutations are also its multiples\n%s\n",
				chosenNumber, numbers);

		return solution;
	}
}
