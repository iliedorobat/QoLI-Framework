package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.SafetyStatsDAO;

import java.util.Map;

public class SafetyStatsImpl implements SafetyStatsDAO {
    // The lists of queried values
    private static final String[]
            CRIME_RATIO = {"TOTAL", "TOTAL", "PC"},
            OFFENCES = {},
            PENSION_RATIO = {"TOTAL", "TOTAL", "PPS_HAB"},
            SOCIAL_PROTECTION_RATIO = {"SPBENEFNOREROUTE", "PPS_HAB"},
            UNEXPECTED_RATIO = {"TOTAL", "TOTAL", "PC"},
            UNPAID_RATIO = {"TOTAL", "TOTAL", "PC"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            crimeRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO + JSON_EXT,
            offencesPath = FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES + JSON_EXT,
            pensionRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.PENSION_RATIO + JSON_EXT,
            socialProtectionRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO + JSON_EXT,
            unexpectedRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO + JSON_EXT,
            unpaidRatioPath = FilePathConst.SAFETY_PATH + FileNameConst.UNPAID_RATIO + JSON_EXT;

    private static final Map<String, Number>
            crimeRatio = MeasureUtils.consolidateList(CRIME_RATIO, crimeRatioPath),
            //TODO: consolidate all the offences types
//            offences = MeasureUtils.consolidateList(OFFENCES, offencesPath),
            pensionRatio = MeasureUtils.consolidateList(PENSION_RATIO, pensionRatioPath),
            socialProtectionRatio = MeasureUtils.consolidateList(SOCIAL_PROTECTION_RATIO, socialProtectionRatioPath),
            unexpectedRatio = MeasureUtils.consolidateList(UNEXPECTED_RATIO, unexpectedRatioPath),
            unpaidRatio = MeasureUtils.consolidateList(UNPAID_RATIO, unpaidRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(unpaidRatioPath);
    }
}
