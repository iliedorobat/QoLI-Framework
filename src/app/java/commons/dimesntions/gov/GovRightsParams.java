package app.java.commons.dimesntions.gov;

import app.java.commons.constants.ParamsConst;
import app.java.data.fetch.FetcherUtils;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;

public class GovRightsParams {
    public static final String[] POPULATION_TRUST = {
            "LEGTST", // legal system
            "PLCTST", // police
            "PLTTST"  // political system
    };

    public static MultiValuedMap<String, String> getActiveCitizenshipParams() {
        String[] activities = {"AC43A"};
        return CommonParams.getActivePeopleParams(activities);
    }

    public static MultiValuedMap<String, String> getEmploymentParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y20-64");
        params.put(ParamsConst.INDIC_EM, "EMP_LFS");
        params.put(ParamsConst.SEX, "F");
        params.put(ParamsConst.SEX, "M");
        params.put(ParamsConst.UNIT, "PC_POP");
        return params;
    }

    public static MultiValuedMap<String, String> getGenderPayGapParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.NACE_R2, "B-S_X_O");
        params.put(ParamsConst.UNIT, "PC");
        return params;
    }

    public static MultiValuedMap<String, String> getPopulationTrustParams() {
        MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
        params.put(ParamsConst.AGE, "Y_GE16");
        FetcherUtils.addParams(params, ParamsConst.AIR_POLLUTION, POPULATION_TRUST);
        params.put(ParamsConst.ISCED_11, "TOTAL");
        params.put(ParamsConst.SEX, "T");
        params.put(ParamsConst.UNIT, "RTG");
        return params;
    }
}
