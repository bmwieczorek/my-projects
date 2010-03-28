public class ContextSwitching {
    private static int i = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if (i > 0)
                        throw new RuntimeException("I>0");
                    System.out.println("t1");
                    //Thread.yield();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    i++;
                    System.out.println("t2");
                    //Thread.yield();
                }
            }
        });
        t1.start();
        t2.start();
    }

}
