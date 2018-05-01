/**
* This class is used for symbol tables during code generation
*
* @author Caleb Baker
* @version 1.0
* File: Statement.java
* Created: Spring 2018
* (C)Copyright Cedarville University, its Computer Science faculty, and the
* authors. All rights reserved.
*
* Description: This class is used as a symbol table.
*              It contains a hash maps of symbols and a pointer to the symbol
*              table for the super-scope.
*
*/


package parser;
import java.util.HashMap;

public class SymbolTable {

    // Symbol table for scope containing this scope.
    private SymbolTable parent;

    // Hash map that maps variable names to register numbers.
    private HashMap<String, SymbolValue> table;

    public class SymbolValue {
        public SymbolValue(Integer l, boolean a) {
            location = l;
            isArray = a;
        }
        public SymbolValue(Integer l) {
            this(l, false);
        }
        public Integer location;
        public boolean isArray;
    }

    /** Constructor.
     *  @param p the symbol table of the parent scope.
    */
    public SymbolTable(SymbolTable p) {
        parent = p;
        table = new HashMap<String, SymbolValue>();
    }

    /**
     * Global scope constructor.
     */
    public SymbolTable() {
        this(null);
    }

    /**
     * Gets the register number for a variable
     * @param name the name of the variable that is being looked for.
     * @return the register the variable is in. -1 if variable is in global scope.
     *          null if variable does not exist.
     */
    public Integer get(String name) {
        Integer reg = null;
        for (SymbolTable curr = this; curr != null && reg == null; curr = curr.parent) {
            SymbolValue temp = curr.table.get(name);
            if (temp != null) {
                reg = temp.location;   
            }
        }
        return reg;
    }

    /**
     * Gets the register number for a variable
     * @param name the name of the variable that is being looked for.
     * @return the value stored in the symbol table,
     */
    public SymbolValue getValue(String name) {
        SymbolValue reg = null;
        for (SymbolTable curr = this; curr != null && reg == null; curr = curr.parent) {
            reg = curr.table.get(name);
        }
        return reg;
    }

    /**
     * Inserts a variable into the symbol table.
     * @param name the name of the variable being inserted.
     * @param regNum the number of the register holding the variable.
     */
    public void insert(String name, Integer regNum) {
        table.put(name, new SymbolValue(regNum));
    }

    /**
     * Inserts a variable into the symbol table.
     * @param name the name of the variable being inserted.
     * @param regNum the number of the register holding the variable.
     * @param arr a boolean flag saying whether the symbol is an array.
     */
    public void insert(String name, Integer regNum, boolean arr) {
        table.put(name, new SymbolValue(regNum, arr));
    }
}