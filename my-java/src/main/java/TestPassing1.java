public class TestPassing1 {
    private int value;

    public static void add(int a, int b) {
        a = a + b;
    }

    public void subtract(TestPassing1 tp, int b) {
        tp.value = tp.value - b;
    }

    public void setup(int value) {
        this.value = 5;
    }

    public void run() {
        value = 2;
        setup(value);
        add(value, 5);
        subtract(this, 3);
        System.out.println(value);
    }

    public static void main(String[] args) {
        new TestPassing1().run();
    }
}