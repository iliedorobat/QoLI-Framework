package ro.webdata.qoli.aggr.data.fetch;

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
import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;

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

public class Fetcher {
    private static final String ENCODING_SCHEME = "UTF-8";
    private static final String URI_PROTOCOL_NAME = "https";
    private static final String URI_HOST_NAME = "ec.europa.eu/eurostat/api/dissemination";
    private static final String URI_SERVICE = "statistics";
    private static final String URI_SERVICE_VERSION = "1.0/data";
    private static final String URI_FORMAT = "json";
    private static final String URI_LANG = "en";
    private static final String URI_SEPARATOR = "/";

    /**
     * Fetch the Eurostat data.
     *
     * @param dataset The search path (E.g.: "nama_10r_2hhinc")
     * @param inputParams The main parameters used in search path
     * @return HTTP Response as a StringBuilder
     */
    public static StringBuilder fetchData(String dataset, MultiValuedMap<String, String> inputParams) {
        return fetchData(dataset, inputParams, Constants.EU28_MEMBERS);
    }

    /**
     * Fetch the Eurostat data.
     *
     * @param dataset The search path (E.g.: "nama_10r_2hhinc")
     * @param inputParams The main parameters used in search path
     * @param countryCodes The list of country codes
     * @return HTTP Response as a StringBuilder
     */
    public static StringBuilder fetchData(String dataset, MultiValuedMap<String, String> inputParams, String[] countryCodes) {
        MultiValuedMap<String, String> params = FetcherUtils.consolidateHttpParams(inputParams, countryCodes);
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

            InputStreamReader is = new InputStreamReader(entity.getContent());
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
     * Percentage of the population rating their satisfaction as high, medium or low<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05b<br/>
     * Years: 2013; 2018; 2022
     *
     * @return
     */
    public static StringBuilder fetchSatisfactionRatio(MultiValuedMap<String, String> params) {
        return Fetcher.fetchData("ilc_pw05b", params);
    }

    /**
     * Participation in formal or informal voluntary activities or active citizenship<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015; 2022
     *
     */
    public static StringBuilder fetchActivePeopleRatio(MultiValuedMap<String, String> params) {
        return Fetcher.fetchData("ilc_scp19", params);
    }

    /**
     * Generate the URI used to extract data
     *
     * @param dataset The search path (E.g.: "nama_10r_2hhinc")
     * @param params The parameters used in search path
     * @return URI
     */
    public static URI buildURI(String dataset, MultiValuedMap<String, String> params) {
        String path = URI_SERVICE
                + URI_SEPARATOR + URI_SERVICE_VERSION
                + URI_SEPARATOR + dataset;

        URIBuilder uriBuilder =  new URIBuilder()
                .setScheme(URI_PROTOCOL_NAME)
                .setHost(URI_HOST_NAME)
                .setPath(path)
                .setParameter("format", URI_FORMAT)
                .setParameter("lang", URI_LANG)
                .setParameter("sinceTimePeriod", String.valueOf(EnvState.MIN_YEAR))
                .setParameter("untilTimePeriod", String.valueOf(EnvState.MAX_YEAR));

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

    /**
     * Pause the thread execution
     *
     * @param sleepTime The amount of milliseconds
     */
    public static void sleep(long sleepTime) {
        try {
            System.out.println("Sleeping " + sleepTime + " milliseconds");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
