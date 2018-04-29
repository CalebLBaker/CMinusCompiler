/**
* This class is the root of a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: Program.java
* Created: Spring 2018
* authors. All rights reserved.
* (C)Copyright Cedarville University, its Computer Science faculty, and the
*
* Description: This class becomes the root of a C- abstract syntax tree.
*
*/
package parser;
import java.util.ArrayList;
import lowlevel.CodeItem;

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

	public CodeItem genLLCode() throws CodeGenerationException {

		int len = decl.size();

		if (len == 0) {
			throw new CodeGenerationException();
		}
		CodeItem ret = decl.get(0).codeGen();
		CodeItem curr = ret;
		for (int i = 0; i < len; i++) {
			curr.setNextItem(decl.get(i).codeGen());
			curr = curr.getNextItem();
		}
		return ret;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print() {
		System.out.println("Program {");
		for (Declaration d : decl) {
			d.print("    ");
		}
		System.out.println("}");
	}
}
