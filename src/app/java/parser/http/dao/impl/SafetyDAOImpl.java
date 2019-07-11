package app.java.parser.http.dao.impl;

import app.java.parser.ParserUtils;
import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.SafetyDAO;
import org.apache.commons.collections4.MultiValuedMap;

public class SafetyDAOImpl implements SafetyDAO {
    public StringBuilder getPensionRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("spdepb", "TOTAL");
        params.put("spdepm", "TOTAL");
        params.put("unit", "PPS_HAB");
        return DataFetcher.fetchData("spr_exp_pens", params);
    }

    public StringBuilder getSocialProtectionRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("spdeps", "SPBENEFNOREROUTE");
        params.put("unit", "PPS_HAB");
        return DataFetcher.fetchData("spr_exp_sum", params);
    }

    public StringBuilder getUnexpectedRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdes04", params);
    }

    public StringBuilder getUnpaidRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mdes05", params);
    }

    public StringBuilder getOffences() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("iccs", "ICCS02011");  // Assault
        params.put("iccs", "ICCS020221"); // Kidnapping
        params.put("iccs", "ICCS0301");   // Sexual violence
        params.put("iccs", "ICCS0401");   // Robbery
        params.put("iccs", "ICCS0501");   // Burglary
        params.put("iccs", "ICCS0502");   // Theft
        params.put("iccs", "ICCS0601");   // Unlawful acts involving controlled drugs or precursors
        params.put("unit", "NR");
        return DataFetcher.fetchData("crim_off_cat", params);
    }

    public StringBuilder getCrimeRatio() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("unit", "PC");
        return DataFetcher.fetchData("ilc_mddw03", params);
    }
}
