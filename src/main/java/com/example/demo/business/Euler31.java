package com.example.demo.business;

import java.util.HashMap;
import java.util.HashSet;

import com.example.demo.records.Euler;

public class Euler31 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:</p>\r\n"
				+ "<blockquote>1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).</blockquote>\r\n"
				+ "<p>It is possible to make £2 in the following way:</p>\r\n"
				+ "<blockquote>1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p</blockquote>\r\n"
				+ "<p>How many different ways can £2 be made using any number of coins?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(5);
		} else {
			solution = solveProblem(200);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		HashMap<String, Double> coins = new HashMap<>();
		coins.put("1p", 0.01); // onePence
		coins.put("2p", 0.02); // twoPence
		coins.put("5p", 0.05); // fivePence
		coins.put("10p", 0.10); // tenPence
		coins.put("20p", 0.20); // twentyPence
		coins.put("50p", 0.50); // fiftyPence
		coins.put("£1", 1.00); // onePound
		coins.put("£2", 2.00); // twoPound

		HashSet<String> combinations = new HashSet<>();
		String newCombination;
		for (int onePence = 0; onePence <= limit; onePence++) {
			for (int twoPence = 0; twoPence <= limit / 2; twoPence++) {
				for (int fivePence = 0; fivePence <= limit / 5; fivePence++) {
					for (int tenPence = 0; tenPence <= limit / 10; tenPence++) {
						for (int twentyPence = 0; twentyPence <= limit / 20; twentyPence++) {
							for (int fiftyPence = 0; fiftyPence <= limit / 50; fiftyPence++) {
								for (int onePound = 0; onePound <= limit / 100; onePound++) {
									for (int twoPound = 0; twoPound <= limit / 200; twoPound++) {
										if (onePence + 2 * twoPence + 5 * fivePence + 10 * tenPence + 20 * twentyPence
												+ 50 * fiftyPence + 100 * onePound + 200 * twoPound == limit) {
											newCombination = String.format("%d,%d,%d,%d,%d,%d,%d,%d", onePence,
													twoPence, fivePence, tenPence, twentyPence, fiftyPence, onePound,
													twoPound);
											combinations.add(newCombination);
//											System.out.println(newCombination);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// Print the #different ways that £2 be made using any number of coins
		String solution = String.valueOf(combinations.size());
//		System.out.printf("#different ways:\t%d\n-----\n", combinations.size());
//		System.out.println(combinations);

		return solution;
	}
}
