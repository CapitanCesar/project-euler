package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.records.Euler;

public class Euler42 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The $n$<sup>th</sup> term of the sequence of triangle numbers is given by, $t_n = \\frac12n(n+1)$; so the first ten triangle numbers are:\r\n"
				+ "$$1, 3, 6, 10, 15, 21, 28, 36, 45, 55, \\dots$$</p>\r\n"
				+ "<p>By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is $19 + 11 + 25 = 55 = t_{10}$. If the word value is a triangle number then we shall call the word a triangle word.</p>\r\n"
				+ "<p>Using <a href=\"resources/documents/0042_words.txt\">words.txt</a> (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?</p>";
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
		ArrayList<Integer> triangleNumbers = new ArrayList<>();
		triangleNumbers.add(1);

		for (int i = 2; i < limit; i++) {
			triangleNumbers.add(triangleNumbers.getLast() + i);
		}

		HashSet<Integer> triangleNumbersSet = new HashSet<>(triangleNumbers);

		// Gather the words list from file
		ArrayList<String> words = Euler22.readFile("words.txt");

		ArrayList<String> triangleWords = new ArrayList<>();
		for (String word : words) {
			int nameScore = 0;
			for (char letter : word.toCharArray()) {
				int letterScore = letter - 64;
				nameScore += letterScore;
			}

			if (triangleNumbersSet.contains(nameScore)) {
				triangleWords.add(word);
			}
		}

		// Print the names
		String solution = String.valueOf(triangleWords.size());
		System.out.printf("There are %s triangle words\n-----\n", solution);
		Euler22.printList(triangleWords);

		return solution;
	}
}
