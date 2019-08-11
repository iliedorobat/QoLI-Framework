package app.java.data.fetch.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.Fetcher;
import app.java.data.fetch.FetcherUtils;
import app.java.data.fetch.dao.SafetyDAO;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class SafetyDAOImpl implements SafetyDAO {
    public StringBuilder getCrimeRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INCGRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mddw03", params);
    }

    public StringBuilder getOffences() {
        MultiValuedMap<String, String> params = getOffencesMainHttpParams();
        params.put(ParamsConst.ICCS, "ICCS02011");  // Assault
        params.put(ParamsConst.ICCS, "ICCS020221"); // Kidnapping
        params.put(ParamsConst.ICCS, "ICCS0301");   // Sexual violence
        params.put(ParamsConst.ICCS, "ICCS0401");   // Robbery
        params.put(ParamsConst.ICCS, "ICCS0501");   // Burglary
        params.put(ParamsConst.ICCS, "ICCS0502");   // Theft
        params.put(ParamsConst.ICCS, "ICCS0601");   // Unlawful acts involving controlled drugs or precursors
        params.put(ParamsConst.UNIT, "NR");
        return Fetcher.fetchData("crim_off_cat", params);
    }

    public StringBuilder getPensionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SPDEPB, "TOTAL");
        params.put(ParamsConst.SPDEPM, "TOTAL");
        params.put(ParamsConst.UNIT, "PPS_HAB");
        return Fetcher.fetchData("spr_exp_pens", params);
    }

    public StringBuilder getSocialProtectionRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SPDEPS, "SPBENEFNOREROUTE");
        params.put(ParamsConst.UNIT, "PPS_HAB");
        return Fetcher.fetchData("spr_exp_sum", params);
    }

    public StringBuilder getUnexpectedRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INCGRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mdes04", params);
    }

    public StringBuilder getUnpaidRatio() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INCGRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return Fetcher.fetchData("ilc_mdes05", params);
    }

    /**
     * Get main parameters for the offences
     *
     * @return
     */
    private static MultiValuedMap<String, String> getOffencesMainHttpParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put(ParamsConst.LANG, "en");

        if (EnvConst.IS_TESTING) {
            params.put(ParamsConst.GEO, "RO");
            params.put(ParamsConst.TIME, "2015");
        } else {
            FetcherUtils.addParams(params, ParamsConst.GEO, Constants.EU28_MEMBERS_EXTENDED);
        }

        return params;
    }
}
