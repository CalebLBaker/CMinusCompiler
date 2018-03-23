package parser;
import java.util.ArrayList;

import scanner.Token;

public class FunctionDeclaration extends Declaration {
    // name of the function 
    private String name;
    
    //return type of the function
    private Token.TokenType returnType;
    
    // parameters of the function
    private ArrayList<Parameter> parameters;
    
    // compound statement the makes up the function body
    private CompoundStatement statement;

    /**
     * Function declaration constructor
     * @param t the return type of the function
     * @param n the name of the array.
     * @param p the function parameters
     * @param s the compound statement the makes up the function body
     */
    FunctionDeclaration( Token.TokenType t, String n, ArrayList<Parameter> p, CompoundStatement s) {
        returnType = t;
        name = n;
        parameters = p;
        statement = s;
    }

    /**
     * Prints the node
     * @param tab current level of indentation
     */
    public void print(String tab) {
        System.out.println(tab + Token.toString(returnType) + " " + name + "(");
        if (parameters != null) {
            String newTab = tab + "    ";
            for (Parameter p : parameters) {
                p.print(newTab);
            }
        }
        System.out.println(tab + ")");
        statement.print(tab);
    }
}