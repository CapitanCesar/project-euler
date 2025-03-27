package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.example.demo.records.Euler;

public class Euler15 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Starting in the top left corner of a $2 \\times 2$ grid, and only being able to move to the right and down, there are exactly $6$ routes to the bottom right corner.</p>\r\n"
				+ "<div class=\"center\">\r\n"
				+ "<img src=\"resources/images/0015.png?1678992052\" class=\"dark_img\" alt=\"\"></div>\r\n"
				+ "<p>How many such routes are there through a $20 \\times 20$ grid?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(2);
		} else {
			solution = solveProblem(20);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
	    String str = "";
	    for (int i = 0; i < limit; i++) {
	    	str += 'D';
	    	str += 'R';
	    }
	    int n = str.length();
	    HashSet<String> set = new HashSet<>();
	    int numberOfPermutations = permute(str, 0, n-1, set);

		// Print the amount of permutations and the list itself
		String solution = String.valueOf(numberOfPermutations);
		System.out.printf("#Permutations:\t%d\nList:\n-----\n", numberOfPermutations);
        ArrayList<String> list = new ArrayList<>(set);
		printList(list);

	    return solution;
	}

    public static int factorial(int n) {
        if (n == 0) { // Base case: factorial of 0 is 1
            return 1;
        } else {
            return n * factorial(n - 1); // Recursive case
        }
    }

	public static int permute(String str, int l, int r, HashSet<String> list) {
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

	public static int permute(String str, int l, int r, ArrayList<String> list) {
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

	private static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	private static void printList(ArrayList<String> list) {
		// Get the iterator
	    Iterator<String> it = list.iterator();

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
}
