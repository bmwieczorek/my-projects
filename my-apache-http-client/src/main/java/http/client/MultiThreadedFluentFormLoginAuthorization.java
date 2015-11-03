package http.client;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

public class MultiThreadedFluentFormLoginAuthorization {

    private static final int COUNT = 10;
    private static volatile boolean hasAnyThreadFinished = false;

    public static void main(String[] args) throws Exception {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        Executor executor = Executor.newInstance()
                .auth(new HttpHost("tkthli700.sabre.com", 8090), Settings.USERNAME, Settings.PASSWORD);

        TimeUnit.SECONDS.sleep(1); // give time to collect via JVisualVM
        CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT + 1); // + 1 for main thread to monitor CPU load
        for (int i = 0; i < COUNT; i++) {
            new Thread(() ->  run(executor, cyclicBarrier)).start();;
        }

        cyclicBarrier.await();
        while (!hasAnyThreadFinished) {
            printCpuLoad(operatingSystemMXBean);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    private static void run(Executor executor, CyclicBarrier cyclicBarrier) {
        String content;
        try {
            cyclicBarrier.await();

            content = executor
                    .execute(Request.Get("http://tkthli700.sabre.com:8090/view?pattern=DEBUG&path=/apps/tkt-hub/logs/*"))
                    .returnContent()
                    .asString();

            hasAnyThreadFinished = true;
            System.err.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printCpuLoad(OperatingSystemMXBean mxBean) {
        for (Method method : mxBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            String methodName = method.getName();
            if (methodName.startsWith("get") && methodName.contains("Cpu") && methodName.contains("Load")
                    && Modifier.isPublic(method.getModifiers())) {
                Object value;
                try {
                    value = method.invoke(mxBean);
                } catch (Exception e) {
                    value = e;
                }
                System.out.print(methodName + "," + value + ",");
            }
        }
        System.out.println("");
    }
}
