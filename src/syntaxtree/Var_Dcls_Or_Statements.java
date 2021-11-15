package syntaxtree;

import java.util.Stack;
// we used Object as Stack type because order of insertion
// between Var_Dcls and Statements matters;
public class Var_Dcls_Or_Statements {
    public Stack<Object> var_dcls_and_statements;
    public Var_Dcls_Or_Statements(Object object, Var_Dcls_Or_Statements var_dcls_or_statements){
        var_dcls_or_statements.var_dcls_and_statements.push(object);
        this.var_dcls_and_statements = var_dcls_or_statements.var_dcls_and_statements;
    }
    public Var_Dcls_Or_Statements(Object object){
        var_dcls_and_statements = new Stack<>();
        var_dcls_and_statements.push(object);
    }

}
