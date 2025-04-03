package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler33 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The fraction $49/98$ is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that $49/98 = 4/8$, which is correct, is obtained by cancelling the $9$s.</p>\r\n"
				+ "<p>We shall consider fractions like, $30/50 = 3/5$, to be trivial examples.</p>\r\n"
				+ "<p>There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.</p>\r\n"
				+ "<p>If the product of these four fractions is given in its lowest common terms, find the value of the denominator.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		ArrayList<String> fractions = new ArrayList<>();

		for (int numerator = 11; numerator < 100; numerator++) {
			for (int denominator = numerator + 1; denominator < 100; denominator++) {
				double fraction = computeFraction(numerator, denominator);

				// Extract digits
				int firstNum = numerator / 10, secondNum = numerator % 10;
				int firstDen = denominator / 10, secondDen = denominator % 10;

				if (secondNum != 0 && secondDen != 0) { // Avoid trivial cases
				    int[][] conditions = {
				        {firstNum, firstDen, secondNum, secondDen}, // X_ / X_
				        {firstNum, secondDen, secondNum, firstDen}, // X_ / _X
				        {secondNum, firstDen, firstNum, secondDen}, // _X / X_
				        {secondNum, secondDen, firstNum, firstDen}  // _X / _X
				    };

				    for (int[] cond : conditions) {
				        if (cond[0] == cond[1] && computeFraction(cond[2], cond[3]) == fraction) {
				            fractions.add(String.format("%d/%d", numerator, denominator));
				            break; // No need to check further once a match is found
				        }
				    }
				}
			}
		}

		ArrayList<Integer> primes = PrimeGenerator.readFile();

		int numeratorOfProduct = 1;
		int denominatorOfProduct = 1;
		for (String fraction : fractions) {
			String[] fractionArray = fraction.split("/");
			numeratorOfProduct *= Integer.valueOf(fractionArray[0]);
			denominatorOfProduct *= Integer.valueOf(fractionArray[1]);
		}
		
		denominatorOfProduct = computeLowestCommonTerms(numeratorOfProduct, denominatorOfProduct, primes);

		String solution = String.valueOf(denominatorOfProduct);
//		System.out.println(fractions);
		return solution;
	}
	
	public static double computeFraction(int numerator, int denominator) {
		return (double) numerator / (double) denominator;
	}

	public static int computeLowestCommonTerms(int numerator, int denominator, ArrayList<Integer> primes) {
		int index = 0, prime = 2;
		while (prime < denominator) {
			prime = primes.get(index++);
			if (numerator % prime == 0 && denominator % prime == 0) {
				numerator /= prime;
				denominator /= prime;
				index = 0;
			}
		}

//		System.out.println(numerator + "/" + denominator + "\n");
		return denominator;
	}
}
