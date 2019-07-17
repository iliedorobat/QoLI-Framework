package app.java.data.measurement.dao.impl;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.EducationStatsDAO;

import java.util.Map;

public class EducationStatsImpl implements EducationStatsDAO {
    // The lists of queried values
    private static final String[]
            DIGITAL_SKILLS = {"I_DSK_BAB", "IND_TOTAL", "PC_IND"},
            EARLY_EDUCATION_RATIO = {"T", "PC"},
            EDUCATION_RATIO = {"PC", "T", "ED5-8", "Y15-64"},
            EXCLUDED_RATIO = {"T", "Y18-24", "NO_FED_NFE", "NEMP", "PC"},
            LEAVERS_RATIO = {"T", "POP", "Y18-24", "PC"},
            PUPILS_RATIO_2012 = {"ST1_1"},
            PUPILS_RATIO_2013 = {"RT", "ED1-3"},
            TRAINING_RATIO = {"PC", "TOTAL", "T", "Y25-64"},
            ZERO_FOREIGN_LANG_RATIO = {"0", "PC", "Y25-64"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            digitalSkillsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.DIGITAL_SKILLS_RATIO + JSON_EXT,
            earlyEducationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EARLY_EDU_RATIO + JSON_EXT,
            educationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EDU_RATIO + JSON_EXT,
            excludedRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EXCLUDED_RATIO + JSON_EXT,
            leaversRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.LEAVERS_RATIO + JSON_EXT,
            pupilsRatio2012Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2012 + JSON_EXT,
            pupilsRatio2013Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2013 + JSON_EXT,
            trainingRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.TRAINING_RATIO + JSON_EXT,
            zeroForeignLangRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.ZERO_FOREIGN_LANG_RATIO + JSON_EXT;

    private static final Map<String, Number>
            digitalSkillsRatio = MeasureUtils.consolidateList(DIGITAL_SKILLS, digitalSkillsRatioPath),
            earlyEducationRatio = MeasureUtils.consolidateList(EARLY_EDUCATION_RATIO, earlyEducationRatioPath),
            educationRatio = MeasureUtils.consolidateList(EDUCATION_RATIO, educationRatioPath),
            excludedRatio = MeasureUtils.consolidateList(EXCLUDED_RATIO, excludedRatioPath),
            leaversRatio = MeasureUtils.consolidateList(LEAVERS_RATIO, leaversRatioPath),
            pupilsRatio2012 = MeasureUtils.consolidateList(PUPILS_RATIO_2012, pupilsRatio2012Path),
            pupilsRatio2013 = MeasureUtils.consolidateList(PUPILS_RATIO_2013, pupilsRatio2013Path),
            trainingRatio = MeasureUtils.consolidateList(TRAINING_RATIO, trainingRatioPath),
            zeroForeignLangRatio = MeasureUtils.consolidateList(ZERO_FOREIGN_LANG_RATIO, zeroForeignLangRatioPath);

    public void print() {
//        System.out.println(zeroForeignLangRatio);
        MeasureUtils.print(zeroForeignLangRatioPath);
    }
}
