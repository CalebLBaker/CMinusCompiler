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

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;
import lowlevel.BasicBlock;
import lowlevel.Attribute;

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


	/**
	 * Generates Low-level code for the function call.
	 * @param func the caller function.
	 * @param tab the symbol table for the current scope in the caller function.
	 * @return the register number for register containing the return value.
	 * @throws CodeGenerationException if an undeclared variable is used.
	 */
	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException{

		int paramNum = 0;
		BasicBlock currBlock = func.getCurrBlock();

		// Generate code to evaluate and pass all of the parameters.
		if (parameters != null) {
			paramNum = parameters.size();
			ArrayList<Operation> params = new ArrayList<Operation>();

			// Evaluate all of the parameters.
			for (int i = 0; i < paramNum; i++) {
				Expression param = parameters.get(i);
				Operand parReg = new Operand(Operand.OperandType.REGISTER, param.genCode(func, tab));
				Operation op = new Operation(Operation.OperationType.PASS, currBlock);
				Attribute pn = new Attribute("PARAM_NUM", Integer.toString(i));
				op.addAttribute(pn);
				op.setSrcOperand(0, parReg);
				params.add(op);
			}

			// Pass the parameters to the function.
			for (int i = 0; i < params.size(); i++) {
				currBlock.appendOper(params.get(i));
			}
		}

		// Generate call operation.
		Operation call = new Operation(Operation.OperationType.CALL, currBlock);
		Operand callName = new Operand(Operand.OperandType.STRING, name);
		call.setSrcOperand(0, callName);
		Attribute pn = new Attribute("numParams", Integer.toString(paramNum));
		call.addAttribute(pn);
		currBlock.appendOper(call);

		// Move return value into a gneral-purpose register.
		Operation getResult = new Operation(Operation.OperationType.ASSIGN, currBlock);
		Operand retReg = new Operand(Operand.OperandType.MACRO, "RetReg");
		int regNum = func.getNewRegNum();
		Operand exprReg = new Operand(Operand.OperandType.REGISTER, regNum);
		getResult.setSrcOperand(0, retReg);
		getResult.setDestOperand(0, exprReg);
		currBlock.appendOper(getResult);
		return regNum;
	}
}
