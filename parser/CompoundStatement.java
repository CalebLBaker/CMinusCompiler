/**
* This class represents an expression statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: CompoundStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a compound statement.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import java.util.ArrayList;
import lowlevel.Function;

public class CompoundStatement extends Statement {

	// Local declarations
	private ArrayList<VariableDeclaration> decl;

	// Statements
	private ArrayList<Statement> stmt;

	/**
	 * Constructor
	 * @param d the local declarations
	 * @param s the statements
	 */
	public CompoundStatement(ArrayList<VariableDeclaration> d, ArrayList<Statement> s) {
		decl = d;
		stmt = s;
	}

	/**
	 * Accessor for the local declarations
	 * @return an array list containing the local declarations
	 */
	public ArrayList<VariableDeclaration> getDeclarations() {
		return decl;
	}

	/**
	 * Accessor for the statements
	 * @return an array list containing the statements
	 */
	public ArrayList<Statement> getStatements() {
		return stmt;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "{");
		String newTab = tab + "    ";
		for (VariableDeclaration d : decl) {
			d.print(newTab);
		}
		for (Statement s : stmt) {
			s.print(newTab);
		}
		System.out.println(tab + "}");
	}

	public void genCode(Function func) {

	}
}
