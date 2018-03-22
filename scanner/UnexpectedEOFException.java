/**
 * This is an exception that is thrown when an end-of-file is found somewhere unexpected
 *
 * @author Caleb Baker
 * @version 1.0
 *
 * File:	UnexpectedEOFException.java
 * Created:	February 5, 2017
 */

package scanner;

public class UnexpectedEOFException extends Exception {
	private int linenum;

	/**
	 * Constructor for UnexpectedEOFException class
	 * @param line the line number in the source file that the invalid token was found on
	 */
	public UnexpectedEOFException(int line) {
		super();
		linenum = line;
	}

	/**
	 * Prints an error message explaining the unexpected EOF error
	 */
	public void printErrorMessage() {
		System.out.println("Error: Unexpected end-of-file on line " + Integer.toString(linenum) + ". Expected */ token first.");
	}
}
