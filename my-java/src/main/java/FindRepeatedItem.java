import java.util.HashSet;
import java.util.Set;

public class FindRepeatedItem {

    public static void main(String[] args) {
        int[] ints = { 1, 2, 3, 1, 4, 5, 3, 1 };
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < ints.length; i++) {
            int j = ints[i];
            if (set.contains(j)) {
                System.out.println("Already on the set j=" + j);
            } else {
                System.out.println("Adding j=" + j);
                set.add(j);
            }
        }
    }

}
