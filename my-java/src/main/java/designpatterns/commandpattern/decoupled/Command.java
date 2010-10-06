package designpatterns.commandpattern.decoupled;

public interface Command {

    public int execute();

    public int unexecute();

}