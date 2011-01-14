package innerclasses;

import java.util.Arrays;

interface Comparator<T> extends java.util.Comparator<T> {
}

public class ImplComparatorAsAnonymousClassEx {

    public static void main(String[] args) {

        String[] words = new String[] { "aa", "b", "cccc", "ddd" };
        Arrays.sort(words, new java.util.Comparator<String>() {

            @Override
            public int compare(String t1, String t2) {
                return t1.length() - t2.length();
            }
        });

        System.out.println(Arrays.asList(words));

    }
}
