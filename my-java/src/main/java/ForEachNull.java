import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForEachNull {

    public static void main(String[] args) {

        // good to return Collections.emptyList() instead of null, as foreach
        // will produce null pointer exception
        List<String> names = Collections.emptyList();

        // cannot add elements to the "EMPTY_LIST"
        // names.add("ania");

        System.out.println(getName(names));

        names = null;

        // cannot iterate over null reference
        // System.out.println(getName(names));

        names = new ArrayList<String>();
        System.out.println(getName(names));

    }

    private static String getName(List<String> names) {

        // loop is never entered in case of EMPTY_LIST
        for (String name : names) {
            System.out.println("In the loop");
            return name;
        }
        return null;
    }

}
