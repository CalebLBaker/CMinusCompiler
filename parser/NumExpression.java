/**
* This class represents an integer literal in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: NumExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a C- integer literal.
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;
import lowlevel.BasicBlock;

public class NumExpression extends Expression {


	// Actual data that holds the value of the literal
	private int data;

	/**
	 * Constructor
	 * @param x the value of the literal.
	 */
	public NumExpression(int x) {
		data = x;
	}

	/**
	 * Accessor to access the value of the literal
	 * @return the value of the literal as an integer
	 */
	public int getData() {
		return data;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.print(tab);
		System.out.println(data);
	}


	/**
	 * Generates Low-level code for an integer literal
	 * @param func the function that the literal appears in.
	 * @param tab the symbol table for the current scope.
	 * @return the register number for the register containing the literal.
	 * @throws CodeGenerationException if an undeclared variable is used.
	 */
	public int genCode(Function func, SymbolTable tab) {
		BasicBlock currBlock = func.getCurrBlock();
		Operation op = new Operation(Operation.OperationType.ASSIGN, currBlock);
		Operand val = new Operand(Operand.OperandType.INTEGER, data);
		int regNum = func.getNewRegNum();
		Operand reg = new Operand(Operand.OperandType.REGISTER, regNum);
		op.setSrcOperand(0, val);
		op.setDestOperand(0, reg);
		currBlock.appendOper(op);
		return regNum;
	}
}
