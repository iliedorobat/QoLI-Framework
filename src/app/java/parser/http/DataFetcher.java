package app.java.parser.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;

public class DataFetcher {
    public static StringBuilder fetchData(String dataset, Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        URI uri = URIGenerator.generateURI(dataset, params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet request = new HttpGet(uri);

        try {
            HttpResponse response = client.execute(request, context);
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();

            InputStreamReader is = new InputStreamReader(entity.getContent(), charset);
            BufferedReader br = new BufferedReader(is);
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
