package parser;

import scanner.Token;

public class FunctionDeclaration extends Declaration {
    private String name;    
    private Token.TokenType returnType;
    private Declaration parameters;
            
    FunctionDeclaration( Token.TokenType t, String n, Declaration p) {
        returnType = t;
        name = n;
        parameters = p;
    }
}