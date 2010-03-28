package designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

interface Component{
    void execute();
    void add(Component component);
    void remove(Component component);    
}

abstract class AbstractLeafComponent implements Component {

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
    
    abstract public void execute();
    
}

class Leaf1Component extends AbstractLeafComponent{
    public void execute() {
        System.out.println("leaf1");
    }    
}

class Leaf2Component extends AbstractLeafComponent{
    public void execute() {
        System.out.println("leaf2");
    }    
}


class ComponentGroup implements Component{

    List<Component> components = new ArrayList<Component>();
    
    public void add(Component component) {
        components.add(component);
    }

    public void execute() {
        for (Component component : components) {
            component.execute();
        }
    }

    public void remove(Component component) {
        components.remove(component);
    }
    
}

public class Example {
    public static void main(String[] args) {
        Component components = new ComponentGroup();
        components.add(new Leaf1Component());
        components.add(new Leaf2Component());
        components.execute();
    }
    
}
