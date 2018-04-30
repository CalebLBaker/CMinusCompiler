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
import java.util.Set;
import java.util.HashMap;

public class CompoundStatement extends Statement {

	// Local declarations
	private ArrayList<VariableDeclaration> decl;

	// Statements
	private ArrayList<Statement> stmt;

	public SymbolTable symTab;

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

	public void genCode(Function func, SymbolTable tab, boolean handleParams) {
		SymbolTable newTab = new SymbolTable(tab);
		HashMap paramTable = func.getTable();
		Set<String> params = paramTable.keySet();
		for (String k : params) {
			newTab.insert(k, (Integer) paramTable.get(k));
		}
		// func.getTable().putAll(newTab.table);
		finishGenCode(func, newTab);
	}

	public void genCode(Function func, SymbolTable tab) {
		SymbolTable newTab = new SymbolTable(tab);
		finishGenCode(func, newTab);
	}

	public void finishGenCode(Function func, SymbolTable newTab) {
		if (decl != null) {
			for (int i = 0; i < decl.size(); i++) {
				decl.get(i).genCode(func, newTab);
			}
		}
		if (stmt != null) {
			for (int i = 0; i < stmt.size(); i++) {
				stmt.get(i).genCode(func, newTab);
			}
		}
	}
}
