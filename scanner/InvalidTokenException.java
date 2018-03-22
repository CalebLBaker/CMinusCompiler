/**
 * This is an exception that represents an invalid token in a source file
 *
 * @author Caleb Baker
 * @version 1.0
 *
 * File:	InvalidTokenException.java
 * Created:	February 4, 2017
 */


package scanner;

public class InvalidTokenException extends Exception {
	private String invalidToken;
	private int linenum;

	/**
	 * Constructor for InvalidTokenException class
	 * @param token the invalid token that was read
	 * @param line the line number in the source file that the invalid token was found on
	 */
	public InvalidTokenException(String token, int line) {
		super();
		invalidToken = token;
		linenum = line;
	}

	/**
	 * Prints an error message explaining the invalid token error
	 */
	public void printErrorMessage() {
		System.out.println("Invalid token: \"" + invalidToken + "\" on line " + Integer.toString(linenum));
	}
}
