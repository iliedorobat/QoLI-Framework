package ro.webdata.qoli.aggr.stats.dimensions.gov;

import ro.webdata.qoli.aggr.stats.constants.ParamsNames;
import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryParams;
import ro.webdata.qoli.aggr.data.fetch.FetcherUtils;
import ro.webdata.qoli.aggr.stats.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GovRightsParams {
    private static final String[] ACTIVITIES = {
            ParamsValues.ACL00.get("citizenship")
    };
    private static final String[] DOMAIN_TRUST = {
            ParamsValues.DOMAIN.get("legal"),
            ParamsValues.DOMAIN.get("police"),
            ParamsValues.DOMAIN.get("politic")
    };
    private static final String[] DOMAIN_TRUST_OTHER = {
            ParamsValues.DOMAIN.get("other")
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

    public static final MultiValuedMap<String, String> POPULATION_TRUST_PARAMS = getPopulationTrustParams(List.of(DOMAIN_TRUST));

    public static final MultiValuedMap<String, String> POPULATION_TRUST_OTHER_PARAMS = getPopulationTrustParams(List.of(DOMAIN_TRUST_OTHER));

    public static final MultiValuedMap<String, String>
            POPULATION_LEGAL_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.DOMAIN.get("legal")),
            POPULATION_POLICE_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.DOMAIN.get("police")),
            POPULATION_POLITIC_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.DOMAIN.get("politic")),
            POPULATION_OTHER_TRUST_PARAMS = getPopulationTrustParams(ParamsValues.DOMAIN.get("other"));

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
        FetcherUtils.addParams(params, ParamsNames.DOMAIN, trustSystems);
        return params;
    }
}
