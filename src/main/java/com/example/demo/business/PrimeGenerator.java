package com.example.demo.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class PrimeGenerator {
    interface Sieve {
    	ArrayList<Integer> generate(int limit);
    }

    private static Sieve[] sieves = new Sieve[] {
        new Sieve() { public ArrayList<Integer> generate(int limit) { return getPrimes(limit); } },
        new Sieve() { public ArrayList<Integer> generate(int limit) { return sieveOfEratosthenes(limit); } },
        new Sieve() { public ArrayList<Integer> generate(int limit) { return sieveOfAtkin(limit); } },
    };

    public static void main() {
    	// Choose the maximum number to check for prime tests
    	int limit = (int) 1e3; // Example limit
    	main(limit);
	}

    public static void main(int limit) {
        // Choose the sieves to benchmark
        HashMap<String, Boolean> activatedSieves = new HashMap<>();
        activatedSieves.put("BruteForce", false);
        activatedSieves.put("Eratosthenes", false);
        activatedSieves.put("Atkin", true);

        // Perform the benchmark running all active sieves
		ArrayList<Integer> primes = computePrimes(limit, activatedSieves);
        // Return and save to a file the list of prime numbers
		writeFile(primes);
        // Recover primes from file and print them
		primes = readFile();
		printList(primes);
	}

	/**
	 * @param limit
	 * @param activatedSieves
	 * @return
	 */
	private static ArrayList<Integer> computePrimes(int limit, HashMap<String, Boolean> activatedSieves) {
		ArrayList<Integer> primes = new ArrayList<>();  // Prime list
        long startTime, duration;  // Execution time
        for (String selectedSieve : activatedSieves.keySet()) {
        	if (!activatedSieves.get(selectedSieve)) {
				continue;
			}

        	// Record the start time
            startTime = System.currentTimeMillis();

            // Call the sieve function
            System.out.print(selectedSieve + " ");
            switch(selectedSieve) {
	        	case "BruteForce":
	        		primes = sieves[0].generate(limit);
	        		break;
	        	case "Eratosthenes":
	        		primes = sieves[1].generate(limit);
	        		break;
	        	case "Atkin":
	        		primes = sieves[2].generate(limit);
	        		break;
            }

            // Calculate and print the execution time
            duration = System.currentTimeMillis() - startTime;

    	    printTimeRepresentation(duration);
    	    System.out.println("-----");
    	}
		return primes;
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
	 * @param primes
	 */
	public static void printList(ArrayList<Integer> primes) {
		// Get the iterator
	    Iterator<Integer> it = primes.iterator();

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
	 * @param primes
	 */
	private static void writeFile(ArrayList<Integer> primes) {
		try {
			FileWriter myWriter = new FileWriter("primes.txt");
			int lastPrime = primes.getLast();
			for (int prime : primes) {
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
	 * @param primes
	 */
	public static ArrayList<Integer> readFile() {
		ArrayList<Integer> primes = new ArrayList<>();

		try {
			File myObj = new File("primes.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	int data = myReader.nextInt();
		        primes.add(data);
		    }
		    myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Last row did not contain a number.");
			e.printStackTrace();
		}
		return primes;
	}

	/**
	 * Function to print all primes smaller than or equal to n
	 * @param limit
	 * @return
	 */
    public static ArrayList<Integer> sieveOfAtkin(int limit) {
        // Create a boolean array and initialize all entries as false
        boolean[] primes = new boolean[limit + 1];
        Arrays.fill(primes, false);

        // Loop over all values for x, y to find potential primes
        for (int x = 1; x * x <= limit; x++) {
            for (int y = 1; y * y <= limit; y++) {

                // First condition: 4x^2 + y^2
                int n = 4 * x * x + y * y;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    primes[n] = !primes[n];
                }

                // Second condition: 3x^2 + y^2
                n = 3 * x * x + y * y;
                if (n <= limit && n % 12 == 7) {
                    primes[n] = !primes[n];
                }

                // Third condition: 3x^2 - y^2
                n = 3 * x * x - y * y;
                if (x > y && n <= limit && n % 12 == 11) {
                    primes[n] = !primes[n];
                }
            }
        }

        // Mark all multiples of squares of primes as non-prime
        for (int r = 5; r * r <= limit; r++) {
            if (primes[r]) {
                for (int i = r * r; i <= limit; i += r * r) {
                    primes[i] = false;
                }
            }
        }

        // Return all prime numbers
		ArrayList<Integer> primeNumbers = new ArrayList<>(); // Create an ArrayList object
        System.out.println("Primes up to " + limit);
        if (limit >= 2) {
			primeNumbers.add(2);
		}
        if (limit >= 3) {
			primeNumbers.add(3);
		}
        for (int i = 5; i <= limit; i++) {
            if (primes[i]) {
            	primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

	/**
	 * Function to print all primes smaller than or equal to n
	 * @param n
	 * @return
	 */
    public static ArrayList<Integer> sieveOfEratosthenes(int n) {
        // Create a boolean array and initialize all entries as true
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);

        // 0 and 1 are not primes
        primes[0] = primes[1] = false;

        // Start from 2 and mark the multiples of each number as false
        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                // Mark all multiples of p as false
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        // Return all prime numbers
		ArrayList<Integer> primeNumbers = new ArrayList<>(); // Create an ArrayList object
        System.out.println("Primes up to " + n);
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
            	primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

	/**
	 * Function to print all primes smaller than or equal to n
	 * @param maxNumber
	 * @return
	 */
	public static ArrayList<Integer> getPrimes(int maxNumber) {
		ArrayList<Integer> primes = new ArrayList<>(); // Create an ArrayList object

		for (int i = 2; i < maxNumber; i++) {
			boolean isPrime = true;
			for (int prime : primes) {
				if (i % prime == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes.add(i);
			}
		}
        System.out.println("Primes up to " + maxNumber);
		return primes;
	}
}
