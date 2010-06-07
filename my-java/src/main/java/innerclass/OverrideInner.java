package innerclass;

class BaseWithInner {
    Inner bi;

    class Inner {
        public Inner() {
            System.out.println("new BaseInner()");
        }
    }

    public BaseWithInner() {
        System.out.println("new BaseWithInner");
        bi = new Inner();
    }
}

public class OverrideInner extends BaseWithInner {
    class Inner {
        public Inner() {
            System.out.println("new Inner()");
        }
    }

    public static void main(String[] args) {
        new OverrideInner();
    }
}
