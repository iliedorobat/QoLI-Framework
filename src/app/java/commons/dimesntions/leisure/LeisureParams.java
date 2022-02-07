package app.java.commons.dimesntions.leisure;

import app.java.commons.constants.ParamsConst;
import app.java.commons.constants.ParamsValues;
import app.java.commons.dimesntions.common.CommonParams;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_LEVELS_PARAMS;
import static app.java.commons.dimesntions.common.CommonParams.SATISFACTION_TYPES_PARAMS;

public class LeisureParams {
    public static final String[] VOLUNTARY_ACTIVITIES = {
            ParamsValues.ACL00_LEISURE.get("formal"),
            ParamsValues.ACL00_LEISURE.get("informal")
    };

    public static MultiValuedMap<String, String> getSocialActivitiesParams() {
        return new HashSetValuedHashMap<>() {{
            put(ParamsConst.DEG_URB, "TOTAL");
            put(ParamsConst.FREQUENCY, "GE1");
            put(ParamsConst.HHTYP, "TOTAL");
            put(ParamsConst.QUANTILE, "TOTAL");
            put(ParamsConst.UNIT, "PC");
        }};
    }

    public static MultiValuedMap<String, String> getTimeSpentSatisfactionParams() {
        return CommonParams.getSatisfactionParams(
                SATISFACTION_LEVELS_PARAMS.get("HIGH"),
                SATISFACTION_TYPES_PARAMS.get("TIME_SPENT")
        );
    }

    public static MultiValuedMap<String, String> getVoluntaryActivitiesParams() {
        return CommonParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES);
    }

    public static MultiValuedMap<String, String> getVoluntaryActivitiesParams(String activity) {
        String[] VOLUNTARY_ACTIVITIES = { activity };
        return CommonParams.getActivePeopleParams(VOLUNTARY_ACTIVITIES);
    }
}
