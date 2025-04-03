package com.example.demo.business;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.records.Euler;

public class Euler32 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>We shall say that an $n$-digit number is pandigital if it makes use of all the digits $1$ to $n$ exactly once; for example, the $5$-digit number, $15234$, is $1$ through $5$ pandigital.</p>\r\n"
				+ "\r\n"
				+ "<p>The product $7254$ is unusual, as the identity, $39 \\times 186 = 7254$, containing multiplicand, multiplier, and product is $1$ through $9$ pandigital.</p>\r\n"
				+ "\r\n"
				+ "<p>Find the sum of all products whose multiplicand/multiplier/product identity can be written as a $1$ through $9$ pandigital.</p>\r\n"
				+ "\r\n"
				+ "<div class=\"note\">HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.</div>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	public static String solveProblem(int limit) {
		int solution = findPandigitalProductsSum();
		return String.valueOf(solution);
	}

	public static int findPandigitalProductsSum() {
		Set<Integer> products = new HashSet<>();
		String digits = "123456789";
		permute(digits.toCharArray(), 0, products);
		return products.stream().mapToInt(Integer::intValue).sum();
	}

	private static void permute(char[] arr, int index, Set<Integer> products) {
		if (index == arr.length) {
			checkAndAddProduct(new String(arr), products);
			return;
		}
		for (int i = index; i < arr.length; i++) {
			swap(arr, i, index);
			permute(arr, index + 1, products);
			swap(arr, i, index);
		}
	}

	private static void checkAndAddProduct(String perm, Set<Integer> products) {
		// 1-digit × 4-digit = 4-digit case
		int multiplicand1 = Character.getNumericValue(perm.charAt(0));
		int multiplier1 = Integer.parseInt(perm.substring(1, 5));
		int product1 = Integer.parseInt(perm.substring(5));

		if (multiplicand1 * multiplier1 == product1) {
			products.add(product1);
		}

		// 2-digit × 3-digit = 4-digit case
		int multiplicand2 = Integer.parseInt(perm.substring(0, 2));
		int multiplier2 = Integer.parseInt(perm.substring(2, 5));
		int product2 = Integer.parseInt(perm.substring(5));

		if (multiplicand2 * multiplier2 == product2) {
			products.add(product2);
		}
	}

	private static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
