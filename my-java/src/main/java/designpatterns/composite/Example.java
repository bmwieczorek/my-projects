package designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

interface Component {
    void execute();

    void add(Component component);

    void remove(Component component);
}

abstract class AbstractLeafComponent implements Component {

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    abstract public void execute();

}

class Leaf1Component extends AbstractLeafComponent {
    @Override
    public void execute() {
        System.out.println("leaf1");
    }
}

class Leaf2Component extends AbstractLeafComponent {
    @Override
    public void execute() {
        System.out.println("leaf2");
    }
}

class ComponentGroup implements Component {

    List<Component> components = new ArrayList<Component>();

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void execute() {
        for (Component component : components) {
            component.execute();
        }
    }

    @Override
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
