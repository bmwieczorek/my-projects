import java.util.Arrays;

public class ArraysExample {
    public static void main(String[] args) {
        char[] arr = new char[10];
        Arrays.fill(arr, 0, 9, 'x');
        String string = new String(arr);
        System.out.println(string);
    }
}
