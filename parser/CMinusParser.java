package parser;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.LexException;
import java.util.ArrayList;
import java.io.IOException;

public class CMinusParser {

	/**
	 * File-specific constructor
	 * @param filename the name of the file to be parsed
	 */
	public CMinusParser(String filename) throws LexException, IOException {
		if (filename != null) {
			lex = new CMinusScanner(filename);
		}
		else {
			lex = null;
		}
	}

	/**
	 * Unattached constructor
	 */
	public CMinusParser() throws LexException, IOException {
		this(null);
	}

	/**
	 * Parse the file the parser is currently attached to
	 * @return the abstract syntax tree specified by the file
	 */
	public Program parse() {
		return parseProgram();
	}

	/**
	 * Parse a file specified by the parameter
	 * @param filename the file to be parsed
	 * @return the abstract syntax tree specified by the file
	 */
	public Program parse(String filename) throws LexException, IOException {
		lex = new CMinusScanner(filename);
		return parseProgram();
	}

	private CMinusScanner lex;

	// Match a token
	// Throws an excpetion if the next token doesn't match
	private void match(Token.TokenType t) throws LexException, ParseException {
		int line = lex.getLineNum();
		Token nextToken = lex.getNextToken();
		if (nextToken.getTokenType() != t) {
			throw new ParseException(line, nextToken, t);
		}
	}

