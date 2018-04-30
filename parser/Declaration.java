/**
* Abstract Declaration class
*
* @author Caleb Baker, Faith Trautmann
* @version 1.0
* File: Declaration.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* This is an abstract class that other statement classes inherit from. FunctionDeclaration,
* and VariableDeclartion both inherit from it. It does not inherit from any classes.
*
*/
package parser;
import lowlevel.CodeItem;

abstract public class Declaration {
	abstract void print(String tab);

	/**
	 * Generates Low-level code for a top-level declaration.
	 * @param tab the symbol table for global scope.
	 * @throws CodeGenerationException if an undeclared variable is used.
	 * @return a CodeItem representing the low-level code.
	 */
	public abstract CodeItem genCode(SymbolTable tab) throws CodeGenerationException;
}
