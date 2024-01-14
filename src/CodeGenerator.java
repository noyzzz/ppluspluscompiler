import IR.*;
import symboltable.*;
import syntaxtree.*;
import visitor.IRVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Arash on 18/06/28.
 */
public class CodeGenerator {
    public ArrayList<String> codes;
    public IRVisitor irVisitor;
    public ArrayList<String> DCLlist;
    public ArrayList<String> Structlist;
    public SymbolTable root;
    public List<String> stringGlobalCodeList ;
    public List<String> StandardFunctionDeclarations;

    public CodeGenerator(IRVisitor irVisitor, SymbolTable symbolTable) {
        this.irVisitor = irVisitor;
        this.codes = new ArrayList<>();
        this.root = symbolTable;
        DCLlist = new ArrayList<>();
        StandardFunctionDeclarations = new ArrayList<>();
        setStandardFunctionDeclarations();
        stringGlobalCodeList = new ArrayList<>();
        StringGlobalVariableGenerator(irVisitor.stringConstIRList);
        Structlist = new ArrayList<>();
    }
    void StringGlobalVariableGenerator(List<SymbolTableVariable> stringConsts){
        for (SymbolTableVariable symbolTableVariable:
                stringConsts) {
            String thisStringDec = "";
            //@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

            thisStringDec+="@"+symbolTableVariable.name.toString()+" = private unnamed_addr constant ";
            thisStringDec+="[";
            String sizeOfString = Integer.toString(((String) symbolTableVariable.value).length() + 1);
            thisStringDec+= sizeOfString  + " ";
            thisStringDec+="x i8";
            thisStringDec+="] c\"";
            thisStringDec+= ((String) symbolTableVariable.value)+ "\\00" +"\"";
            stringGlobalCodeList.add(thisStringDec);
        }
    }

    void setStandardFunctionDeclarations(){
        StandardFunctionDeclarations.add("declare i32 @printf(i8*, ...)");
        StandardFunctionDeclarations.add("declare i32 @__isoc99_scanf(i8*, ...)");

    }
    public void MakeLabelHandler(int index) {
        MakeLabelIR makeLabelIR = (MakeLabelIR) irVisitor.IRlist.get(index);
        //br label %l2
        codes.add("br label %" + makeLabelIR.labelName);
        codes.add(makeLabelIR.labelName + ": ");
    }
    public void SwitchIRHandler(int index){
        SwitchIR switchIR = ((SwitchIR) irVisitor.IRlist.get(index));
        String t1 = new TempVarNameGenerator().toString();
        codes.add("%"+t1 + " = load i32, i32* @"+ switchIR.switch_id.name.toString());
        codes.add("switch i32 %"+ t1 + ", label %" + switchIR.defaultLabel+ " [");
        for (Integer i:
             switchIR.labels.keySet()) {
            String thisLabel = switchIR.labels.get(i);
            codes.add("i32 "+i.toString() + " , label %"+ thisLabel);
        }
        codes.add("]");
    }

