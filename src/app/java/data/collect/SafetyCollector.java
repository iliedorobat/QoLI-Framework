package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.TextUtils;
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

        TextUtils.writeToJSONFile(pensionRatio, FilePathConst.SAFETY_PATH + FileNameConst.PENSION_RATIO);
        TextUtils.writeToJSONFile(socialProtectionRatio, FilePathConst.SAFETY_PATH + FileNameConst.SOCIAL_PROTECTION_RATIO);
        TextUtils.writeToJSONFile(unexpectedRatio, FilePathConst.SAFETY_PATH + FileNameConst.UNEXPECTED_RATIO);
        TextUtils.writeToJSONFile(unpaidRatio, FilePathConst.SAFETY_PATH + FileNameConst.UNPAID_RATIO);
        TextUtils.writeToJSONFile(offences, FilePathConst.SAFETY_PATH + FileNameConst.OFFENCES);
        TextUtils.writeToJSONFile(crimeRatio, FilePathConst.SAFETY_PATH + FileNameConst.CRIME_RATIO);
    }
}
