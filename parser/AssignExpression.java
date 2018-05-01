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


	/**
	 * Generates Low-level code for the assignment.
	 * @param func the function that the assignment expression is in.
	 * @param tab the symbol table for the current scope.
	 * @return the register number for a register containing the value being assigned.
	 * @throws CodeGenerationException if an undeclared variable is used.
	 */
	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException{

		// Number of register holding value to be assigned
		int in = value.genCode(func, tab);

		// Name of the variable being assigned to.
		String varName = assignee.getName();

		// Register that a value is being assigned to.
		Integer out = tab.get(varName);

		// Current basic block.
		BasicBlock currBlock = func.getCurrBlock();

		// Assignment operation.
		Operation op;

		Expression index = assignee.getIndex();

		// Throw exception if the variable hasn't been declared in this scope.
		if (out == null) {
			throw new CodeGenerationException("Use of undeclared variable: " + varName);
		}

		// Handle assigning to arrays //////////////////////
		else if (index != null) {

			// Set up the store instruction a bit.
			op = new Operation(Operation.OperationType.STORE_I, currBlock);

			// Get the index into the array.
			int indexReg = index.genCode(func, tab);
			Operand indReg = new Operand(Operand.OperandType.REGISTER, indexReg);

			// Global array.
			if (out == -1) {
				Operand arrName = new Operand(Operand.OperandType.STRING, varName);
				op.setSrcOperand(1, arrName);
				op.setSrcOperand(2, indReg);
			}

			// Local array.
			else {
				Operand stackPointer = new Operand(Operand.OperandType.MACRO, "ESP");
				Operand arrLoc = new Operand(Operand.OperandType.INTEGER, out);
				op.setSrcOperand(1, stackPointer);
				op.setSrcOperand(2, arrLoc);
				op.setSrcOperand(3, indReg);
			}

			out = in;
		}

		// If variable is global use a store instead of an assign.
		else if (out == -1) {
			op = new Operation(Operation.OperationType.STORE_I, currBlock);
			Operand dest = new Operand(Operand.OperandType.STRING, varName);
			Operand offset = new Operand(Operand.OperandType.INTEGER, 0);
			op.setSrcOperand(1, dest);
			op.setSrcOperand(2, offset);
			out = in;
		}

		// If the variable is local, use an assign.
		else {
			op = new Operation(Operation.OperationType.ASSIGN, currBlock);
			Operand var = new Operand(Operand.OperandType.REGISTER, out);
			op.setDestOperand(0, var);
		}

		// Set source operand and append operation to block.
		Operand val = new Operand(Operand.OperandType.REGISTER, in);
		op.setSrcOperand(0, val);
		currBlock.appendOper(op);
		return out;
	}
}
