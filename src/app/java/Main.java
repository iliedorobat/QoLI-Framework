package app.java;

import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.net.URI;

//TODO: see printRelativeAmplitude from release/1.0
public class Main {
    public static void main(String[] args) throws Exception {
//        MaterialLivingDAO materialLivingDAO = new MaterialLivingDAOImpl();
//        StringBuilder result = materialLivingDAO.getPovertyRiskRatio();
//        String path = "files/testing/tt.json";
//        TextUtils.writeToFile(result, path);

        // Example
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put("time", "2015");
        params.put("sex", "T");
        params.put("unit", "PC");

        for (int i = 0; i < FetcherUtils.EU28_MEMBERS.length; i++) {
            params.put("geo", FetcherUtils.EU28_MEMBERS[i]);
        }

        URI uri = Fetcher.generateURI("educ_uoe_enra10", params);
        System.out.println(uri.toString());

        System.out.println(FetcherUtils.getGeoParams());
    }
}
