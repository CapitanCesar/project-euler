package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler08 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The four adjacent digits in the $1000$-digit number that have the greatest product are $9 \\times 9 \\times 8 \\times 9 = 5832$.</p>\r\n"
				+ "\r\n"
				+ "<p>Find the thirteen adjacent digits in the $1000$-digit number that have the greatest product. What is the value of this product?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(4);
		} else {
			solution = solveProblem(13);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Process the big 1000-digit number
		String bigNumber = "73167176531330624919225119674426574742355349194934\r\n"
				+ "96983520312774506326239578318016984801869478851843\r\n"
				+ "85861560789112949495459501737958331952853208805511\r\n"
				+ "12540698747158523863050715693290963295227443043557\r\n"
				+ "66896648950445244523161731856403098711121722383113\r\n"
				+ "62229893423380308135336276614282806444486645238749\r\n"
				+ "30358907296290491560440772390713810515859307960866\r\n"
				+ "70172427121883998797908792274921901699720888093776\r\n"
				+ "65727333001053367881220235421809751254540594752243\r\n"
				+ "52584907711670556013604839586446706324415722155397\r\n"
				+ "53697817977846174064955149290862569321978468622482\r\n"
				+ "83972241375657056057490261407972968652414535100474\r\n"
				+ "82166370484403199890008895243450658541227588666881\r\n"
				+ "16427171479924442928230863465674813919123162824586\r\n"
				+ "17866458359124566529476545682848912883142607690042\r\n"
				+ "24219022671055626321111109370544217506941658960408\r\n"
				+ "07198403850962455444362981230987879927244284909188\r\n"
				+ "84580156166097919133875499200524063689912560717606\r\n"
				+ "05886116467109405077541002256983155200055935729725\r\n"
				+ "71636269561882670428252483600823257530420752963450";

		short[] digits = new short[1000];

		long product, greatestProduct = 0L;
		int i = 0, j = 0;
		char character;
		for (i = 0; i < bigNumber.length(); i++) {
			character = bigNumber.charAt(i);
			if (character != '\r' && character != '\n') {
				digits[j++] = Short.valueOf(character + "");
			}
		}

		for (i = limit - 1; i < 1000; i++) {
			product = 1;
			for (j = 0; j < limit; j++) {
				product *= digits[i - j];
			}
			if (product > greatestProduct) {
				greatestProduct = product;
			}
		}

		// Print the sum of multiples and the list itself
		String solution = String.valueOf(greatestProduct);
//		System.out.printf("The greatest product of %d adjacent digits is: %d\n-----\n", limit, greatestProduct);

		return solution;
	}
}
