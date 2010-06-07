class MyObj {
    int i;
    String s;

    MyObj() {
        System.out.println("In MyObj default constructor ");
    }

    MyObj(int i) {
        System.out.println("In MyObj constructor ");
        this.i = i;
    }

    MyObj(String s) {
        System.out.println("In MyObj constructor ");
        this.s = s;
    }
}

public class PassingArgToFunction {
    static void change(MyObj myObj) {
        myObj.i = 10;
        myObj.s = null;
        // myObj.s = "Ania";

    }

    public static void main(String[] args) {
        MyObj myObj = new MyObj(1);
        MyObj myObj1 = new MyObj("ania");
        System.out.println("Pre:" + myObj.i + "" + myObj1.s);
        change(myObj);
        change(myObj1);
        System.out.println("Post:" + myObj.i + "" + myObj1.s);
    }

}
