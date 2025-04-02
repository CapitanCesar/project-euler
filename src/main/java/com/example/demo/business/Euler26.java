package com.example.demo.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.demo.records.Euler;

public class Euler26 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>A unit fraction contains $1$ in the numerator. The decimal representation of the unit fractions with denominators $2$ to $10$ are given:</p>\r\n"
				+ "\\begin{align}\r\n"
				+ "1/2 &amp;= 0.5\\\\\r\n"
				+ "1/3 &amp;=0.(3)\\\\\r\n"
				+ "1/4 &amp;=0.25\\\\\r\n"
				+ "1/5 &amp;= 0.2\\\\\r\n"
				+ "1/6 &amp;= 0.1(6)\\\\\r\n"
				+ "1/7 &amp;= 0.(142857)\\\\\r\n"
				+ "1/8 &amp;= 0.125\\\\\r\n"
				+ "1/9 &amp;= 0.(1)\\\\\r\n"
				+ "1/10 &amp;= 0.1\r\n"
				+ "\\end{align}\r\n"
				+ "<p>Where $0.1(6)$ means $0.166666\\cdots$, and has a $1$-digit recurring cycle. It can be seen that $1/7$ has a $6$-digit recurring cycle.</p>\r\n"
				+ "<p>Find the value of $d \\lt 1000$ for which $1/d$ contains the longest recurring cycle in its decimal fraction part.</p>";
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
		// ASUMPTION
		int LONGEST_CYCLE = 2000;

		// Compute the list of fractions
		BigDecimal[] fractions = new BigDecimal[limit - 1];
		for (int i = 2; i <= limit; i++) {
			BigDecimal denominator = new BigDecimal(i);
			fractions[i - 2] = BigDecimal.ONE.divide(denominator, LONGEST_CYCLE, RoundingMode.FLOOR).stripTrailingZeros();
		}

		int longestRecurringCycle = 0;
		int chosenDenominator = 0;
		
		// Find and count repeating decimal sequences
		for (int i = 0; i < fractions.length; i++) {
			int repeatLength = findRepeatingLength(fractions[i]);
			if (repeatLength > longestRecurringCycle) {
				longestRecurringCycle = repeatLength;
				chosenDenominator = i + 2;
			}
//			System.out.printf("Fraction %d/%d repeating length: %d\n", 1, i + 2, repeatLength);
		}

		// Print the length of the fractions array and the array itself
		String solution = String.valueOf(chosenDenominator);
		System.out.printf("1/%d has a length:\t%d\n-----\n", chosenDenominator, longestRecurringCycle);
		System.out.println(fractions[chosenDenominator-2]);
//		printList(fractions);

		return solution;
	}

	// Method to find repeating decimal length
	public static int findRepeatingLength(BigDecimal fraction) {
		// Convert to a string
		String fractionStr = fraction.toString();

		// Look for the decimal point
		int decimalIndex = fractionStr.indexOf('.');
		if (decimalIndex == -1) {
			return 0; // No decimal part means no repeating digits
		}

		String decimalPart = fractionStr.substring(decimalIndex + 1);

		// If the decimal part is empty (e.g., exact integer), return 0
		if (decimalPart.isEmpty()) {
			return 0;
		}

		// Step 1: Check if there's any repeating pattern at all
		// Loop through the length of the decimal part
		for (int nonRepeatLength = 0; nonRepeatLength < decimalPart.length(); nonRepeatLength++) {
			// Start looking for repeating patterns after the non-repeating part
			String remainingDecimalPart = decimalPart.substring(nonRepeatLength);

			// Step 2: Check for repeating patterns in the remaining decimal part
			for (int repeatLength = 1; repeatLength <= remainingDecimalPart.length() / 2; repeatLength++) {
				String repeatCandidate = remainingDecimalPart.substring(0, repeatLength);
				String repeated = repeatCandidate;
				while (repeated.length() < remainingDecimalPart.length()) {
					repeated += repeatCandidate;
				}

				// Check if the repeated pattern matches the remaining decimal part
				if (repeated.startsWith(remainingDecimalPart)) {
					return repeatLength; // Length of repeating part
				}
			}
		}
		// If no repeating part is found
		return 0;
	}

	public static void printList(BigDecimal[] fractions) {
		for (int i = 0; i < fractions.length; i++) {
			String str = fractions[i].toString();
			str = str.substring(0, Math.min(str.length(), 10));

			if (i % 20 == 19) {
				System.out.printf("%s\n", str);
			} else {
				System.out.printf("%s\t", str);
			}
		}
	}
}
