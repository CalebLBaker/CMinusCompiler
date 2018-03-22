/**
* This class represents a return statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: ReturnStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a return statement;
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;

public class ReturnStatement extends Statement {

	// Index if variable is an array. Null otherwise.
	private Expression ret;

	/**
	 * Constructor
	 * @param exp the expression being returned
	 */
	public ReturnStatement(Expression exp) {
		ret = exp;
	}

	/**
	 * Null constructor
	 */
	public ReturnStatement() {
		this(null);
	}

	/**
	 * Accessor for the return value
	 * @return the expression
	 */
	public Expression getReturnValue() {
		return ret;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "return");
		if (ret != null) {
			ret.print(tab + "    ");
		}
	}
}
