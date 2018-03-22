package scanner;

import java.io.IOException;

/**
* This is the Scanner interface that will be used by CMinusScanner
*
* @author Faith Trautmann and Caleb Baker
* @version 1.0
* File: Scanner.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved. 
*
* Description: This is an interface for Scanners. Specifically it will be  
* used by CMinusScanner.
*
*/

public interface Scanner {

	/**
	 * Consumes and returns the next token of the input file
	 * @return the next token of the input file
	 * @throws IOException if something went wrong reading from the input file
	 * @throws InvalidTokenException if an invalid token was found
	 * @throws UnexpectedEOFException if an end-of-file was found in an unexpected place
	public Token getNextToken() throws IOException, InvalidTokenException, UnexpectedEOFException;

	/**
	 * Returns the next token of the input file without consuming it
	 * @return the next token of the input file
	 */
	public Token viewNextToken()throws IOException, InvalidTokenException, UnexpectedEOFException;	
};
