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

abstract public class Declaration {
	abstract void print(String tab);
}