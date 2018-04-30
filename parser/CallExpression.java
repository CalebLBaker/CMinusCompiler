/**
* This class represents a function call in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: CallExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a function call.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;

import java.util.ArrayList;
import lowlevel.Function;

public class CallExpression extends Expression {


	// Name of the function
	private String name;

	// Parameters.
	private ArrayList<Expression> parameters;

	/**
	 * Constructor
	 * @param n the name of the function.
	 * @param p the parameters being passed to the function.
	 */
	public CallExpression(String n, ArrayList<Expression> p) {
		name = n;
		parameters = p;
	}

	/**
	 * Accessor for the name of the function
	 * @return the name of the function
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor for the function parameters
	 * @return an arraylist of the function parameters
	 */
	public ArrayList<Expression> getParameters() {
		return parameters;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + name + " (");
		String moreTab = tab + "    ";
		for (Expression p : parameters) {
			p.print(moreTab);
		}
		System.out.println(tab + ")");
	}

	public void genCode(Function func) {
		
	}
}
