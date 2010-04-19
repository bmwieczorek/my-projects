package switchcase;
public class SwitchStatementTest {

    public static void main(String[] args) {
        int a = 3;
        int b = 0;
        switch (a) {
            case 1: {
                b = a + 2;
                System.err.println(b + ":1:" + a);
            }
            case 2: {
                b = a + 3;
                System.err.println(b + ":2:" + a);
            }
            case 3: {
                b = a + 4;
                System.err.println(b + ":3:" + a);
            }
            case 4: {
                b = a + 5;
                System.err.println(b + ":4:" + a);
            }
            case 5: {
                b = a + 6;
                System.err.println(b + ":5:" + a);
            }
            default: {
                b = a * 2;
                System.err.println(b + ":d:" + a);
            }
        }
        System.out.println(b);
    }
}