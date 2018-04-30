/**
* This class represents an if statement in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: SelectionStatement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents an if statement
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import lowlevel.Function;
import lowlevel.BasicBlock;
import lowlevel.Operand;
import lowlevel.Operation;

public class SelectionStatement extends Statement {

	private Expression condition;

	private Statement body;

	private Statement elsePart;

	/**
	 * Constructor
	 * @param predicate the condition for the if statement
	 * @param contents the contents of the if statement
	 * @param
	 */
	public SelectionStatement (Expression predicate, Statement contents, Statement alternative) {
		condition = predicate;
		body = contents;
		elsePart = alternative;
	}

	public SelectionStatement(Expression predicate, Statement contents) {
		this(predicate, contents, null);
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
	 * Accessor for the else part
	 * @return the body of the loop
	 */
	public Statement getElse() {
		return elsePart;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + "if");
		String newTab = tab + "    ";
		condition.print(newTab);
		body.print(newTab);
		if (elsePart != null) {
			System.out.println(tab + "else");
			elsePart.print(newTab);
		}
	}


	/**
	 * Generates Low-level code for a selection statement
	 * @param func the function that the statement appears in.
	 * @param tab the symbol table for the current scope.
	 * @throws CodeGenerationException if an undeclared variable is used.
	 */
	public void genCode(Function func, SymbolTable tab) throws CodeGenerationException{

		boolean els = elsePart != null;

		// Generate code for the branch condition.
		int condRegNum = condition.genCode(func, tab);

		// Create new basic blocks.
		BasicBlock prevBlock = func.getCurrBlock();
		BasicBlock thenBlock = new BasicBlock(func);
		BasicBlock elseBlock = null;
		if (els) {
			elseBlock = new BasicBlock(func);
		}
		BasicBlock postBlock = new BasicBlock(func);

		// Create branch operation.
		Operation branch = new Operation(Operation.OperationType.BEQ, prevBlock);
		Operand condReg = new Operand(Operand.OperandType.REGISTER, condRegNum);
		Operand zilch = new Operand(Operand.OperandType.INTEGER, 0);
		Operand dest;
		if (els) {
			dest  = new Operand(Operand.OperandType.BLOCK, elseBlock.getBlockNum());
		}
		else {
			dest = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
		}
		branch.setSrcOperand(0, condReg);
		branch.setSrcOperand(1, zilch);
		branch.setSrcOperand(2, dest);
		prevBlock.appendOper(branch);

		// Append then block to main chain.
		func.appendToCurrentBlock(thenBlock);

		// Set current block to be then block
		func.setCurrBlock(thenBlock);

		// Generate code for the body of the if-part.
		body.genCode(func, tab);

		// Append post block to main chain.
		func.appendToCurrentBlock(postBlock);

		// Handle the else part
		if (els) {

			// Set current block to be the else block
			func.setCurrBlock(elseBlock);

			// Generate code for the else part
			elsePart.genCode(func, tab);

			// Jump to post block.
			BasicBlock endOfElse = func.getCurrBlock();
			Operation jump = new Operation(Operation.OperationType.JMP, endOfElse);
			dest = new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum());
			jump.setSrcOperand(0, dest);
			endOfElse.appendOper(jump);

			// Append else block to unconnected chain.
			func.appendUnconnectedBlock(elseBlock);

		}

		// Set current block to be the post block.
		func.setCurrBlock(postBlock);

	}
}
