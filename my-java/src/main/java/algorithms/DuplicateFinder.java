package algorithms;
import java.util.HashSet;
import java.util.Set;

public class DuplicateFinder {

    private static String[] strArray =
            { "Cat", "Dog", "Tiger", "Lion", "Lion" };

    public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
    	for (String element : strArray) {
            if (set.contains(element)) {
                System.out.println("Duplicated element: " + element);
                break;
            }
            set.add(element);
        }
    }
}
