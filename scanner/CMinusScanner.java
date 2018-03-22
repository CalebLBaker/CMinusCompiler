/**
 * This is a scanner that lexes for the C minus programming language.
 *
 * @author Caleb Baker and Faith Trautmann
 * @version 1.0
 *
 * File: 	CMinusScanner.java
 * Created:	February 5, 2017
 *
 * Usage: java scanner.CMinusScanner inputFile outputFile
 */


package scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import scanner.Token;
import scanner.Scanner;
import scanner.InvalidTokenException;
import scanner.UnexpectedEOFException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CMinusScanner implements Scanner {

	private BufferedReader inFile;	// Source file
	private Token nextToken;	// The next token in the source file
	private int linenum;		// The line number of the source file being parsed

	private final char EOF = '\uffff';

	// States the Lexing DFA can be in while producing a token.
	private enum StateType {
		START,
		HAVE_ASSIGNMENT,
		HAVE_LESS_THAN,
		HAVE_GREATER_THAN,
		HAVE_DIVISION,
		IN_COMMENT,
		LEAVING_COMMENT,
		IN_NOT_EQUAL,
		IN_NUM,
		IN_ID,
	}

	/**
	 * Constructor for the CMinusScanner class
	 * @param scanFile the name of the file to be lexed
	 * @throws InvalidTokenException if the first token of the file is not a valid C Minus token
	 * @throws IOException if something goes wrong reading from the file
	 */
	public CMinusScanner(String scanFile) throws InvalidTokenException, IOException, UnexpectedEOFException {
		inFile = new BufferedReader(new FileReader(scanFile));
		nextToken = scanToken();
		linenum = 1;
	}

	/**
	 * Lexes a C minus source file and writes the resulting tokens to a file
	 * @param args the filenames of the input and output files
	 */
	public static void main(String []args) {
		try { 
			BufferedWriter writer;
			//Check for the proper parameters passed in
			if (args.length > 1 && args[0].length() > 0 && args[1].length() > 0) {
				CMinusScanner scan = new CMinusScanner(args[0]);
				writer = new BufferedWriter(new FileWriter(args[1]));
				Token token = scan.getNextToken();
				
				// While the token gotten is not the end of file token read in tokens 
				// and print them to the file
				while(token.getTokenType() != Token.TokenType.END_OF_FILE) {
					switch(token.getTokenType()) {
						case IDENTIFIER : {
							writer.write("IDENTIFIER\t" + (String) token.getTokenData());
							break;
						}
						case NUMBER : {
							writer.write("NUMBER\t" + (Integer.toString((int) token.getTokenData())));
							break;
						}
						default : {
							writer.write((String) token.getTokenData());
						}
					}
					writer.newLine();
					token = scan.getNextToken();
				}
				writer.close();
			}
			else {
				System.out.println("Error: Please make sure that you have entered both a input and output file.");
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("Error: file " + args[0] + " does not exist.");
			return;
		}
		catch (IOException ex) {
			System.out.println("Error: something went wrong reading from or writing to a file.");
			return;
		}
		catch (InvalidTokenException ex) {
			ex.printErrorMessage();
			return;
		}
		catch (UnexpectedEOFException ex) {
			ex.printErrorMessage();
			return;
		}
	}

	/**
	 * Consumes and returns the next token of the input file
	 * @return the next token of the input file
	 * @throws InvalidTokenException if the following token is not a valid C Minus token
	 * @throws UnexpectedEOFException if an end-of-file was reached inside of a comment
	 * @throws IOException if something went wrong reading from the file
	 */
	public Token getNextToken() throws InvalidTokenException, IOException, UnexpectedEOFException {
		Token returnToken = nextToken;
		if(nextToken.getTokenType() != Token.TokenType.END_OF_FILE) {
			nextToken = scanToken();
		}
		return returnToken;
	}

	/**
	 * Returns the next token of the input file without consuming it
	 * @return the next token of the input file
	 */
	public Token viewNextToken() {
		return nextToken;
	}

	// Marks the location in the source file and then gets the next character
	private char getNextChar() throws IOException{
		int returned;
		inFile.mark(1);
		returned = inFile.read();
		return (char) returned;
	}

	// Gets the next token from the input file.
	private Token scanToken() throws InvalidTokenException, IOException, UnexpectedEOFException {
		Integer numValue = 0;			// Value of a number
		String idValue = "";			// Value of an identifier
		StateType state = StateType.START;	// State of the DFA

		// Main loop
		while (true) {
			char c = getNextChar();		// Get the next character

			// DFA
			switch (state) {
				case START : {
					switch(c) {
						case ('/') : {
							state = StateType.HAVE_DIVISION;
							break;
						}
						case ('+') : {
							return new Token(Token.TokenType.ADDITION, (Object) "ADDITION");
						}
						case ('-') : {
							return new Token(Token.TokenType.SUBTRACTION, (Object) "SUBTRACTION");
						}
						case ('*') : {
							return new Token(Token.TokenType.MULTIPLICATION, (Object) "MULTIPLICATION");
						}
						case ('(') : {
							return new Token(Token.TokenType.LEFT_PAREN, (Object) "LEFT_PAREN");
						}
						case (')') : {
							return new Token(Token.TokenType.RIGHT_PAREN, (Object) "RIGHT_PAREN");
						}
						case ('[') : {
							return new Token(Token.TokenType.LEFT_BRACKET, (Object) "LEFT_BRACKET");
						}
						case (']') : {
							return new Token(Token.TokenType.RIGHT_BRACKET, (Object) "RIGHT_BRACKET");
						}
						case ('{') : {
							return new Token(Token.TokenType.LEFT_BRACE, (Object) "LEFT_BRACE");
						}
						case ('}') : {
							return new Token(Token.TokenType.RIGHT_BRACE, (Object) "RIGHT_BRACE");
						}
						case ('<') : {
							state = StateType.HAVE_LESS_THAN;
							break;
						}
						case ('>') : {
							state = StateType.HAVE_GREATER_THAN;
							break;
						}
						case ('=') : {
							state = StateType.HAVE_ASSIGNMENT;
							break;
						}
						case ('!') : {
							state = StateType.IN_NOT_EQUAL;
							break;
						}
						case (';') : {
							return new Token(Token.TokenType.SEMI_COLON, (Object) "SEMI_COLON");
						}
						case (',') : {
							return new Token(Token.TokenType.COMMA, (Object) "COMMA");
						}
						case ('\n') : {
							linenum++;
							break;
						}
						case (EOF) : {	// EOF
							return new Token(Token.TokenType.END_OF_FILE, (Object) "END_OF_FILE");
						}

						// If not a special symbol it must be number, ID, whitespace, or invalid
						default : {
							if (Character.isDigit(c)) {
								state = StateType.IN_NUM;
								numValue = (int) (c - '0');
								break;
							}
							else if (Character.isLetter(c)) {
								state = StateType.IN_ID;
								idValue = Character.toString(c);
								break;
							}
							else if (Character.isWhitespace(c)) {
								break;
							}
							else {
								throw new InvalidTokenException(Character.toString(c), linenum);
							}
						}
					}
					break;
				}
				case HAVE_ASSIGNMENT : {
					if (c == '=') {
						return new Token(Token.TokenType.EQUALITY, (Object) "EQUALITY");
					}
					inFile.reset();
					return new Token(Token.TokenType.ASSIGNMENT, (Object) "ASSIGNMENT");
				}
				case HAVE_LESS_THAN : {
					if (c == '=') {
						return new Token(Token.TokenType.LESS_THAN_EQUAL_TO, (Object) "LESS_THAN_EQUAL_TO");
					}
					inFile.reset();
					return new Token(Token.TokenType.LESS_THAN, (Object) "LESS_THAN");
				}
				case HAVE_GREATER_THAN : {
					if (c == '=') {
						return new Token(Token.TokenType.GREATER_THAN_EQUAL_TO, (Object) "GREATER_THAN_EQUAL_TO");
					}
					inFile.reset();
					return new Token(Token.TokenType.GREATER_THAN, (Object) "GREATER_THAN");
				}
				case HAVE_DIVISION : {
					if (c == '*') {
						state = StateType.IN_COMMENT;
						break;
					}
					inFile.reset();
					return new Token(Token.TokenType.DIVISION, (Object) "DIVISION");
				}
				case IN_COMMENT : {
					if (c == '*') {
						state = StateType.LEAVING_COMMENT;
					}
					else if (c == EOF) {	//EOF
						throw new UnexpectedEOFException(linenum);
					}
					if (c == '\n') {
						linenum++;
					}
					break;
				}
				case LEAVING_COMMENT : {
					if (c == '/') {
						state = StateType.START;
					}
					else if (c == EOF) {	// EOF
						throw new UnexpectedEOFException(linenum);
					}
					else if (c != '*') {
						if (c == '\n') {
							linenum++;
						}
						state = StateType.IN_COMMENT;
					}
					break;
				}
				case IN_NOT_EQUAL : {
					if (c == '=') {
						return new Token(Token.TokenType.NOT_EQUAL, (Object) "NOT_EQUAL");
					}
					else {
						throw new InvalidTokenException("!", linenum);
					}
				}
				case IN_NUM : {
					if (Character.isDigit(c)) {
						numValue *= 10;
						numValue += (c - '0');
					}
					else if (Character.isLetter(c)) {
						throw new InvalidTokenException((Integer.toString(numValue)) + c, linenum);
					}
					else {
						inFile.reset();
						return new Token(Token.TokenType.NUMBER, (Object) numValue);
					}
					break;
				}
				case IN_ID : {
					if (Character.isLetter(c)) {
						idValue += c;
					}
					else if (Character.isDigit(c)) {
						throw new InvalidTokenException(idValue + Character.toString(c), linenum);
					}
					else {
						// Identifier is finished. Repent last token and check if its a keyword
						inFile.reset();
						if (idValue.equals("else")) {
							return new Token(Token.TokenType.ELSE, (Object) "ELSE");
						}
						else if (idValue.equals("if")) {
							return new Token(Token.TokenType.IF, (Object) "IF");
						}
						else if (idValue.equals("int")) {
							return new Token(Token.TokenType.INT, (Object) "INT");
						}
						else if (idValue.equals("return")) {
							return new Token(Token.TokenType.RETURN, (Object) "RETURN");
						}
						else if (idValue.equals("void")) {
							return new Token(Token.TokenType.VOID, (Object) "VOID");
						}
						else if (idValue.equals("while")) {
							return new Token(Token.TokenType.WHILE, (Object) "WHILE");
						}
						else {
							return new Token(Token.TokenType.IDENTIFIER, (Object) idValue);
						}
					}
				}
			}
		}
	}
}
