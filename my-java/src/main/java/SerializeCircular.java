import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class CC2 implements Serializable {
	private static final long serialVersionUID = 1L;
	CC1 c1;
}

class CC1 implements Serializable {
	private static final long serialVersionUID = 1L;
	CC2 c2;
}

public class SerializeCircular {

    public static void main(String[] args) throws IOException {
        FileOutputStream f = new FileOutputStream("TestSerial");
        // List alist = new ArrayList();
        new ObjectOutputStream(f).writeObject(new CC2());
    }
}
