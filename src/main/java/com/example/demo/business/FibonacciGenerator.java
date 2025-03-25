package com.example.demo.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class FibonacciGenerator {
    interface Function {
    	ArrayList<Integer> generate(int limit);
    }

    private static Function[] functions = new Function[] {
        new Function() { public ArrayList<Integer> generate(int limit) { return getFibNumbers(limit); } },
    };

    public static void main() {
    	// Choose the maximum number to check for fibonacci tests
        int limit = (int) 4e6; // Example limit
    	main(limit);
	}

    public static void main(int limit) {
        // Choose the functions to benchmark
        HashMap<String, Boolean> activatedFunctions = new HashMap<>();
        activatedFunctions.put("BruteForce", true);

        // Perform the benchmark running all active functions
		ArrayList<Integer> fibNumbers = computeFibNumbers(limit, activatedFunctions);
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
	private static ArrayList<Integer> computeFibNumbers(int limit, HashMap<String, Boolean> activatedFunctions) {
		ArrayList<Integer> fibNumbers = new ArrayList<>();  // Prime list
        long startTime, duration;  // Execution time
        for (String selectedFunction : activatedFunctions.keySet()) {
        	if (!activatedFunctions.get(selectedFunction)) {
				continue;
			}

        	// Record the start time
            startTime = System.currentTimeMillis();

            // Call the function function
            System.out.print(selectedFunction + " ");
            switch(selectedFunction) {
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
	public static void printList(ArrayList<Integer> fibNumbers) {
		// Get the iterator
	    Iterator<Integer> it = fibNumbers.iterator();

	    // Print the array
	    while (it.hasNext()) {
	    	for (int i = 0; i < 20; i++) {
	    		if (it.hasNext()) {
		    		System.out.printf("%d\t", it.next());
	    		}
	    	}
	    	System.out.print('\n');
    	}
	}

	/**
	 * @param fibNumbers
	 */
	private static void writeFile(ArrayList<Integer> fibNumbers) {
		try {
			FileWriter myWriter = new FileWriter("fibonacci.txt");
			int lastPrime = fibNumbers.getLast();
			for (int prime : fibNumbers) {
				if (prime != lastPrime) {
					myWriter.write(prime + "\n");
				} else {
					myWriter.write(prime + "");
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
	public static ArrayList<Integer> readFile() {
		ArrayList<Integer> fibNumbers = new ArrayList<>();

		try {
			File myObj = new File("fibonacci.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	int data = myReader.nextInt();
		        fibNumbers.add(data);
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
	 * Function to print all fibonacci numbers smaller than or equal to n
	 * @param maxNumber
	 * @return
	 */
	public static ArrayList<Integer> getFibNumbers(int maxNumber) {
		ArrayList<Integer> fibNumbers = new ArrayList<>();
		fibNumbers.add(1);
		fibNumbers.add(2);

		int secondToLastFib = 1;
		int lastFib = 2;
		int newFib;

		for (int i = 2; i < maxNumber; i++) {
			lastFib = fibNumbers.getLast();
			newFib = secondToLastFib + lastFib;
			if (newFib < maxNumber) {
				fibNumbers.add(secondToLastFib + lastFib);
				secondToLastFib = lastFib;				
			} else break;
		}
        System.out.println("FibNumbers up to " + maxNumber);
		return fibNumbers;
	}
}
