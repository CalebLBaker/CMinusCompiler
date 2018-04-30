/**
* This class represents a while loop in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: IterationStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a while loop
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;

public class IterationStatement extends Statement {

	// The looping condition
	private Expression condition;

	// The loop body
	private Statement body;

	/**
	 * Constructor
	 * @param predicate the condition for the while loop
	 * @param contents the body of the while loop
	 */
	public IterationStatement(Expression predicate, Statement contents) {
		condition = predicate;
		body = contents;
	}

	/**
	 * Accessor for the condition
	 * @return the loop condition
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * Accessor for the body
	 * @return the body of the loop
	 */
	public Statement getBody() {
		return body;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "while");
		tab += "    ";
		condition.print(tab);
		body.print(tab);
	}

	public void genCode(Function func, SymbolTable tab) {
		
	}
}
