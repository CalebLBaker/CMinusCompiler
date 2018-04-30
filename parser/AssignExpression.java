/**
* This class represents an assignment expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: AssignExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an assignment expression
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;
import lowlevel.Operation;

import javax.swing.plaf.basic.BasicScrollPaneUI.ViewportChangeHandler;

import lowlevel.BasicBlock;
import lowlevel.Operand;

public class AssignExpression extends Expression {


	// Variable to be assigned to.
	private VarExpression assignee;

	// Value to be assigned to the variable
	private Expression value;

	/**
	 * Constructor
	 * @param var the variable to be assigned to.
	 * @param val the value to be assigned.
	 */
	public AssignExpression(VarExpression var, Expression val) {
		assignee = var;
		value = val;
	}

	/**
	 * Accessor for the variable
	 * @return the variable
	 */
	public VarExpression getVariable() {
		return assignee;
	}

	/**
	 * Accessor for the value
	 * @return the value
	 */
	public Expression getValue() {
		return value;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "=");
		tab += "    ";
		assignee.print(tab);
		value.print(tab);
	}

	// public int genCodeStore(Function func, SymbolTable tab) throws CodeGenerationException {
	// 	Integer regNum = tab.get(name);
	// 	if (regNum == null) {
	// 		throw new CodeGenerationException("Use of undeclared variable: " + name);
	// 	}
	// 	return regNum;
	// }

	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException{
		int in = value.genCode(func, tab);
		String varName = assignee.getName();
		Integer out = tab.get(varName);
		BasicBlock currBlock = func.getCurrBlock();
		Operation op;
		if (out == null) {
			throw new CodeGenerationException("Use of undeclared variable: " + varName);
		}
		else if (out == -1) {
			op = new Operation(Operation.OperationType.STORE_I, currBlock);
			Operand dest = new Operand(Operand.OperandType.STRING, varName);
			Operand offset = new Operand(Operand.OperandType.INTEGER, 0);
			op.setSrcOperand(1, dest);
			op.setSrcOperand(2, offset);
		}
		else {
			op = new Operation(Operation.OperationType.ASSIGN, currBlock);
			Operand var = new Operand(Operand.OperandType.REGISTER, out);
			op.setDestOperand(0, var);
		}
		Operand val = new Operand(Operand.OperandType.REGISTER, in);
		op.setSrcOperand(0, val);
		currBlock.appendOper(op);
		return out;
	}
}
