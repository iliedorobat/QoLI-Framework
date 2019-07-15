package app.java.data.collect;

import app.java.commons.Constants;
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

        TextUtils.writeToJSONFile(pensionRatio, Constants.SAFETY_PATH + "pensionRatio");
        TextUtils.writeToJSONFile(socialProtectionRatio, Constants.SAFETY_PATH + "socialProtectionRatio");
        TextUtils.writeToJSONFile(unexpectedRatio, Constants.SAFETY_PATH + "unexpectedRatio");
        TextUtils.writeToJSONFile(unpaidRatio, Constants.SAFETY_PATH + "unpaidRatio");
        TextUtils.writeToJSONFile(offences, Constants.SAFETY_PATH + "offences");
        TextUtils.writeToJSONFile(crimeRatio, Constants.SAFETY_PATH + "crimeRatio");
    }
}
