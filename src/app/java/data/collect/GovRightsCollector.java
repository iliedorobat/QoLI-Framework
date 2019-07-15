package app.java.data.collect;

import app.java.data.fetch.dao.GovRightsDAO;
import app.java.data.fetch.dao.impl.GovRightsDAOImpl;

public class GovRightsCollector {
    private static GovRightsDAO govRightsDAO = new GovRightsDAOImpl();

    public static void dataCollector() {
        StringBuilder activeCitizenship = govRightsDAO.getActiveCitizenship(),
                populationTrustRatio = govRightsDAO.getPopulationTrustRatio(),
                employmentGap = govRightsDAO.getEmploymentGap(),
                genderPayGap = govRightsDAO.getGenderPayGap();
    }
}
