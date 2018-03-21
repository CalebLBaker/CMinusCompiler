package parser;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.InvalidTokenException;
import scanner.UnexpectedEOFException;
import java.util.ArrayList;
import java.io.IOException;

public class CMinusParser {

	private CMinusScanner lex;

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
				lex.getNextToken();
				return parseExpressionPrime((String) nextToken.getTokenData());
			}
			case NUMBER : {
				lex.getNextToken();
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

	private Expression parseExpressionPrime(String id) {
		return null;
	}

	private Expression parseExpressionPrimePrime() {
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