package calccmd;

import java.util.ArrayList;
import java.util.List;

import calccmd.CalcCmd.Operation;

public class Caller {

    List<Cmd> calcCmds = new ArrayList<Cmd>();

    int index = 0;

    public void calculate(Operation operation, int value) {
        Cmd calcCmd = new CalcCmd(operation, value);
        calcCmd.execute();
        calcCmds.add(calcCmd);
        index++;
    }

    public void redo() {
        Cmd calcCmd = calcCmds.get(index - 1);
        calcCmd.execute();
        calcCmds.add(calcCmd);
        index++;
    }

    public void undo() {
        Cmd calcCmd = calcCmds.get(index - 1);
        calcCmd.unexecute();
        calcCmds.add(calcCmd);
        index++;
    }

}
