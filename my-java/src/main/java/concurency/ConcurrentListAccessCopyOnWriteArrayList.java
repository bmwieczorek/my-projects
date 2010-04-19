package concurency;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentListAccessCopyOnWriteArrayList {
    // using ArrayList by 2 threads (one reading, second writing (adding
    // element) causes concurrent access exception, as a solution use
    // CopyOnWriteArrayList
    // private List<Integer> ints = new ArrayList<Integer>();
    private List<Integer> ints = new CopyOnWriteArrayList<Integer>();

    public static void main(String[] args) {
        final ConcurrentListAccessCopyOnWriteArrayList cls = new ConcurrentListAccessCopyOnWriteArrayList();
        System.out.println("before");
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (true) {
                    cls.ints.add(i++);
                    Thread.yield();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // for (Integer i : cls.ints) {
                    // System.out.println(i);
                    // }

                    // or
                    Iterator<Integer> iterator = cls.ints.iterator();
                    while (iterator.hasNext()) {
                        Integer i = iterator.next();
                        System.out.println(i);
                    }
                    Thread.yield();
                }
            }
        }).start();
        System.out.println("after");
    }
}
