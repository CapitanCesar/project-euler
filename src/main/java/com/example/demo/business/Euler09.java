package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler09 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>A Pythagorean triplet is a set of three natural numbers, $a \\lt b \\lt c$, for which,\r\n"
				+ "$$a^2 + b^2 = c^2.$$</p>\r\n"
				+ "<p>For example, $3^2 + 4^2 = 9 + 16 = 25 = 5^2$.</p>\r\n"
				+ "<p>There exists exactly one Pythagorean triplet for which $a + b + c = 1000$.<br>Find the product $abc$.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(12);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int[] pythagoreanTriplet = {0, 0, 0};
		for (int i = 1; i < limit; i++) {
			for (int j = i + 1; j < limit; j++) {
				for (int k = j + 1; k < limit; k++) {
					if (i + j + k == limit && i * i + j * j == k * k) {
						pythagoreanTriplet[0] = i;
						pythagoreanTriplet[1] = j;
						pythagoreanTriplet[2] = k;
						break;
					}
				}
				if (pythagoreanTriplet[0] != 0) {
					break;
				}
			}
			if (pythagoreanTriplet[0] != 0) {
				break;
			}
		}

		int product = 1;
		for (int i : pythagoreanTriplet) {
			product *= i;
		}
		
		// Print the product of the pythagorean triplet and the list itself
		String solution = String.valueOf(product);
//		System.out.printf("%d + %d + %d = %d\n-----\n", pythagoreanTriplet[0], pythagoreanTriplet[1], pythagoreanTriplet[2], limit);
//		System.out.printf("%d x %d x %d = %d\n-----\n", pythagoreanTriplet[0], pythagoreanTriplet[1], pythagoreanTriplet[2], product);

		return solution;
	}
}
