/**
 * This is an exception that can be thrown by a parser.
 *
 * @author Caleb Baker
 * @version 1.0
 *
 * File:	ParseException.java
 * Created:	March 20, 2017
 */


package parser;
import scanner.Token;

public class ParseException extends Exception {

	// Type of thing that was being parsed when exception was thrown
	private String thing;

	// Line in the file that caused the exception to be thrown
	private int linenum;

	// Token that caused the exception to be thrown.
	private Token found;

	// Type of token that was expected.
	private Token.TokenType expected;

	/**
	 * Fully-defined constructor
	 * @param t the type of thing currently being parsed
	 * @param l the line number the error happened on
	 * @param f the token that caused the error
	 * @param e the type of token that was expected
	 */
	public ParseException(String t, int l, Token f, Token.TokenType e) {
		super();
		thing = t;
		linenum = l;
		found = f;
		expected = e;
	}

	/**
	 * Parse constructor
	 * @param t the type of thing currently being parsed
	 * @param l the line number the error happened on
	 * @param f the token that caused the error
	 */
	public ParseException(String t, int l, Token f) {
		this(t, l, f, null);
	}

	/**
	 * Match constructor
	 * @param l the line number the error happened on
	 * @param f the token that caused the error
	 * @param e the type of token that was expected
	 */
	public ParseException(int l, Token f, Token.TokenType e) {
		this(null, l, f, e);
	}


	/**
	 * Default constructor
	 */
	public ParseException() {
		this(null, 0, null, null);
	}

	/**
	 * Prints a message describing the error.
	 */
	public void printErrorMessage() {
		String line = Integer.toString(linenum);
		String f = Token.toString(found);
		System.out.println("Error on line " + line);
		if (thing == null) {
			String e = Token.toString(expected);
			System.out.println("\tExpected token:\t" + e);
		}
		else {
			System.out.println("\tTrying to parse " + thing);
		}
		System.out.println("\tFound:\t\t\t" + f + "\n");
	}
}
