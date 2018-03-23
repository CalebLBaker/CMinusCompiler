/**
* This class is the root of a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: Program.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class becomes the root of a C- abstract syntax tree.
*
*/
package parser;
import java.util.ArrayList;

public class Program {

	// declarations
	private ArrayList<Declaration> decl;

	/**
	 * Constructor
	 * @param d is the top-level declarations made in the program
	 */
	public Program(ArrayList<Declaration> d) {
		decl = d;
	}

	/**
	 * Accessor for declarations
	 * @return an array list containing the top-level declarations
	 */
	public ArrayList<Declaration> getDeclarations() {
		return decl;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "Program {");
		String newTab = tab + "    ";
		for (Declaration d : decl) {
			d.print(newTab);
		}
		System.out.println(tab + "}");
	}
}
