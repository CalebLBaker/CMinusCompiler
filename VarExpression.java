/**
* This class represents a variable in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: NumExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an occurence of a C- variable.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;

public class VarExpression extends Expression {


	// Name of the variable
	private String name;

	// Index if variable is an array. Null otherwise.
	private Expression index;

	/**
	 * Array constructor
	 * @param n the name of the array.
	 * @param i the index into the array.
	 */
	public VarExpression(String n, Expression i) {
		name = n;
		index = i;
	}


	/**
	 * Scalar constructor
	 * @param n the name of the variable.
	 */
	public VarExpression(String n) {
		VarExpression(n, null);
	}

	/**
	 * Accessor for the name of the variable
	 * @return the name of the variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor for the index into the array
	 * @return the index into the array as an expression
	 */
	public Expression getIndex() {
		return index;
	}

	// /**
	//  * Prints the node
	//  * @param tab how far to indent the node
	//  */
	// public void print(int tab) {
	// 	String space = "";
	// 	for (int i = 0; i < tab; i++) {
	// 		space += " ";
	// 	}
	// 	System.out.print(space);
	// 	System.out.println(data);
	// }
}