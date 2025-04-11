package com.example.demo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler51 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>By replacing the 1<sup>st</sup> digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.</p>\r\n"
				+ "<p>By replacing the 3<sup>rd</sup> and 4<sup>th</sup> digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.</p>\r\n"
				+ "<p>Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(5, 7);
		} else {
			solution = solveProblem(6, 8);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit, int childrenThreshold) {
		// Gather the prime list from file
		ArrayList<Integer> primes = PrimeGenerator.readFile(), children = new ArrayList<>();
		HashMap<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();
		HashSet<Integer> primesSet = new HashSet<>(primes);

		for (int i = 0; i <= limit; i++) {
			map.put(i, getDigitCombinations(i));
		}

		for (int prime : primes) {
			int nDigits = Integer.toString(prime).length();
			if (limit < nDigits || nDigits < limit || children.contains(prime)) {
				continue;
			}

			for (ArrayList<Integer> positions : map.get(nDigits)) {
				getFamily(prime, childrenThreshold, positions, primesSet, children);				
			}
		}

		int smallest;
		if (children.isEmpty()) {
			smallest = 0;
		} else {
			smallest = Collections.min(children);
		}
		// Print the smallest prime and the ten generated numbers, yielding the family
		String solution = String.valueOf(smallest);
		System.out.printf("Smallest prime: %d\nFamiliy: %s\n-----\n", smallest, children.toString());

		return solution;
	}

	private static void getFamily(int prime, int childrenThreshold,
			ArrayList<Integer> positions, HashSet<Integer> primesSet, ArrayList<Integer> children) {
		ArrayList<Integer> family = replace(prime, positions), members = new ArrayList<>();
		for (int child : family) {
			if (primesSet.contains(child)) {
				members.add(child);
			}
		}

		if (members.size() >= childrenThreshold) {
			children.addAll(members);
			System.out.printf("%d has a family of %d members %s\n", prime, members.size(), members.toString());
		}
	}

	private static ArrayList<Integer> replace(int number, ArrayList<Integer> positions) {
		ArrayList<Integer> family = new ArrayList<>();
		char[] originalDigits = Integer.toString(number).toCharArray(), newDigits;
		String newString;
		int newNumber;

		for (int i = 0; i < 10; i++) {
			newDigits = originalDigits.clone();
			newString = "";

			for (int position : positions) {
				newDigits[position] = Integer.toString(i).charAt(0);
			}

			for (char digit : newDigits) {
				newString += digit;
			}

			if (newString.charAt(0) == '0') {
				continue;
			}
			newNumber = Integer.parseInt(newString);
//			System.out.println(newNumber + positions.toString());
			family.add(newNumber);
		}
		return family;
	}

	private static ArrayList<ArrayList<Integer>> getDigitCombinations(int numberOfDigits) {
	    ArrayList<ArrayList<Integer>> allCombinations = new ArrayList<>();

	    // Return empty list if the input is invalid (0 or negative)
	    if (numberOfDigits <= 0) {
	        return allCombinations;
	    }

	    // Total combinations = 2^numberOfDigits
	    // We start from 1 to skip the empty combination
	    int totalCombinations = 1 << numberOfDigits;

	    for (int i = 1; i < totalCombinations; i++) {
	        ArrayList<Integer> combination = new ArrayList<>();

	        // For each bit in the current number, check if it's set
	        // If so, include the corresponding digit (from 0 to numberOfDigits - 1)
	        for (int j = 0; j < numberOfDigits; j++) {
	            if ((i & (1 << j)) != 0) {
	                combination.add(j);
	            }
	        }

	        // Add the generated combination to the result list
	        allCombinations.add(combination);
	    }

	    return allCombinations;
	}
}
