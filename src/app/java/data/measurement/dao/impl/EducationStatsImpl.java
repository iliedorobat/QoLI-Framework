package app.java.data.measurement.dao.impl;

import app.java.commons.MapOrder;
import app.java.commons.MapUtils;
import app.java.commons.Statistics;
import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.dao.EducationStatsDAO;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class EducationStatsImpl implements EducationStatsDAO {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    // The lists of queried values
    private static final String[]
            DIGITAL_SKILLS = {"I_DSK_BAB", "IND_TOTAL", "PC_IND"},
            EARLY_EDUCATION_RATIO = {"T", "PC"},
            EDUCATION_RATIO = {"PC", "T", "ED5-8", "Y15-64"},
            EXCLUDED_RATIO = {"T", "Y18-24", "NO_FED_NFE", "NEMP", "PC"},
            LEAVERS_RATIO = {"T", "POP", "Y18-24", "PC"},
            TRAINING_RATIO = {"PC", "TOTAL", "T", "Y25-64"},
            ZERO_FOREIGN_LANG_RATIO = {"0", "PC", "Y25-64"},

            PUPILS_RATIO_2012 = {"ST1_1"},
            PUPILS_RATIO_2013 = {"RT", "ED1-3"};

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

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            consolidatedPupilsRatio2012 = MeasureUtils.consolidateList(PUPILS_RATIO_2012, pupilsRatio2012Path),
            consolidatedPupilsRatio2013 = MeasureUtils.consolidateList(PUPILS_RATIO_2013, pupilsRatio2013Path);

    private static final Map<String, Number>
            initDigitalSkillsRatio = Initializer.initConsolidatedList(DIGITAL_SKILLS, digitalSkillsRatioPath),
            initEarlyEducationRatio = Initializer.initConsolidatedList(EARLY_EDUCATION_RATIO, earlyEducationRatioPath),
            initEducationRatio = Initializer.initConsolidatedList(EDUCATION_RATIO, educationRatioPath),
            initExcludedRatio = Initializer.initConsolidatedList(EXCLUDED_RATIO, excludedRatioPath),
            initLeaversRatio = Initializer.initConsolidatedList(LEAVERS_RATIO, leaversRatioPath),
            initPupilsRatio = initConsolidatedPupilsRatio(),
            initTrainingRatio = Initializer.initConsolidatedList(TRAINING_RATIO, trainingRatioPath),
            initZeroForeignLangRatio = Initializer.initConsolidatedList(ZERO_FOREIGN_LANG_RATIO, zeroForeignLangRatioPath);

    public double calculateIndex() {
        Map<String, Number>
                digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
                earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
                educationRatio = Preparation.prepareData(initEducationRatio),
                excludedRatio = Preparation.prepareData(initExcludedRatio),
                leaversRatio = Preparation.prepareData(initLeaversRatio),
                pupilsRatio = Preparation.prepareData(initPupilsRatio),
                trainingRatio = Preparation.prepareData(initTrainingRatio),
                zeroForeignLangRatio = Preparation.prepareData(initZeroForeignLangRatio);

//        Print.printVariation(Statistics.generateVariation(pupilsRatio, true));
//        Print.print(pupilsRatio, false);

        return 0;
    }


    /**
     * Generate an initialized consolidated pupils ratio list
     *
     * @return
     */
    private static Map<String, Number> initConsolidatedPupilsRatio() {
        Map<String, Number> consolidatedList = consolidatePupilsRatio();
        return Initializer.initMap(consolidatedList);
    }

    /**
     * Consolidate the lists of values for pupils ratio before 2012 and after 2013
     *
     * @return Sorted list with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    private static Map<String, Number> consolidatePupilsRatio() {
        Map<String, Number> preparedMap = new TreeMap<>(new MapOrder());

        ArrayList<Map<String, Number>> mapsList = new ArrayList<>();
        mapsList.add(consolidatedPupilsRatio2012);
        mapsList.add(consolidatedPupilsRatio2013);

        // Iterate over EU28_MEMBERS in order to add the entries by country code into the ordered map
        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            String code = EU28_MEMBERS[i];

            for (int j = 0; j < mapsList.size(); j++) {
                Map<String, Number> map = mapsList.get(j);

                for (Map.Entry<String, Number> entry : map.entrySet()) {
                    String entryCode = MapUtils.getEntryCode(entry);
                    String entryKey = entry.getKey();
                    Number entryValue = entry.getValue();

                    if (code.equals(entryCode) && !preparedMap.containsKey(entryKey))
                        preparedMap.put(entryKey, entryValue);
                }
            }
        }

        return preparedMap;
    }
}

/*
 * initDigitalSkillsRatio
 *      -> ALL: 2015 - 2017
 *      -> IT: 2015 - 2016
 *      => setPrevNextYearsValue
 *
 * initZeroForeignLangRatio
 *      -> ALL 2007; 2011; 2016
 *      => setPrevNextYearsValue
 *
 * initEarlyEducationRatio
 *      -> IT, PT, RO, SE, SI, SK: 2007 - 2012
 *      -> UK, AT, EL, HR, HU: with some exceptions
 *      -> small variations (with some exceptions)
 *      => setPrevNextYearsValue
 *
 * initPupilsRatio
 *      -> DK: 2014
 *      -> EL: 2007; 2012 - 2017
 *      -> IE: 2007-2013
 *      -> MT: 2008 - 2017
 *      -> NL: 2007 - 2012; 2015 - 2017
 *      => setPrevNextYearsValue
 */
