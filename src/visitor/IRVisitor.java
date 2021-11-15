package visitor;

import IR.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import symboltable.MethodSymbolTable;
import symboltable.Scope;
import symboltable.SymbolTable;
import symboltable.SymbolTableVariable;
import syntaxtree.*;

import java.util.*;

/**
 * Created by Arash on 18/07/02.
 */
public class IRVisitor implements Visitor {
    public ArrayList<Quadruple> IRlist;
    public SymbolTable root;
    public Scope currentScope;
    public int blockNumber;
    Hashtable<Quadruple, List<Label>> labels;
    Hashtable<String, Label> workList;
    Stack<ReturnIR> labelLessReturns;
    Stack<SymbolTableVariable> methodReturnVariable;
    Stack<String> startLoopStack;
    Stack<String> endLoopStack;
    public ArrayList<SymbolTableVariable> stringConstIRList;

    public IRVisitor(SymbolTable root) {
        blockNumber = 0;
        this.IRlist = new ArrayList<>();
        this.root = root;
        this.currentScope = root;
        this.labels = new Hashtable<>();
        this.workList = new Hashtable<>();
        labelLessReturns = new Stack<>();
        methodReturnVariable = new Stack<>();
        startLoopStack = new Stack<>();
        endLoopStack = new Stack<>();
        stringConstIRList = new ArrayList<>();
    }

    public Integer nextBlockNumber() {
        blockNumber++;
        return blockNumber;
    }


    public Label addLabel(Quadruple q, Label l) {
        List<Label> temp = labels.get(q);

        if (temp == null) {
            temp = new ArrayList<Label>();
        }

        temp.add(l);
        labels.put(q, temp);

        return l;
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
        //done
    }

    @Override
    public void visit(Arithmatic a) {
        //nothing to be done
    }

    @Override
    public void visit(Array_Index_Recursive a) {
        for (Expr expr : a.exprStack){
            visit(expr);
        }
    }

    @Override
    public void visit(Assignment a) {
        visit(a.variable);
        visit(a.expr);
        //if(a.variable.variableType)// TODO: 05/07/18 assignment difoidjsfkhdsaljkfhnksaljdnhfjka 
        IRlist.add(new VariableAssignIR(a.expr.result, a.variable,currentScope));
    }

    @Override
    public void visit(Binary_Op b) {
        //done
    }

