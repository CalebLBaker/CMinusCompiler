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

	private String thing;
	private int linenum;
	private Token found;
	private Token.TokenType expected;

	public ParseException(String t, int l, Token f, Token.TokenType e) {
		super();
		thing = t;
		linenum = l;
		found = f;
		expected = e;
	}

	public ParseException(String t, int l, Token f) {
		this(t, l, f, null);
	}

	public ParseException(int l, Token f, Token.TokenType e) {
		this(null, l, f, e);
	}

	public ParseException() {
		this(null, 0, null, null);
	}
}
