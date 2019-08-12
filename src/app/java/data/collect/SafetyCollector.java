package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.SafetyDAO;
import app.java.data.fetch.dao.impl.SafetyDAOImpl;

public class SafetyCollector {
    private static SafetyDAO safetyDAO = new SafetyDAOImpl();

    public static void dataCollector() {
        StringBuilder pensionRatio = safetyDAO.getPensionRatio(),
                socialProtectionRatio = safetyDAO.getSocialProtectionRatio(),
                unexpectedRatio = safetyDAO.getUnexpectedRatio(),
                unpaidRatio = safetyDAO.getUnpaidRatio(),
                offences = safetyDAO.getOffences(),
                crimeRatio = safetyDAO.getCrimeRatio();

        FileUtils.writeToJSONFile(pensionRatio, FilePathConst.SAFETY_PATH + FileNameConst.PENSION_RATIO);
        FileUtils.writeToJSONFile(socialProtectionRatio, FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO);
        FileUtils.writeToJSONFile(unexpectedRatio, FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO);
        FileUtils.writeToJSONFile(unpaidRatio, FilePathConst.SAFETY_PATH + FileNameConst.UNPAID_RATIO);
        FileUtils.writeToJSONFile(offences, FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES);
        FileUtils.writeToJSONFile(crimeRatio, FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO);
    }
}
