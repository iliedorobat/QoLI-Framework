package app.java.commons.dimensions.gov;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.common.CommonParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class GovRightsParams {
    public static MultiValuedMap<String, String> getCitizenshipParams() {
        String[] activities = {
                ParamsValues.ACL00_LEISURE.get("citizenship")
        };
        return CommonParams.getActivePeopleParams(activities);
    }

    public static MultiValuedMap<String, String> getEmploymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y20-64");
            put(ParamsConst.INDIC_EM, "EMP_LFS");
            put(ParamsConst.SEX, ParamsValues.SEX.get("female"));
            put(ParamsConst.SEX, ParamsValues.SEX.get("male"));
            put(ParamsConst.UNIT, "PC_POP");
        }};
    }

    public static MultiValuedMap<String, String> getEmploymentParams(String sex) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y20-64");
            put(ParamsConst.INDIC_EM, "EMP_LFS");
            put(ParamsConst.SEX, sex);
            put(ParamsConst.UNIT, "PC_POP");
        }};
    }

    public static MultiValuedMap<String, String> getGenderPayGapParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.NACE_R2, "B-S_X_O");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getPopulationTrustParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, ParamsValues.SEX.get("total"));
            put(ParamsConst.UNIT, "RTG");
        }};
        FetcherUtils.addParams(params, ParamsConst.INDIC_WB, ParamsValues.INDIC_WB);
        return params;
    }

    public static MultiValuedMap<String, String> getPopulationTrustParams(String trustSystem) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.AGE, "Y_GE16");
            put(ParamsConst.INDIC_WB, trustSystem);
            put(ParamsConst.ISCED_11, "TOTAL");
            put(ParamsConst.SEX, ParamsValues.SEX.get("total"));
            put(ParamsConst.UNIT, "RTG");
        }};
    }
}
