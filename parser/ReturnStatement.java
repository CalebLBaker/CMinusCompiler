/**
* This class represents a return statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: ReturnStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a return statement;
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import java.util.HashSet;
import java.util.Set;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;
import lowlevel.BasicBlock;

public class ReturnStatement extends Statement {

	// Index if variable is an array. Null otherwise.
	private Expression ret;

	/**
	 * Constructor
	 * @param exp the expression being returned
	 */
	public ReturnStatement(Expression exp) {
		ret = exp;
	}

	/**
	 * Null constructor
	 */
	public ReturnStatement() {
		this(null);
	}

	/**
	 * Accessor for the return value
	 * @return the expression
	 */
	public Expression getReturnValue() {
		return ret;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "return");
		if (ret != null) {
			ret.print(tab + "    ");
		}
	}

	public void genCode(Function func, SymbolTable tab) throws CodeGenerationException {
            BasicBlock currBlock = func.getCurrBlock();
            if (ret != null) {
                int regNum = ret.genCode(func, tab);
                Operation assign = new Operation(Operation.OperationType.ASSIGN, currBlock);
                Operand exprReg = new Operand(Operand.OperandType.REGISTER, regNum);
                Operand retReg = new Operand(Operand.OperandType.MACRO, "retReg");
                assign.setSrcOperand(0, exprReg);
                assign.setDestOperand(0, retReg);
                currBlock.appendOper(assign);
            }
            BasicBlock block = func.getReturnBlock();
            Operation jump = new Operation(Operation.OperationType.JMP, currBlock);
            Operand retBlock = new Operand(Operand.OperandType.BLOCK, block);
            jump.setSrcOperand(0, retBlock);
            currBlock.appendOper(jump);
	}
}
