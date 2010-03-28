
class Superclass {
    void myPrint(){
        System.out.println(this);
    }
}

public class SubClass extends Superclass{

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.myPrint();
    }

}
