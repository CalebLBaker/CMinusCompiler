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

	/**
	 * Generates Low-level code for a variable access
	 * @param func the function that the variable appears in.
	 * @param tab the symbol table for the current scope.
	 * @return the register number for the register containing the variable.
	 * @throws CodeGenerationException if the variable is undeclared.
	 */
	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException{

		// Get register number.
		Integer regNum = tab.get(name);

		// Throw an exception if the variable is undeclared.
		if (regNum == null) {
			throw new CodeGenerationException("Use of undeclared variable: " + name);
		}

		// Load if the variable comes from global scope.
		else if (index == null && regNum == -1) {
			BasicBlock currBlock = func.getCurrBlock();
			Operation ld = new Operation(Operation.OperationType.LOAD_I, currBlock);
			regNum = func.getNewRegNum();
			Operand globVar = new Operand(Operand.OperandType.STRING, name);
			Operand reg = new Operand(Operand.OperandType.REGISTER, regNum);
			ld.setSrcOperand(0, globVar);
			ld.setDestOperand(0, reg);
			currBlock.appendOper(ld);
		}

		// Array
		else if (index != null) {

			// Set up the load instruction a bit.
			BasicBlock currBlock = func.getCurrBlock();
			Operation ld = new Operation(Operation.OperationType.LOAD_I, currBlock);
			int newReg = func.getNewRegNum();
			Operand reg = new Operand(Operand.OperandType.REGISTER, newReg);
			ld.setDestOperand(0, reg);

			// Get the index into the array.
			int indexReg = index.genCode(func, tab);
			Operand indReg = new Operand(Operand.OperandType.REGISTER, indexReg);

			// Global array.
			if (regNum == -1) {
				Operand arrName = new Operand(Operand.OperandType.STRING, name);
				ld.setSrcOperand(0, arrName);
				ld.setSrcOperand(1, indReg);
			}

			// Local array.
			else {
				Operand stackPointer = new Operand(Operand.OperandType.MACRO, "ESP");
				Operand arrLoc = new Operand(Operand.OperandType.INTEGER, regNum);
				ld.setSrcOperand(0, stackPointer);
				ld.setSrcOperand(1, arrLoc);
				ld.setSrcOperand(2, indReg);
			}

			// Append operation to block.
			currBlock.appendOper(ld);
			regNum = newReg;
		}

		// Return regNum
		return regNum;
	}
}
