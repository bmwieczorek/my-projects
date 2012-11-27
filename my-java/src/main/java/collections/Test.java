package collections;

import java.util.HashSet;
import java.util.Set;

public class Test {

    private static final int SIZE = 10000;

    private static class Item {
        private int id;

        public Item(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Item other = (Item) obj;
            if (id != other.id)
                return false;
            return true;
        }

    }

    public static void main(String[] args) {
        Set<Item> items = new HashSet<Item>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            items.add(new Item(i));
        }
        long start = System.nanoTime();

        for (int i = 0; i < SIZE; i++) {
            items.contains(new Item(1));
        }

        long stop = System.nanoTime();
        System.out.println(stop - start); // NOPMD
    }
}
