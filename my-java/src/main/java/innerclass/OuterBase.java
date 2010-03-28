package innerclass;

class Outer{
	private void a(){};
	class Inner{
		void b(){ a(); }
	}
}

public class OuterBase extends Outer.Inner{

	public OuterBase(Outer o) {
		o.super();
	}
	
	public static void main(String[] args) {
		Outer o = new Outer();
		OuterBase ob = new OuterBase(o);
		ob.b();

	}

}
