package com.example.demo.business;

import com.example.demo.classes.LetterMap;
import com.example.demo.records.Euler;

public class Euler17 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>If the numbers $1$ to $5$ are written out in words: one, two, three, four, five, then there are $3 + 3 + 5 + 4 + 4 = 19$ letters used in total.</p>\r\n"
				+ "<p>If all the numbers from $1$ to $1000$ (one thousand) inclusive were written out in words, how many letters would be used? </p>\r\n"
				+ "<br><p class=\"note\"><b>NOTE:</b> Do not count spaces or hyphens. For example, $342$ (three hundred and forty-two) contains $23$ letters and $115$ "
				+ "(one hundred and fifteen) contains $20$ letters. The use of \"and\" when writing out numbers is in compliance with British usage.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(5);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Compute the Power Digit Sum
		LetterMap letterCountMap = new LetterMap(false);
//		LetterMap letterCountMap = new LetterMap();

		int letterCount = 0, nDigits, onesDigit, twoDigits, tensDigit, hundredsDigit;
		for (int i = 1; i <= limit; i++) {
			nDigits = String.valueOf(i).length();
			if (i == 100) {
				// Special case. One hundred by itself
				letterCount += (letterCountMap.get(1) + letterCountMap.get(100));
			} else if (i == 1000) {
				// Special case. One thousand by itself
				letterCount += (letterCountMap.get(1) + letterCountMap.get(1000));
			} else if (letterCountMap.containsKey(i)) {
				// Special case. Numbers with proper name, such as eleven
				letterCount += letterCountMap.get(i);
			} else if (nDigits == 2) {
				// Numbers with two digits apart from special cases
				onesDigit = i % 10;
				tensDigit = i - onesDigit;
				// Sum both digit letters, such as twenty_one
				letterCount += (letterCountMap.get(tensDigit) + letterCountMap.get(onesDigit));
			} else if (nDigits == 3) {
				/*
				 * Sum the hundreds digits. The following letters are added to the count:
				 * letterCountMap.get(hundredsDigit) HUNDRED AND
				 */
				hundredsDigit = (i - (i % 100)) / 100;
				letterCount += (letterCountMap.get(hundredsDigit) + letterCountMap.get(100) + letterCountMap.get(-1, false));

				// Numbers with three digits apart from special cases
				onesDigit = i % 10;
				hundredsDigit = i - (i % 100);
				twoDigits = i - hundredsDigit;

				if (twoDigits == 0) {
					// Special cases, such as 200, 300, ...
					letterCount -= letterCountMap.get(-1, false);
				} else if (letterCountMap.containsKey(twoDigits)) {
//					letterCountMap.get(-1, true);
					letterCountMap.get(-1);
					// If the last two digits have their proper name
					letterCount += letterCountMap.get(twoDigits);
				} else {
//					letterCountMap.get(-1, true);
					letterCountMap.get(-1);
					// Numbers with two digits apart from special cases
					tensDigit = twoDigits - onesDigit;
					// Sum both digit letters, such as twenty_one
					letterCount += (letterCountMap.get(tensDigit) + letterCountMap.get(onesDigit));
				}
			} else {
//				System.out.println(
//						"The number of digits has exceeded 3. So the sum will be returned as if you inputted 1000\n"
//								+ "The Number Letter Counts of 1-1000 is:");
			}
//			System.out.println();
		}
		// Print the number letter counts
		String solution = String.valueOf(letterCount);
//		System.out.printf("The Number Letter Counts is:\t%d\n-----\n", letterCount);

		return solution;
	}
}
