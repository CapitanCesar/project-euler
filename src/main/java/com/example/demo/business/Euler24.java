package com.example.demo.business;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.demo.records.Euler;

public class Euler24 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:</p>\r\n"
				+ "<p class=\"center\">012   021   102   120   201   210</p>\r\n"
				+ "<p>What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?</p>";
//			System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(3);
		} else {
			solution = solveProblem(9);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		ArrayList<String> permutations = new ArrayList<>();
		String digits = "";
		int n = 0;
		for (int i = 0; i <= limit; i++) {
			digits += String.valueOf(i);
			n++;
		}

		@SuppressWarnings("unused")
		int nPermutations = permute(digits, 0, n - 1, permutations);

		permutations.sort(null);
		String chosenPermutation;
		if (limit == 9) {
			chosenPermutation = permutations.get(999999);
		} else {
			chosenPermutation = permutations.get(21);
		}

		// Print the Nth lexicographic permutation of the digits and the list itself
		String solution = String.valueOf(chosenPermutation);
//		System.out.printf("The lexicographic permutation is:\t%s\n-----\n", chosenPermutation);
//		printList(permutations);

		return solution;
	}

	public static void printList(ArrayList<String> permutations) {
		// Get the iterator
	    Iterator<String> it = permutations.iterator();

	    // Print the array
	    while (it.hasNext()) {
	    	for (int i = 0; i < 20; i++) {
	    		if (it.hasNext()) {
		    		System.out.printf("%s\t", it.next());
	    		}
	    	}
	    	System.out.print('\n');
    	}
	}

	public static int permute(String str, int l, int r, ArrayList<String> list) {
		if (list.size() >= Integer.MAX_VALUE - 1) {
			System.out.println("WARN: Maximum list size reached. The returning list is incomplete");
			return list.size();
		}

		if (l == r) {
			list.add(str);
		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r, list);
				str = swap(str, l, i);
			}
		}
		return list.size();
	}

	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

}
