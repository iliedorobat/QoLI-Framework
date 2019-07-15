package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
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

        TextUtils.writeToJSONFile(activeCitizenship, FilePathConst.GOV_RIGHTS_PATH + FileNameConst.ACTIVE_CITIZENSHIP);
        TextUtils.writeToJSONFile(populationTrustRatio, FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST_RATIO);
        TextUtils.writeToJSONFile(employmentGap, FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT_GAP);
        TextUtils.writeToJSONFile(genderPayGap, FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP);
    }
}
