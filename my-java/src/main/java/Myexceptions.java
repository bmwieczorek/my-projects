import java.io.FileNotFoundException;

class Ccc {
    void myM() throws Exception {
        System.out.println(0);
    }
}

class Dddd extends Ccc {
    @Override
    void myM() throws FileNotFoundException {
        System.out.println(1);
    }
}

public class Myexceptions {
    public static void main(String[] args) {
        Ccc c = new Dddd();
        //c.myM();
    }

}
