package http.client;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class FormLoginAuthorizationTest {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpHost proxy = new HttpHost(Settings.PROXY_HOST, Settings.PROXY_PORT);
        
        String httpGetContent = Executor.newInstance()
                .auth(proxy, Settings.USERNAME, Settings.PASSWORD)
                .execute(Request.Get("https://www.apache.org/dist/jmeter/KEYS")
                        .viaProxy(proxy))
                .returnContent()
                .asString();
        System.err.println(httpGetContent);


        String xmlRq = FileUtils.readFileToString(new File("EMDImageRQ.xml"));
        String httpPostContent = Executor.newInstance()
                .auth(proxy, Settings.USERNAME, Settings.PASSWORD)
                .execute(Request.Post("https://stg.xxx.com/xmlts/sandboxdm")
                        .viaProxy(proxy)
                        .bodyString(xmlRq, ContentType.TEXT_PLAIN))
                .returnContent()
                .asString();
        System.err.println(httpPostContent);
    }
}


