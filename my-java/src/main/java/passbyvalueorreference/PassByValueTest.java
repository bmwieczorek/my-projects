package passbyvalueorreference;

public class PassByValueTest {
    private int value;

    public static void add(int a, int b) {
        a = a + b;
    }

    public void subtract(PassByValueTest tp, int b) {
        tp.value = tp.value - b;
    }

    public void setup(int value) {
        this.value = 5;
    }

    public void run() {
                            // this.value:
        value = 2;          // 2
        setup(value);       // 5
        add(value, 5);      // 5
        subtract(this, 3);  // 2 
        System.out.println(value);
    }

    public static void main(String[] args) {
        new PassByValueTest().run();
    }
}