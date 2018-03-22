/**
* This class represents an assignment expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: AssignExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an assignment expression
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;

public class AssignExpression extends Expression {


	// Variable to be assigned to.
	private VarExpression assignee;

	// Value to be assigned to the variable
	private Expression value;

	/**
	 * Constructor
	 * @param var the variable to be assigned to.
	 * @param val the value to be assigned.
	 */
	public AssignExpression(VarExpression var, Expression val) {
		assignee = var;
		value = val;
	}

	/**
	 * Accessor for the variable
	 * @return the variable
	 */
	public VarExpression getVariable() {
		return assignee;
	}

	/**
	 * Accessor for the value
	 * @return the value
	 */
	public Expression getValue() {
		return value;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "=");
		tab += "    ";
		assignee.print(tab);
		value.print(tab);
	}
}
