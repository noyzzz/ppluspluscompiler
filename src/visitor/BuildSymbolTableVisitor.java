package visitor;

import IR.DclIR;
import IR.StructIR;
import symboltable.BlockSymbolTable;
import symboltable.MethodSymbolTable;
import symboltable.Scope;
import symboltable.SymbolTable;
import syntaxtree.*;

import java.util.ArrayList;

/**
 * Created by Arash on 18/07/01.
 */
public class BuildSymbolTableVisitor implements Visitor {
    public SymbolTable root;
    public Scope currentScope;
    public int blockNumber;
    public ArrayList<StructIR> structList;
    public ArrayList<DclIR> dclList;

    public BuildSymbolTableVisitor() {
        root = new SymbolTable();
        currentScope = root;
        blockNumber = 0;
        dclList = new ArrayList<DclIR>();
        structList = new ArrayList<>();
    }

    public Scope getFirstScope() {
        return root;
    }

    public Integer nextBlockNumber() {
        blockNumber++;
        return blockNumber;
    }

    @Override
    public void visit(Program n) {
        if (n.func_extern != null) {
            visit(n.func_extern);
        }
        if (n.struct_dec != null)
            visit(n.struct_dec);
        if (n.var_dcls != null) {
            visit(n.var_dcls);
        }
        if (n.program != null)
            visit(n.program);
    }

    @Override
    public void visit(Arguments a) {
        visit(a.identifier);
        visit(a.idarray);
        visit(a.type);
        if (a.arguments != null)
            visit(a.arguments);
    }

    @Override
    public void visit(Arithmatic a) {
        // :D
    }

    @Override
    public void visit(Array_Index_Recursive a) {
        for (Expr e :
                a.exprStack) {
            visit(e);
        }
    }

    @Override
    public void visit(Assignment a) {
        visit(a.variable);
        visit(a.expr);
    }

    @Override
    public void visit(Binary_Op b) {
        // both Arithmatic and Conditional are empty
    }

    @Override
    public void visit(Block b) {
        if (b.var_dcls_or_statements != null) {
            for (int i = b.var_dcls_or_statements.var_dcls_and_statements.size() - 1; i >= 0; i--)
            {
                Object o = b.var_dcls_or_statements.var_dcls_and_statements.get(i);
                if (o instanceof Statement)
                    visit((Statement) o);
                else
                    visit((Var_Dcls) o);
            }
        }
    }

    @Override
    public void visit(Brac b) {
        //done
    }

    @Override
    public void visit(Cond_Stmt c) {
        switch (c.cond_stmt_enum) {

            case IF: {
                visit(c.ifExpr);
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof BlockSymbolTable) {
                    BlockSymbolTable blockSymbolTable = (BlockSymbolTable) currentScope;
                    blockSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(c.ifBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(c.ifBlock);
                    currentScope = currentScope.exitScope();
                }
                break;
            }
            case IFELSE: {
                visit(c.ifExpr);
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(c.ifBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(c.ifBlock);
                    currentScope = currentScope.exitScope();
                }
                blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(c.elseBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(c.elseBlock);
                    currentScope = currentScope.exitScope();
                }

            }
            break;
            case DEFAULT_SWITCH: {
                visit(c.switch_id);
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(c.defaultCaseBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(c.defaultCaseBlock);
                    currentScope = currentScope.exitScope();
                }
                break;
            }
            case SWITCH: {
                visit(c.switch_id);
                visit(c.switch_case_cases);
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(c.defaultCaseBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(c.defaultCaseBlock);
                    currentScope = currentScope.exitScope();
                }
                break;
            }
        }
    }

    @Override
    public void visit(Conditional c) {
        //
    }

    @Override
    public void visit(Const_Val c) {
    }

    @Override
    public void visit(Expr e) {
        switch (e.exprType) {
            case BINARY_EXPR: {
                visit(e.binaryExprLeft);
                visit(e.binaryExprRight);
                break;
            }
            case METHOD_CALL: {
                visit(e.method_call);
                break;
            }
            case VARIABLE: {
                visit(e.variable);
                break;
            }
            case CONST_VAL: {
                visit(e.const_val);
                break;
            }
            case EXPR: {
                visit(e.expr);
                break;
            }
            case UMINUS: {
                visit(e.expr);
                break;
            }
            case TILDE: {
                visit(e.expr);
                break;
            }
            case SIZEOF: {
                visit(e.type);
                break;
            }
        }
    }

    @Override
    public void visit(Extern_Dcl e) {
        //fuck you
    }

    @Override
    public void visit(Func_Dcl f) {
        if (f.block == null)
            return;
        visit(f.type);
        visit(f.identifier);
        try {
            if (!currentScope.equals(root))
                throw new Exception("root is not current scope in func dcl");
        } catch (Exception e) {
            e.printStackTrace();
        }
        root.addMethod(f.type, f.identifier, f.arguments);
        currentScope = root.enterScope(f.getHash());
        if (f.arguments != null)
            visit(f.arguments);
        visit(f.block);
        currentScope = currentScope.exitScope();

    }

    @Override
    public void visit(Func_Extern f) {
        if (f.extern_dcl != null) {
            //fuck you
        }
        if (f.func_dcl != null) {
            visit(f.func_dcl);
        }
    }

