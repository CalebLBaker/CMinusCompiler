/**
* This class represents a variable in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: VarExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an occurence of a C- variable.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

public class VarExpression extends Expression {


	// Name of the variable
	private String name;

	// Index if variable is an array. Null otherwise.
	private Expression index;

	/**
	 * Array constructor
	 * @param n the name of the array.
	 * @param i the index into the array.
	 */
	public VarExpression(String n, Expression i) {
		name = n;
		index = i;
	}


	/**
	 * Scalar constructor
	 * @param n the name of the variable.
	 */
	public VarExpression(String n) {
		this(n, null);
	}

	/**
	 * Accessor for the name of the variable
	 * @return the name of the variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor for the index into the array
	 * @return the index into the array as an expression
	 */
	public Expression getIndex() {
		return index;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.print(tab + name);
		if (index == null) {
			System.out.println();
		}
		else {
			System.out.println(" [");
			index.print(tab + "    ");
			System.out.println(tab + "]");
		}
	}

	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException{
		Integer regNum = tab.get(name);
		if (regNum == null) {
			throw new CodeGenerationException("Use of undeclared variable: " + name);
		}
		else if (regNum == -1) {
			BasicBlock currBlock = func.getCurrBlock();
			Operation ld = new Operation(Operation.OperationType.LOAD_I, currBlock);
			regNum = func.getNewRegNum();
			Operand globVar = new Operand(Operand.OperandType.STRING, name);
			Operand reg = new Operand(Operand.OperandType.REGISTER, regNum);
			ld.setSrcOperand(0, globVar);
			ld.setDestOperand(0, reg);
			currBlock.appendOper(ld);
		}
		return regNum;
	}
}
