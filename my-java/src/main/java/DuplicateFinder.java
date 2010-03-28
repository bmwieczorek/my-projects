import java.util.HashSet;
import java.util.Set;

public class DuplicateFinder {

    private static String[] strArray =
            { "Cat", "Dog", "Tiger", "Lion", "Lion" };

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < strArray.length; i++) {
            String element = strArray[i];
            if (set.contains(element)) {
                System.out.println("Duplicated element: " + element);
                break;
            }
            set.add(element);
        }
    }
}
