import syntaxtree.Type;

public class CGenHelper {
    public static String getBinaryOperator(String op, Type type) {
        if (op.equals("==")) {
            switch (type.typeEnum) {

                case INT: {
                    return " = icmp eq i32 %";

                }
                case BOOL: {
                    return " = icmp eq i8 %";
                }
                case FLOAT: {
                    return " = fcmp oeq double %";

                }
                case LONG: {
                    return " = icmp eq i64 %";

                }
                case CHAR: {
                    return " = icmp eq i8 %";

                }
                case DOUBLE: {
                    return " = fcmp oeq double %";

                }
                case STRING: {
                    break;
                }
                case VOID:
                    break;
                case AUTO:
                    break;
                case ID:
                    break;
            }
        }
        if (op.equals("<")) {
            switch (type.typeEnum) {
                case INT:
                    return " = icmp slt i32 %";
                case BOOL:
                    return " = icmp slt i8 %";
                case FLOAT:
                    return " = fcmp  olt double %";
                case LONG:
                    return " = icmp slt i64 %";
                case CHAR:
                    return " = icmp slt i8 %";
                case DOUBLE:
                    return " = fcmp  olt double %";
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
        if (op.equals("<=")) {
            switch (type.typeEnum) {
                case INT:
                    return " = icmp sle i32 %";
                case BOOL:
                    return " = icmp sle i8 %";
                case FLOAT:
                    return " = fcmp  ole double %";
                case LONG:
                    return " = icmp sle i64 %";
                case CHAR:
                    return " = icmp sle i8 %";
                case DOUBLE:
                    return " = fcmp  ole double %";
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
        if (op.equals(">")) {
            switch (type.typeEnum) {
                case INT:
                    return " = icmp sgt i32 %";
                case BOOL:
                    return " = icmp sgt i8 %";
                case FLOAT:
                    return " = fcmp  ogt double %";
                case LONG:
                    return " = icmp sgt i64 %";
                case CHAR:
                    return " = icmp sgt i8 %";
                case DOUBLE:
                    return " = fcmp  ogt double %";
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
        if (op.equals(">=")) {
            switch (type.typeEnum) {
                case INT:
                    return " = icmp sge i32 %";
                case BOOL:
                    return " = icmp sge i8 %";
                case FLOAT:
                    return " = fcmp  oge double %";
                case LONG:
                    return " = icmp sge i64 %";
                case CHAR:
                    return " = icmp sge i32 %";
                case DOUBLE:
                    return " = fcmp  oge double %";
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
        if(op.equals("!=")){
            switch (type.typeEnum){

                case INT:
                    return " = icmp ne i32 %";
                case BOOL:
                    return " = icmp ne i8 %";
                case FLOAT:
                    return " = fcmp  une double %";
                case LONG:
                    return " = icmp ne i64 %";
                case CHAR:
                    return " = icmp ne i8 %";
                case DOUBLE:
                    return " = fcmp  une double %";
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
        return null;
    }
}
