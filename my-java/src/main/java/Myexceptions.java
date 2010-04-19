
class Ccc {
	void myM() throws Exception {
        System.out.println(0);
    }
}

class Dddd extends Ccc {
	void myM() throws RuntimeException {
        System.out.println(1);
    }
}

public class Myexceptions {
	public static void main(String[] args) throws Exception {
        Ccc c = new Dddd();
		c.myM();
    }
}
