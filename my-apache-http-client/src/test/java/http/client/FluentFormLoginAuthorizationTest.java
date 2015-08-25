package http.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

public class FluentFormLoginAuthorizationTest {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        Executor executor = Executor.newInstance()
                .auth(new HttpHost("tkthli702.sabre.com", 8090), Settings.USERNAME, Settings.PASSWORD);

        
        String content = executor
                .execute(Request.Get("http://tkthli702.sabre.com:8090/view?pattern=6074551101538&path=/apps/tkt-hub/logs/t2.emd*.log&hosts=tkthli702"))
                .returnContent()
                .asString();

        System.err.println(content);
    }
}
