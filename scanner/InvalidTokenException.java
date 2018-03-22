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

public class InvalidTokenException extends LexException {
	/**
	 * Constructor for InvalidTokenException class
	 * @param token the invalid token that was read
	 * @param line the line number in the source file that the invalid token was found on
	 */
	public InvalidTokenException(String token, int line) {
		super("Invalid token: \"" + token + "\" on line " + Integer.toString(line));
	}
}
