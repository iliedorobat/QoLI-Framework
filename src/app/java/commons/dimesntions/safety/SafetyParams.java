package app.java.commons.dimesntions.safety;

import app.java.commons.constants.EnvConst;
import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS_EXTENDED;

public class SafetyParams {
    public static final String[] OFFENCESES_TYPES = {
            "ICCS02011",    // Assault
            "ICCS020221",   // Kidnapping
            "ICCS0301",     // Sexual violence
            "ICCS0401",     // Robbery
            "ICCS0501",     // Burglary
            "ICCS0502",     // Theft
            "ICCS0601"      // Unlawful acts involving controlled drugs or precursors
    };

    public static MultiValuedMap<String, String> getCrimeParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getNonPaymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getOffencesParams() {
        MultiValuedMap<String, String> params = getOffencesMainHttpParams();
        FetcherUtils.addParams(params, ParamsConst.ICCS, OFFENCESES_TYPES);
        params.put(ParamsConst.UNIT, "NR");
        return params;
    }

    public static MultiValuedMap<String, String> getPensionPpsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SPDEPB, "TOTAL");
        params.put(ParamsConst.SPDEPM, "TOTAL");
        params.put(ParamsConst.UNIT, "PPS_HAB");
        return params;
    }

    public static MultiValuedMap<String, String> getSocialProtectionPpsParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.SPDEPS, "SPBENEFNOREROUTE");
        params.put(ParamsConst.UNIT, "PPS_HAB");
        return params;
    }

    public static MultiValuedMap<String, String> getUnexpectedParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.HHTYP, "TOTAL");
        params.put(ParamsConst.INC_GRP, "TOTAL");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    private static MultiValuedMap<String, String> getOffencesMainHttpParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put(ParamsConst.LANG, "en");

        if (EnvConst.IS_TESTING) {
            params.put(ParamsConst.GEO, "RO");
            params.put(ParamsConst.TIME, "2015");
        } else {
            FetcherUtils.addParams(params, ParamsConst.GEO, EU28_MEMBERS_EXTENDED);
        }

        return params;
    }
}
