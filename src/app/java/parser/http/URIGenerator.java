package app.java.parser.http;

import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder {
    private static final String encode = "UTF-8";

    public static URI getURI(String scheme, String host, String path, Map<String, String> params) throws Exception {
        URIBuilder uriBuilder =  new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriBuilder.addParameter(
                    URLEncoder.encode(entry.getKey(), encode),
                    URLEncoder.encode(entry.getValue(), encode)
            );
        }

        return uriBuilder.build();
    }
}
