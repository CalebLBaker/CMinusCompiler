package parser;
import java.util.HashMap;

public class SymbolTable {

    public SymbolTable parent;
    public HashMap<String, Integer> table;

    public SymbolTable(SymbolTable p) {
        parent = p;
        table = new HashMap<String, Integer>();
    }

    public SymbolTable() {
        this(null);
    }

    public Integer get(String name) {
        Integer reg = null;
        for (SymbolTable curr = this; curr != null && reg == null; curr = curr.parent) {
            reg = curr.table.get(name);
        }
        return reg;
    }

    public void insert(String name, Integer regNum) {
        table.put(name, regNum);
    }
}