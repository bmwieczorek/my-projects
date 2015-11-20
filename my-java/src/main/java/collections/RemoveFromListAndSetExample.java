package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RemoveFromListAndSetExample {

    public static void my(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        Integer[] t = { 1, 3 };
        my(t);

        Set<Integer> set = new TreeSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + ":" + list);
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(i);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println(set + ":" + list);
    }

}