    public void AssignmentHandler(int index) {
        AssignmentIR assignmentIR = (AssignmentIR) irVisitor.IRlist.get(index);
        SymbolTableVariable res = (SymbolTableVariable) assignmentIR.result;
        SymbolTableVariable arg1 = (SymbolTableVariable) assignmentIR.argument1;
        SymbolTableVariable arg2 = null;
        if (assignmentIR.argument2 != null) {
            arg2 = (SymbolTableVariable) assignmentIR.argument2;
        }
        if (assignmentIR.operator.equals("SIZEOF")){
            switch (assignmentIR.argument1.type.typeEnum) {
                case INT: {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 32, i32* %" + res.name.toString());
                    break;
                }
                case BOOL:
                {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 8, i32* %" + res.name.toString());
                    break;
                }
                case FLOAT:
                {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 32, i32* %" + res.name.toString());
                    break;
                }
                case LONG:
                {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 64, i32* %" + res.name.toString());
                    break;
                }
                case CHAR:
                {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 8, i32* %" + res.name.toString());
                    break;
                }
                case DOUBLE:
                {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 64, i32* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("+")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i32");
                    codes.add("%" + t1 + " = load i32 , i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i32 , i32* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = add i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                }
                break;
                case BOOL:
                    //is not possible
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca float");
                    codes.add("%" + t1 + " = load float , float* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load float , float* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fadd float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                }
                break;
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i64");
                    codes.add("%" + t1 + " = load i64 , i64* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i64 , i64* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fadd i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                }
                break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca double");
                    codes.add("%" + t1 + " = load double , double* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load double , double* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fadd double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3+ ", double* %" + res.name.toString());
                }
                break;
                case STRING:
                    // TODO: 18/07/03 string + string
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("and")) {
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            String tt1 = new TempVarNameGenerator().toString();
            String tt2 = new TempVarNameGenerator().toString();
            String r1 = new TempVarNameGenerator().toString();
            String r2 = new TempVarNameGenerator().toString();
            String r3 = new TempVarNameGenerator().toString();
            String r4 = new TempVarNameGenerator().toString();
            String l1 = new LabelGenerator().toString();
            String l2 = new LabelGenerator().toString();
            String l3 = new LabelGenerator().toString();
            String l4 = new LabelGenerator().toString();
            codes.add("%" + res.name.toString() + " = alloca i8");
            codes.add("%" + t1 + " = load i8, i8* %" + arg1.name.toString());
            codes.add("%" + t2 + " = load i8, i8* %" + arg2.name.toString());
            codes.add("%" + tt1 + " = trunc i8 %" + t1 + " to i1");
            codes.add("%" + tt2 + " = trunc i8 %" + t2 + " to i1");
            codes.add("%" + r1 + " = icmp eq i1 %" + tt1 + ", %" + tt2);
            codes.add("br i1 %" + r1 + ", label %" + l1 + ", label %" + l2);
            codes.add(l1 +":");
            codes.add("%" + r2 + " = icmp eq i1 %" + tt1 + ", 1");
            codes.add("br i1 %" + r2 + ", label %" + l3 + ", label %" + l2);
            codes.add(l2 + ":");
            codes.add("%" + r3 + " = zext i1 0 to i8");
            codes.add("store i8 %" + r3 +", i8* %" + res.name.toString());
            codes.add("br label %" + l4);
            codes.add(l3 + ":");
            codes.add("%" + r4 + " = zext i1 1 to i8");
            codes.add("store i8 %" + r4 +", i8* %" + res.name.toString());
            codes.add("br label %" + l4);
            codes.add(l4 + ":");
        }



        if (assignmentIR.operator.equals("||")){
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            String tt1 = new TempVarNameGenerator().toString();
            String tt2 = new TempVarNameGenerator().toString();
            String r1 = new TempVarNameGenerator().toString();
            String l1 = new LabelGenerator().toString();
            String l2 = new LabelGenerator().toString();
            String l3 = new LabelGenerator().toString();
            codes.add("%" + res.name.toString() + " = alloca i8");
            codes.add("%" + t1 + " = load i8, i8* %" + arg1.name.toString());
            codes.add("%" + t2 + " = load i8, i8* %" + arg2.name.toString());
            codes.add("%" + tt1 + " = trunc i8 %" + t1 + " to i1");
            codes.add("%" + tt2 + " = trunc i8 %" + t2 + " to i1");
            codes.add("br i1 %" + tt1 +", label %" + l1 + ", label %" + l2);
            codes.add(l1 + ":");
            codes.add("store i8 1, i8* %" + res.name.toString());
            codes.add("br label %" + l3);
            codes.add(l2 + ":");
            codes.add("%" + r1 + " = zext i1 %" + tt2 + " to i8");
            codes.add("store i8 %" + r1 + ", i8* %" + res.name.toString());
            codes.add("br label %" + l3);
            codes.add(l3 + ":");


        }
        if (assignmentIR.operator.equals("-")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i32");
                    codes.add("%" + t1 + " = load i32 , i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i32 , i32* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = sub i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                }
                break;
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca float");
                    codes.add("%" + t1 + " = load float , float* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load float , float* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fsub float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                }
                break;
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i64");
                    codes.add("%" + t1 + " = load i64 , i64* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i64 , i64* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fsub i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                }
                break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca double");
                    codes.add("%" + t1 + " = load double , double* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load double , double* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fsub double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                }
                break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("*")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i32");
                    codes.add("%" + t1 + " = load i32 , i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i32 , i32* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = mul i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                }
                break;
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca float");
                    codes.add("%" + t1 + " = load float , float* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load float , float* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fmul float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3+ ", float* %" + res.name.toString());
                }
                break;
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i64");
                    codes.add("%" + t1 + " = load i64 , i64* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i64 , i64* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fmul i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                }
                break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca double");
                    codes.add("%" + t1 + " = load double , double* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load double , double* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fmul double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                }
                break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("/")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i32");
                    codes.add("%" + t1 + " = load i32 , i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i32 , i32* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = udiv i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                }
                break;
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca float");
                    codes.add("%" + t1 + " = load float , float* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load float , float* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fdiv float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                }
                break;
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i64");
                    codes.add("%" + t1 + " = load i64 , i64* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i64 , i64* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fdiv i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                }
                break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca double");
                    codes.add("%" + t1 + " = load double , double* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load double , double* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = fdiv double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                }
                break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("%")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i32");
                    codes.add("%" + t1 + " = load i32 , i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i32 , i32* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = urem i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                }
                break;
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca float");
                    codes.add("%" + t1 + " = load float , float* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load float , float* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = frem float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                }
                break;
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca i64");
                    codes.add("%" + t1 + " = load i64 , i64* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load i64 , i64* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = frem i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                }
                break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = " + "alloca double");
                    codes.add("%" + t1 + " = load double , double* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = load double , double* %" + arg2.name.toString());
                    codes.add("%" + t3 + " = frem double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                }
                break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }



        if (assignmentIR.operator.equals("&")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = and i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {

                    // TODO: 18/07/03 boolean i8 or i1
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    codes.add("%" + t1 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = and i8 %" + t1 + ", %" + t2);
                    codes.add("store i8 %" + t3 + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca float");
                    codes.add("%" + t1 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = and float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                    break;
                }
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i64");
                    codes.add("%" + t1 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = and i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                    break;
                }
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("%" + t1 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = and double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("|")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = or i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {

                    // TODO: 18/07/03 boolean i8 or i1
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    codes.add("%" + t1 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = or i8 %" + t1 + ", %" + t2);
                    codes.add("store i8 %" + t3 + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca float");
                    codes.add("%" + t1 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = or float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                    break;
                }
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i64");
                    codes.add("%" + t1 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = or i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                    break;
                }
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("%" + t1 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = or double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }

        }
        if (assignmentIR.operator.equals("UMINUS")){
            switch (res.type.typeEnum){
                case INT:{
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* %" + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = sub i32 0, %" + t1);
                    codes.add("store i32 %" + t2 + ", i32* %" + res.name.toString());
                    break;
                }
                default:
                    break;
        }
    }

        if (assignmentIR.operator.equals("^")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i32, i32* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = xor i32 %" + t1 + ", %" + t2);
                    codes.add("store i32 %" + t3 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {

                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    codes.add("%" + t1 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i8, i8* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = xor i8 %" + t1 + ", %" + t2);
                    codes.add("store i8 %" + t3 + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca float");
                    codes.add("%" + t1 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load float, float* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = xor float %" + t1 + ", %" + t2);
                    codes.add("store float %" + t3 + ", float* %" + res.name.toString());
                    break;
                }
                case LONG: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i64");
                    codes.add("%" + t1 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load i64, i64* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = xor i64 %" + t1 + ", %" + t2);
                    codes.add("store i64 %" + t3 + ", i64* %" + res.name.toString());
                    break;
                }
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("%" + t1 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
                    codes.add("%" + t2 + " = load double, double* " + ((SymbolTableVariable) assignmentIR.argument2).name.toString());
                    codes.add("%" + t3 + " = xor double %" + t1 + ", %" + t2);
                    codes.add("store double %" + t3 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }

        if (assignmentIR.operator.equals("alloca")) {
            Type type =(((SymbolTableVariable) assignmentIR.argument1).type);
            codes.add("%" + ((SymbolTableVariable) assignmentIR.argument1).name.toString() + " = alloca "+ type.getLLVMName());
            codes.add("store "+type.getLLVMName()+" " + 0 + ", "+type.getLLVMName()+"* %" + ((SymbolTableVariable) assignmentIR.argument1).name.toString());
        }
        if(assignmentIR.operator.equals("=Const")){
            switch (res.type.typeEnum){

                case INT: {
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 " + ((Integer) res.value).toString() + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    Boolean b = (Boolean) res.value;
                    Integer out = b ? 1 : 0;
                    codes.add("store i8 " + out.toString() + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("store double " + ((Double) res.value).toString() + ", double* %" + res.name.toString());
                    break;
                }
                case LONG: {
                    codes.add("%" + res.name.toString() + " = alloca i64");
                    codes.add("store i64 " + ((Long) res.value).toString() + ", i64* %" + res.name.toString());
                    break;
                }
                case CHAR:{
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    codes.add("store i8 " + ((int) ((Character) res.value).charValue()) + ", i8* %" + res.name.toString());
                    break;
                }
                case DOUBLE:{// TODO: 05/07/18 lexer doesn't have double :D
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("store double " + ((Double) res.value).toString() + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("=")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load i32, i32* %" + arg1.name.toString());
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("store i32 %" + t1 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load i8, i8* %" + arg1.name.toString());
                    codes.add("store i8 %" + t1 + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                    codes.add("%" + res.name.toString() + " = alloca double");
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load double, double* %" + arg1.name.toString());
                    codes.add("store double %" + t1 + ", double* %" + res.name.toString());
                    break;
                }
                case LONG: {
                    codes.add("%" + res.name.toString() + " = alloca i64");
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load i64, i64* %" + arg1.name.toString());
                    codes.add("store i64 %" + t1 + ", i64* %" + res.name.toString());
                    break;
                }
                case CHAR: {
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load i8, i8* %" + arg1.name.toString());
                    codes.add("store i8 %" + t1 + ", i8* %" + res.name.toString());
                    break;
                }
                case DOUBLE: {
                    codes.add("%" + res.name.toString() + " = alloca double");
                    String t1 = new TempVarNameGenerator().toString();
                    codes.add("%" + t1 + " = load double, double* %" + arg1.name.toString());
                    codes.add("store double %" + t1 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING: {
// TODO: 18/07/04 string  = sth
                }
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("<")
                || assignmentIR.operator.equals("<=")
                || assignmentIR.operator.equals(">")
                || assignmentIR.operator.equals(">=")
                || assignmentIR.operator.equals("!=")
                || assignmentIR.operator.equals("==")) {
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            String t3 = new TempVarNameGenerator().toString();
            String t4 = new TempVarNameGenerator().toString();
            codes.add("%" + res.name.toString() + " = alloca i8");
            codes.add("%" + t1 + " = load "+assignmentIR.argument1.type.getLLVMName()+", "+assignmentIR.argument1.type.getLLVMName()+"* %" + arg1.name.toString());
            codes.add("%" + t2 + " = load "+assignmentIR.argument1.type.getLLVMName()+", "+assignmentIR.argument1.type.getLLVMName()+"* %" + arg2.name.toString());
            codes.add("%" + t3 + CGenHelper.getBinaryOperator(assignmentIR.operator,assignmentIR.argument1.type) + t1 + ", %" + t2);
            codes.add("%" + t4 + " = zext i1 %" + t3 + " to i8");
            codes.add("store i8 %" + t4 + ", i8* %" + res.name.toString());
        }

        if (assignmentIR.operator.equals("TILDE")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = xor i32 %" + t1 + ", -1");
                    codes.add("store i32 %" + t2 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    String t3 = new TempVarNameGenerator().toString();
                    String t4 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i8");
                    codes.add("%" + t1 + " = load i8* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = xor i8 %" + t1 + ", -1");
                    codes.add("%" + t3 + " = trunc i8 %" + t2 + " to i1");
                    codes.add("%" + t4 + " = zext i1 %" + t3 + "to i8");
                    codes.add("store i8 %" + t4 + ", i8* %" + res.name.toString());
                    break;
                }
                case FLOAT: {
                }
                case LONG:
                    break;
                case CHAR:
                    break;
                case DOUBLE:
                    break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }

        if (assignmentIR.operator.equals("MINUSMINUS")) {
            switch (res.type.typeEnum) {// TODO: 06/07/18 just integer cases are done for both MINUSMINUS and PLUSPLUS
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = sub i32 %" + t1 + ", 1");
                    codes.add("store i32 %" + t2 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca float");
                    codes.add("%" + t1 + " = load float %" + arg1.name.toString());
                    codes.add("%" + t2 + " = fsub float %" + t1 + ", 1.0");
                    codes.add("store float %" + t2 + ", float* %" + arg1.name.toString());
                    codes.add("store float %" + t2 + ", float* %" + res.name.toString());
                    break;
                }
                case LONG:
                    break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("%" + t1 + " = load double %" + arg1.name.toString());
                    codes.add("%" + t2 + " = fsub double %" + t1 + ", 1.0");
                    codes.add("store double %" + t2 + ", double* %" + arg1.name.toString());
                    codes.add("store double %" + t2 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (assignmentIR.operator.equals("PLUSPLUS")) {
            switch (res.type.typeEnum) {
                case INT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca i32");
                    codes.add("%" + t1 + " = load i32, i32* %" + arg1.name.toString());
                    codes.add("%" + t2 + " = add i32 %" + t1 + ", 1");
                    codes.add("store i32 %" + t2 + ", i32* %" + res.name.toString());
                    break;
                }
                case BOOL:
                    break;
                case FLOAT: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca float");
                    codes.add("%" + t1 + " = load float %" + arg1.name.toString());
                    codes.add("%" + t2 + " = fsub float %" + t1 + ", 1.0");
                    codes.add("store float %" + t2 + ", float* %" + arg1.name.toString());
                    codes.add("store float %" + t2 + ", float* %" + res.name.toString());
                    break;
                }
                case LONG:
                    break;
                case CHAR:
                    break;
                case DOUBLE: {
                    String t1 = new TempVarNameGenerator().toString();
                    String t2 = new TempVarNameGenerator().toString();
                    codes.add("%" + res.name.toString() + " = alloca double");
                    codes.add("%" + t1 + " = load double %" + arg1.name.toString());
                    codes.add("%" + t2 + " = fsub double %" + t1 + ", 1.0");
                    codes.add("store double %" + t2 + ", double* %" + arg1.name.toString());
                    codes.add("store double %" + t2 + ", double* %" + res.name.toString());
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }



    }
    public String arrayIndexGenerator(String s, Stack<Expr> stack, int index, String type) {
        if (index == 0) {
            s += "[" + ((Integer) stack.get(index).const_val.const_val) + " x " + type + " ]";
            return s;
        }
        s += "[ " + ((Integer) stack.get(index).const_val.const_val) + " x ";
        s = arrayIndexGenerator(s, stack, index - 1, type);
        s += "]";
        return s;
    }
    public void ArithmaticLocalIRHandler(int index){
        ArithmaticLocalIR arithmaticLocalIR = ((ArithmaticLocalIR) irVisitor.IRlist.get(index));
        String t1 = new TempVarNameGenerator().toString();
        String t2 = new TempVarNameGenerator().toString();
        codes.add("%" + t1 + " = load "+arithmaticLocalIR.result.type.getLLVMName()+", "+arithmaticLocalIR.result.type.getLLVMName()+"* %" +  arithmaticLocalIR.result.name.toString());
        if(arithmaticLocalIR.operator.equals("+")){
            codes.add("%" + t2 +
                    " = add "+arithmaticLocalIR.result.type.getLLVMName()+" %" + t1 + ", " + 1);
        }
        if(arithmaticLocalIR.operator.equals("-")){
            codes.add("%" + t2 +
                    " = sub "+arithmaticLocalIR.result.type.getLLVMName()+" %" + t1 + ", " + 1);

        }
        codes.add("store "+arithmaticLocalIR.result.type.getLLVMName()+" %" + t2 + ", "+arithmaticLocalIR.result.type.getLLVMName()+"* %" + arithmaticLocalIR.result.name.toString());

    }
    public void AssignToArrayHandler(int index){
        AssignToArrayIR assignToArrayIR = (AssignToArrayIR) irVisitor.IRlist.get(index);
        int i = assignToArrayIR.indexes.size();
        String sexted[] = new String[i];
        while (i > 0){
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            codes.add("%" + t1 + " = load i32, i32* %" + assignToArrayIR.indexes.get(i -1).result.name.toString());
            codes.add("%" + t2 + " = sext i32 %" + t1 + " to i64");
            sexted[i - 1] = t2;
            i--;
        }
        i = assignToArrayIR.indexes.size();
        String loaded[] = new String[i];
        String type ="";
        while (i > 0){
            String temp = "";
            String ttt = new TempVarNameGenerator().toString();
            loaded[i - 1] = ttt;
            temp += "%" + ttt + " = getelementptr inbounds ";
            String temp2 = "";
            switch (assignToArrayIR.result.type.typeEnum) {
                case INT:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "i32");type = "i32";
                    break;
                case BOOL:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "i8");type = "i8";
                    break;
                case FLOAT:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "double");
                    type = "double";
                    break;
                case LONG:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "i64");
                    type = "i64";
                    break;
                case CHAR:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "i8");
                    type = "i8";
                    break;
                case DOUBLE:temp2 = arrayIndexGenerator(temp, assignToArrayIR.arrayId.array_index_recursive.exprStack, i - 1, "double");
                    type = "double";
                    break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
            int g = temp2.indexOf('[');
            temp2 = temp2.substring(g);
            if (i == assignToArrayIR.indexes.size()){
                temp += temp2 + ", " + temp2 + "* " + "@" + assignToArrayIR.arrayId.name.toString() + ", i64 0, i64 %";
            }
            else {
                temp += temp2 + ", " + temp2 + "* " + "%" + loaded[i] + ", i64 0, i64 %";
            }
            temp += sexted[i - 1];
            codes.add(temp);
            i--;
        }
        codes.add("%" + assignToArrayIR.result.name.toString() + " = alloca " + type);
        String y = new TempVarNameGenerator().toString();
        codes.add("%" + y + " = load " + type + ", " + type + "* %" + loaded[0]);
        codes.add("store " + type + " %" + y + ", " + type + "* %" + assignToArrayIR.result.name.toString());

    }



    public void ReturnHandler(int index) {
        ReturnIR returnIR = (ReturnIR) irVisitor.IRlist.get(index);
        if (returnIR.isVoid) {
            codes.add("br label %" + returnIR.returnLabel);
        } else {
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            String t3 = new TempVarNameGenerator().toString();
            switch (returnIR.methodReturnVariable.type.typeEnum) {
                case INT: {
                    codes.add("%" + t1 + " = load i32, i32* %" + returnIR.toBeReturnedExp.name.toString());
                    codes.add("store i32 %" + t1 + ", i32* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case BOOL: {
                    codes.add("%" + t1 + " = load i8, i8* %" + returnIR.toBeReturnedExp.name.toString());
                    codes.add("store i8 %" + t1 + ", i8* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case FLOAT: {
                    codes.add("%" + t1 + " = load double, double* %" + returnIR.toBeReturnedExp.name.toString());
                    codes.add("store double %" + t1 + ", double* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case LONG: {
                    codes.add("%" + t1 + " = load i64, i64* %" + returnIR.toBeReturnedExp.name.toString());

                    codes.add("store i64 %" + returnIR.toBeReturnedExp.name.toString() + ", i64* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case CHAR: {
                    codes.add("%" + t1 + " = load i8, i8* %" + returnIR.toBeReturnedExp.name.toString());
                    codes.add("store i8 %" + t1 + ", i8* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case DOUBLE: {
                    codes.add("%" + t1 + " = load double, double* %" + returnIR.toBeReturnedExp.name.toString());
                    codes.add("store double %" + t1 + ", double* %" + returnIR.methodReturnVariable.name.toString());
                    codes.add("br label %" + returnIR.returnLabel);
                    break;
                }
                case STRING:
                    // TODO: 18/07/04 string !!!
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;


                case ID:
                    break;
            }
        }


    }


    public void ConditionalBranchHandler(int index) {
        ConditionalBranch conditionalBranch = (ConditionalBranch) irVisitor.IRlist.get(index);
        String s8 = new TempVarNameGenerator().toString();
        String s1 = new TempVarNameGenerator().toString();
        codes.add("%" + s8 + " = load i8, i8* %" + conditionalBranch.cond.name.toString());
        codes.add("%" + s1 + " = trunc i8 %" + s8 + " to i1");
        codes.add("br i1 %" + s1 + ", " + " label %" + conditionalBranch.ifTrue + ", label %" + conditionalBranch.ifFalse);
    }

    public void DCLlistHandler(ArrayList<DclIR> dclIRArrayList) {
        for (DclIR dcl :
                dclIRArrayList) {
            DCLlist.add(VarDclHandler(dcl));
        }
    }

    public String VarDclHandler(DclIR dclIR) {
        SymbolTableVariable tempVar = dclIR.scope.lookupVariable(dclIR.v.variable.identifier.toString());
        if (dclIR.v.variable.array_index_recursive == null) {
            String temp = "@" + tempVar.name.toString() + " = ";
            if (!tempVar.isConst)
                temp += "common global ";
            else {
                temp += " constant ";
            }
            switch (tempVar.type.typeEnum) {
                case INT: {
                    temp += " i32 0";
                    break;
                }
                case BOOL: {
                    temp += " i8 0";
                    break;
                }
                case FLOAT: {
                    temp += " float 0.0";
                    break;
                }
                case LONG: {
                    temp += " i64 0";
                    break;
                }
                case CHAR: {
                    temp += " i8 0";
                    break;
                }
                case DOUBLE: {
                    temp += " double 0.0";
                    break;
                }
                case STRING: {
                    break;
                    // TODO: 18/07/04 global string
                }
                case VOID: {
                    break;
                }
                case AUTO: {
                    break;
                }
                case ID: {
                    temp += "%struct.";
                    temp += dclIR.v.type.identifier.toString();
                    temp += " zeroinitializer";
                    break;
                }
            }
            return temp;
        } else {
            int bracCount = tempVar.array_index_recursive.exprStack.size();
            String temp = "@" + tempVar.name.toString() + " = ";
            if (tempVar.isConst)
                temp += "constant ";
            else
                temp += "common global ";
            temp = arrayDefineCodeGenerator(temp, tempVar.array_index_recursive.exprStack.size() - 1, tempVar.array_index_recursive.exprStack, tempVar.type);
            temp+= " zeroinitializer";
            return temp;
        }

    }

    private String arrayDefineCodeGenerator(String code, Integer stackPos, Stack<Expr> stack, Type type) {
        if (stackPos == 0) {
            code += " [";
            code += ((Integer) (stack.get(stackPos).result.value)).toString() + " x ";
            switch (type.typeEnum) {
                case INT: {
                    code += " i32 ";
                    break;
                }
                case BOOL: {
                    code += " i8 ";
                    break;
                }
                case FLOAT: {
                    code += " float ";
                    break;
                }
                case LONG:
                    code += " i64 ";
                    break;
                case CHAR:
                    code += " i8 ";
                    break;
                case DOUBLE:
                    code += " double ";
                    break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    code += "%struct.";
                    code += type.identifier.toString();
                    break;
            }
            code += " ]";
            return code;
        } else {
            code += "[" + ((Integer) (stack.get(stackPos).result.value)).toString() + " x ";
            code = arrayDefineCodeGenerator(code, stackPos - 1, stack, type);
            code += " ]";
            return code;
        }
    }


    private String arrayDefineCodeGeneratorForStruct(String code, Integer stackPos, Stack<Expr> stack, Type type) {
        if (stackPos == 0) {
            code += " [";
            code += ((Integer) (stack.get(stackPos).const_val.const_val)).toString() + " x ";
            switch (type.typeEnum) {
                case INT: {
                    code += " i32 ";
                    break;
                }
                case BOOL: {
                    code += " i8 ";
                    break;
                }
                case FLOAT: {
                    code += " float ";
                    break;
                }
                case LONG:
                    code += " i64 ";
                    break;
                case CHAR:
                    code += " i8 ";
                    break;
                case DOUBLE:
                    code += " double ";
                    break;
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    code += "%struct.";
                    code += type.identifier.toString();
                    break;
            }
            code += " ]";
            return code;
        } else {
            code += "[" + ((Integer) (stack.get(stackPos).const_val.const_val)).toString() + " x ";
            code = arrayDefineCodeGeneratorForStruct(code, stackPos - 1, stack, type);
            code += " ]";
            return code;
        }
    }

    public void methodCallHandler(int index) {
        MethodCallIR methodCallIR = (MethodCallIR) irVisitor.IRlist.get(index);
        if(methodCallIR.hash.equals("scanf")){
            String s1 = new TempVarNameGenerator().toString();
            //  %2 = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i32 0, i32 0), double* %1)
            String scanfCall = "";
            String scanfVar = methodCallIR.resultVariable.name.toString();


            String sizeOfString= new Integer(((String) methodCallIR.parameterStack.get(1).const_val.const_val).length()+1).toString();
            scanfCall+= "call i32 (i8* , ...) @__isoc99_scanf(i8* getelementptr inbounds (["+sizeOfString+" x i8], ["+sizeOfString+" x i8]* @"+methodCallIR.parameterStack.get(1).const_val.result.name+", i32 0, i32 0), "+methodCallIR.parameterStack.get(0).result.type.getLLVMName()+"* @"+scanfVar+ ")";
            codes.add(scanfCall);
            return;
        }
        if(methodCallIR.hash.equals("printf")){
            String loadPrintfData = "";

            String s1 = new TempVarNameGenerator().toString();
            loadPrintfData+=("%" + s1 + " = load "+methodCallIR.parameterStack.get(0).result.type.getLLVMName()
                    +", "+methodCallIR.parameterStack.get(0).result.type.getLLVMName() +
                    "* %" +methodCallIR.parameterStack.get(0).result.name);
            codes.add(loadPrintfData);
            // %2 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i32 0, i32 0), i32 1)
            String printfCall = "";
            printfCall+="call i32(i8*, ...) @printf(i8* getelementptr inbounds (";
            printfCall+="[";
            String sizeOfString= new Integer(((String) methodCallIR.parameterStack.get(1).const_val.const_val).length()+1).toString();
            printfCall+= sizeOfString+" x i8], ["+sizeOfString + " x i8]* @";
            printfCall+=methodCallIR.parameterStack.get(1).const_val.result.name+", i32 0, i32 0), ";
            printfCall+= methodCallIR.parameterStack.get(0).result.type.getLLVMName() + " "
                    + "%"+s1+")";
            codes.add(printfCall);
            return;
        }
        if (methodCallIR.methodSymbolTable.returnType.typeEnum != Type.TypeEnum.VOID) {
            //allocating this node's return variable
            switch (methodCallIR.methodSymbolTable.returnType.typeEnum) {
                case INT: {
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca i32");
                    break;
                }
                case BOOL:
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca i8");
                    break;
                case FLOAT:
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca double");
                    break;
                case LONG:
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca i64");
                    break;
                case CHAR:
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca i8");
                    break;
                case DOUBLE:
                    codes.add("%" + methodCallIR.resultVariable.name.toString() + " = alloca double");
                    break;
                case STRING:
                    break;
                case VOID://this is handled outside of the switch
                    break;
                case AUTO:
                    break;
                case ID:// TODO: 05/07/18 IDs are not handled properly at aaaall
                    break;
            }


        }
        ArrayList<String> tempVarsForArguments = new ArrayList<>();//where they are loaded
        if (methodCallIR.parameterStack != null) {
            for (int i = methodCallIR.parameterStack.size() - 1; i >= 0; i--) {
                String s1 = new TempVarNameGenerator().toString();
                tempVarsForArguments.add(s1);
                switch (methodCallIR.parameterStack.get(i).result.type.typeEnum) {

                    case INT:
                        codes.add("%" + s1 + " = load i32, i32* %" + methodCallIR.parameterStack.get(i).result.name.toString());
                        break;
                    case BOOL:
                        codes.add("%" + s1 + " = load i8, i8* %" + methodCallIR.parameterStack.get(i).result.name.toString());

                        break;
                    case FLOAT:
                        codes.add("%" + s1 + " = load double, double* %" + methodCallIR.parameterStack.get(i).result.name.toString());

                        break;
                    case LONG:
                        codes.add("%" + s1 + " = load i64, i64* %" + methodCallIR.parameterStack.get(i).result.name.toString());

                        break;
                    case CHAR:
                        codes.add("%" + s1 + " = load i8, i8* %" + methodCallIR.parameterStack.get(i).result.name.toString());
                        break;
                    case DOUBLE:
                        codes.add("%" + s1 + " = load double, double* %" + methodCallIR.parameterStack.get(i).result.name.toString());

                        break;
                    case STRING:
                        break;
                    case VOID:

                        break;
                    case AUTO:
                        break;
                    case ID:
                        break;
                }
            }
        }
        String tmp = "";


        String t1 = new TempVarNameGenerator().toString();
        if (methodCallIR.methodSymbolTable.returnType.typeEnum != Type.TypeEnum.VOID)
            tmp += "%" + t1 + " = ";
        tmp += "call ";
        switch (methodCallIR.methodSymbolTable.returnType.typeEnum) {
            case INT:
                tmp += "i32";
                break;
            case BOOL:
                tmp += "i8";
                break;
            case FLOAT:
                tmp += "float";
                break;
            case LONG:
                tmp += "i64";
                break;
            case CHAR:
                tmp += "i8";
                break;
            case DOUBLE:
                tmp += "double";
                break;
            case STRING:
                break;
            case VOID:
                tmp += "void";
                break;
            case AUTO:
                break;
            case ID:
                break;
        }
        tmp += " @" + methodCallIR.hash + "( ";
        if (methodCallIR.parameterStack != null) {
            for (int i = methodCallIR.parameterStack.size() - 1; i >= 0; i--) {// TODO: 05/07/18 parameterStack might be the reversed order
                // TODO: 05/07/18 last COMMA should be omitted
                switch (methodCallIR.parameterStack.get(i).result.type.typeEnum) {
                    case INT: {
                        tmp += "i32 %";
                        break;
                    }
                    case BOOL: {
                        tmp += "i8 %";
                        break;
                    }
                    case FLOAT: {
                        tmp += "float %";
                        break;
                    }
                    case LONG: {
                        tmp += "i64 %";
                        break;
                    }
                    case CHAR: {
                        tmp += "i8 %";
                        break;
                    }
                    case DOUBLE: {
                        tmp += "double %";
                        break;
                    }
                    case STRING:
                        break;
                    case VOID:
                        break;
                    case AUTO:
                        break;
                    case ID:
                        break;
                }
                tmp += tempVarsForArguments.get(methodCallIR.parameterStack.size() - 1 - i);
                if (i > 0)
                    tmp += ", ";

            }
        }
        tmp += " )";
        codes.add(tmp);
        switch (methodCallIR.methodSymbolTable.returnType.typeEnum) {

            case INT:
                codes.add("store i32 %" + t1 + ", i32* %" + methodCallIR.resultVariable.name.toString());
                break;
            case BOOL:
                codes.add("store i8 %" + t1 + ", i8* %" + methodCallIR.resultVariable.name.toString());
                break;
            case FLOAT:
                codes.add("store double %" + t1 + ", double* %" + methodCallIR.resultVariable.name.toString());
                break;
            case LONG:
                codes.add("store i64 %" + t1 + ", i64* %" + methodCallIR.resultVariable.name.toString());

                break;
            case CHAR:
                codes.add("store i8 %" + t1 + ", i8* %" + methodCallIR.resultVariable.name.toString());
                break;
            case DOUBLE:
                codes.add("store double %" + t1 + ", double* %" + methodCallIR.resultVariable.name.toString());
                break;
            case STRING:
                break;
            case VOID:
                break;
            case AUTO:
                break;
            case ID:
                break;
        }


        }


    public void StringGeneratorHandler(int index) {
        StringGeneratorIR stringGeneratorIR = (StringGeneratorIR) irVisitor.IRlist.get(index);
        codes.add(stringGeneratorIR.codeString);
    }

    public void GenerateMethodSignatureHandler(int index) {
        GenerateMethodSignature generateMethodSignature = (GenerateMethodSignature) irVisitor.IRlist.get(index);
        String temp = "define ";
        MethodSymbolTable methodSymbolTable = root.lookupMethod(generateMethodSignature.func_dcl.getHash());
        switch (methodSymbolTable.returnType.typeEnum) {
            case INT:
                temp += "i32 @";
                break;
            case BOOL:
                temp += "i8 @";
                break;
            case FLOAT:
                temp += "float @";
                break;
            case LONG:
                temp += "i64 @";
                break;
            case CHAR:
                temp += "i8 @";
                break;
            case DOUBLE:
                temp += "double @";
                break;
            case STRING:
                break;
            case VOID:
                temp += "void @";
                break;
            case AUTO:
                break;
            case ID:
                break;
        }
        temp += generateMethodSignature.func_dcl.getHash();
        temp += " (";
        int i = -1;
        for (String s :
                methodSymbolTable.args.keySet()) {
            SymbolTableVariable symbolTableVariable = methodSymbolTable.args.get(s);
            i++;
            switch (symbolTableVariable.type.typeEnum) {
                case INT: {
                    temp += "i32 %" + symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ",";
                    break;
                }
                case BOOL: {
                    temp += "i8 %"+ symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ",";
                    break;
                }
                case FLOAT: {
                    temp += "float %"+ symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ",";
                    break;
                }
                case LONG: {
                    temp += "i64 %"+ symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ", ";
                    break;
                }
                case CHAR: {
                    temp += "i8 %"+ symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ", ";
                    break;
                }
                case DOUBLE: {
                    temp += "double %"+ symbolTableVariable.name.toString();
                    if (i != methodSymbolTable.args.size() - 1)
                        temp += ",";
                    break;
                }
                case STRING:
                    break;
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        temp += " ){";
        codes.add(temp);

    }

    public void AssignFromVariableIRHandler(int index) {
        AssignFromVariableIR assignFromVariableIR = (AssignFromVariableIR) irVisitor.IRlist.get(index);
        //%3 = load i32, i32* @a, align 4
        //store i32 %3, i32* %2, align 4
        String t1 = new TempVarNameGenerator().toString();
                if(assignFromVariableIR.globalIdentifier.isLoaded){
                    codes.add("%" + assignFromVariableIR.result.name.toString() + " = alloca "+assignFromVariableIR.result.type.getLLVMName());
                    codes.add("store "+assignFromVariableIR.result.type.getLLVMName()
                            +" %" + assignFromVariableIR.globalIdentifier.name.toString() + ", "+assignFromVariableIR.result.type.getLLVMName()+"* %" + assignFromVariableIR.result.name.toString());
                }else {
                    codes.add("%" + assignFromVariableIR.result.name.toString() + " = alloca "+assignFromVariableIR.result.type.getLLVMName());
                    codes.add("%" + t1 + " = load "+assignFromVariableIR.result.type.getLLVMName()+", "+assignFromVariableIR.result.type.getLLVMName()+"* @" + assignFromVariableIR.globalIdentifier.name.toString());
                    codes.add("store "+assignFromVariableIR.result.type.getLLVMName()+" %" + t1 + ", "+assignFromVariableIR.result.type.getLLVMName()+"* %" + assignFromVariableIR.result.name.toString());
                }


    }

    public void VariableAssignIRHandler(int index) {
        VariableAssignIR assignmentIR = (VariableAssignIR) irVisitor.IRlist.get(index);
        String t1 = new TempVarNameGenerator().toString();
        /*
        first we should check the type
        is it just an identifier ? array identifier? or record(later)
         */


        switch (assignmentIR.resultVariable.variableType) {
            case IDENTIFIER: {
                switch (assignmentIR.exprSymbol.type.typeEnum) {
                    case INT:
                        codes.add("%" + t1 + " = load i32, i32* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i32 %" + t1 + ", i32* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);
                        break;
                    case BOOL:
                        codes.add("%" + t1 + " = load i8, i8* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i8 %" + t1 + ", i8* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);

                        break;
                    case FLOAT:
                        codes.add("%" + t1 + " = load double, double* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store double %" + t1 + ", double* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);


                        break;
                    case LONG:
                        codes.add("%" + t1 + " = load i64, i64* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i64 %" + t1 + ", i64* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);

                        break;
                    case CHAR:
                        codes.add("%" + t1 + " = load i8, i8* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i8 %" + t1 + ", i8* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);

                        break;
                    case DOUBLE:
                        codes.add("%" + t1 + " = load double, double* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store double %" + t1 + ", double* @" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).name);
                        break;
                    case STRING:
                        break;
                    case VOID:
                        break;
                    case AUTO:
                        break;
                    case ID: {

                        break;
                    }
                }
                break;
            }
            case ARRAY_IDENTIFIER: {
                int i = assignmentIR.resultVariable.array_index_recursive.exprStack.size();
                String[] addresses = new String[assignmentIR.resultVariable.array_index_recursive.exprStack.size()];
                while (i > 0) {
                    String tt1 = new TempVarNameGenerator().toString();
                    String tt2 = new TempVarNameGenerator().toString();
                    codes.add("%" + tt1 + " = load i32, i32* %" + assignmentIR.resultVariable.array_index_recursive.exprStack.get(i - 1).result.name.toString());
                    codes.add("%" +  tt2 +" = sext i32 %" + tt1 + " to i64");
                    addresses[i - 1] = tt2;
                    i--;
                }
                i = assignmentIR.resultVariable.array_index_recursive.exprStack.size();
                String[] loaded = new String[assignmentIR.resultVariable.array_index_recursive.exprStack.size()];
                String type = "";
                while (i > 0) {
                    String temp = "";
                    String ttt = new TempVarNameGenerator().toString();
                    loaded[i - 1] = ttt;
                    temp += "%" + ttt + " = getelementptr inbounds ";


                    String temp2 = " ";
                    switch (assignmentIR.exprSymbol.type.typeEnum) {
                        case INT:                     temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "i32");
                            type = "i32";
                            break;
                        case BOOL:                     temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "i8");
                            type = "i8";

                            break;
                        case FLOAT:                     temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "double");
                            type = "double";

                            break;
                        case LONG:                     temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "i64");
                            type = "i64";
                            break;

                        case CHAR:                     temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "i8");
                            type = "i8";

                            break;
                        case DOUBLE:                    temp2 = arrayIndexGenerator(temp, assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.toString()).array_index_recursive.exprStack, i - 1, "double");
                            type = "double";

                            break;
                        case STRING:
                            break;
                        case VOID:
                            break;
                        case AUTO:
                            break;
                        case ID:
                            break;
                    }
                    int g = temp2.indexOf('[');
                    temp2 = temp2.substring(g);
                    if (i == assignmentIR.resultVariable.array_index_recursive.exprStack.size())
                        temp += temp2 + ", " + temp2 + "* " + "@" + assignmentIR.currentScope.lookupVariable(assignmentIR.resultVariable.identifier.id).name.toString() + ", i64 0, i64 %";
                    else
                        temp += temp2 + ", " + temp2 + "* " + "%" + loaded[i] + ", i64 0, i64 %";
                    temp += addresses[i - 1];
                    codes.add(temp);
                    i--;
                }
                String yy = new TempVarNameGenerator().toString();
                codes.add("%" + yy + " = load "+ type + ", " + type +"* %" + assignmentIR.exprSymbol.name.toString());
                codes.add("store " +type +  " %" + yy + ", " + type + "* %" + loaded[0]);
                break;

            }

            case RECORD_ITEM: {
                String nameOfThisComplexStruct = new TempVarNameGenerator().toString();
                generateRecordCode(assignmentIR.resultVariable,nameOfThisComplexStruct,assignmentIR.currentScope);
                switch (assignmentIR.exprSymbol.type.typeEnum) {
                    case INT:
                        codes.add("%" + t1 + " = load i32, i32* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i32 %" + t1 + ", i32* %" + nameOfThisComplexStruct);
                        break;
                    case BOOL:
                        codes.add("%" + t1 + " = load i8, i8* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i8 %" + t1 + ", i8* %" +nameOfThisComplexStruct);

                        break;
                    case FLOAT:
                        codes.add("%" + t1 + " = load double, double* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store double %" + t1 + ", double* %" + nameOfThisComplexStruct);


                        break;
                    case LONG:
                        codes.add("%" + t1 + " = load i64, i64* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i64 %" + t1 + ", i64* %" + nameOfThisComplexStruct);

                        break;
                    case CHAR:
                        codes.add("%" + t1 + " = load i8, i8* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store i8 %" + t1 + ", i8* %" + nameOfThisComplexStruct);

                        break;
                    case DOUBLE:
                        codes.add("%" + t1 + " = load double, double* %" + assignmentIR.exprSymbol.name.toString());
                        codes.add("store double %" + t1 + ", double* %" + nameOfThisComplexStruct);
                        break;
                    case STRING:
                        break;
                    case VOID:
                        break;
                    case AUTO:
                        break;
                    case ID: {

                        break;
                    }
                }
                break;
            }
        }


    }
    public void generateRecordCode(Variable variable, String nameOfThisComplexStruct, Scope currentScope){
        if(variable.variable.variable==null){
            SymbolTableVariable symbolTableVariable = currentScope.lookupVariable(variable.variable.identifier.toString());
            RecordSymbolTable recordSymbolTable = root.getRecordSymbolTable(symbolTableVariable.type.identifier.toString());
            codes.add("%"+nameOfThisComplexStruct+
                    " = getelementptr inbounds %struct."+recordSymbolTable.identifier+", %struct." + recordSymbolTable.identifier
                    + "* @" +currentScope.lookupVariable(variable.variable.identifier.toString()).name+", i32 0, i32 " + recordSymbolTable.argumentNumber.get(variable.identifier.toString()));

        }else{
            String tmpName = new TempVarNameGenerator().toString();
            generateRecordCode(variable.variable,tmpName,currentScope);
            RecordSymbolTable recordSymbolTable = root.getRecordSymbolTable(variable.variable.identifier.toString());
            //%2 = getelementptr inbounds %struct.A, %struct.A* %1, i32 0, i32 0
            codes.add("%"+nameOfThisComplexStruct+
                    " = getelementptr inbounds %struct."+recordSymbolTable.identifier+", %struct." + recordSymbolTable.identifier
            + "* %" + tmpName+", i32 0, i32 " + recordSymbolTable.argumentNumber.get(variable.identifier.toString()));
        }
    }
    public void AssignFromRecordIR(int index){
        AssignFromRecord assignFromRecord = (AssignFromRecord) irVisitor.IRlist.get(index);
        String structString = new TempVarNameGenerator().toString();
        generateRecordCode(assignFromRecord.variable,structString,assignFromRecord.currentScope);
        String t1 = new TempVarNameGenerator().toString();
        switch (assignFromRecord.symbolTableVariable.type.typeEnum) {
            case INT:
                codes.add("%" + assignFromRecord.symbolTableVariable.name.toString() + " = alloca i32");
                codes.add("%" + t1 + " = load i32, i32* %" +structString);
                codes.add("store i32 %" + t1 + ", i32* %" + assignFromRecord.symbolTableVariable.name.toString());
                break;
            case BOOL:
                codes.add("%" + t1 + " = load i8, i8* %" +structString);
                codes.add("%"
                        + assignFromRecord.symbolTableVariable.name.toString() + " = alloca i8");

                codes.add("store i8 %" + t1 + ", i8* %" + assignFromRecord.symbolTableVariable.name.toString());


                break;
            case FLOAT:
                codes.add("%" + t1 + " = load double, double* %" +structString);
                codes.add("%" + assignFromRecord.symbolTableVariable.name.toString() + " = alloca double");

                codes.add("store double %" + t1 + ", double* %" + assignFromRecord.symbolTableVariable.name.toString());



                break;
            case LONG:
                codes.add("%" + t1 + " = load i64, i64* %" +structString);
                codes.add("%" + assignFromRecord.symbolTableVariable.name.toString() + " = alloca i64");

                codes.add("store i64 %" + t1 + ", i64* %" + assignFromRecord.symbolTableVariable.name.toString());

                break;
            case CHAR:
                codes.add("%" + t1 + " = load i8, i8* %" +structString);
                codes.add("%" + assignFromRecord.symbolTableVariable.name.toString() + " = alloca i8");

                codes.add("store i8 %" + t1 + ", i8* %" + assignFromRecord.symbolTableVariable.name.toString());


                break;
            case DOUBLE:
                codes.add("%" + t1 + " = load double, double* %" +structString);

                codes.add("%" + assignFromRecord.symbolTableVariable.name.toString() + " = alloca double");

                codes.add("store double %" + t1 + ", double* %" + assignFromRecord.symbolTableVariable.name.toString());
                break;
            case STRING:
                break;
            case VOID:
                break;
            case AUTO:
                break;
            case ID: {

                break;
            }
        }
    }
    public void UnconditionalBranch(int index) {
        UnconditionalBranchIR unconditionalBranchIR = (UnconditionalBranchIR) irVisitor.IRlist.get(index);
        codes.add("br label %" + unconditionalBranchIR.label);
    }
    public void ForEachConditionalBrHandler(int index){//this handler should also bind item to array index
        ForEachConditionalBr forEachConditionalBr = ((ForEachConditionalBr) irVisitor.IRlist.get(index));
        SymbolTableVariable arraySymbolTableVariable =
                forEachConditionalBr.currentScope.lookupVariable(forEachConditionalBr.foreachSetIdentifier.toString());
        SymbolTableVariable itemSymbolTableVariable =
                forEachConditionalBr.currentScope.lookupVariable(forEachConditionalBr.foreachItem.toString());
        SymbolTableVariable countLoop = forEachConditionalBr.countLoop;

        String t11 = new TempVarNameGenerator().toString();
        String t12 = new TempVarNameGenerator().toString();
        String t13 = new TempVarNameGenerator().toString();
        String t14 = new TempVarNameGenerator().toString();
        String y = new TempVarNameGenerator().toString();
        codes.add("%" + t13 + " = load "+countLoop.type.getLLVMName()+", "+countLoop.type.getLLVMName()+"* %" + countLoop.name.toString());
        codes.add("%" + t14 + " = add i32 %" + t13 + ", " + 1);
        codes.add("%" + t12 + CGenHelper.getBinaryOperator("<=",new Type(Type.TypeEnum.INT)) + t14 + ", " + arraySymbolTableVariable.array_index_recursive.exprStack.get(0).result.value);
        codes.add("store "+countLoop.type.getLLVMName()+" %" + t14 + ", "+countLoop.type.getLLVMName()+"* %" + countLoop.name.toString());
        String type ="";

        {
            String t1 = new TempVarNameGenerator().toString();
            String t2 = new TempVarNameGenerator().toString();
            t1=t13;
            codes.add("%" + t2 + " = sext i32 %" + t1 + " to i64");
            String ttt = new TempVarNameGenerator().toString();
            {
                String temp = "";
                temp += "%" + ttt + " = getelementptr inbounds ";
                String temp2 = "";
                switch (itemSymbolTableVariable.type.typeEnum) {
                    case INT:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "i32");type = "i32";
                        break;
                    case BOOL:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "i8");type = "i8";
                        break;
                    case FLOAT:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "double");
                        type = "double";
                        break;
                    case LONG:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "i64");
                        type = "i64";
                        break;
                    case CHAR:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "i8");
                        type = "i8";
                        break;
                    case DOUBLE:temp2 = arrayIndexGenerator(temp, arraySymbolTableVariable.array_index_recursive.exprStack, 0, "double");
                        type = "double";
                        break;
                    case STRING:
                        break;
                    case VOID:
                        break;
                    case AUTO:
                        break;
                    case ID:
                        break;
                }
                int g = temp2.indexOf('[');
                temp2 = temp2.substring(g);
                temp += temp2 + ", " + temp2 + "* " + "@" + arraySymbolTableVariable.name.toString() + ", i64 0, i64 %";
                temp += t2;
                codes.add(temp);

            }
            String tttt = new TempVarNameGenerator().toString();
            codes.add("%" + tttt + " = alloca " + type);
            codes.add("%" + y + " = load " + type + ", " + type + "* %" + ttt);
            codes.add("store " + type + " %" + y + ", " + type + "* %" + tttt);
        }
        codes.add("store " + type + " %" + y + ", " + type + "* @" + itemSymbolTableVariable.name.toString());
        codes.add("br i1 %" + t12 + ", " + " label %" + forEachConditionalBr.startBlockLabel + ", label %" + forEachConditionalBr.afterLoopLabel);


    }

    public void RetIRHandler(int index) {
        RetIR retIR = (RetIR) irVisitor.IRlist.get(index);
        // %10 = load i32, i32* %1, align 4
        String t1 = new TempVarNameGenerator().toString();
        switch (retIR.toReturn.type.typeEnum) {
            case INT:
                codes.add("%" + t1 + " = load i32 , i32* %" + retIR.toReturn.name.toString());
                codes.add("ret i32 %" + t1);
                break;
            case BOOL:
                codes.add("%" + t1 + " = load i8 , i8* %" + retIR.toReturn.name.toString());
                codes.add("ret i8 %" + t1);
                break;
            case FLOAT:
                codes.add("%" + t1 + " = load double , double* %" + retIR.toReturn.name.toString());
                codes.add("ret double %" + t1);
                break;
            case LONG:
                codes.add("%" + t1 + " = load i64 , i64* %" + retIR.toReturn.name.toString());
                codes.add("ret i64 %" + t1);
                break;
            case CHAR:
                codes.add("%" + t1 + " = load i8 , i8* %" + retIR.toReturn.name.toString());
                codes.add("ret i8 %" + t1);
                break;
            case DOUBLE:
                codes.add("%" + t1 + " = load double , double* %" + retIR.toReturn.name.toString());
                codes.add("ret double %" + t1);
                break;
            case STRING:
                break;
            case VOID: {
                codes.add("ret void ");
            }
            break;
            case AUTO:
                break;
            case ID:
                break;
        }

    }

    public void StructListHandler(ArrayList<StructIR> structIRS) {
        for (StructIR structIR : structIRS){
            //%struct.s = type { i32, double }
            String thisStructCode = "%struct."+structIR.identifier.toString()+ " = type { ";
            for (int i = structIR.var_dcls.var_dclList.size()-1; i >=0 ; i--) {
                Var_Dcl thisVar_dcl = structIR.var_dcls.var_dclList.get(i);
                if(thisVar_dcl.variable.array_index_recursive==null){
                    switch (thisVar_dcl.type.typeEnum){

                        case INT:
                            thisStructCode += "i32 ";
                            break;
                        case BOOL:
                            thisStructCode += "i8 ";

                            break;
                        case FLOAT:
                            thisStructCode += "double ";

                            break;
                        case LONG:
                            thisStructCode += "i64 ";

                            break;
                        case CHAR:
                            thisStructCode += "i8 ";

                            break;
                        case DOUBLE:
                            thisStructCode += "double ";

                            break;
                        case STRING:
                            break;
                        case VOID:
                            break;
                        case AUTO:
                            break;
                        case ID:
                            thisStructCode += "%struct." + thisVar_dcl.type.identifier;
                            break;
                    }
                }else{
                    String thisArrCode = "";
                    thisArrCode = arrayDefineCodeGeneratorForStruct(thisArrCode,thisVar_dcl.variable.array_index_recursive.exprStack.size()-1,thisVar_dcl.variable.array_index_recursive.exprStack,thisVar_dcl.type);
                    thisStructCode += thisArrCode;
                }
                if(i!=0)
                    thisStructCode+=" , ";
            }
            thisStructCode+= "}";
            Structlist.add(thisStructCode);
        }
    }
}
