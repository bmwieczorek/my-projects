
class A {
    public static void main(String[] args) {
        System.out.println("A");
    }
}

class B extends A {
    public static void main(String[] args) {
        System.out.println("B");
    }
}

public class C {

	void changeString(String s){
		//s=null;
		s="bbbb";
	}
	void changeInt(int i){
		i = 0;
	}
	
	@SuppressWarnings("static-access")
    public static void main(String[] args) {
		System.out.println("C");
		A a = new B();
		a.main(null);  //print A
		String s = "aaaa";
		int i = 10;
		System.out.println("1:" + s + ">" + i);
		new C().changeString(s);
		new C().changeInt(i);
		System.out.println("2:" + s + ">" + i);
	}

}
