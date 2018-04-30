/**
* This class represents an integer literal in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: NumExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a C- integer literal.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;

public class NumExpression extends Expression {


	// Actual data that holds the value of the literal
	private int data;

	/**
	 * Constructor
	 * @param x the value of the literal.
	 */
	public NumExpression(int x) {
		data = x;
	}

	/**
	 * Accessor to access the value of the literal
	 * @return the value of the literal as an integer
	 */
	public int getData() {
		return data;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.print(tab);
		System.out.println(data);
	}

	public void genCode(Function func) {
		
	}
}
