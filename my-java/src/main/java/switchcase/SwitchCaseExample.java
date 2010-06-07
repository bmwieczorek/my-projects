package switchcase;

public class SwitchCaseExample {
    enum CountryCode {
        PL, GB, US
    }

    public static void main(String[] args) {
        // enum
        switchCaseEnum(CountryCode.PL);

        // primitives convertible to int
        switchInt(1);
        switchChar('c');
        short s = 1;
        switchShort(s);
        // switchShort(1); // compilation failure

        // works for Integer
        switchInteger(1);

        // compile error
        // int i = 1L; // doesn't compile directly, needs cast to int
        // switchLong(1L);

    }

    private static void switchCaseEnum(CountryCode code) {
        switch (code) {
        case PL:
            System.out.println(code);
            break;
        default:
            System.out.println("default");
            break;
        }
    }

    private static void switchInt(int i) {
        switch (i) {
        case 1:
            System.out.println(i);
            break;
        default:
            System.out.println("default");
            break;
        }
    }

    private static void switchShort(short s) {
        switch (s) {
        case 1:
            System.out.println(s);
            break;
        default:
            System.out.println("default");
            break;
        }
    }

    private static void switchChar(char c) {
        switch (c) {
        case 'c':
            System.out.println(c);
            break;
        default:
            System.out.println("default");
            break;
        }
    }

    private static void switchInteger(Integer integer) {
        switch (integer) {
        case 1:
            System.out.println(integer);
            break;
        default:
            System.out.println("default");
            break;
        }
    }
}
