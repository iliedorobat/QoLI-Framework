package app.java.data.fetch;

import app.java.commons.utils.MapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static app.java.commons.constants.Constants.EU28_MEMBERS;

public class Fetcher {
    private static final String ENCODING_SCHEME = "UTF-8";
    private static final String URI_PROTOCOL_NAME = "http";
    private static final String URI_HOST_NAME = "ec.europa.eu/eurostat/wdds";
    private static final String URI_SERVICE = "rest/data";
    private static final String URI_SERVICE_VERSION = "v2.1";
    private static final String URI_FORMAT = "json";
    private static final String URI_LANGUAGE = "en";
    private static final String URI_SEPARATOR = "/";

    // TODO: documentation:
    public static StringBuilder fetchData(String dataset, MultiValuedMap<String, String> inputParams) {
        return fetchData(dataset, inputParams, EU28_MEMBERS);
    }

    // TODO: documentation:
    public static StringBuilder fetchData(String dataset, MultiValuedMap<String, String> inputParams, String[] countries) {
        MultiValuedMap<String, String> params = FetcherUtils.consolidateHttpParams(inputParams, countries);
        StringBuilder result = new StringBuilder();
        URI uri = buildURI(dataset, params);

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

    /**
     * Generate the URI used to extract data
     * @param dataset The search path (E.g.: "nama_10r_2hhinc")
     * @param params The parameters used in search path
     * @return URI
     */
    public static URI buildURI(String dataset, MultiValuedMap<String, String> params) {
        String path = URI_SERVICE
                + URI_SEPARATOR + URI_SERVICE_VERSION
                + URI_SEPARATOR + URI_FORMAT
                + URI_SEPARATOR + URI_LANGUAGE
                + URI_SEPARATOR + dataset;

        URIBuilder uriBuilder =  new URIBuilder()
                .setScheme(URI_PROTOCOL_NAME)
                .setHost(URI_HOST_NAME)
                .setPath(path);

        ArrayList<String> keysList = MapUtils.getUniqueKeys(params);
        Map<String, Collection<String>> map = params.asMap();

        for (String key : keysList) {
            Collection<String> values = map.get(key);
            for (String value : values) {
                try {
                    uriBuilder.addParameter(
                            URLEncoder.encode(key, ENCODING_SCHEME),
                            URLEncoder.encode(value, ENCODING_SCHEME)
                    );
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
