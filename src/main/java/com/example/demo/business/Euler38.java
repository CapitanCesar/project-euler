package com.example.demo.business;

import com.example.demo.records.Euler;

public class Euler38 {
    public static Euler main() {
        return main(false);
    }

    public static Euler main(boolean isTest) {
        String problem = "<p>Take the number $192$ and multiply it by each of $1$, $2$, and $3$:</p>\r\n"
        		+ "\\begin{align}\r\n"
        		+ "192 \\times 1 &amp;= 192\\\\\r\n"
        		+ "192 \\times 2 &amp;= 384\\\\\r\n"
        		+ "192 \\times 3 &amp;= 576\r\n"
        		+ "\\end{align}\r\n"
        		+ "<p>By concatenating each product we get the $1$ to $9$ pandigital, $192384576$. We will call $192384576$ the concatenated product of $192$ and $(1,2,3)$.</p>\r\n"
        		+ "<p>The same can be achieved by starting with $9$ and multiplying by $1$, $2$, $3$, $4$, and $5$, giving the pandigital, $918273645$, which is the concatenated product of $9$ and $(1,2,3,4,5)$.</p>\r\n"
        		+ "<p>What is the largest $1$ to $9$ pandigital $9$-digit number that can be formed as the concatenated product of an integer with $(1,2, \\dots, n)$ where $n \\gt 1$?</p>";
		String solution;

		if (isTest) {
			solution = solveProblem(10);
		} else {
			solution = solveProblem(10000);
		}

		return new Euler(problem, solution);
    }

    public static String solveProblem(int limit) {
        int maxPandigital = 0;

        for (int i = 1; i < limit; i++) {
            StringBuilder concatenated = new StringBuilder();
            int n = 1;

            while (concatenated.length() < 9) {
                concatenated.append(i * n++);
            }

            if (concatenated.length() == 9 && isPandigital(concatenated.toString())) {
                maxPandigital = Math.max(maxPandigital, Integer.parseInt(concatenated.toString()));
            }
        }

        return String.valueOf(maxPandigital);
    }

    private static boolean isPandigital(String num) {
        if (num.length() != 9 || num.contains("0")) return false;
        boolean[] seen = new boolean[10];
        for (char c : num.toCharArray()) {
            int digit = c - '0';
            if (seen[digit]) return false;
            seen[digit] = true;
        }
        return true;
    }
}
