package app.java.data.collect;

import app.java.commons.Constants;
import app.java.commons.TextUtils;
import app.java.data.fetch.dao.GovRightsDAO;
import app.java.data.fetch.dao.impl.GovRightsDAOImpl;

public class GovRightsCollector {
    private static GovRightsDAO govRightsDAO = new GovRightsDAOImpl();

    public static void dataCollector() {
        StringBuilder activeCitizenship = govRightsDAO.getActiveCitizenship(),
                populationTrustRatio = govRightsDAO.getPopulationTrustRatio(),
                employmentGap = govRightsDAO.getEmploymentGap(),
                genderPayGap = govRightsDAO.getGenderPayGap();

        TextUtils.writeToJSONFile(activeCitizenship, Constants.GOV_RIGHTS_PATH + "activeCitizenship");
        TextUtils.writeToJSONFile(populationTrustRatio, Constants.GOV_RIGHTS_PATH + "populationTrustRatio");
        TextUtils.writeToJSONFile(employmentGap, Constants.GOV_RIGHTS_PATH + "employmentGap");
        TextUtils.writeToJSONFile(genderPayGap, Constants.GOV_RIGHTS_PATH + "genderPayGap");
    }
}
