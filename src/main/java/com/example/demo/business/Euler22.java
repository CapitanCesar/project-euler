package com.example.demo.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.example.demo.records.Euler;

public class Euler22 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>Using <a href=\"resources/documents/0022_names.txt\">names.txt</a> (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.</p>\r\n"
				+ "<p>For example, when the list is sorted into alphabetical order, COLIN, which is worth $3 + 15 + 12 + 9 + 14 = 53$, is the $938$th name in the list. So, COLIN would obtain a score of $938 \\times 53 = 49714$.</p>\r\n"
				+ "<p>What is the total of all the name scores in the file?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(1000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Gather the name list from file
		ArrayList<String> names = readFile();

		// Sort them in alphabetical order
		names.sort(null);

		BigInteger totalSum = BigInteger.ZERO;

		for (String name : names) {
			int nameScore = 0;
			for (char letter : name.toCharArray()) {
				int letterScore = letter - 64;
				nameScore += letterScore;
			}
			nameScore *= (names.indexOf(name) + 1);
			BigInteger bigNameScore = new BigInteger(String.valueOf(nameScore));
			totalSum = totalSum.add(bigNameScore);
		}
		// Print the names
		String solution = totalSum.toString();
//		System.out.printf("Total name scores:\t%s\n-----\n", totalSum.toString());
//		printList(names);

		return solution;
	}

	public static void printList(ArrayList<String> names) {
		// Get the iterator
		Iterator<String> it = names.iterator();

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

	public static ArrayList<String> readFile() {
		ArrayList<String> names = new ArrayList<>();

		try {
			File myObj = new File("names.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] matches = data.split(",");
				for (String match : matches) {
					names.add(match.replace("\"", ""));
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			System.out.println("Last row did not contain a number.");
			e.printStackTrace();
		}
		return names;
	}
}
