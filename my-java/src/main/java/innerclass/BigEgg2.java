package innerclass;

class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    private Yolk yolk = new Yolk();

    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void setYolk(Yolk y) {
        this.yolk = y;
    }

    public void g() {
        yolk.f();
    }

}

public class BigEgg2 extends Egg2 {
    class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    Yolk yolk = new Yolk();

    public BigEgg2() {
        System.out.println("New BigEgg2()");
        setYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();
    }
}
