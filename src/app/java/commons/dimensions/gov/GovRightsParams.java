package app.java.commons.dimensions.gov;

import app.java.commons.constants.ParamsNames;
import app.java.commons.dimensions.auxiliary.AuxiliaryParams;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static app.java.commons.constants.ParamsValues.ACL00;
import static app.java.commons.constants.ParamsValues.INDIC_WB;

public class GovRightsParams {
    private static final String[] ACTIVITIES = {
            ACL00.get("citizenship")
    };

    public static final MultiValuedMap<String, String> CITIZENSHIP_RATIO_PARAMS = AuxiliaryParams.getActivePeopleParams(ACTIVITIES);

    public static final MultiValuedMap<String, String> GENDER_EMP_GAP_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.AGE, "Y20-64");
        put(ParamsNames.INDIC_EM, "EMP_LFS");   // Total employment (resident population concept - LFS)
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.UNIT, "PC_POP");
    }};

    public static final MultiValuedMap<String, String> GENDER_PAY_GAP_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.NACE_R2, "B-S_X_O");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> POPULATION_TRUST_PARAMS = getPopulationTrustParams(INDIC_WB.values());

    public static final MultiValuedMap<String, String>
            POPULATION_LEGTST_TRUST_PARAMS = getPopulationTrustParams(INDIC_WB.get("legal")),
            POPULATION_PLCTST_TRUST_PARAMS = getPopulationTrustParams(INDIC_WB.get("police")),
            POPULATION_PLTTST_TRUST_PARAMS = getPopulationTrustParams(INDIC_WB.get("politic")),
            POPULATION_OTHERS_TRUST_PARAMS = getPopulationTrustParams(INDIC_WB.get("others"));

    private static MultiValuedMap<String, String> getPopulationTrustParams(String trustSystem) {
        List<String> trustSystems = new ArrayList<>() {{ add(trustSystem); }};
        return getPopulationTrustParams(trustSystems);
    }

    private static MultiValuedMap<String, String> getPopulationTrustParams(Collection<String> trustSystems) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.AGE, "Y_GE16");
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.ISCED_11, "TOTAL");
            put(ParamsNames.SEX, "T");
            put(ParamsNames.UNIT, "RTG");
        }};
        FetcherUtils.addParams(params, ParamsNames.INDIC_WB, trustSystems);
        return params;
    }
}
