package innerclasses;

import java.util.Iterator;

import org.apache.commons.lang.NotImplementedException;

public class MySet<E> {
    private static class Entry {
        String key;
        int value;
        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
        public int getValue(){
            return value;
        }
        public String getKey(){
            return key;
        }
    }

    final private E[] myarray;

    final private Entry[] entries = new Entry[]{new Entry("ania", 26), new Entry("bartek", 27)};

    public MySet(E[] array) {
        // myarray = (E[])new Object[10];
        myarray = array;
    }

    // my iterator cannot be static since it accesses myarray defined in the
    // outer class
    private class MyIterator implements Iterator<E> {
        int index;

        public boolean hasNext() {
            if (index >= myarray.length)
                return false;
            else
                return true;
        }

        public E next() {
            return myarray[index++];
        }

        public void remove() {
			throw new NotImplementedException();
        }

    }

    public int getValue(String key) {
        for (Entry entry : entries) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return -1;
        
    }

    public Iterator<E> iterator() {
        return new MyIterator();
    }

    public static void main(String[] args) {
        Integer[] inputArray = { 1, 2, 123, 4 };
        MySet<Integer> mySet = new MySet<Integer>(inputArray);
        for (Iterator<Integer> iterator = mySet.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        int value = mySet.getValue("ania");
        System.out.println(value);
    }
}
