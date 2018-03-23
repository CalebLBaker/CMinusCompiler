package parser;

public class Parameter {
    // name of the parameter 
    private String name;
    
    // true if the parameter is an array, false otherwise
    private boolean isArray;

    /**
     * Parameter constructor
     * @param n the name of the parameter.
     * @param iA is the parameter an array.
     */
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