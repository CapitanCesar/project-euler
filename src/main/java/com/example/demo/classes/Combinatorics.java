package com.example.demo.classes;

import java.math.BigInteger;
import java.util.HashMap;

public class Combinatorics {

    // Cache to store previously computed factorials for efficiency
    private final HashMap<Integer, BigInteger> factorialLookupTable;

    // Constructor initializes the lookup table
    public Combinatorics() {
        this.factorialLookupTable = new HashMap<>();
        this.factorialLookupTable.put(0, BigInteger.ONE); // base case
        this.factorialLookupTable.put(1, BigInteger.ONE); // base case
    }

    /**
     * Computes the number of combinations (n choose r) using:
     * C(n, r) = n! / (r! * (n - r)!)
     * 
     * @param n total number of items
     * @param r number of items to choose
     * @return BigInteger result of C(n, r)
     */
    public BigInteger getCombinations(int n, int r) {
        if (r > n) {
            System.out.println("WARN: r must be less than or equal to n");
            return BigInteger.ZERO;
        }

        // Use cached factorials to calculate combination
        BigInteger numerator = factorial(n);
        BigInteger denominator = factorial(r).multiply(factorial(n - r));

        return numerator.divide(denominator);
    }

    /**
     * Computes factorial using memoization to store intermediate results.
     * 
     * @param number the number to compute factorial for
     * @return BigInteger factorial of number
     */
    public BigInteger factorial(int number) {
        // Check if factorial already computed
        if (factorialLookupTable.containsKey(number)) {
            return factorialLookupTable.get(number);
        }

        // Recursively compute factorial and store it in the lookup table
        BigInteger result = BigInteger.valueOf(number).multiply(factorial(number - 1));
        factorialLookupTable.put(number, result);
        return result;
    }
}
