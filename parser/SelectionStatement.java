/**
* This class represents an if statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: SelectionStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an if statement
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;

public class SelectionStatement extends Statement {

	private Expression condition;

	private Statement body;

	private Statement elsePart;

	/**
	 * Constructor
	 * @param predicate the condition for the if statement
	 * @param contents the contents of the if statement
	 * @param
	 */
	public SelectionStatement (Expression predicate, Statement contents, Statement alternative) {
		condition = predicate;
		body = contents;
		elsePart = alternative;
	}

	public SelectionStatement(Expression predicate, Statement contents) {
		this(predicate, contents, null);
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
	 * Accessor for the else part
	 * @return the body of the loop
	 */
	public Statement getElse() {
		return elsePart;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "if");
		String newTab = tab + "    ";
		condition.print(newTab);
		body.print(newTab);
		if (elsePart != null) {
			System.out.println(tab + "else");
			elsePart.print(newTab);
		}
	}

	public void genCode(Function func, SymbolTable tab) {
		
	}
}
