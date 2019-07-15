package app.java.data.collect;

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
    }
}
