package com.example.demo.business;

import java.util.HashMap;

import com.example.demo.records.Euler;

public class Euler19 {
	public static Euler main() {
		return main(false);
	}

	public static Euler main(boolean isTest) {
		String problem = "<p>You are given the following information, but you may prefer to do some research for yourself.</p>\r\n"
				+ "<ul><li>1 Jan 1900 was a Monday.</li>\r\n" + "<li>Thirty days has September,<br />\r\n"
				+ "April, June and November.<br />\r\n" + "All the rest have thirty-one,<br />\r\n"
				+ "Saving February alone,<br />\r\n" + "Which has twenty-eight, rain or shine.<br />\r\n"
				+ "And on leap years, twenty-nine.</li>\r\n"
				+ "<li>A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.</li>\r\n"
				+ "</ul><p>How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?</p>";
//		System.out.println(problem);
		String solution;

		if (isTest) {
			solution = solveProblem(1901);
		} else {
			solution = solveProblem(2000);
		}

		return new Euler(problem, solution);
	}

	/**
	 * @param primes
	 */
	private static String solveProblem(int limit) {
		// Info
		HashMap<Integer, Integer> daysInMonth = new HashMap<>();
		daysInMonth.put(1, 31);
		daysInMonth.put(2, 28);
		daysInMonth.put(0, 29);
		daysInMonth.put(3, 31);
		daysInMonth.put(4, 30);
		daysInMonth.put(5, 31);
		daysInMonth.put(6, 30);
		daysInMonth.put(7, 31);
		daysInMonth.put(8, 31);
		daysInMonth.put(9, 30);
		daysInMonth.put(10, 31);
		daysInMonth.put(11, 30);
		daysInMonth.put(12, 31);

		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		String[] months = { "Leap-February", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		String currentDay = "Monday", currentMonth = "January";
		int i = 0, d = 1, m = 1, y = 1900, firstSundays = 0;

		while (!(d == 31 && m == 12 && y == limit)) {
			// Compute current day name, current month name and the counting condition
			currentDay = days[i % 7];
			currentMonth = months[m % 13];
			if (currentDay == "Sunday" && d == 1 && y != 1900) {
				firstSundays++;
				// Print current day iteration
//				System.out.printf("%s the %dth of %s of %d\n", currentDay, d, currentMonth, y);
			}

			// Leap year
			if (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) {
				if (d == 31 && m == 1) {
					d = 0;
					m = 0;
				} else if (m == 0 && d == 29) {
					d = 28;
					m = 2;
				}
			}

			// Month jumps
			if (d >= daysInMonth.get(m)) {
				d = 1;
				if (m != 12) {
					m++;
				} else {
					m = 1;
					y++;					
				}
			} else {
				d++;
			}
			i++;
		}
		// #Sundays that fell on the first of the month during the twentieth century
		String solution = String.valueOf(firstSundays);

		return solution;
	}
}
