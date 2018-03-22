package scanner;

/**
* This class provides the token object and its methods that CMinusScanner uses
*
* @author Faith Trautmann and Caleb Baker
* @version 1.0
* File: Token.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class is used by CMinusScanner for the tokens it returns.
* It does not inherit from any classs and it is not expected to be inherited
* by any classes
*
*/

public class Token {
	private TokenType tokenType;
	private Object tokenData;

	/**
	* The constructor that takes a type parameter
	*
	* @param type The type to be stored in tokenType
	*/
	public Token(TokenType type) {
		this(type, null);
	}

	/**
	* The constructor that takes a type and data parameter
	*
	* @param type The type to be stored in tokenType
	* @param data The data to be stored in dataType
	*/
	public Token (TokenType type, Object data) {
		tokenType = type;
		tokenData = data;
	}

	public static String toString(Token t) {
		switch (t.getTokenType()) {
			case IDENTIFIER : {
				return (String) t.getTokenData();
			}
			case NUMBER : {
				return Integer.toString((Integer) t.getTokenData());
			}
			default : {
				return toString(t.getTokenType());
			}
		}
	}

	public static String toString(TokenType t) {
		switch (t) {
			case IDENTIFIER : {
				return "IDENTIFIER";
			}
			case NUMBER : {
				return "NUMBER";
			}
			case INT : {
				return "int";
			}
			case VOID : {
				return "void";
			}
			case WHILE : {
				return "while";
			}
			case RETURN : {
				return "return";
			}
			case IF : {
				return "if";
			}
			case ELSE : {
				return "else";
			}
			case SEMI_COLON : {
				return ";";
			}
			case COMMA : {
				return ",";
			}
			case LEFT_PAREN : {
				return "(";
			}
			case RIGHT_PAREN : {
				return ")";
			}
			case LEFT_BRACKET : {
				return "[";
			}
			case RIGHT_BRACKET : {
				return "]";
			}
			case LESS_THAN : {
				return "<";
			}
			case LESS_THAN_EQUAL_TO : {
				return "<=";
			}
			case GREATER_THAN : {
				return ">";
			}
			case GREATER_THAN_EQUAL_TO : {
				return ">=";
			}
			case EQUALITY : {
				return "==";
			}
			case NOT_EQUAL : {
				return "!=";
			}
			case ASSIGNMENT : {
				return "=";
			}
			case ADDITION : {
				return "+";
			}
			case SUBTRACTION : {
				return "-";
			}
			case MULTIPLICATION : {
				return "*";
			}
			case DIVISION : {
				return "/";
			}
			case END_OF_FILE : {
				return "EOF";
			}
			default : {
				return "Unknown token";
			}
		}
	}

	/**
	* Accessor for tokenType
	*
	* @return tokenType always
	*/
	public TokenType getTokenType() {
		return tokenType;
	}

	/**
	* Accessor for tokenData
	*
	* @return tokenData always
	*/
	public Object getTokenData() {
		return tokenData;
	}

	/**
	* Mutator for tokenType
	*
	* @param type Type to be stored in tokenType
	*/
	public void setTokenType(TokenType type) {
		tokenType = type;
	}

	/**
	* Mutator for tokenData
	*
	* @param type Data to be stored in tokenData
	*/
	public void setTokenData(Object data) {
		tokenData = data;
	}

	// TokenType enum that will be used by the scanner
	public enum TokenType {
		IDENTIFIER,		// [a-zA-Z]+
		NUMBER,			// [0-9]+
		INT,			// int
		VOID,			// void
		WHILE,			// while
		RETURN,			// return
		IF,			// if
		ELSE,			// else
		SEMI_COLON,		// ;
		COMMA,			// ,
		LEFT_PAREN,		// \(
		RIGHT_PAREN,		// \)
		LEFT_BRACKET,		// \[
		RIGHT_BRACKET,		// \]
		LEFT_BRACE,		// \{
		RIGHT_BRACE,		// \}
		LESS_THAN,		// <
		LESS_THAN_EQUAL_TO,	// <=
		GREATER_THAN,		// >
		GREATER_THAN_EQUAL_TO,	// >=
		EQUALITY,		// ==
		NOT_EQUAL,		// !=
		ASSIGNMENT,		// =
		ADDITION,		// \+
		SUBTRACTION,		// -
		MULTIPLICATION,		// \*
		DIVISION,		// \/
		END_OF_FILE
	}
}
