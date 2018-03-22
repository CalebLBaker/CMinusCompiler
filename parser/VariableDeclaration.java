package parser;

public class VariableDeclaration extends Declaration {
    private String name;
    private int index;
    
    VariableDeclaration(String n) {
        name = n;
        index = -1;
    }
    
    VariableDeclaration(String n, int i) {
        name = n;
        index = i;
    }
}