package ro.webdata.qoli.aggr.stats.dimensions.safety;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static final Map<String, String> ALLOWED_PARAM_LABELS = new HashMap<>() {{
        put(CRIME_RATIO, "Crime Ratio");
        put(PENSION_PPS_RATIO, "Pension in PPS Ratio");
        put(NON_PAYMENT_RATIO, "Non Payment Ratio");
        put(OFFENCES_RATIO, "Total Offences Ratio");
        put(SOCIAL_PROTECTION_PPS_RATIO, "Social Protection in PPS Ratio");
        put(UNEXPECTED_RATIO, "Unexpected Ratio");
    }};

    public static final List<String> ALLOWED_PARAMS = List.copyOf(ALLOWED_PARAM_LABELS.keySet());

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(CRIME_RATIO, true);
        put(NON_PAYMENT_RATIO, true);
        put(OFFENCES_RATIO, true);
        put(PENSION_PPS_RATIO, false);
        put(SOCIAL_PROTECTION_PPS_RATIO, false);
        put(UNEXPECTED_RATIO, true);
    }};
}
