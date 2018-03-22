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

public class UnexpectedEOFException extends LexException {
	/**
	 * Constructor for UnexpectedEOFException class
	 * @param line the line number in the source file that the invalid token was found on
	 */
	public UnexpectedEOFException(int line) {
		super("Error: Unexpected end-of-file on line " + Integer.toString(line) + ". Expected */ token first.");
	}
}
