package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.GovRightsStatsDAO;

import java.util.Map;

//TODO: voter turnout parser
public class GovRightsStatsImpl implements GovRightsStatsDAO {
    // The lists of queried values
    private static final String[]
            ACTIVE_CITIZENSHIP = {"TOTAL", "AC43A", "Y_GE16", "T", "PC"},
            EMPLOYMENT_GAP = {"Y20-64", "PC_POP", "F", "EMP_LFS"},
            GENDER_PAY_GAP = {"PC", "B-S_X_O"},
            POPULATION_LEGTST_TRUST_RATIO = {"RTG", "TOTAL", "LEGTST", "T", "Y_GE16"},
            POPULATION_PLCTST_TRUST_RATIO = {"RTG", "TOTAL", "PLCTST", "T", "Y_GE16"},
            POPULATION_PLTTST_TRUST_RATIO = {"RTG", "TOTAL", "PLTTST", "T", "Y_GE16"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String CSV_EXT = Constants.CSV_EXTENSION;
    private static final String
            activeCitizenshipPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.ACTIVE_CITIZENSHIP + JSON_EXT,
            employmentGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.EMPLOYMENT_GAP + JSON_EXT,
            genderPayGapPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.GENDER_PAY_GAP + JSON_EXT,
            populationTrustRatioPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.POPULATION_TRUST_RATIO + JSON_EXT,
            voterTurnoutPath = FilePathConst.GOV_RIGHTS_PATH + FileNameConst.VOTER_TURNOUT + CSV_EXT;

    private static final Map<String, Number>
            activeCitizenship = MeasureUtils.consolidateList(ACTIVE_CITIZENSHIP, activeCitizenshipPath),
            employmentGap = MeasureUtils.consolidateList(EMPLOYMENT_GAP, employmentGapPath),
            genderPayGap = MeasureUtils.consolidateList(GENDER_PAY_GAP, genderPayGapPath),
            populationLegtstTrustRatio = MeasureUtils.consolidateList(POPULATION_LEGTST_TRUST_RATIO, populationTrustRatioPath),
            populationPlctstTrustRatio = MeasureUtils.consolidateList(POPULATION_PLCTST_TRUST_RATIO, populationTrustRatioPath),
            populationPlttstTrustRatio = MeasureUtils.consolidateList(POPULATION_PLTTST_TRUST_RATIO, populationTrustRatioPath);

    public void print() {
        System.out.println(populationPlttstTrustRatio);
//        MeasureUtils.print(populationTrustRatioPath);
    }
}
