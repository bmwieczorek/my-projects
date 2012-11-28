package generics.effective.java;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add(new Integer(1));
        list.add(new String("aaa"));

        @SuppressWarnings("unused")
        List<?> list2 = createList();

    }

    private static List<?> createList() {
        List<String> list = new ArrayList<String>();
        return list;
    }

}
