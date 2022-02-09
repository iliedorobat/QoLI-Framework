package app.java.commons.dimesntions.safety;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class SafetyParams {
    public static MultiValuedMap<String, String> getCrimeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getNonPaymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getOffencesParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.UNIT, "NR");
        }};
        FetcherUtils.addParams(params, ParamsConst.ICCS, ParamsValues.ICCS);
        return params;
    }

    public static MultiValuedMap<String, String> getOffencesParams(String offencesType) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.ICCS, offencesType);
            put(ParamsConst.UNIT, "NR");
        }};
    }

    public static MultiValuedMap<String, String> getPensionPpsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.SPDEPB, "TOTAL");
            put(ParamsConst.SPDEPM, "TOTAL");
            put(ParamsConst.UNIT, "PPS_HAB");
        }};
    }

    public static MultiValuedMap<String, String> getSocialProtectionPpsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.SPDEPS, "SPBENEFNOREROUTE");
            put(ParamsConst.UNIT, "PPS_HAB");
        }};
    }

    public static MultiValuedMap<String, String> getUnexpectedParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }
}
