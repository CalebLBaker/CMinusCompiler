package parser;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.InvalidTokenException;
import scanner.UnexpectedEOFException;
import java.util.ArrayList;
import java.io.IOException;

public class CMinusParser {

	private CMinusScanner lex;

	private void match(Token.TokenType t) throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
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

	private Expression parseExpression() throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
		Token nextToken = lex.viewNextToken();
		Token.TokenType type = nextToken.getTokenType();
		switch(type) {
			case IDENTIFIER : {
				match(Token.TokenType.IDENTIFIER);
				return parseExpressionPrime((String) nextToken.getTokenData());
			}
			case NUMBER : {
				match(Token.TokenType.NUMBER);
				NumExpression n = new NumExpression((int) nextToken.getTokenData());
				return parseSimpleExpressionPrime(n);
			}
			case LEFT_PAREN : {
				Expression f = parseFactor();
				return parseSimpleExpressionPrime(f);
			}
			default : {
				throw new ParseException();
			}
		}
	}

	private Expression parseExpressionPrime(String id) throws InvalidTokenException, IOException, UnexpectedEOFException, ParseException {
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
		}
		else if (type == Token.TokenType.LEFT_PAREN) {

		}
		else if (isBinaryOp() || type == Token.TokenType.SEMI_COLON || type == Token.TokenType.COMMA
					|| type == Token.TokenType.RIGHT_BRACKET || type == Token.TokenType.RIGHT_PAREN) {

		}
		else {

		}
		return null;
	}

	private Expression parseExpressionPrimePrime(VarExpression x) {
		return null;
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