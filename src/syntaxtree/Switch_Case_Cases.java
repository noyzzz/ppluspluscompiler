package syntaxtree;

import java.util.ArrayList;
import java.util.Stack;

public class Switch_Case_Cases {
    public Stack<Switch_Case_Case> switch_case_caseStack;
    public Switch_Case_Cases(Integer int_const, Block block, Switch_Case_Cases switch_case_cases){
        Switch_Case_Case switch_case_case = new Switch_Case_Case(int_const, block);
        switch_case_cases.switch_case_caseStack.push(switch_case_case);
        this.switch_case_caseStack = switch_case_cases.switch_case_caseStack;
    }
    public Switch_Case_Cases(Integer int_const ,Block block){
        switch_case_caseStack = new Stack<>();
        Switch_Case_Case switch_case_case = new Switch_Case_Case(int_const, block);
        switch_case_caseStack.push(switch_case_case);
    }
}