    @Override
    public void visit(Block b) {
        if (b.var_dcls_or_statements != null) {
            for (int i = b.var_dcls_or_statements.var_dcls_and_statements.size() - 1; i >= 0; i--) {
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
                ConditionalBranch conditionalBranch =
                        new ConditionalBranch(c.ifExpr.result, new LabelGenerator().toString()
                                , new LabelGenerator().toString());
                IRlist.add(conditionalBranch);
                IRlist.add(new MakeLabelIR(conditionalBranch.ifTrue));
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(c.ifBlock);
                currentScope = currentScope.exitScope();
                IRlist.add(new MakeLabelIR(conditionalBranch.ifFalse));
                break;
            }
            case IFELSE: {
                visit(c.ifExpr);
                Integer blockNum = nextBlockNumber();
                ConditionalBranch conditionalBranch =
                        new ConditionalBranch(c.ifExpr.result, new LabelGenerator().toString()
                                , new LabelGenerator().toString());
                IRlist.add(conditionalBranch);
                IRlist.add(new MakeLabelIR(conditionalBranch.ifTrue));
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(c.ifBlock);
                currentScope = currentScope.exitScope();
                UnconditionalBranchIR unconditionalBranchIR =
                        new UnconditionalBranchIR("");//will be determined* after else block visiting
                IRlist.add(unconditionalBranchIR);
                blockNum = nextBlockNumber();
                IRlist.add(new MakeLabelIR(conditionalBranch.ifFalse));
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(c.elseBlock);
                currentScope = currentScope.exitScope();
                MakeLabelIR makeLabelIR = new MakeLabelIR(new LabelGenerator().toString());
                IRlist.add(makeLabelIR);
                unconditionalBranchIR.label = makeLabelIR.labelName;//determined* here
                break;
            }
            case DEFAULT_SWITCH: {
                visit(c.switch_id);
                Integer blockNum = nextBlockNumber();
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(c.defaultCaseBlock);
                currentScope = currentScope.exitScope();
                break;
            }
            case SWITCH: {
                String defaultLabel = new LabelGenerator().toString();
                String outLabel = new LabelGenerator().toString();
                HashMap<Integer, String> labels = new HashMap<>();
                for (int i = 0; i < c.switch_case_cases.switch_case_caseStack.size(); i++) {
                    labels.put(c.switch_case_cases.switch_case_caseStack.get(i).int_const
                            , new LabelGenerator().toString());
                }
                IRlist.add(new SwitchIR(
                        c.switch_id.getResult(currentScope), defaultLabel, labels));//will generate the switch
                for (Switch_Case_Case switch_case_case : c.switch_case_cases.switch_case_caseStack) {
                    Integer blockNum = nextBlockNumber();
                    IRlist.add(new MakeLabelIR(labels.get(switch_case_case.int_const)));
                    currentScope = currentScope.enterScope(blockNum.toString());
                    visit(switch_case_case.block);
                    currentScope = currentScope.exitScope();
                    IRlist.add(new UnconditionalBranchIR(outLabel));
                }
                IRlist.add(new MakeLabelIR(defaultLabel));
                Integer blockNum = nextBlockNumber();
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(c.defaultCaseBlock);
                currentScope = currentScope.exitScope();
                IRlist.add(new MakeLabelIR(outLabel));
                break;
            }
        }

    }

    @Override
    public void visit(Conditional c) {
        //done
    }

    @Override
    public void visit(Const_Val c) {
        IRlist.add(new AssignmentIR("=Const", null, null, c.generateResult()));
        if(c.const_val instanceof String){
            stringConstIRList.add(c.result);
        }

    }

