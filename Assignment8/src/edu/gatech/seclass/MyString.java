package edu.gatech.seclass;

import java.util.HashSet;


public class MyString implements MyStringInterface {
	private String myString;
	private String VOWELS2 = new String("aeiouAEIOU");

	public MyString() {
		this.myString = "";
	}

	@Override
	/**
	 * Sets the value of the current string.
	 * 
	 * @param string
	 *            The value to be set
	 */
	public void setString(String string) {
		this.myString = string;
	}

	/**
	 * Returns the current string
	 * 
	 * @return Current string
	 */
	public String getString() {
		return this.myString;
	}

	/**
	 * Returns a string that consists of all the vowels in the current string,
	 * in the same order and with the same case.
	 * ("y" is not considered a vowel)
	 * 
	 * @return Vowels in the current string
	 */
	public String getVowels() {
		//aeiou AEIOU
		String rtn = "";
		for (int i = 0; i < myString.length(); i++) {
			char c = myString.charAt(i);
			if (VOWELS2.indexOf(c) != -1) {
				rtn += String.valueOf(c);
			}
		}
		
		return rtn;
	}

	/**
	 * Returns the number of vowels in the current string
	 * ("y" is not considered a vowel)
	 * 
	 * @return Number of vowels in the current string
	 */
	public int numberOfVowels() {
		return this.getVowels().length();
	}

	/**
	 * Returns the character at position "position" in the string, with 1 being the
	 * first position
	 * 
	 * @param position
	 *            Position of the character to return
	 * @return The character at position "position"
	 * @throws IllegalArgumentException
	 *             If "position" is invalid (e.g., "position" <= 0)
	 * @throws PositionOutOfBoundsException
	 *             If the string has less than "position" characters in it
	 */
	public char getCharacter(int position) throws IllegalArgumentException, IllegalIndexException {
		if (position <= 0) {
			throw new IllegalArgumentException();
		}
		
		if (this.myString.length() < position) {
			throw new IllegalIndexException();
		}
		
		return this.myString.charAt(position-1);
	}

	/**
	 * Changes the case of the alphabetic characters in the current string,
	 * between startPosition and endPosition (included), with 1 being the first
	 * position:
	 * - lower case characters are replaced with the corresponding upper case characters
	 * - upper case characters are replaced with the corresponding lower case characters
	 * 
	 * @param startPosition
	 *            Position of the first character to consider
	 * @param endPosition
	 *            Position of the last character to consider
	 * @return
	 * @throws IllegalArgumentException
	 * startPosition" <= 0, "endPosition" <= 0, "startPosition" > "endPosition
	 *             If either "startPosition" or "endPosition" are invalid (e.g.,
	 *             "startPosition" <= 0, "endPosition" <= 0, "startPosition" > "endPosition")
	 * @throws IllegalIndexException
	 *             If the string has less than "endPosition" characters in it
	 */
	public void flipCaseInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, IllegalIndexException {
		String s = "";
		
		if (startPosition <= 0 || endPosition <= 0 || startPosition > endPosition) {
			throw new IllegalArgumentException();
		}
		
		if (this.myString.length() < endPosition) {
			throw new IllegalIndexException();
		}
		
		for (int i = startPosition - 1; i <= endPosition - 1; i++) {
			char c = this.myString.charAt(i);
			if (Character.isLowerCase(c)) {
				s += Character.toUpperCase(c);
			} else if (Character.isUpperCase(c)) {
				s += Character.toLowerCase(c);
			}
		}
		
		this.myString = this.myString.substring(0, startPosition) + s + this.myString.substring(endPosition - 1);
	}

}
