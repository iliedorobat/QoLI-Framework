package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.dao.GovRightsDAO;
import app.java.data.fetch.dao.impl.GovRightsDAOImpl;

public class GovRightsCollector {
    private static final GovRightsDAO govRightsDAO = new GovRightsDAOImpl();

    public static void dataCollector() {
        StringBuilder
                activeCitizenship = govRightsDAO.getActiveCitizenship(),
                employmentGap = govRightsDAO.getEmploymentRatio(),
                genderPayGap = govRightsDAO.getGenderPayGap(),
                populationTrustRatio = govRightsDAO.getPopulationTrust();

        FileUtils.writeToJSONFile(activeCitizenship, FilePathConst.GOV_RIGHTS_PATH, FileNameConst.ACTIVE_CITIZENSHIP);
        FileUtils.writeToJSONFile(employmentGap, FilePathConst.GOV_RIGHTS_PATH, FileNameConst.EMPLOYMENT);
        FileUtils.writeToJSONFile(genderPayGap, FilePathConst.GOV_RIGHTS_PATH, FileNameConst.GENDER_PAY_GAP);
        FileUtils.writeToJSONFile(populationTrustRatio, FilePathConst.GOV_RIGHTS_PATH, FileNameConst.POPULATION_TRUST);
    }
}
