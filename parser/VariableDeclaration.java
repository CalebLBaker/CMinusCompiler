/**
* This is the variable declaration class for the c- parser
*
* @author Caleb Baker, Faith Trautmann
* @version 1.0
* File: VariableDeclaration.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* This class defines a variable declaration for use in the c- parser. It inherits
* from Declaration and is not expected to be inherited from. 
*
*/
package parser;
import lowlevel.Data;
import lowlevel.CodeItem;
import lowlevel.Function;

public class VariableDeclaration extends Declaration {
    private String name;
    private Integer index;

    VariableDeclaration(String n) {
        
        // name of variable
        name = n;
        
        // index if it is an array, null otherwise 
        index = null;
    }

    /**
     * variable constructor
     * @param n the name of the variable.
     * @param i the index into an array.
     */
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

	public CodeItem genCode(SymbolTable tab) {
        tab.insert(name, -1);
		return (CodeItem) new Data(Data.TYPE_INT, name);
    }
    
    public void genCode(Function func, SymbolTable tab) {

    }
}
