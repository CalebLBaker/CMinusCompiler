package parser;

public class Parameter {
    private String name;
    private boolean isArray;

    Parameter(String n, boolean iA) {
        name = n;
        isArray = iA;
    }

    /**
     * Print this node of the tree
     * @param tab the current level of indentation
     */
    public void print(String tab) {
    	String n = name;
    	if (isArray) {
    		n += "[]";
    	}
    	System.out.println(tab + n);
    }
}