package com.example.demo.business;

import java.util.ArrayList;

import com.example.demo.classes.BreathFirst;
import com.example.demo.classes.SimpleTree;
import com.example.demo.records.Euler;

public class Euler18 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is $23$.</p>\r\n"
				+ "<p class=\"monospace center\"><span class=\"red\"><b>3</b></span><br><span class=\"red\"><b>7</b></span> 4<br>\r\n"
				+ "2 <span class=\"red\"><b>4</b></span> 6<br>\r\n"
				+ "8 5 <span class=\"red\"><b>9</b></span> 3</p>\r\n" + "<p>That is, $3 + 7 + 4 + 9 = 23$.</p>\r\n"
				+ "<p>Find the maximum total from top to bottom of the triangle below:</p>\r\n"
				+ "<p class=\"note\"><b>NOTE:</b> As there are only $16384$ routes, it is possible to solve this problem by trying every route."
				+ "However, <a href=\"problem=67\">Problem 67</a>, is the same challenge with a triangle containing one-hundred rows;"
				+ "it cannot be solved by brute force, and requires a clever method! ;o)</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			String bigPyramid = "03<br>\r\n"
					+ "07 04<br>\r\n"
					+ "02 04 06<br>\r\n"
					+ "08 05 09 03";
			solution = solveProblem(bigPyramid);
		} else {
			String bigPyramid = "75<br>\r\n"
					+ "95 64<br>\r\n"
					+ "17 47 82<br>\r\n"
					+ "18 35 87 10<br>\r\n"
					+ "20 04 82 47 65<br>\r\n"
					+ "19 01 23 75 03 34<br>\r\n"
					+ "88 02 77 73 07 63 67<br>\r\n"
					+ "99 65 04 28 06 16 70 92<br>\r\n"
					+ "41 41 26 56 83 40 80 70 33<br>\r\n"
					+ "41 48 72 33 47 32 37 16 94 29<br>\r\n"
					+ "53 71 44 65 25 43 91 52 97 51 14<br>\r\n"
					+ "70 11 33 28 77 73 17 78 39 68 17 57<br>\r\n"
					+ "91 71 52 38 17 14 91 43 58 50 27 29 48<br>\r\n"
					+ "63 66 04 68 89 53 67 30 73 16 69 87 40 31<br>\r\n"
					+ "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
			solution = solveProblem(bigPyramid);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(String bigPyramid) {
		// Process the big 16384 routes in the pyramid

		ArrayList<Short> numbers = new ArrayList<>();
		String cleanedPyramid = bigPyramid.replaceAll("<.+>", "");

		int i = 0;
		char character;
		while (i < cleanedPyramid.length()) {
			character = cleanedPyramid.charAt(i);
			if (character != '\r' && character != '\n' && character != ' ') {
				numbers.add(Short.valueOf(cleanedPyramid.substring(i, i + 2)));
				i++;
			}
			i++;
		}

		SimpleTree searchTree = null;
		for (Short number : numbers) {
			if (searchTree == null) {
				searchTree = new SimpleTree(number);
			} else {
				searchTree.insert(number);
			}
		}

		BreathFirst solver = new BreathFirst(searchTree.getRoot(), "longestPath");
		ArrayList<Integer> solutionPath = solver.solve();

		int totalSum = 0;
		for (int node : solutionPath) {
			totalSum += node;
		}

		// Print the longest path of the pyramid and the total sum
		String solution = String.valueOf(totalSum);
//		System.out.printf("The longest path is:\t%d\n-----\n", totalSum);
//		PrimeGenerator.printList(solutionPath);

		return solution;
	}
}