    @Override
    public void visit(Expr e) {
        switch (e.exprType) {
            case BINARY_EXPR: {
                visit(e.binaryExprLeft);
                visit(e.binaryExprRight);
                if (e.binary_op.arithmatic != null) {
                    switch (e.binary_op.arithmatic.arithmaticSign) {
                        case PLUS: {
                            IRlist.add(new AssignmentIR("+", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case MINUS: {
                            IRlist.add(new AssignmentIR("-", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case TIMES: {
                            IRlist.add(new AssignmentIR("*", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case SLASH: {
                            IRlist.add(new AssignmentIR("/", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case MOD: {
                            IRlist.add(new AssignmentIR("%", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case AMPERSAND: {
                            IRlist.add(new AssignmentIR("&", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case PIPE: {
                            IRlist.add(new AssignmentIR("|", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                        case CARET: {
                            IRlist.add(new AssignmentIR("^", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                    }
                } else {
                    //this is conditional case
                    switch (e.binary_op.conditional.conditionalType) {
                        case ISEQUAL: {
                            IRlist.add(new AssignmentIR("==", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case NOTEQUAL: {
                            IRlist.add(new AssignmentIR("!=", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case GEQ: {
                            IRlist.add(new AssignmentIR(">=", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case LEQ: {
                            IRlist.add(new AssignmentIR("<=", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case LESS: {
                            IRlist.add(new AssignmentIR("<", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case GREATER: {
                            IRlist.add(new AssignmentIR(">", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case AND: {
                            IRlist.add(new AssignmentIR("and", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case OR: {
                            IRlist.add(new AssignmentIR("||", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));


                            break;
                        }
                        case NOT: {
                            IRlist.add(new AssignmentIR("not", e.binaryExprLeft.result, e.binaryExprRight.result, e.generateResult()));
                            break;
                        }
                    }
                }
                break;
            }
            case METHOD_CALL: {
                visit(e.method_call);
                IRlist.add(new AssignmentIR("=", e.method_call.result, null, e.generateResult()));
                break;
            }
            case VARIABLE: {
                visit(e.variable);
                IRlist.add(new AssignmentIR("=", e.variable.result, null, e.generateResult()));
                break;
            }
            case CONST_VAL: {
                visit(e.const_val);
                IRlist.add(new AssignmentIR("=", e.const_val.result, null, e.generateResult()));

                break;
            }
            case EXPR:
                visit(e.expr);
                IRlist.add(new AssignmentIR("=", e.expr.result, null, e.generateResult()));
                break;
            case UMINUS:
                visit(e.expr);
                IRlist.add(new AssignmentIR("UMINUS", e.expr.result, null, e.generateResult()));
                break;
            case TILDE:
                visit(e.expr);
                IRlist.add(new AssignmentIR("TILDE", e.expr.result, null, e.generateResult()));
                break;
            case SIZEOF:
                visit(e.type);
                IRlist.add(new AssignmentIR("SIZEOF", e.type.result, null, e.generateResult()));
                break;
        }
    }

    @Override
    public void visit(Extern_Dcl e) {
        //fck :)
    }

    @Override
    public void visit(Func_Dcl f) {
        if (f.block == null)
            return;
        visit(f.identifier);
        visit(f.type);
        IRlist.add(new GenerateMethodSignature(f));
        currentScope = currentScope.enterScope(f.getHash());
        MethodSymbolTable methodSymbolTable = ((MethodSymbolTable) currentScope);
     /*   for (String name: methodSymbolTable.args.keySet()){
            //SymbolTable subArg = methodSymbolTable.args.get(name)
            methodSymbolTable.updateArg;
        }*/
        IRlist.add(new AssignmentIR("alloca", f.returnVariable, null, null));
        methodReturnVariable.push(f.returnVariable);
        if (f.arguments != null)
            visit(f.arguments);
        visit(f.block);
        MakeLabelIR makeLabelIR = new MakeLabelIR(new LabelGenerator().toString());
        IRlist.add(makeLabelIR);
        while (!labelLessReturns.empty()) {
            ReturnIR returnIR = labelLessReturns.pop();
            returnIR.returnLabel = makeLabelIR.labelName;
        }
        methodReturnVariable.pop();
        IRlist.add(new RetIR(f.returnVariable));
        IRlist.add(new StringGeneratorIR("}"));
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

    }

    @Override
    public void visit(Identifier i) {
        //it has getResult
    }

    @Override
    public void visit(Loop_Stmt l) {
        switch (l.loopType) {
            case FOR: {
                if (l.assignment != null)
                    visit(l.assignment);
                String startLoopLabel = new LabelGenerator().toString();
                String endLoopLabel = new LabelGenerator().toString();
                startLoopStack.push(startLoopLabel);
                endLoopStack.push(endLoopLabel);
                String startBlockLabel = new LabelGenerator().toString();
                IRlist.add(new MakeLabelIR(startLoopLabel));
                visit(l.expr);
                ConditionalBranch conditionalBranch =
                        new ConditionalBranch(l.expr.result, startBlockLabel
                                , endLoopLabel);
                IRlist.add(conditionalBranch);
                IRlist.add(new MakeLabelIR(startBlockLabel));
                Integer blockNum = nextBlockNumber();
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(l.forBlock);
                currentScope = currentScope.exitScope();
                if (l.assignment_expr != null) {
                    if (l.assignment_expr instanceof Assignment)
                        visit((Assignment) l.assignment_expr);
                    else
                        visit((Expr) l.assignment_expr);
                }
                UnconditionalBranchIR unconditionalBranchIR =
                        new UnconditionalBranchIR(startLoopLabel);
                IRlist.add(unconditionalBranchIR);
                IRlist.add(new MakeLabelIR(endLoopLabel));
                startLoopStack.pop();
                endLoopStack.pop();
                break;
            }
            case REPEAT: {
                String startLoopLabel = new LabelGenerator().toString();
                String afterLoopLabel = new LabelGenerator().toString();
                startLoopStack.push(startLoopLabel);
                endLoopStack.push(afterLoopLabel);
                IRlist.add(new MakeLabelIR(startLoopLabel));
                Integer blockNum = nextBlockNumber();
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(l.repeatBlock);
                currentScope = currentScope.exitScope();
                visit(l.repeatExpr);
                ConditionalBranch conditionalBranch =
                        new ConditionalBranch(l.repeatExpr.result, startLoopLabel, afterLoopLabel);
                IRlist.add(conditionalBranch);
                IRlist.add(new MakeLabelIR(afterLoopLabel));
                startLoopStack.pop();
                endLoopStack.pop();
                break;
            }
            case FOREACH: {
                SymbolTableVariable countLoop =
                        new SymbolTableVariable(new Identifier(new NameGenerator().toString()),new Type(Type.TypeEnum.INT),null,null,false);
                IRlist.add(new AssignmentIR("alloca", countLoop, null, null));
                String startLoopLabel = new LabelGenerator().toString();
                String startBlockLabel = new LabelGenerator().toString();
                String afterLoopLabel = new LabelGenerator().toString();
                startLoopStack.push(startLoopLabel);
                endLoopStack.push(afterLoopLabel);
                visit(l.foreachItem);
                visit(l.foreachSetIdentifier);
                IRlist.add(new MakeLabelIR(startLoopLabel));
                IRlist.add(new ForEachConditionalBr(countLoop,l.foreachItem,l.foreachSetIdentifier,currentScope,startBlockLabel,afterLoopLabel));
                Integer blockNum = nextBlockNumber();
                IRlist.add(new MakeLabelIR(startBlockLabel));
                currentScope = currentScope.enterScope(blockNum.toString());
                visit(l.foreachBlock);
                currentScope = currentScope.exitScope();
                IRlist.add(new UnconditionalBranchIR(startLoopLabel));
                IRlist.add(new MakeLabelIR(afterLoopLabel));
                startLoopStack.pop();
                endLoopStack.pop();
                break;
            }
        }
    }

    @Override
    public void visit(Method_Call m) {
        if (m.parameters != null) {
            for (Expr expr : m.parameters.parameterStack) {
                visit(expr);
            }
        }
        if(m.getHash().contains("scanf")){
            IRlist.add(new MethodCallIR("scanf",null,
                    m.parameters.parameterStack,currentScope.lookupVariable(m.parameters.parameterStack.get(0).variable.identifier.toString())));
        }
        else if(m.getHash().contains("printf")){
            IRlist.add(new MethodCallIR("printf",null,
                    m.parameters.parameterStack,null));
        }
        else if (m.parameters == null) {
            Type resultType =currentScope.lookupMethod(m.getHash()).returnType;
            IRlist.add(new MethodCallIR(m.getHash(),currentScope.lookupMethod(m.getHash()),
                    null,m.generateResult(resultType)));
        } else {
            Type resultType =currentScope.lookupMethod(m.getHash()).returnType;
            IRlist.add(new MethodCallIR(m.getHash(), currentScope.lookupMethod(m.getHash()),
                    m.parameters.parameterStack, m.generateResult(resultType)));
        }
    }

    @Override
    public void visit(Parameters p) {
        //will be done in method_call
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
                IRlist.add(new ReturnIR(true, null, null));
                labelLessReturns.push(((ReturnIR) IRlist.get(IRlist.size() - 1)));
                break;
            case BREAK:
                IRlist.add(new UnconditionalBranchIR(endLoopStack.peek()));
                break;
            case CONTINUE:
                IRlist.add(new UnconditionalBranchIR(startLoopStack.peek()));
                break;
            case RETURN_EXP:
                visit(s.expr);
                SymbolTableVariable returnVariable = methodReturnVariable.peek();
                IRlist.add(new ReturnIR(false, returnVariable, s.expr.result));
                labelLessReturns.push(((ReturnIR) IRlist.get(IRlist.size() - 1)));
                break;
        }
    }

    @Override
    public void visit(Struct_Dec s) {
/*        for (Var_Dcl var_dcl:
             s.var_dcls.var_dclList)
        {
            visit(var_dcl.variable.array_index_recursive);
        }*/
        //done in buildSymbolTableVisitor
    }

    @Override
    public void visit(Switch_Case_Case s) {
        //done handled in cond_stmt
    }

    @Override
    public void visit(Switch_Case_Cases s) {
        //done handled in cond_stmt
    }

    @Override
    public void visit(Type t) {
        t.generateResult();
    }

    @Override
    public void visit(Var_Dcl v) {
        //here just the expression should be assigned to v and the declaration should have been done
        //the declarations are in another code array which is made in BuildSymbolTableVisitor
        if(v.variable.array_index_recursive!=null)
            visit(v.variable.array_index_recursive);
        if (v.expr != null) {
            visit(v.expr);
            IRlist.add(new VariableAssignIR
                    (v.expr.result,v.variable,currentScope));
        }
    }

    @Override
    public void visit(Var_Dcls v) {
        for (Var_Dcl var_dcl : v.var_dclList) {
            visit(var_dcl);
        }
    }

    @Override
    public void visit(Variable v) {
        switch (v.variableType) {

            case IDENTIFIER: {
                IRlist.add(new AssignFromVariableIR(v.identifier.getResult(currentScope), v.generateResult(currentScope)));
                break;
            }
            case ARRAY_IDENTIFIER: {
                visit(v.array_index_recursive);

                IRlist.add(new AssignToArrayIR //it is actually assigning FROM array
                        ("=", v.identifier.getResult(currentScope), v.array_index_recursive.exprStack, v.generateResult(currentScope)));
                break;
            }
            case TILDE_VARIABLE: {
                visit(v.variable);
                IRlist.add(new AssignmentIR("TILDE", v.variable.result, null, v.generateResult(currentScope)));

                break;
            }
            case MINUSMINUS_VARIABLE: {
                visit(v.variable);
                IRlist.add(new AssignmentIR("MINUSMINUS", v.variable.result, null, v.generateResult(currentScope)));
                IRlist.add(new VariableAssignIR(v.result,v.variable,currentScope));

                break;
            }
            case PLUSPLUS_VARIABLE: {
                visit(v.variable);
                IRlist.add(new AssignmentIR("PLUSPLUS", v.variable.result, null, v.generateResult(currentScope)));
                IRlist.add(new VariableAssignIR(v.result,v.variable,currentScope));

                break;
            }
            case VARIABLE_MINUSMINUS: {
                visit(v.variable);
                IRlist.add(new AssignmentIR("MINUSMINUS", v.variable.result, null, v.generateResult(currentScope)));
                IRlist.add(new VariableAssignIR(v.result,v.variable,currentScope));
                IRlist.add(new ArithmaticLocalIR("+",v.result));
                break;
            }
            case VARIABLE_PLUSPLUS: {
                visit(v.variable);
                IRlist.add(new AssignmentIR("PLUSPLUS", v.variable.result, null, v.generateResult(currentScope)));
                IRlist.add(new VariableAssignIR(v.result,v.variable,currentScope));
                IRlist.add(new ArithmaticLocalIR("-",v.result));
                break;
            }
            case RECORD_ITEM: {
                IRlist.add(new AssignFromRecord( v, v.generateResult(currentScope),currentScope));

                /*visit(v.variable);
                IRlist.add
                        (new getStructElementIR(v.variable.result,
                                root.getRecordSymbolTable
                                        (v.variable.result.type.identifier.toString()).argumentNumber.get(v.identifier), v.generateResult(root)));*/
                //variable.result's type should be a struct

                break;
            }
        }

    }
}
