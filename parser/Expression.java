/**
* This class represents an expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: CallExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an expression.
*			   It is an abstract class that more specific expression classes can exted.
*
*/


package parser;

abstract public class Expression {

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	abstract public void print(String tab);
}