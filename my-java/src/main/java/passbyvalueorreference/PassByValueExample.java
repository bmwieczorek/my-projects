package passbyvalueorreference;

@SuppressWarnings("unused")
public class PassByValueExample {

    void changeString(String s) {
        // s=null;
        s = "bbbb";
    }

    void changeInt(int i) {
        i = 0;
    }

    public static void main(String[] args) {
        String s = "aaaa";
        int i = 10;
        System.out.println("1:" + s + ">" + i);
        new PassByValueExample().changeString(s);
        new PassByValueExample().changeInt(i);
        System.out.println("2:" + s + ">" + i);
    }

}
