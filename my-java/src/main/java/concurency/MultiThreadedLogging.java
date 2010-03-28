package concurency;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultiThreadedLogging implements Runnable {
    private static int i = 0;

    private static ThreadLocal<File> file = new ThreadLocal<File>() {
        protected File initialValue() {
            return new File(Thread.currentThread().getName());
        };
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MultiThreadedLogging());
        t1.setDaemon(true);
        t1.start();
        Thread t2 = new Thread(new MultiThreadedLogging());
        t2.setDaemon(true);
        t2.start();
        Thread.sleep(10);
    }

    public void run() {
        File file2 = file.get();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file2, false);
            while (true)
                fileOutputStream.write((i++ + "\n").getBytes());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(Thread.currentThread() + ": cleaning resources");
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
