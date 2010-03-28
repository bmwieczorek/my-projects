package mypckg;

public class Outside {

    public static void main(String[] args) {
        int x = 1, y = 1, z = 3;
        x += (++y + z--);
        System.out.println(x);
    }
}