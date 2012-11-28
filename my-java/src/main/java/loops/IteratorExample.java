package loops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    enum Lower {
        a, b, c
    }

    enum Higher {
        A, B, C, D
    }

    public static void main(String[] args) {
        // List<Lower> lowers = Arrays.asList(Lower.values());
        List<Higher> highers = new ArrayList<Higher>(Arrays.asList(Higher.values()));
        // for (Iterator<Lower> li = lowers.iterator(); li.hasNext();) {
        // Lower lower = li.next();
        @SuppressWarnings("unused")
        int i = 0;
        for (Iterator<Higher> hi = highers.iterator(); hi.hasNext(); i++) {
            hi.next();
            // if ((i % 2) == 0)
            hi.remove();

        }
        for (Higher higher : highers) {
            System.out.println(higher);
        }
        // }

        // for (Iterator<Lower> li = lowers.iterator(); li.hasNext();) {
        // // Lower lower = li.next();
        // for (Iterator<Higher> hi = highers.iterator(); hi.hasNext();) {
        // // Higher higher = hi.next();
        // // System.out.println(lower + ":" + higher);
        // System.out.println(li.next() + ":" + hi.next());
        // }
        // }

    }
}