	// Check if the next token is a comparison operator
	private boolean isRelOp() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return t == Token.TokenType.LESS_THAN || t == Token.TokenType.LESS_THAN_EQUAL_TO
			|| t == Token.TokenType.GREATER_THAN || t == Token.TokenType.GREATER_THAN_EQUAL_TO
			|| t == Token.TokenType.EQUALITY || t == Token.TokenType.NOT_EQUAL;
	}

	// Check if the next token is + or -
	private boolean isAddop() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return t == Token.TokenType.ADDITION || t == Token.TokenType.SUBTRACTION;
	}

	// Check if the next token is * or /
	private boolean isMulop() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return t == Token.TokenType.MULTIPLICATION || t == Token.TokenType.DIVISION;
	}

	// Check if the next token is in the first set of factor
	private boolean isFactorFirstSet() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return t == Token.TokenType.IDENTIFIER || t == Token.TokenType.NUMBER
			|| t == Token.TokenType.LEFT_PAREN;
	}

	// Check if the next token is in the follow set of term
	private boolean isTermFollowSet() {
		Token.TokenType t = lex.viewNextToken().getTokenType();
		return isAddop() || isRelOp() || t == Token.TokenType.SEMI_COLON
				|| t == Token.TokenType.COMMA || t == Token.TokenType.RIGHT_BRACKET
				|| t == Token.TokenType.RIGHT_PAREN;
	}

	// Check if the next token is in the follow set of factor
	private boolean isFactorFollowSet() {
		return isTermFollowSet() || isMulop();
	}

	private Program parseProgram() {
		return null;
	}

	private Declaration parseDeclaration() throws LexException, ParseException {
            int linenum = lex.getLineNum();
            Token nextToken = lex.getNextToken();
            Token nextID = lex.getNextToken();
            Token.TokenType type = nextToken.getTokenType();
            Token.TokenType type2 = nextID.getTokenType();
            if(type2 == Token.TokenType.IDENTIFIER) {
                throw new ParseException("Declaration", linenum, nextToken);
            }
            switch(type) {
                case INT:
                    return parseDeclPrime((String) nextID.getTokenData());
                case VOID:
                    //return parseFunDeclPrime((String) nextID.getTokenData());
                default:
                    throw new ParseException("Declaration", linenum, nextToken);
            }
	}

	private Declaration parseDeclPrime(String id) {
		return null;
	}

	private FunctionDeclaration parseFunDeclPrime(String id) {
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

	// Parse an expression statement
	private ExpressionStatement parseExpressionStmt() {

		// Get lookahead token and line number
		Token lookahead = lex.viewNextToken();
		Token.TokenType type = lookahead.getTokenType();
		int linenum = lex.getLineNum();

		// Parse
		if (isFactorFirstSet()) {
			Expression e = parseExpression();
			return new ExpressionStatement(e);
		}
		else if (type == SEMI_COLON) {
			return new ExpressionStatement(null);
		}
		else {
			throw new ParseException("Expression Statement", linenum, lookahead);
		}
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

	// Parse an expression
	private Expression parseExpression() throws LexException, ParseException {

		// Get the line number and lookahead token
		int linenum = lex.getLineNum();
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
				throw new ParseException("Expression", linenum, nextToken);
			}
		}
	}

	// Parse an expression that has had the first identifier stripped off
	private Expression parseExpressionPrime(String id) throws LexException, ParseException {

		// Get lookahead token
		Token nextToken = lex.viewNextToken();
		Token.TokenType type = nextToken.getTokenType();

		// Expression starts with an array
		if (type == Token.TokenType.LEFT_BRACKET) {
			match(Token.TokenType.LEFT_BRACKET);
			Expression i = parseExpression();
			match(Token.TokenType.RIGHT_BRACKET);
			VarExpression x = new VarExpression(id, i);
			return parseExpressionPrimePrime(x);
		}

		// Assignment expression
		else if (type == Token.TokenType.ASSIGNMENT) {
			VarExpression x = new VarExpression(id);
			match(Token.TokenType.ASSIGNMENT);
			Expression value = parseExpression();
			return new AssignExpression(x, value);
		}

		// Call expression
		else if (type == Token.TokenType.LEFT_PAREN) {
			match(Token.TokenType.LEFT_PAREN);
			ArrayList<Expression> a = parseArgs();
			match(Token.TokenType.RIGHT_PAREN);
			CallExpression call = new CallExpression(id, a);
			return parseSimpleExpressionPrime(call);
		}

		// Some other type of expression
		else if (isFactorFollowSet()) {
			VarExpression firstFactor = new VarExpression(id);
			return parseSimpleExpressionPrime(firstFactor);
		}

		// Error
		else {
			int linenum = lex.getLineNum();
			throw new ParseException("Expression", linenum, nextToken);
		}
	}

	// Parse an expression that has had the leading factor pulled off
	private Expression parseExpressionPrimePrime(VarExpression x) throws LexException, ParseException {
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
			int linenum = lex.getLineNum();
			throw new ParseException("Expression", linenum, nextToken);
		}
	}

	// Parse a simple expression that has had the first factor pulled off
	private Expression parseSimpleExpressionPrime(Expression leadFactor) throws LexException, ParseException {
		if (isFactorFollowSet()) {
			Expression addExp = parseAdditiveExpression(leadFactor);
			if (isRelOp()) {
				Token.TokenType operator = lex.getNextToken().getTokenType();
				Expression lastExp = parseAdditiveExpression(null);
				return new BinaryExpression(addExp, operator, lastExp);
			}
			else {
				return addExp;
			}
		}
		else {
			int linenum = lex.getLineNum();
			Token nextToken = lex.viewNextToken();
			throw new ParseException("Simple Expression", linenum, nextToken);
		}
	}

	// Parse an additive expression
	// First factor could be passed in as a parameter
	private Expression parseAdditiveExpression(Expression prime) throws LexException, ParseException {

		// If looking for a factor and there is a factor
		// or if the factor was passed as a parameter and there is an operator
		if (prime == null && isFactorFirstSet() || prime != null && (isAddop() || isMulop())) {
			Expression left = parseTerm(prime);
			if (isAddop()) {
				Token.TokenType operator = lex.getNextToken().getTokenType();
				Expression right = parseAdditiveExpression(null);
				return new BinaryExpression(left, operator, right);
			}
			else {
				return left;
			}
		}

		// Error
		else {
			int linenum = lex.getLineNum();
			Token nextToken = lex.viewNextToken();
			throw new ParseException("Additive Expression", linenum, nextToken);
		}
	}

	// Parse a term
	private Expression parseTerm(Expression prime) throws LexException, ParseException {

		// If the first factor wasn't passed, parse a factor
		if (prime == null) {
			prime = parseFactor();
		}

		// Term has several factors
		if (isMulop()) {
			Token.TokenType operator = lex.getNextToken().getTokenType();
			Expression right = parseTerm(null);
			return new BinaryExpression(prime, operator, right);
		}

		// Term is a single factor
		else if (isTermFollowSet()) {
			return prime;
		}

		// Error
		else {
			int linenum = lex.getLineNum();
			Token nextToken = lex.viewNextToken();
			throw new ParseException("Term", linenum, nextToken);
		}
	}

	// Parse a factor
	private Expression parseFactor() throws LexException, ParseException {

		// Get the line number and lookahead token
		int linenum = lex.getLineNum();
		Token lookahead = lex.getNextToken();
		Token.TokenType type = lookahead.getTokenType();


		switch(type) {
			case LEFT_PAREN : {
				Expression e = parseExpression();
				match(Token.TokenType.RIGHT_PAREN);
				return e;
			}
			case NUMBER : {
				return new NumExpression((int) lookahead.getTokenData());
			}
			case IDENTIFIER : {
				return parseFactorPrime((String) lookahead.getTokenData());
			}
			default : {
				throw new ParseException("Factor", linenum, lookahead);
			}
		}
	}

	// Parse a factor that has had an identifier pulled off
	private Expression parseFactorPrime(String id) throws LexException, ParseException {

		// Get line number and lookahead character
		int linenum = lex.getLineNum();
		Token lookahead = lex.viewNextToken();
		Token.TokenType type = lookahead.getTokenType();

		// Function call
		if (type == Token.TokenType.LEFT_PAREN) {
			match(Token.TokenType.LEFT_PAREN);
			ArrayList<Expression> parameters = parseArgs();
			match(Token.TokenType.RIGHT_PAREN);
			return new CallExpression(id, parameters);
		}

		// Array
		else if (type == Token.TokenType.LEFT_BRACKET) {
			match(Token.TokenType.LEFT_BRACKET);
			Expression index = parseExpression();
			match(Token.TokenType.RIGHT_BRACKET);
			return new VarExpression(id, index);
		}

		// Just a variable
		else if (isFactorFollowSet()) {
			return new VarExpression(id);
		}

		// Error
		else {
			throw new ParseException("Factor", linenum, lookahead);
		}
	}

	// Parse the arguments for a function call
	private ArrayList<Expression> parseArgs() throws LexException, ParseException {

		ArrayList<Expression> ret = new ArrayList<Expression>();
		int linenum = lex.getLineNum();

		// Parse each argument and throw them in an arraylist
		while (isFactorFirstSet()) {
			ret.add(parseExpression());
			Token lookahead = lex.getNextToken();
			Token.TokenType type = lookahead.getTokenType();
			switch (type) {
				case COMMA : {
					break;
				}
				case RIGHT_PAREN : {
					return ret;
				}
				default : {
					throw new ParseException("Args", linenum, lookahead);
				}
			}
			linenum = lex.getLineNum();
		}

		// Check if arguments are terminated with right paren
		// If not, error
		Token lookahead = lex.viewNextToken();
		Token.TokenType type = lookahead.getTokenType();
		if (ret.isEmpty() && type == Token.TokenType.RIGHT_PAREN) {
			return ret;
		}
		else {
			throw new ParseException("Args", linenum, lookahead);
		}
	}
}
