package cpu;

public class CpuIntesive {
    public static void main(String[] args) {
        double value = Math.E;
        for (double i = Math.E; i > 0; i++) {
            value = Math.sqrt(i * (Math.PI * Math.E)) / i;
        }
        System.out.println(value);
    }
}
