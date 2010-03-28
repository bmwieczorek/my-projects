package collections;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.Iterator;

public class IterateOverCollection {

    public static void main(String[] args) {
        Collection<Integer> ints = asList(1, 2, 3);
        for (Iterator<Integer> iterator = ints.iterator(); iterator.hasNext();) {
            Integer integer = iterator.next();
            System.out.println(integer + ":" + iterator.hasNext());
        }
        
        for (Integer integer : ints) {
            System.out.println(integer);
        }

    }

}
