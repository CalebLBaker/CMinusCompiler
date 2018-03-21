package parser;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.InvalidTokenException;
import scanner.UnexpectedEOFException;
import java.util.ArrayList;
import java.io.IOException;

public class CMinusParser {

	private CMinusScanner lex;

	private void match(Token.TokenType t)
	  throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
		if (lex.getNextToken().getTokenType() != t) {
			throw new ParseException();
		}
	}

	private boolean isBinaryOp() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return t == Token.TokenType.LESS_THAN || t == Token.TokenType.LESS_THAN_EQUAL_TO
				|| t == Token.TokenType.GREATER_THAN || t == Token.TokenType.GREATER_THAN_EQUAL_TO
				|| t == Token.TokenType.EQUALITY || t == Token.TokenType.NOT_EQUAL
				|| t == Token.TokenType.ADDITION || t == Token.TokenType.SUBTRACTION
				|| t == Token.TokenType.MULTIPLICATION || t == Token.TokenType.DIVISION;
	}

	private boolean isFactorFollowSet() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return isBinaryOp() || t == Token.TokenType.SEMI_COLON
				|| t == Token.TokenType.COMMA || t == Token.TokenType.RIGHT_BRACKET
				|| t == Token.TokenType.RIGHT_PAREN;
	}

	private Program parseProgram() {
		return null;
	}

	private Declaration parseDeclaration() {
		return null;
	}

	private Declaration parseDeclPrime() {
		return null;
	}

	private FunctionDeclaration parseFunDeclPrime() {
		return null;
	}

	private VariableDeclaration parseVarDecl() {
		return null;
	}

	private ArrayList<Parameter> parseParams() {
		return null;
	}

	private Parameter parseParam() {
		return null;
	}

	private ArrayList<Parameter> parseParamList() {
		return null;
	}

	private CompoundStatement parseCompoundStmt() {
		return null;
	}

	private Statement parseStatement() {
		return null;
	}

	private ExpressionStatement parseExpressionStmt() {
		return null;
	}

	private SelectionStatement parseSelectionStmt() {
		return null;
	}

	private IterationStatement parseIterationStmt() {
		return null;
	}

	private ReturnStatement parseReturnStmt() {
		return null;
	}

	private Expression parseExpression()
	  throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
		Token nextToken = lex.getNextToken();
		Token.TokenType type = nextToken.getTokenType();
		switch(type) {
			case IDENTIFIER : {
				return parseExpressionPrime((String) nextToken.getTokenData());
			}
			case NUMBER : {
				NumExpression n = new NumExpression((int) nextToken.getTokenData());
				return parseSimpleExpressionPrime(n);
			}
			case LEFT_PAREN : {
				Expression e = parseExpression();
				match(Token.TokenType.RIGHT_PAREN);
				return parseSimpleExpressionPrime(e);
			}
			default : {
				throw new ParseException();
			}
		}
	}

	private Expression parseExpressionPrime(String id)
	  throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
		Token nextToken = lex.viewNextToken();
		Token.TokenType type = nextToken.getTokenType();
		if (type == Token.TokenType.LEFT_BRACKET) {
			match(Token.TokenType.LEFT_BRACKET);
			Expression i = parseExpression();
			match(Token.TokenType.RIGHT_BRACKET);
			VarExpression x = new VarExpression(id, i);
			return parseExpressionPrimePrime(x);
		}
		else if (type == Token.TokenType.ASSIGNMENT) {
			VarExpression x = new VarExpression(id);
			match(Token.TokenType.ASSIGNMENT);
			Expression value = parseExpression();
			return new AssignExpression(x, value);
		}
		else if (type == Token.TokenType.LEFT_PAREN) {
			match(Token.TokenType.LEFT_PAREN);
			ArrayList<Expression> a = parseArgs();
			match(Token.TokenType.RIGHT_PAREN);
			CallExpression call = new CallExpression(id, a);
			return parseSimpleExpressionPrime(call);
		}
		else if (isFactorFollowSet()) {
			VarExpression firtFactor = new VarExpression(id);
			return parseSimpleExpressionPrime(firtFactor);
		}
		else {
			throw new ParseException();
		}
	}

	private Expression parseExpressionPrimePrime(VarExpression x) {
		Token nextToken = lex.viewNextToken();
		if (nextToken.getTokenType() == Token.TokenType.ASSIGNMENT) {
			match(Token.TokenType.ASSIGNMENT);
			Expression value = parseExpression();
			return new AssignExpression(x, value);
		}
		else if (isFactorFollowSet()) {
			return parseSimpleExpressionPrime(x);
		}
		else {
			throw new ParseException();
		}
	}

	private Expression parseSimpleExpressionPrime(Expression leadFactor) {
		return null;
	}

	private Expression parseAdditiveExpression() {
		return null;
	}

	private Expression parseTerm() {
		return null;
	}

	private Expression parseFactor() {
		return null;
	}

	private ArrayList<Expression> parseArgs() {
		return null;
	}

	private ArrayList<Expression> parseArgsList() {
		return null;
	}
}