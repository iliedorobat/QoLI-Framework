package app.java.commons.dimensions.gov;

import app.java.commons.constants.ParamsNames;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class GovRightsParams {
    private static final String[] ACTIVITIES = {
            ParamsValues.ACL00_LEISURE.get("citizenship")
    };

    public static final MultiValuedMap<String, String> CITIZENSHIP_RATIO_PARAMS = AuxiliaryParams.getActivePeopleParams(ACTIVITIES);

    public static final MultiValuedMap<String, String> EMPLOYMENT_FEMALE_RATIO_PARAMS = getEmploymentParams("F");

    public static final MultiValuedMap<String, String> EMPLOYMENT_MALE_RATIO_PARAMS = getEmploymentParams("M");

    public static final MultiValuedMap<String, String> EMPLOYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y20-64");
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.INDIC_EM, "EMP_LFS");
        put(ParamsNames.SEX, "F");
        put(ParamsNames.SEX, "M");
        put(ParamsNames.UNIT, "PC_POP");
    }};

    public static final MultiValuedMap<String, String> GENDER_PAY_GAP_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.NACE_R2, "B-S_X_O");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POPULATION_TRUST_PARAMS = getPopulationTrustParams();

    public static final MultiValuedMap<String, String>
            POPULATION_LEGTST_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.INDIC_WB.get("legal")),
            POPULATION_PLCTST_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.INDIC_WB.get("police")),
            POPULATION_PLTTST_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.INDIC_WB.get("politic")),
            POPULATION_OTHERS_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.INDIC_WB.get("others"));


    private static MultiValuedMap<String, String> getEmploymentParams(String sex) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y20-64");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.INDIC_EM, "EMP_LFS");
            put(ParamsNames.SEX, sex);
            put(ParamsNames.UNIT, "PC_POP");
        }};
    }

    private static MultiValuedMap<String, String> getPopulationTrustParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "RTG");
        }};
        FetcherUtils.addParams(params, ParamsNames.INDIC_WB, ParamsValues.INDIC_WB);
        return params;
    }

    private static MultiValuedMap<String, String> getPopulationTrustParams(String trustSystem) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.INDIC_WB, trustSystem);
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "RTG");
        }};
    }
}
