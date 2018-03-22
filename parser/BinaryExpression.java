/**
* This class represents a binary expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: BinaryExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a binary expression
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import scanner.Token;

public class BinaryExpression extends Expression {

	// Left hand side of the expression
	private Expression lhs;

	// Right hand side of the expression
	private Expression rhs;

	// Operator
	Token.TokenType operator;

	/**
	 * Constructor
	 * @param l the left hand side of the expression
	 * @param o the operator used by the expression
	 * @param r the right hand side of the expression
	 */
	public BinaryExpression(Expression l, Token.TokenType o, Expression r) {
		lhs = l;
		rhs = r;
		operator = o;
	}

	/**
	 * Accessor for the left side of the expression
	 * @return the left hand side of the expression
	 */
	public Expression getLeft() {
		return lhs;
	}

	/**
	 * Accessor for the right side of the expression
	 * @return the right hand side of the expression
	 */
	public Expression getRight() {
		return rhs;
	}

	/**
	 * Accessor for the expression's operator
	 * @return the operator
	 */
	public Token.TokenType getOperator() {
		return operator;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.print(tab + Token.toString(operator));
		tab += "    ";
		lhs.print(tab);
		rhs.print(tab);
	}
}