    @Override
    public void visit(Idarray i) {
        //:D
    }

    @Override
    public void visit(Identifier i) {
        //nothing to be visited
    }

    @Override
    public void visit(Loop_Stmt l) {
        switch (l.loopType) {
            case FOR: {
                if (l.assignment != null)
                    visit(l.assignment);
                visit(l.expr);
                if (l.assignment_expr != null) {
                    if (l.assignment_expr instanceof Assignment)
                        visit((Assignment) l.assignment_expr);
                    else
                        visit((Expr) l.assignment_expr);
                }
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(l.forBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(l.forBlock);
                    currentScope = currentScope.exitScope();
                }
                break;
            }
            case REPEAT: {
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(l.repeatBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(l.repeatBlock);
                    currentScope = currentScope.exitScope();
                }
                visit(l.repeatExpr);
                break;
            }
            case FOREACH: {
                visit(l.foreachSetIdentifier);
                Integer blockNum = nextBlockNumber();
                if (currentScope instanceof MethodSymbolTable) {
                    MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
                    methodSymbolTable.addBlock(blockNum.toString());
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(l.foreachItem);
                    visit(l.foreachBlock);
                    currentScope = currentScope.exitScope();
                } else {
                    SymbolTable symbolTable = (SymbolTable) currentScope;
                    symbolTable.addBlock(blockNum.toString());
                    currentScope.enterScope(blockNum.toString());
                    visit(l.foreachItem);
                    visit(l.foreachBlock);
                    currentScope = currentScope.exitScope();
                }
                break;
            }
        }

    }

    @Override
    public void visit(Method_Call m) {
        visit(m.identifier);
        if (m.parameters != null) {
            for (Expr e :
                    m.parameters.parameterStack) {
                visit(e);
            }
        }
    }

    @Override
    public void visit(Parameters p) {
        for (Expr e :
                p.parameterStack) {
            visit(e);
        }
    }

    @Override
    public void visit(Statement s) {
        switch (s.statementType) {
            case ASSIGNMENT:
                visit(s.assignment);
                break;
            case METHOD_CALL:
                visit(s.method_call);
                break;
            case COND_STMT:
                visit(s.cond_stmt);
                break;
            case LOOP_STMT:
                visit(s.loop_stmt);
                break;
            case RETURN_VOID:
                break;
            case BREAK:
                break;
            case CONTINUE:
                break;
            case RETURN_EXP:
                visit(s.expr);
                break;
        }


    }

    @Override
    public void visit(Struct_Dec s) {
        visit(s.identifier);
        SymbolTable symbolTable = (SymbolTable) currentScope;
        symbolTable.addRecord(s.identifier, s.var_dcls);// TODO: 05/07/18 seems to be wrong 
        structList.add(new StructIR(s.identifier, s.var_dcls)); // here we add the struct in code

    }

    @Override
    public void visit(Switch_Case_Case s) {
        visit(new Const_Val(s.int_const));
        Integer blockNum = nextBlockNumber();
        if (currentScope instanceof MethodSymbolTable) {
            MethodSymbolTable methodSymbolTable = (MethodSymbolTable) currentScope;
            methodSymbolTable.addBlock(blockNum.toString());
            currentScope = currentScope.enterScope(blockNum.toString());
            visit(s.block);
            currentScope = currentScope.exitScope();
        } else {
            SymbolTable symbolTable = (SymbolTable) currentScope;
            symbolTable.addBlock(blockNum.toString());
            currentScope.enterScope(blockNum.toString());
            visit(s.block);
            currentScope = currentScope.exitScope();
        }
    }

    @Override
    public void visit(Switch_Case_Cases s) {
        for (Switch_Case_Case switch_case_case : s.switch_case_caseStack) {
            visit(switch_case_case);
        }
    }

    @Override
    public void visit(Type t) {
        if (t.identifier != null)
            visit(t.identifier);
    }

    @Override
    public void visit(Var_Dcl v) {
        visit(v.type);
        //const values are handled in addVariable
        currentScope.addVariable(v);
        dclList.add(new DclIR(v, currentScope));
        if (v.expr != null)
            visit(v.expr);
    }

    @Override
    public void visit(Var_Dcls v) {
        for (Var_Dcl vd :
                v.var_dclList) {
            visit(vd);
        }
    }

    @Override
    public void visit(Variable v) {
        switch (v.variableType) {
            case IDENTIFIER:
                visit(v.identifier);
                break;
            case ARRAY_IDENTIFIER:
                visit(v.identifier);
                visit(v.array_index_recursive);
                break;
            case TILDE_VARIABLE:
                visit(v.variable);
                break;
            case MINUSMINUS_VARIABLE:
                visit(v.variable);
                break;
            case PLUSPLUS_VARIABLE:
                visit(v.variable);
                break;
            case VARIABLE_MINUSMINUS:
                visit(v.variable);
                break;
            case VARIABLE_PLUSPLUS:
                visit(v.variable);
                break;
            case RECORD_ITEM:
/*                visit(v.variable.identifier);
                currentScope = currentScope.enterScope(v.variable.identifier.toString());
                visit(v.identifier);
                currentScope = currentScope.exitScope();*/
                break;
        }
    }
}
