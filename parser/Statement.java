/**
* This class represents an statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: Statement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a statement.
*			   It is an abstract class that more specific expression classes can exted.
*
*/


package parser;
import lowlevel.Function;

abstract public class Statement {

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	abstract public void print(String tab);
	abstract public void genCode(Function func);
}