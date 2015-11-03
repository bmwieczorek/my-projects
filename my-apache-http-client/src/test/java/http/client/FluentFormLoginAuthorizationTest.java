package http.client;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import com.bawi.threads.cpu.monitoring.StopWatch;

public class FluentFormLoginAuthorizationTest {
    public static void main(String[] args) throws Exception {
        Executor executor = Executor.newInstance()
                .auth(new HttpHost("tkthli702.sabre.com", 8090), Settings.USERNAME, Settings.PASSWORD);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String content = executor
                .execute(Request.Get("http://tkthli702.sabre.com:8090/view?pattern=DEBUG&path=/apps/tkt-hub/logs/*&hosts=tkthli702"))
                .returnContent()
                .asString();

        stopWatch.stop();
        System.out.println(stopWatch);
        System.out.println(content);
    }
}

/*
User (app code only) time:                   78 ms (0.85%)
System (os code on behalf app e.g.I/O) time: 46 ms (0.51%)
CPU (total CPU spent for app) time:          124 ms (1.37%)
Elapsed time:                                9127 ms (100.00%)

"main" #1 prio=5 os_prio=0 tid=0x00000000021fe000 nid=0x2214 runnable [0x000000000260e000]
   java.lang.Thread.State: RUNNABLE
    at java.net.SocketInputStream.socketRead0(Native Method)
    at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
    at java.net.SocketInputStream.read(SocketInputStream.java:170)
    at java.net.SocketInputStream.read(SocketInputStream.java:141)
    at org.apache.http.impl.io.SessionInputBufferImpl.streamRead(SessionInputBufferImpl.java:139)
    at org.apache.http.impl.io.SessionInputBufferImpl.fillBuffer(SessionInputBufferImpl.java:155)
    at org.apache.http.impl.io.SessionInputBufferImpl.readLine(SessionInputBufferImpl.java:284)

...
*/
