package com.example.demo.business;

import java.util.HashMap;

import com.example.demo.records.Euler;

public class Euler39 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>If $p$ is the perimeter of a right angle triangle with integral length sides, $\\{a, b, c\\}$, there are exactly three solutions for $p = 120$.</p>\r\n"
				+ "<p>$\\{20,48,52\\}$, $\\{24,45,51\\}$, $\\{30,40,50\\}$</p>\r\n"
				+ "<p>For which value of $p \\le 1000$, is the number of solutions maximised?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(3, 120);
		} else {
			solution = solveProblem(3, 1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int start, int limit) {
		HashMap<Integer, Integer> solutions = new HashMap<>();
		@SuppressWarnings("unused")
		int maxP = 0, maxNSolutions = 0;
		for (int p = start; p <= limit; p++) {
			for (int c1 = 1; c1 < limit; c1++) {
				for (int c2 = c1 + 1; c2 < limit; c2++) {
					int h = p - c1 - c2;
					if (h > 0 && c1 * c1 + c2 * c2 == h * h) {
						int nSolutions = solutions.getOrDefault(p, 0);
						if (nSolutions != 0) {
							solutions.replace(p, nSolutions + 1);
						} else {
							solutions.put(p, 1);
						}
					}
				}
			}
			
			if (solutions.getOrDefault(p, 0) > maxNSolutions) {
				maxP = p;
				maxNSolutions = solutions.get(p);
			}
		}

		// Print the number of right angle triangles that can be formed given a fixed perimeter
		String solution = String.valueOf(maxNSolutions);
//		System.out.printf("With a perimeter of p=%d, %d right angle triangles can be formed\n-----\n", maxP, maxNSolutions);
//		System.out.println(solutions);

		return solution;
	}
}
