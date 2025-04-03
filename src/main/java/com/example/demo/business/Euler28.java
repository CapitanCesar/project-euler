package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler28 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Starting with the number $1$ and moving to the right in a clockwise direction a $5$ by $5$ spiral is formed as follows:</p>\r\n"
				+ "<p class=\"monospace center\"><span class=\"red\"><b>21</b></span> 22 23 24 <span class=\"red\"><b>25</b></span><br>\r\n"
				+ "20  <span class=\"red\"><b>7</b></span>  8  <span class=\"red\"><b>9</b></span> 10<br>\r\n"
				+ "19  6  <span class=\"red\"><b>1</b></span>  2 11<br>\r\n"
				+ "18  <span class=\"red\"><b>5</b></span>  4  <span class=\"red\"><b>3</b></span> 12<br><span class=\"red\"><b>17</b></span> 16 15 14 <span class=\"red\"><b>13</b></span></p>\r\n"
				+ "<p>It can be verified that the sum of the numbers on the diagonals is $101$.</p>\r\n"
				+ "<p>What is the sum of the numbers on the diagonals in a $1001$ by $1001$ spiral formed in the same way?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(5);
		} else {
			solution = solveProblem(1001);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the prime list from file
		int[][] matrix = new int[limit][limit];

		int num = limit * limit;
		int left = 0, right = limit - 1, top = 0, bottom = limit - 1;

		while (num > 0) {
			// Fill the top part (from left to right)
			for (int i = right; i >= left && num > 0; i--) {
				matrix[top][i] = num--;
			}
			top++;

			// Fill the right side (from up to down)
			for (int i = top; i <= bottom && num > 0; i++) {
				matrix[i][left] = num--;
			}
			left++;

			// Fill the bottom part (from right to left)
			for (int i = left; i <= right && num > 0; i++) {
				matrix[bottom][i] = num--;
			}
			bottom--;

			// Fill the left side (from down to up)
			for (int i = bottom; i >= top && num > 0; i--) {
				matrix[i][right] = num--;
			}
			right--;
		}

		if (limit == 5) {
			for (int i = 0; i < limit; i++) {
				for (int j = 0; j < limit; j++) {
					System.out.printf(matrix[i][j] + "\t");
				}
				System.out.println("");
			}
		}

		int sumOfDiagonals = 0;
		for (int i = 0; i < limit; i++) {
			sumOfDiagonals += matrix[i][i];
			if (i != limit - i - 1) {
				sumOfDiagonals += matrix[i][limit - i - 1];
			}
		}

		String solution = String.valueOf(sumOfDiagonals);
		return solution;
	}
}
