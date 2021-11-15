package syntaxtree;

import java.util.*;

public class Var_Dcls {
    // clones it's child list
    public Stack<Var_Dcl> var_dclList;
    public  Var_Dcls() {
        var_dclList = new Stack<>();
    }
    public Var_Dcls(Var_Dcls var_dcls1, Var_Dcls var_dcls2){
        for (int i = var_dcls1.var_dclList.size()-1; i >=0; i--) {
            var_dcls2.var_dclList.push(var_dcls1.var_dclList.get(i));
        }
        this.var_dclList = var_dcls2.var_dclList;
    }
    public Var_Dcls(Var_Dcl var_dcl, Var_Dcls var_dcls) {
        var_dcls.var_dclList.push(var_dcl);
        var_dclList = var_dcls.var_dclList;
    }

    public Var_Dcls(Var_Dcl var_dcl) {
        this.var_dclList = new Stack<Var_Dcl>();
        this.var_dclList.push(var_dcl);
    }

    public Var_Dcls(Type type, Var_Dcl var_dcl) {
        var_dcl.type = type;
        this.var_dclList = new Stack<>();
        this.var_dclList.push(var_dcl);
    }

    public Var_Dcls(Type type, Var_Dcl var_dcl, Var_Dcls var_dcls) {
        this(var_dcl, var_dcls);
        for (int i = 0; i < var_dclList.size(); i++) {
            var_dclList.get(i).type = type;
        }
    }

    public Var_Dcls(boolean isConst, Var_Dcls var_dcls) {
        this.var_dclList = var_dcls.var_dclList;
        for (int i = 0; i < var_dclList.size(); i++) {
            var_dclList.get(i).isConst = isConst;
        }
    }
}
