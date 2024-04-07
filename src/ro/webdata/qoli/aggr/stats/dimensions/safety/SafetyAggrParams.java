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

    public static final Map<String, String> IND_PARAMS_LABELS = new HashMap<>() {{
        put(CRIME_RATIO, "Crime Ratio");
        put(NON_PAYMENT_RATIO, "Non Payment Ratio");
        put(OFFENCES_ASSAULT, "Police-recorded Offences - Assaults");
        put(OFFENCES_ATTEMPTED_HOMICIDE, "Police-recorded Offences - Attempted International Homicide");
        put(OFFENCES_BRIBERY, "Police-recorded Offences - Bribery");
        put(OFFENCES_BURGLARY, "Police-recorded Offences - Burglary");
        put(OFFENCES_BURGLARY_PRIVATE, "Police-recorded Offences - Burglary of Private Residential Premises");
        put(OFFENCES_COMPUTERS, "Police-recorded Offences - Acts Against Computer Systems");
        put(OFFENCES_CRIMINAL_GROUPS, "Police-recorded Offences - Participation in an Organized Criminal Group");
        put(OFFENCES_CORRUPTION, "Police-recorded Offences - Corruption");
        put(OFFENCES_FRAUD, "Police-recorded Offences - Fraud");
        put(OFFENCES_HOMICIDE, "Police-recorded Offences - International Homicide");
        put(OFFENCES_KIDNAPPING, "Police-recorded Offences - Kidnapping");
        put(OFFENCES_MONEY_LAUNDERING, "Police-recorded Offences - Money Laundering");
        put(OFFENCES_NARCOTICS, "Police-recorded Offences - Narcotics");
        put(OFFENCES_RAPE, "Police-recorded Offences - Rape");
        put(OFFENCES_ROBBERY, "Police-recorded Offences - Robbery");
        put(OFFENCES_SEXUAL_ASSAULT, "Police-recorded Offences - Sexual Assault");
        put(OFFENCES_SEXUAL_EXPLOITATION, "Police-recorded Offences - Sexual Exploitation");
        put(OFFENCES_SEXUAL_VIOLENCE, "Police-recorded Offences - Sexual Violence");
        put(OFFENCES_THEFT, "Police-recorded Offences - Theft");
        put(OFFENCES_THEFT_VEHICLE, "Police-recorded Offences - Theft of a Motorized Vehicle or Parts of Thereof");
        put(PENSION_PPS_RATIO, "Pension in PPS Ratio");
        put(SOCIAL_PROTECTION_PPS_RATIO, "Social Protection in PPS Ratio");
        put(UNEXPECTED_RATIO, "Unexpected Ratio");
    }};

    public static final Map<String, String> IND_PARAMS_UNITS = new HashMap<>() {{
        put(CRIME_RATIO, PERCENT);
        put(NON_PAYMENT_RATIO, PERCENT);
        put(OFFENCES_ASSAULT, NUMBER);
        put(OFFENCES_ATTEMPTED_HOMICIDE, NUMBER);
        put(OFFENCES_BRIBERY, NUMBER);
        put(OFFENCES_BURGLARY, NUMBER);
        put(OFFENCES_BURGLARY_PRIVATE, NUMBER);
        put(OFFENCES_COMPUTERS, NUMBER);
        put(OFFENCES_CRIMINAL_GROUPS, NUMBER);
        put(OFFENCES_CORRUPTION, NUMBER);
        put(OFFENCES_FRAUD, NUMBER);
        put(OFFENCES_HOMICIDE, NUMBER);
        put(OFFENCES_KIDNAPPING, NUMBER);
        put(OFFENCES_MONEY_LAUNDERING, NUMBER);
        put(OFFENCES_NARCOTICS, NUMBER);
        put(OFFENCES_RAPE, NUMBER);
        put(OFFENCES_ROBBERY, NUMBER);
        put(OFFENCES_SEXUAL_ASSAULT, NUMBER);
        put(OFFENCES_SEXUAL_EXPLOITATION, NUMBER);
        put(OFFENCES_SEXUAL_VIOLENCE, NUMBER);
        put(OFFENCES_THEFT, NUMBER);
        put(OFFENCES_THEFT_VEHICLE, NUMBER);
        put(PENSION_PPS_RATIO, PPS_PER_INHABITANT);
        put(SOCIAL_PROTECTION_PPS_RATIO, PPS_PER_INHABITANT);
        put(UNEXPECTED_RATIO, PERCENT);
    }};

    public static final List<String> IND_PARAMS = List.copyOf(IND_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> IND_REVERSED_STATE = new HashMap<>() {{
        put(CRIME_RATIO, true);
        put(NON_PAYMENT_RATIO, true);
        put(OFFENCES_ASSAULT, true);
        put(OFFENCES_ATTEMPTED_HOMICIDE, true);
        put(OFFENCES_BRIBERY, true);
        put(OFFENCES_BURGLARY, true);
        put(OFFENCES_BURGLARY_PRIVATE, true);
        put(OFFENCES_COMPUTERS, true);
        put(OFFENCES_CRIMINAL_GROUPS, true);
        put(OFFENCES_CORRUPTION, true);
        put(OFFENCES_FRAUD, true);
        put(OFFENCES_HOMICIDE, true);
        put(OFFENCES_KIDNAPPING, true);
        put(OFFENCES_MONEY_LAUNDERING, true);
        put(OFFENCES_NARCOTICS, true);
        put(OFFENCES_RAPE, true);
        put(OFFENCES_ROBBERY, true);
        put(OFFENCES_SEXUAL_ASSAULT, true);
        put(OFFENCES_SEXUAL_EXPLOITATION, true);
        put(OFFENCES_SEXUAL_VIOLENCE, true);
        put(OFFENCES_THEFT, true);
        put(OFFENCES_THEFT_VEHICLE, true);
        put(PENSION_PPS_RATIO, false);
        put(SOCIAL_PROTECTION_PPS_RATIO, false);
        put(UNEXPECTED_RATIO, true);
    }};
}
