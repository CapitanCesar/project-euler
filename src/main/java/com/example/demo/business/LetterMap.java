package com.example.demo.business;

import java.util.HashMap;

public class LetterMap extends HashMap<Integer, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer, String> letterMap;
	private boolean print;

	public LetterMap() {
		new LetterMap(true);
	}

	public LetterMap(boolean print) {
		this.print = print;

		this.put(1, 3); // One
		this.put(2, 3); // Two
		this.put(3, 5); // Three
		this.put(4, 4); // Four
		this.put(5, 4); // Five
		this.put(6, 3); // Six
		this.put(7, 5); // Seven
		this.put(8, 5); // Eight
		this.put(9, 4); // Nine
		this.put(10, 3); // Ten
		this.put(11, 6); // Eleven
		this.put(12, 6); // Twelve
		this.put(13, 8); // Thirteen
		this.put(14, 8); // Fourteen
		this.put(15, 7); // Fifteen
		this.put(16, 7); // Sixteen
		this.put(17, 9); // Seventeen
		this.put(18, 8); // Eighteen
		this.put(19, 8); // Nineteen
		this.put(20, 6); // Twenty
		this.put(30, 6); // Thirty
		this.put(40, 5); // Forty
		this.put(50, 5); // Fifty
		this.put(60, 5); // Sixty
		this.put(70, 7); // Seventy
		this.put(80, 6); // Eighty
		this.put(90, 6); // Ninety
		this.put(100, 7); // Hundred
		this.put(1000, 8); // Thousand
		this.put(-1, 3); // And

		this.letterMap = new HashMap<>();
		this.letterMap.put(1, "One");
		this.letterMap.put(2, "Two");
		this.letterMap.put(3, "Three");
		this.letterMap.put(4, "Four");
		this.letterMap.put(5, "Five");
		this.letterMap.put(6, "Six");
		this.letterMap.put(7, "Seven");
		this.letterMap.put(8, "Eight");
		this.letterMap.put(9, "Nine");
		this.letterMap.put(10, "Ten");
		this.letterMap.put(11, "Eleven");
		this.letterMap.put(12, "Twelve");
		this.letterMap.put(13, "Thirteen");
		this.letterMap.put(14, "Fourteen");
		this.letterMap.put(15, "Fifteen");
		this.letterMap.put(16, "Sixteen");
		this.letterMap.put(17, "Seventeen");
		this.letterMap.put(18, "Eighteen");
		this.letterMap.put(19, "Nineteen");
		this.letterMap.put(20, "Twenty");
		this.letterMap.put(30, "Thirty");
		this.letterMap.put(40, "Forty");
		this.letterMap.put(50, "Fifty");
		this.letterMap.put(60, "Sixty");
		this.letterMap.put(70, "Seventy");
		this.letterMap.put(80, "Eighty");
		this.letterMap.put(90, "Ninety");
		this.letterMap.put(100, "Hundred");
		this.letterMap.put(1000, "Thousand");
		this.letterMap.put(-1, "And");
	}
	
	public Integer get(int key) {
		return this.get(key, this.print);
	}

	public Integer get(int key, boolean print) {
		if (print) {
			System.out.print(this.letterMap.get(key) + " ");			
		}
		return super.get(key);
	}
}
