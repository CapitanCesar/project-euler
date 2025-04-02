package com.example.demo.business;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

import com.example.demo.records.Euler;

public class Euler23 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of $28$ would be $1 + 2 + 4 + 7 + 14 = 28$, which means that $28$ is a perfect number.</p>\r\n"
				+ "<p>A number $n$ is called deficient if the sum of its proper divisors is less than $n$ and it is called abundant if this sum exceeds $n$.</p>\r\n"
				+ "\r\n"
				+ "<p>As $12$ is the smallest abundant number, $1 + 2 + 3 + 4 + 6 = 16$, the smallest number that can be written as the sum of two abundant numbers is $24$. By mathematical analysis, it can be shown that all integers greater than $28123$ can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.</p>\r\n"
				+ "<p>Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(28);
		} else {
			solution = solveProblem(28123);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		int currentDivisors = 0;
		BigInteger totalSum = BigInteger.ZERO;
		ArrayList<Integer> abundantNumbers = new ArrayList<>();
		HashSet<Integer> abundantSumSet = new HashSet<>();

		for (int currentNumber = 12; currentNumber <= limit; currentNumber++) {
			currentDivisors = 0;
			// Loop from 1 to sqrt(n)
			for (int i = 1; i * i <= currentNumber; i++) {
				if (currentNumber % i == 0) {
					if (i == 1) {
						// Don't count the currentNumber itself
						currentDivisors++;
					} else if (i * i == currentNumber) {
						currentDivisors += i;
					} else {
						// Count both divisors: i and n/i
						currentDivisors += (i + currentNumber / i);
					}
				}
			}

			if (currentNumber < currentDivisors) {
				abundantNumbers.add(currentNumber);
			}
		}

		for (int i : abundantNumbers) {
			for (int j : abundantNumbers) {
				int sum = i + j;
				if (!abundantSumSet.contains(sum)) {
					abundantSumSet.add(sum);
				}
			}
		}

//		System.out.printf("Abundant Sum Set size=%d\n", abundantSumSet.size());
//		System.out.printf("All numbers=%d\n", limit);
		for (int candidateNumber = 0; candidateNumber <= limit; candidateNumber++) {
			if (!abundantSumSet.contains(candidateNumber)) {
//				System.out.printf("%d cannot be written as the sum of two abundant numbers\n", candidateNumber);
				BigInteger candidate = new BigInteger(String.valueOf(candidateNumber));
				totalSum = totalSum.add(candidate);
			}
		}
		// Print the sum of all the positive integers which cannot be written as the sum
		// of two abundant numbers
		String solution = totalSum.toString();
//		System.out.printf("The sum is:\t%s\n-----\n", totalSum.toString());
//		System.out.println(abundantSumSet);

		return solution;
	}
}
