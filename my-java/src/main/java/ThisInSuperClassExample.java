
class Superclass {
    void myPrint(){
        System.out.println(this);
    }
}

class Subclass extends Superclass {
}

public class ThisInSuperClassExample {
	public static void main(String[] args) {
		Subclass subclass = new Subclass();
		subclass.myPrint();
	}

}
