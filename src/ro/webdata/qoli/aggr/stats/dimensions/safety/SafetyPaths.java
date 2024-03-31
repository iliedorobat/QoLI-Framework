package ro.webdata.qoli.aggr.stats.dimensions.safety;

import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.io.File;

public class SafetyPaths {
    public static final String SAFETY_FILE_NAME = "safety";

    public static final String CRIME_RATIO_FILE_NAME = "crimeRatio";
    public static final String NON_PAYMENT_RATIO_FILE_NAME = "unpaidRatio";
    public static final String OFFENCES_FILE_NAME = "offences";
    public static final String PENSION_PPS_FILE_NAME = "pensionRatio";
    public static final String SOCIAL_PROTECTION_RATIO_FILE_NAME = "socialProtectionRatio";
    public static final String UNEXPECTED_RATIO_FILE_NAME = "unexpectedRatio";

    public static final String SAFETY_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, SAFETY_FILE_NAME);

    private static String generatePath(String fileName) {
        return SAFETY_RAW_PATH + File.separator + fileName + Constants.JSON_EXTENSION;
    }

    public static final String
            CRIME_RATIO_PATH = generatePath(CRIME_RATIO_FILE_NAME),
            NON_PAYMENT_RATIO_PATH = generatePath(NON_PAYMENT_RATIO_FILE_NAME),
            OFFENCES_PATH = generatePath(OFFENCES_FILE_NAME),
            PENSION_PPS_PATH = generatePath(PENSION_PPS_FILE_NAME),
            SOCIAL_PROTECTION_PPS_PATH = generatePath(SOCIAL_PROTECTION_RATIO_FILE_NAME),
            UNEXPECTED_RATIO_PATH = generatePath(UNEXPECTED_RATIO_FILE_NAME);
}
