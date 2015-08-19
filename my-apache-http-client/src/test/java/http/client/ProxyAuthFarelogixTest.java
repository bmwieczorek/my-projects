package http.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public class ProxyAuthFarelogixTest {

    public static void main(String[] args) throws IOException {
        String xmlRq = FileUtils.readFileToString(new File("EMDImageRQ.xml"));
        HttpClient client = createHttpClient();
        HttpPost post = new HttpPost("https://stg.farelogix.com/xmlts/sandboxdm");
        post.setEntity(new StringEntity(xmlRq));
        
        // when
        HttpResponse response = client.execute(post);

        // then
        String content = getContent(response);
        System.err.println(content);
    }

    private static HttpClient createHttpClient() {
        HttpHost proxy = new HttpHost(Settings.PROXY_HOST, Settings.PROXY_PORT);
        Credentials credentials = new UsernamePasswordCredentials(Settings.USERNAME, Settings.PASSWORD);
        AuthScope authScope = new AuthScope(Settings.PROXY_HOST, Settings.PROXY_PORT);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(authScope, credentials);
        HttpClient client = HttpClientBuilder
                .create()
                .setProxy(proxy)
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        return client;
    }

    private static String getContent(HttpResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}