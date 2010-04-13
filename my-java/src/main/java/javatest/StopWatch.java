package javatest;


public class StopWatch {
    java.util.Calendar cal1, cal2;

    public void start() {
        cal1 = java.util.Calendar.getInstance();
    }

    public void stop() {
        cal2 = java.util.Calendar.getInstance();
    }

    public long getDifference() {
        long diff = 0;
        try {
            diff = (cal2.getTime().getTime() - cal1.getTime().getTime()) / 1000;
        } catch (Exception e) {
            diff = -1;
        }
        return diff;
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch s = new StopWatch();
        s.start();
        Thread.sleep(3000);
        s.stop();
        System.out.println(s.getDifference());
        // System.out.println(s.cal2.get(java.util.Calendar.SECOND));

    }
}