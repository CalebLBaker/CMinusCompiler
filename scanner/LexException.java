/**
 * This is an exception that can be thrown by a lexer
 *
 * @author Caleb Baker
 * @version 1.0
 *
 * File:	LexException.java
 * Created:	February 5, 2017
 */

package scanner;

public class LexException extends Exception {

	private String message;

	/**
	 * Constructor for UnexpectedEOFException class
	 * @param line the line number in the source file that the invalid token was found on
	 */
	public LexException(String m) {
		super();
		message = m;
	}

	/**
	 * Prints an error message explaining the unexpected EOF error
	 */
	public void printErrorMessage() {
		System.out.println(message);
	}
}
