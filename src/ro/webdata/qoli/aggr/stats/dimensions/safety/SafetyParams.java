package ro.webdata.qoli.aggr.commons.dimensions.safety;

import ro.webdata.qoli.aggr.commons.constants.ParamsNames;
import ro.webdata.qoli.aggr.data.fetch.FetcherUtils;
import ro.webdata.qoli.aggr.commons.constants.ParamsValues;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SafetyParams {
    public static final MultiValuedMap<String, String> CRIME_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String> NON_PAYMENT_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    public static final MultiValuedMap<String, String>
            OFFENCES_PARAMS = getOffencesParams(ParamsValues.ICCS.values()),
            // UK = UKC-L + UKM + UKN (England and Wales + Scotland + Northern Ireland)
            OFFENCES_ASSAULT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("assault")),
            OFFENCES_ATTEMPTED_HOMICIDE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("attemptedHomicide")),
            OFFENCES_BRIBERY_PARAMS = getOffencesParams(ParamsValues.ICCS.get("bribery")),
            OFFENCES_BURGLARY_PARAMS = getOffencesParams(ParamsValues.ICCS.get("burglary")),
            OFFENCES_BURGLARY_PRIVATE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("burglaryPrivate")),
            OFFENCES_COMPUTERS_PARAMS = getOffencesParams(ParamsValues.ICCS.get("computers")),
            OFFENCES_CORRUPTION_PARAMS = getOffencesParams(ParamsValues.ICCS.get("corruption")),
            OFFENCES_CRIMINAL_GROUPS_PARAMS = getOffencesParams(ParamsValues.ICCS.get("criminalGroups")),
            OFFENCES_FRAUD_PARAMS = getOffencesParams(ParamsValues.ICCS.get("fraud")),
            OFFENCES_HOMICIDE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("homicide")),
            OFFENCES_KIDNAPPING_PARAMS = getOffencesParams(ParamsValues.ICCS.get("kidnapping")),
            OFFENCES_MONEY_LAUNDERING_PARAMS = getOffencesParams(ParamsValues.ICCS.get("moneyLaundering")),
            OFFENCES_NARCOTICS_PARAMS = getOffencesParams(ParamsValues.ICCS.get("narcotics")),
            OFFENCES_RAPE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("rape")),
            OFFENCES_ROBBERY_PARAMS = getOffencesParams(ParamsValues.ICCS.get("robbery")),
            OFFENCES_SEXUAL_ASSAULT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("sexualAssault")),
            OFFENCES_SEXUAL_VIOLENCE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("sexualViolence")),
            OFFENCES_SEXUAL_EXPLOITATION_PARAMS = getOffencesParams(ParamsValues.ICCS.get("sexualExploitation")),
            OFFENCES_THEFT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("theft")),
            OFFENCES_THEFT_VEHICLE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("theftVehicle"));

    public static final MultiValuedMap<String, String> PENSION_PPS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SPDEPB, "TOTAL");
        put(ParamsNames.SPDEPM, "TOTAL");
        put(ParamsNames.UNIT, "PPS_HAB");
    }};

    public static final MultiValuedMap<String, String> SOCIAL_PROTECTION_PPS_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.SPDEPS, "SPBENEFNOREROUTE");
        put(ParamsNames.UNIT, "PPS_HAB");
    }};

    public static final MultiValuedMap<String, String> UNEXPECTED_RATIO_PARAMS = new HashSetValuedHashMap<>() {{
        put(ParamsNames.FREQ, "A");
        put(ParamsNames.HHTYP, "TOTAL");
        put(ParamsNames.INC_GRP, "TOTAL");
        put(ParamsNames.UNIT, "PC");
    }};

    private static MultiValuedMap<String, String> getOffencesParams(String offence) {
        List<String> offences = new ArrayList<>() {{ add(offence); }};
        return getOffencesParams(offences);
    }

    private static MultiValuedMap<String, String> getOffencesParams(Collection<String> offences) {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsNames.FREQ, "A");
            put(ParamsNames.UNIT, "NR");
        }};
        FetcherUtils.addParams(params, ParamsNames.ICCS, offences);
        return params;
    }
}
