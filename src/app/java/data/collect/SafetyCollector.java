package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.SafetyDAO;
import app.java.data.fetch.dao.impl.SafetyDAOImpl;

public class SafetyCollector {
    private static final SafetyDAO safetyDAO = new SafetyDAOImpl();

    public static void dataCollector() {
        StringBuilder
                crimeRatio = safetyDAO.getCrimeRatio(),
                offences = safetyDAO.getOffences(),
                pensionRatio = safetyDAO.getPensionPps(),
                socialProtectionRatio = safetyDAO.getSocialProtectionPps(),
                unexpectedRatio = safetyDAO.getUnexpectedRatio(),
                unpaidRatio = safetyDAO.getNonPaymentRatio();

        FileUtils.writeToJSONFile(crimeRatio, FilePathConst.SAFETY_PATH, FileNameConst.CRIME_RATIO);
        FileUtils.writeToJSONFile(offences, FilePathConst.SAFETY_PATH, FileNameConst.OFFENCES);
        FileUtils.writeToJSONFile(pensionRatio, FilePathConst.SAFETY_PATH, FileNameConst.PENSION_PPS);
        FileUtils.writeToJSONFile(socialProtectionRatio, FilePathConst.SAFETY_PATH, FileNameConst.SOCIAL_PROTECTION_RATIO);
        FileUtils.writeToJSONFile(unexpectedRatio, FilePathConst.SAFETY_PATH, FileNameConst.UNEXPECTED_RATIO);
        FileUtils.writeToJSONFile(unpaidRatio, FilePathConst.SAFETY_PATH, FileNameConst.NON_PAYMENT_RATIO);
    }
}
