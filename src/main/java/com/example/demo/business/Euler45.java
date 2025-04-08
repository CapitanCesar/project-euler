package com.example.demo.business;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.example.demo.records.Euler;

public class Euler45 {
	static final BigDecimal FOUR = BigDecimal.valueOf(4);
	static final BigDecimal SIX = BigDecimal.valueOf(6);
	static final BigInteger EIGHT = BigInteger.valueOf(8);
	static final BigInteger TWENTY_FOUR = BigInteger.valueOf(24);

	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:</p>\r\n"
				+ "<table><tr><td>Triangle</td>\r\n" + "<td> </td>\r\n" + "<td>$T_n=n(n+1)/2$</td>\r\n"
				+ "<td> </td>\r\n" + "<td>$1, 3, 6, 10, 15, \\dots$</td>\r\n" + "</tr><tr><td>Pentagonal</td>\r\n"
				+ "<td> </td>\r\n" + "<td>$P_n=n(3n - 1)/2$</td>\r\n" + "<td> </td>\r\n"
				+ "<td>$1, 5, 12, 22, 35, \\dots$</td>\r\n" + "</tr><tr><td>Hexagonal</td>\r\n" + "<td> </td>\r\n"
				+ "<td>$H_n=n(2n - 1)$</td>\r\n" + "<td> </td>\r\n" + "<td>$1, 6, 15, 28, 45, \\dots$</td>\r\n"
				+ "</tr></table><p>It can be verified that $T_{285} = P_{165} = H_{143} = 40755$.</p>\r\n"
				+ "<p>Find the next triangle number that is also pentagonal and hexagonal.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(285);
		} else {
			solution = solveProblem(10000000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		ArrayList<String> triangleNumbers = new ArrayList<>(), naturalNumbers = new ArrayList<>();
		BigInteger bigLimit = BigInteger.valueOf(limit);
		BigInteger currentTriangleNumber = BigInteger.ZERO;
		BigInteger currentNaturalNumber = BigInteger.ONE;
		boolean found = false;

		// Start generating triangle numbers on-the-fly
		while (!found && currentNaturalNumber.compareTo(bigLimit) <= 0) {
			// Compute the current triangle number as the sum of natural numbers
			currentTriangleNumber = currentTriangleNumber.add(currentNaturalNumber);

			// Check if it's pentagon and hexagonal (on-the-fly)
			if (isPentagonal(currentTriangleNumber) && isHexagonal(currentTriangleNumber)) {
				triangleNumbers.add(currentTriangleNumber.toString());
				naturalNumbers.add(currentNaturalNumber.toString());
			}

			// End when a third solution has been found
			if (triangleNumbers.size() > 2) {
				found = true;
			} else {
				currentNaturalNumber = currentNaturalNumber.add(BigInteger.ONE);				
			}
		}

		// Print the triangle number that has at least [limit] divisors
		String solution = triangleNumbers.getLast();
//		System.out.printf("T_%s = %s is also pentagon and hexagonal\n", naturalNumbers.getLast(), triangleNumbers.getLast());
//		System.out.println("All triangular numbers: " + triangleNumbers + "\n-----");
		return solution;
	}

	private static boolean isPentagonal(BigInteger number) {
		// Check if the number is pentagon: P_n = n(3n - 1)/2
		//                                  3n*2 - n - 2P_n = 0
		// Solve the quadratic equation: n = (1 + square_root(1 + 24 * number)) / 6

		// Step 1: compute square_root(1 + 24 * number)
		BigInteger radicand = BigInteger.ONE.add(TWENTY_FOUR.multiply(number));
		BigDecimal s = new BigDecimal(radicand).sqrt(MathContext.DECIMAL128); // square_root(1 + 24 * number)
		
		if (isInteger(s)) {
			// Step 2: compute (1 + s) / 6
			BigDecimal n = BigDecimal.ONE.add(s).divide(SIX, 10, RoundingMode.FLOOR);
			
			if (isInteger(n)) {
//				System.out.printf("P_%d = %d\n", n.toBigIntegerExact(), number);
				return true;
			}
		}

		return false;
	}

	private static boolean isHexagonal(BigInteger number) {
		// Check if the number is hexagonal: H_n = n(2n - 1)
		//                                   2n*2 - n - H_n = 0
		// Solve the quadratic equation: n = (1 + square_root(1 + 8 * number)) / 4

		// Step 1: compute square_root(1 + 8 * number)
		BigInteger radicand = BigInteger.ONE.add(EIGHT.multiply(number));
		BigDecimal s = new BigDecimal(radicand).sqrt(MathContext.DECIMAL128); // square_root(1 + 8 * number)

		if (isInteger(s)) {
			// Step 2: compute (1 + s) / 4
			BigDecimal n = BigDecimal.ONE.add(s).divide(FOUR, 10, RoundingMode.FLOOR);

			if (isInteger(n)) {
//				System.out.printf("H_%d = %d\n", n.toBigIntegerExact(), number);
				return true;
			}
		}

		return false;
	}

	private static boolean isInteger(BigDecimal value) {
		// Strip trailing zeros and check if the result is an integer
		BigDecimal strippedValue = value.stripTrailingZeros();
		return strippedValue.scale() <= 0; // If scale is <= 0, it's an integer
	}
}
