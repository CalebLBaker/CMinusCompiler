/**
* This class represents an expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: Expression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an expression.
*			   It is an abstract class that more specific expression classes can exted.
*
*/


package parser;
import lowlevel.Function;

abstract public class Expression {

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	abstract public void print(String tab);

	/**
	 * Generates Low-level code for an expression
	 * @param func the function that contains the expression.
	 * @param tab the symbol table for the current scope.
	 * @return the number of the register containing the expression's value
	 * @throws CodeGenerationException if an undeclared variable is used.
	 */
	abstract public int genCode(Function func, SymbolTable tab) throws CodeGenerationException;
}