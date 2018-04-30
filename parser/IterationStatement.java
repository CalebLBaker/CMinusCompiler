/**
* This class represents a while loop in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: IterationStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a while loop
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

public class IterationStatement extends Statement {

	// The looping condition
	private Expression condition;

	// The loop body
	private Statement body;

	/**
	 * Constructor
	 * @param predicate the condition for the while loop
	 * @param contents the body of the while loop
	 */
	public IterationStatement(Expression predicate, Statement contents) {
		condition = predicate;
		body = contents;
	}

	/**
	 * Accessor for the condition
	 * @return the loop condition
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * Accessor for the body
	 * @return the body of the loop
	 */
	public Statement getBody() {
		return body;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "while");
		tab += "    ";
		condition.print(tab);
		body.print(tab);
	}

	public void genCode(Function func, SymbolTable tab) throws CodeGenerationException {
		// Generate code for the branch condition.
		int condRegNum = condition.genCode(func, tab);

		// Create new basic blocks.
		BasicBlock prevBlock = func.getCurrBlock();
		BasicBlock bodyBlock = new BasicBlock(func);
		BasicBlock postBlock = new BasicBlock(func);

		// Create branch operation.
		Operation branch = new Operation(Operation.OperationType.BEQ, prevBlock);
		Operand condReg = new Operand(Operand.OperandType.REGISTER, condRegNum);
		Operand zilch = new Operand(Operand.OperandType.INTEGER, 0);
		Operand dest = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
                
		branch.setSrcOperand(0, condReg);
		branch.setSrcOperand(1, zilch);
		branch.setSrcOperand(2, dest);
		prevBlock.appendOper(branch);

		// Append body block to main chain.
		func.appendToCurrentBlock(bodyBlock);

		// Set current block to be body block
		func.setCurrBlock(bodyBlock);

		// Generate code for the body
		body.genCode(func, tab);
                
                // Create branch operation.
                condRegNum = condition.genCode(func, tab);
		Operation iterBranch = new Operation(Operation.OperationType.BNE, bodyBlock);
                Operand iterDest = new Operand(Operand.OperandType.BLOCK, bodyBlock.getBlockNum());

		iterBranch.setSrcOperand(0, condReg);
		iterBranch.setSrcOperand(1, zilch);
		iterBranch.setSrcOperand(2, iterDest);
		func.getCurrBlock().appendOper(iterBranch);
                
		// Append post block to main chain.
		func.appendToCurrentBlock(postBlock);
                
		// Set current block to be the post block.
		func.setCurrBlock(postBlock);
	}
}
