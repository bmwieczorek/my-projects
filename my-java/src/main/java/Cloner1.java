import java.util.Arrays;
import java.util.Random;

public class Cloner1 implements Cloneable {
    private final String foo = "Boiled Eggs";

    public static void main(String[] args) throws Exception {
        // Cloner1 aClone = new Cloner1();
        // Cloner1 anotherClone = (Cloner1) aClone.clone();
        // System.out.println(anotherClone.toString());
        // Runtime.getRuntime().exec("touch kasia.txt");
        // String[] array = {"a","b","c"};
        // Cloner1 name = new Cloner1();
        // System.out.println(Arrays.asList( name.mixup(array)));
        // int a = 3; int b = 0;
        // switch (a) {
        // case 1:
        // b = a + 2;
        // case 2:
        // b = a + 3;
        // case 3:
        // b = a + 4;
        // case 4:
        // b = a + 5;
        // case 5:
        // b = a + 6;
        // default:
        // b = a * 2;
        // }
        // System.out.println(b);
        // divide(1, 0);
        System.out.println(stringSize(null));
        throw new ArithmeticException();
    }

    Integer aaa = new Integer(1);

    public static int divide(int i, int j) {
        return i % j;
    }

    static public int stringSize(Object s) {
        try {
            // return s.toString().length();
            return 2;
        } catch (Exception ex) {
            return -1;
        } finally {
            // return 0;
        }
    }

    public String toString() {
        return foo;
    }

    public Object[] mixup(Object[] array) {
        int numElements = array.length;

        Object[] result = (Object[]) array.clone();
        Arrays.fill(result, null);
        Random random = new Random();

        for (int i = 0; i < numElements; i++) {
            int randomIndex;

            do {
                randomIndex = random.nextInt(numElements);
            } while (result[randomIndex] != null);

            result[randomIndex] = array[i];
        }

        return result;
    }

}