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

	public void genCode(Function func) {
		int paramNum = 0;
		BasicBlock currBlock = func.getCurrBlock();
		if (parameters != null) {
			paramNum = parameters.size();
			for (int i = 0; i < paramNum; i++) {
				Expression param = parameters.get(i);
				param.genCode(func);
				Operand parReg = new Operand(Operand.OperandType.REGISTER, param.regNum);
				Operation op = new Operation(Operation.OperationType.PASS, currBlock);
				op.setSrcOperand(0, parReg);
				currBlock.appendOper(op);
			}
		}
		Operation call = new Operation(Operation.OperationType.CALL, currBlock);
		Operand callName = new Operand(Operand.OperandType.STRING, name);
		call.setSrcOperand(0, callName);
		Attribute pn = new Attribute("numParams", Integer.toString(paramNum));
		call.addAttribute(pn);
		currBlock.appendOper(call);

		Operation getResult = new Operation(Operation.OperationType.ASSIGN, currBlock);
		Operand retReg = new Operand(Operand.OperandType.MACRO, "RetReg");
		regNum = func.getNewRegNum();
		Operand exprReg = new Operand(Operand.OperandType.REGISTER, regNum);
		getResult.setSrcOperand(0, retReg);
		getResult.setDestOperand(0, exprReg);
		currBlock.appendOper(getResult);
	}
}
