package ro.webdata.qoli.aggr.stats.dimensions.safety;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.*;

public class SafetyAggrParams {
    public static final String SAFETY = "safety";

    public static final String CRIME_RATIO = SAFETY + ":crimeRatio";
    public static final String NON_PAYMENT_RATIO = SAFETY + ":unpaidRatio";
    public static final String OFFENCES_ASSAULT = SAFETY + ":offencesAssault";
    public static final String OFFENCES_ATTEMPTED_HOMICIDE = SAFETY + ":offencesAttemptedHomicide";
    public static final String OFFENCES_BRIBERY = SAFETY + ":offencesBribery";
    public static final String OFFENCES_BURGLARY = SAFETY + ":offencesBurglary";
    public static final String OFFENCES_BURGLARY_PRIVATE = SAFETY + ":offencesBurglaryPrivate";
    public static final String OFFENCES_COMPUTERS = SAFETY + ":offencesComputers";
    public static final String OFFENCES_CRIMINAL_GROUPS = SAFETY + ":offencesCriminalGroups";
    public static final String OFFENCES_CORRUPTION = SAFETY + ":offencesCorruption";
    public static final String OFFENCES_FRAUD = SAFETY + ":offencesFraud";
    public static final String OFFENCES_HOMICIDE = SAFETY + ":offencesHomicide";
    public static final String OFFENCES_KIDNAPPING = SAFETY + ":offencesKidnapping";
    public static final String OFFENCES_MONEY_LAUNDERING = SAFETY + ":offencesMoneyLaundering";
    public static final String OFFENCES_NARCOTICS = SAFETY + ":offencesNarcotics";
    public static final String OFFENCES_RAPE = SAFETY + ":offencesRape";
    public static final String OFFENCES_ROBBERY = SAFETY + ":offencesRobbery";
    public static final String OFFENCES_SEXUAL_ASSAULT = SAFETY + ":offencesSexualAssault";
    public static final String OFFENCES_SEXUAL_EXPLOITATION = SAFETY + ":offencesSexualExploitation";
    public static final String OFFENCES_SEXUAL_VIOLENCE = SAFETY + ":offencesSexualViolence";
    public static final String OFFENCES_THEFT = SAFETY + ":offencesTheft";
    public static final String OFFENCES_THEFT_VEHICLE = SAFETY + ":offencesTheftVehicle";
    public static final String OFFENCES_RATIO = SAFETY + ":offencesRatio";
    public static final String PENSION_PPS_RATIO = SAFETY + ":pensionPpsRatio";
    public static final String SOCIAL_PROTECTION_PPS_RATIO = SAFETY + ":socialProtectionPpsRatio";
    public static final String UNEXPECTED_RATIO = SAFETY + ":unexpectedRatio";

    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(CRIME_RATIO, "Crime Ratio");
        put(NON_PAYMENT_RATIO, "Non Payment Ratio");
        put(OFFENCES_RATIO, "Total Offences Ratio");
        put(PENSION_PPS_RATIO, "Pension in PPS Ratio");
        put(SOCIAL_PROTECTION_PPS_RATIO, "Social Protection in PPS Ratio");
        put(UNEXPECTED_RATIO, "Unexpected Ratio");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(CRIME_RATIO, PERCENT);
        put(NON_PAYMENT_RATIO, PERCENT);
        put(OFFENCES_RATIO, PER_HUNDRED_INHABITANTS);
        put(PENSION_PPS_RATIO, PPS_PER_INHABITANT);
        put(SOCIAL_PROTECTION_PPS_RATIO, PPS_PER_INHABITANT);
        put(UNEXPECTED_RATIO, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
        put(CRIME_RATIO, true);
        put(NON_PAYMENT_RATIO, true);
        put(OFFENCES_RATIO, true);
        put(PENSION_PPS_RATIO, false);
        put(SOCIAL_PROTECTION_PPS_RATIO, false);
        put(UNEXPECTED_RATIO, true);
    }};
}
