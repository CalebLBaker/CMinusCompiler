package parser;
import java.util.ArrayList;

import scanner.Token;

public class FunctionDeclaration extends Declaration {
    private String name;    
    private Token.TokenType returnType;
    private ArrayList<Parameter> parameters;
    private CompoundStatement statement;
            
    FunctionDeclaration( Token.TokenType t, String n, ArrayList<Parameter> p, CompoundStatement s) {
        returnType = t;
        name = n;
        parameters = p;
        statement = s;    
    }
}