package app.java.parser.http;

import app.java.commons.MapUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class URIGenerator {
    private static final String ENCODING_SCHEME = "UTF-8";
    private static final String URI_PROTOCOL_NAME = "http";
    private static final String URI_HOST_NAME = "ec.europa.eu/eurostat/wdds";
    private static final String URI_SERVICE = "rest/data";
    private static final String URI_SERVICE_VERSION = "v2.1";
    private static final String URI_FORMAT = "json";
    private static final String URI_LANGUAGE = "en";
    private static final String URI_SEPARATOR = "/";

    /**
     * Generate the URI used to extract data
     * @param dataset The search path ("nama_10r_2hhinc")
     * @param params The parameters used in search path
     * @return
     */
    public static URI generateURI(String dataset, MultiValuedMap<String, String> params) {
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

        for (int i = 0; i < keysList.size(); i++) {
            String key = keysList.get(i);
            Collection<String> values = map.get(key);
            Iterator iterator = values.iterator();

            while (iterator.hasNext()) {
                String value = (String) iterator.next();

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
