package com.example.demo.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FibonacciGenerator {
	interface Function {
		ArrayList<BigInteger> generate(int limit);
	}

	private static Function[] functions = new Function[] { new Function() {
		@Override
		public ArrayList<BigInteger> generate(int limit) {
			return getFibNumbers(limit);
		}
	}, };

	public static void main() {
		// Choose the maximum number to check for Fibonacci tests
		int limit = 100; // Example limit
		main(limit);
	}

	public static void main(int limit) {
		// Choose the functions to benchmark
		HashMap<String, Boolean> activatedFunctions = new HashMap<>();
		activatedFunctions.put("BruteForce", true);

		// Perform the benchmark running all active functions
		ArrayList<BigInteger> fibNumbers = computeFibNumbers(limit, activatedFunctions);
		// Return and save to a file the list of prime numbers
		writeFile(fibNumbers);
		// Recover fibNumbers from file and print them
		fibNumbers = readFile();
		printList(fibNumbers);
	}

	/**
	 * @param limit
	 * @param activatedFunctions
	 * @return
	 */
	private static ArrayList<BigInteger> computeFibNumbers(int limit, HashMap<String, Boolean> activatedFunctions) {
		ArrayList<BigInteger> fibNumbers = new ArrayList<>(); // Prime list
		long startTime, duration; // Execution time
		for (String selectedFunction : activatedFunctions.keySet()) {
			if (!activatedFunctions.get(selectedFunction)) {
				continue;
			}

			// Record the start time
			startTime = System.currentTimeMillis();

			// Call the function function
			System.out.print(selectedFunction + " ");
			switch (selectedFunction) {
			case "BruteForce":
				fibNumbers = functions[0].generate(limit);
				break;
			}

			// Calculate and print the execution time
			duration = System.currentTimeMillis() - startTime;

			printTimeRepresentation(duration);
			System.out.println("-----");
		}
		return fibNumbers;
	}

	/**
	 * @param timeInMillis
	 */
	private static void printTimeRepresentation(long timeInMillis) {
		System.out.print("Execution Time: ");
		if (timeInMillis < 1000) {
			// Less than 1 second
			System.out.println(timeInMillis + " milliseconds");
		} else if (timeInMillis < 60000) {
			// Less than 1 minute
			double seconds = timeInMillis / 1000.0;
			System.out.printf("%.2f seconds\n", seconds);
		} else if (timeInMillis < 3600000) {
			// Less than 1 hour
			double minutes = timeInMillis / 60000.0;
			System.out.printf("%.2f minutes\n", minutes);
		} else {
			// 1 hour or more
			double hours = timeInMillis / 3600000.0;
			System.out.printf("%.2f hours\n", hours);
		}
	}

	/**
	 * @param fibNumbers
	 */
	public static void printList(ArrayList<BigInteger> fibNumbers) {
		// Get the iterator
		Iterator<BigInteger> it = fibNumbers.iterator();

		// Print the array
		while (it.hasNext()) {
			for (int i = 0; i < 20; i++) {
				if (it.hasNext()) {
					System.out.printf("%s\t", it.next().toString());
				}
			}
			System.out.print('\n');
		}
	}

	/**
	 * @param fibNumbers
	 */
	private static void writeFile(ArrayList<BigInteger> fibNumbers) {
		try {
			FileWriter myWriter = new FileWriter("fibonacci.txt");
			BigInteger lastFib = fibNumbers.getLast();
			for (BigInteger number : fibNumbers) {
				if (number != lastFib) {
					myWriter.write(number.toString() + "\n");
				} else {
					myWriter.write(number.toString() + "");
				}
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * @param fibNumbers
	 */
	public static ArrayList<BigInteger> readFile() {
		ArrayList<BigInteger> fibNumbers = new ArrayList<>();

		try {
			File myObj = new File("fibonacci.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				BigInteger number = new BigInteger(data);
				fibNumbers.add(number);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Last row did not contain a number.");
			e.printStackTrace();
		}
		return fibNumbers;
	}

	/**
	 * Function compute the Fib Sequence up to Nth number
	 *
	 * @param maxNumber
	 * @return
	 */
	public static ArrayList<BigInteger> getFibNumbers(int limit) {
		ArrayList<BigInteger> fibNumbers = new ArrayList<>();
		fibNumbers.add(BigInteger.ONE);
		fibNumbers.add(BigInteger.ONE);

		BigInteger secondToLastFib = BigInteger.ONE;
		BigInteger lastFib = BigInteger.ONE;
		BigInteger newFib;

		for (int i = 2; i < limit; i++) {
			lastFib = fibNumbers.getLast();
			newFib = secondToLastFib.add(lastFib);
			fibNumbers.add(newFib);
			secondToLastFib = lastFib;
		}
		System.out.printf("FibNumbers up to %dth number", limit);
		return fibNumbers;
	}
}
