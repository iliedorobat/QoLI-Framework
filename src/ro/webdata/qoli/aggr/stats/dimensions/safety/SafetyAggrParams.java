package ro.webdata.qoli.aggr.stats.dimensions.safety;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SafetyAggrParams {
    public static final String SAFETY = "safety";

    public static final String CRIME_RATIO = "crimeRatio";
    public static final String NON_PAYMENT_RATIO = "unpaidRatio";
    public static final String OFFENCES_ASSAULT = "offencesAssault";
    public static final String OFFENCES_ATTEMPTED_HOMICIDE = "offencesAttemptedHomicide";
    public static final String OFFENCES_BRIBERY = "offencesBribery";
    public static final String OFFENCES_BURGLARY = "offencesBurglary";
    public static final String OFFENCES_BURGLARY_PRIVATE = "offencesBurglaryPrivate";
    public static final String OFFENCES_COMPUTERS = "offencesComputers";
    public static final String OFFENCES_CRIMINAL_GROUPS = "offencesCriminalGroups";
    public static final String OFFENCES_CORRUPTION = "offencesCorruption";
    public static final String OFFENCES_FRAUD = "offencesFraud";
    public static final String OFFENCES_HOMICIDE = "offencesHomicide";
    public static final String OFFENCES_KIDNAPPING = "offencesKidnapping";
    public static final String OFFENCES_MONEY_LAUNDERING = "offencesMoneyLaundering";
    public static final String OFFENCES_NARCOTICS = "offencesNarcotics";
    public static final String OFFENCES_RAPE = "offencesRape";
    public static final String OFFENCES_ROBBERY = "offencesRobbery";
    public static final String OFFENCES_SEXUAL_ASSAULT = "offencesSexualAssault";
    public static final String OFFENCES_SEXUAL_EXPLOITATION = "offencesSexualExploitation";
    public static final String OFFENCES_SEXUAL_VIOLENCE = "offencesSexualViolence";
    public static final String OFFENCES_THEFT = "offencesTheft";
    public static final String OFFENCES_THEFT_VEHICLE = "offencesTheftVehicle";
    public static final String OFFENCES_RATIO = "offencesRatio";
    public static final String PENSION_PPS_RATIO = "pensionPpsRatio";
    public static final String SOCIAL_PROTECTION_PPS_RATIO = "socialProtectionPpsRatio";
    public static final String UNEXPECTED_RATIO = "unexpectedRatio";

    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(CRIME_RATIO);
        add(PENSION_PPS_RATIO);
        add(SOCIAL_PROTECTION_PPS_RATIO);
        add(NON_PAYMENT_RATIO);
        add(OFFENCES_RATIO);
        add(UNEXPECTED_RATIO);
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(CRIME_RATIO, true);
        put(PENSION_PPS_RATIO, false);
        put(SOCIAL_PROTECTION_PPS_RATIO, false);
        put(NON_PAYMENT_RATIO, true);
        put(OFFENCES_RATIO, true);
        put(UNEXPECTED_RATIO, true);
    }};
}
