package syntaxtree;

import java.util.Stack;

public class Parameters {
    public Stack<Expr> parameterStack;

    public Parameters(Expr expr, Parameters parameters) {
        parameters.parameterStack.push(expr);
        this.parameterStack = parameters.parameterStack;
    }

    public Parameters(Expr expr) {
        parameterStack = new Stack<Expr>();
        parameterStack.push(expr);
    }

    public String getHash() {
        StringBuilder ret = new StringBuilder();
        for (int i = parameterStack.size()-1; i>=0; i--) {
            ret.append(parameterStack.get(i).result.type.getHash());
            if (parameterStack.get(i).result.array_index_recursive != null)
                ret.append(parameterStack.get(i).result.array_index_recursive.exprStack.size());
        }
        return ret.toString();
    }
}
