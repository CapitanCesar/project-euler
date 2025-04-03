package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.classes.PrimeGenerator;
import com.example.demo.records.Euler;

public class Euler35 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>The number, $197$, is called a circular prime because all rotations of the digits: $197$, $971$, and $719$, are themselves prime.</p>\r\n"
				+ "<p>There are thirteen such primes below $100$: $2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79$, and $97$.</p>\r\n"
				+ "<p>How many circular primes are there below one million?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(100);
		} else {
			solution = solveProblem(1000000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
	    ArrayList<Integer> primes = PrimeGenerator.readFile();
	    HashSet<Integer> primesSet = new HashSet<>(primes), circularPrimes = new HashSet<>();
	    int primeIndex = 0, prime = 2;

	    while (primeIndex < primes.size() && prime < limit) {
	        prime = primes.get(primeIndex++);

	        if (isValidCandidate(prime, circularPrimes)) {
	            ArrayList<Integer> rotations = getRotations(prime);
	            boolean isCircularPrime = true;

	            for (int rotation : rotations) {
	                if (!primesSet.contains(rotation)) {
	                    isCircularPrime = false;
	                    break;
	                }
	            }
	            if (isCircularPrime) {
	                circularPrimes.addAll(rotations);
	            }
	        }
	    }

	    // Print the number of circular primes below limit and the list itself
	    String solution = String.valueOf(circularPrimes.size());
		System.out.printf("Amount of circular primes below %d:\t%s\n-----\n", limit, solution);
	    System.out.println(circularPrimes);
	    return solution;
	}

	/**
	 * Returns false if the number is already in circularPrimes 
	 * or contains any non-circular-prime digits (0, 2, 4, 5, 6, 8).
	 */
	private static boolean isValidCandidate(int num, HashSet<Integer> circularPrimes) {
		String str = String.valueOf(num);
		if (str.length() < 2) {
			circularPrimes.add(num);
			return false;
		} else if (circularPrimes.contains(num)){
			return false;
		}
	    return !str.matches(".*[024568].*");			
	}
	
	public static ArrayList<Integer> getRotations(int num) {
	    ArrayList<Integer> rotations = new ArrayList<>();
	    String str = String.valueOf(num);
	    for (int i = 0; i < str.length(); i++) {
	        str = str.substring(1) + str.charAt(0);
	        rotations.add(Integer.parseInt(str));
	    }
	    return rotations;
	}
}
