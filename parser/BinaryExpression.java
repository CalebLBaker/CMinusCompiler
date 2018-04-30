/**
* This class represents a binary expression in a C- abstract syntax tree
*
* @author Caleb Baker
* @version 1.0
* File: BinaryExpression.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class represents a binary expression
*			   It becomes a node in a C- abstract syntax tree.
*
*/
package parser;
import scanner.Token;
import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operand;
import lowlevel.BasicBlock;

public class BinaryExpression extends Expression {

	// Left hand side of the expression
	private Expression lhs;

	// Right hand side of the expression
	private Expression rhs;

	// Operator
	Token.TokenType operator;

	/**
	 * Constructor
	 * @param l the left hand side of the expression
	 * @param o the operator used by the expression
	 * @param r the right hand side of the expression
	 */
	public BinaryExpression(Expression l, Token.TokenType o, Expression r) {
		lhs = l;
		rhs = r;
		operator = o;
	}

	/**
	 * Accessor for the left side of the expression
	 * @return the left hand side of the expression
	 */
	public Expression getLeft() {
		return lhs;
	}

	/**
	 * Accessor for the right side of the expression
	 * @return the right hand side of the expression
	 */
	public Expression getRight() {
		return rhs;
	}

	/**
	 * Accessor for the expression's operator
	 * @return the operator
	 */
	public Token.TokenType getOperator() {
		return operator;
	}

	/**
	 * Prints the node
	 * @param tab how far to indent the node
	 */
	public void print(String tab) {
		System.out.println(tab + Token.toString(operator));
		tab += "    ";
		lhs.print(tab);
		rhs.print(tab);
	}

	public int genCode(Function func, SymbolTable tab) throws CodeGenerationException {
            BasicBlock currBlock = func.getCurrBlock();
            int leftRegNum = lhs.genCode(func, tab);
            int rightRegNum = rhs.genCode(func, tab);
            Operand leftReg = new Operand(Operand.OperandType.REGISTER, leftRegNum);
            Operand rightReg = new Operand(Operand.OperandType.REGISTER, rightRegNum);
            int regNum = func.getNewRegNum();
            Operand destReg = new Operand(Operand.OperandType.REGISTER, regNum);
            Operation oper;
            switch(operator) {
                case LESS_THAN:
                    oper = new Operation(Operation.OperationType.LT, currBlock);           
                break;
                case LESS_THAN_EQUAL_TO:
                    oper = new Operation(Operation.OperationType.LTE, currBlock);     
                break;      
                case GREATER_THAN:
                    oper = new Operation(Operation.OperationType.GT, currBlock);     
                break;
                case GREATER_THAN_EQUAL_TO:
                    oper = new Operation(Operation.OperationType.GTE, currBlock);     
                break;
                case EQUALITY:
                    oper = new Operation(Operation.OperationType.EQUAL, currBlock);     
                break;
                case NOT_EQUAL:
                    oper = new Operation(Operation.OperationType.NOT_EQUAL, currBlock);     
                break;      
                default:
                    throw new CodeGenerationException("Incorrect operator in binary statement");
            }
            oper.setDestOperand(0, destReg);
            oper.setSrcOperand(0, leftReg);
            oper.setSrcOperand(1, rightReg);
            currBlock.appendOper(oper);
            return regNum;
	}
}
