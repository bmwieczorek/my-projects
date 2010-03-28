package inheritancerisks;

import java.util.ArrayList;
import java.util.List;

class Super2{
    List<Super2> list = new ArrayList<Super2>();
    void myPrint(){
        for (Super2 super1 : list) {
            System.out.println(((Sub2)super1).name);
        }
    } 
    public Super2() {
        System.out.println("superclass constructor");
        //using ref to subclass which is not initialized
        list.add(this);
        myPrint();     
    }
}

class Sub2 extends Super2{
    String name;
    public Sub2() {
        System.out.println("subclass constructor");
        name="bawi";
    }
}

public class SuperClassConstructorUsesUninitializedSubclassRef{
    public static void main(String[] args) {
        Sub2 sub = new Sub2();
        System.out.println("Superclass object initialized");
        sub.myPrint();
    }
}


