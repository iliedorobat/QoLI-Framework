package app.java.commons.dimensions.safety;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.data.fetch.FetcherUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class SafetyParams {
    public static MultiValuedMap<String, String> getCrimeParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getNonPaymentParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getOffencesParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.UNIT, "NR");
        }};
        FetcherUtils.addParams(params, ParamsConst.ICCS, ParamsValues.ICCS);
        return params;
    }

    public static MultiValuedMap<String, String> getOffencesParams(String offencesType) {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.ICCS, offencesType);
            put(ParamsConst.UNIT, "NR");
        }};
    }

    public static MultiValuedMap<String, String> getPensionPpsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SPDEPB, "TOTAL");
            put(ParamsConst.SPDEPM, "TOTAL");
            put(ParamsConst.UNIT, "PPS_HAB");
        }};
    }

    public static MultiValuedMap<String, String> getSocialProtectionPpsParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.SPDEPS, "SPBENEFNOREROUTE");
            put(ParamsConst.UNIT, "PPS_HAB");
        }};
    }

    public static MultiValuedMap<String, String> getUnexpectedParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.FREQ, "A");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.INC_GRP, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static final MultiValuedMap<String, String>
            CRIME_RATIO_PARAMS = getCrimeParams(),
            NON_PAYMENT_RATIO_PARAMS = getNonPaymentParams(),
            PENSION_PPS_PARAMS = getPensionPpsParams(),
            SOCIAL_PROTECTION_PPS_PARAMS = getSocialProtectionPpsParams(),
            UNEXPECTED_RATIO_PARAMS = getUnexpectedParams(),

            // UK = UKC-L + UKM + UKN (England and Wales + Scotland + Northern Ireland)
            OFFENCES_ASSAULT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("assault")),
            OFFENCES_ATTEMPTED_HOMICIDE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("attemptedHomicide")),
            OFFENCES_BURGLARY_PARAMS = getOffencesParams(ParamsValues.ICCS.get("burglary")),
            OFFENCES_BURGLARY_PRIVATE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("burglaryPrivate")),
            OFFENCES_HOMICIDE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("homicide")),
            OFFENCES_KIDNAPPING_PARAMS = getOffencesParams(ParamsValues.ICCS.get("kidnapping")),
            OFFENCES_RAPE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("rape")),
            OFFENCES_ROBBERY_PARAMS = getOffencesParams(ParamsValues.ICCS.get("robbery")),
            OFFENCES_SEXUAL_ASSAULT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("sexualAssault")),
            OFFENCES_SEXUAL_VIOLENCE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("sexualViolence")),
            OFFENCES_THEFT_PARAMS = getOffencesParams(ParamsValues.ICCS.get("theft")),
            OFFENCES_THEFT_VEHICLE_PARAMS = getOffencesParams(ParamsValues.ICCS.get("theftVehicle")),
            OFFENCES_UNLAWFUL_PARAMS = getOffencesParams(ParamsValues.ICCS.get("narcotics"));
}
