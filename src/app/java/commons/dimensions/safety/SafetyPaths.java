package app.java.commons.dimensions.safety;

import app.java.commons.constants.FilePathConst;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class SafetyPaths {
    public static final String SAFETY_FILE_NAME = "safety";

    public static final String CRIME_RATIO_FILE_NAME = "crimeRatio";
    public static final String NON_PAYMENT_RATIO_FILE_NAME = "unpaidRatio";
    public static final String OFFENCES_FILE_NAME = "offences";
    public static final String OFFENCES_ASSAULT_FILE_NAME = "offencesAssault";
    public static final String OFFENCES_ATTEMPTED_HOMICIDE_FILE_NAME = "offencesAttemptedHomicide";
    public static final String OFFENCES_BRIBERY_FILE_NAME = "offencesBribery";
    public static final String OFFENCES_BURGLARY_FILE_NAME = "offencesBurglary";
    public static final String OFFENCES_BURGLARY_PRIVATE_FILE_NAME = "offencesBurglaryPrivate";
    public static final String OFFENCES_COMPUTERS_FILE_NAME = "offencesComputers";
    public static final String OFFENCES_CRIMINAL_GROUPS_FILE_NAME = "offencesCriminalGroups";
    public static final String OFFENCES_CORRUPTION_FILE_NAME = "offencesCorruption";
    public static final String OFFENCES_FRAUD_FILE_NAME = "offencesFraud";
    public static final String OFFENCES_HOMICIDE_FILE_NAME = "offencesHomicide";
    public static final String OFFENCES_KIDNAPPING_FILE_NAME = "offencesKidnapping";
    public static final String OFFENCES_MONEY_LAUNDERING_FILE_NAME = "offencesMoneyLaundering";
    public static final String OFFENCES_NARCOTICS_FILE_NAME = "offencesNarcotics";
    public static final String OFFENCES_RAPE_FILE_NAME = "offencesRape";
    public static final String OFFENCES_ROBBERY_FILE_NAME = "offencesRobbery";
    public static final String OFFENCES_SEXUAL_ASSAULT_FILE_NAME = "offencesSexualAssault";
    public static final String OFFENCES_SEXUAL_EXPLOITATION_FILE_NAME = "offencesSexualExploitation";
    public static final String OFFENCES_SEXUAL_VIOLENCE_FILE_NAME = "offencesSexualViolence";
    public static final String OFFENCES_THEFT_FILE_NAME = "offencesTheft";
    public static final String OFFENCES_THEFT_VEHICLE_FILE_NAME = "offencesTheftVehicle";
    public static final String PENSION_PPS_FILE_NAME = "pensionRatio";
    public static final String SOCIAL_PROTECTION_RATIO_FILE_NAME = "socialProtectionRatio";
    public static final String UNEXPECTED_RATIO_FILE_NAME = "unexpectedRatio";

    public static final String SAFETY_RAW_PATH = String.join(File.separator, FilePathConst.RAW_DATASET_PATH, SAFETY_FILE_NAME);

    private static String generatePath(String fileName) {
        return SAFETY_RAW_PATH + File.separator + fileName + JSON_EXTENSION;
    }

    public static final String
            CRIME_RATIO_PATH = generatePath(CRIME_RATIO_FILE_NAME),
            NON_PAYMENT_RATIO_PATH = generatePath(NON_PAYMENT_RATIO_FILE_NAME),
            OFFENCES_PATH = generatePath(OFFENCES_FILE_NAME),
            PENSION_PPS_PATH = generatePath(PENSION_PPS_FILE_NAME),
            SOCIAL_PROTECTION_PPS_PATH = generatePath(SOCIAL_PROTECTION_RATIO_FILE_NAME),
            UNEXPECTED_RATIO_PATH = generatePath(UNEXPECTED_RATIO_FILE_NAME);
}
