package parser;

public class VariableDeclaration extends Declaration {
    private String name;
    private Integer index;

    VariableDeclaration(String n) {
        name = n;
        index = null;
    }

    VariableDeclaration(String n, Integer i) {
        name = n;
        index = i;
    }

    /**
     * Prints the current node of the tree
     * @param tab the current level of indentation
     */
    public void print(String tab) {
        System.out.println(tab + "int");
        tab += "    ";
        System.out.println(tab + name);
        if (index != null) {
            System.out.println(tab + Integer.toString(index));
        }
    }
}