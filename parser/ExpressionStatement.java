/**
* This class represents an expression statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: ExpressionStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an expression statement.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;

public class ExpressionStatement extends Statement {

	// Index if variable is an array. Null otherwise.
	private Expression e;

	/**
	 * Constructor
	 * @param exp the expression
	 */
	public ExpressionStatement(Expression exp) {
		e = exp;
	}


	/**
	 * Null constructor
	 */
	public ExpressionStatement() {
		this(null);
	}

	/**
	 * Accessor for the expression
	 * @return the expression
	 */
	public Expression getExpression() {
		return e;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "Expression Statement");
		if (e != null) {
			e.print(tab + "    ");
		}
	}

	public void genCode(Function func) {

	}
}
