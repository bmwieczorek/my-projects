import java.util.Map;


public class TestDivisionByZero {

    public static void main(String[] args) {

        int i = 0;
        for (; i == 0; ++i) {
            System.out.println(i);
        }
        System.exit(0);
    }

}
