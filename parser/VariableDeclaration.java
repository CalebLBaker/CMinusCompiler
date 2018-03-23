package parser;

public class VariableDeclaration extends Declaration {
    private String name;
    private Integer index;

    VariableDeclaration(String n) {
        name = n;
        index = null;
    }

    VariableDeclaration(String n, int i) {
        name = n;
        index = i;
    }

    public void print(String tab) {
        System.out.println(tab + "int");
        tab += "    ";
        System.out.println(tab + name);
        if (index != null) {
            System.out.println(tab + Integer.toString(index));
        }
    }
}