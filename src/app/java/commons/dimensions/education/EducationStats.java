package app.java.commons.dimensions.education;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.MergeUtils;
import app.java.data.stats.Preparation;

import java.util.*;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.education.EducationParams.*;
import static app.java.commons.dimensions.education.EducationPaths.*;

public class EducationStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            consolidatedPupilsRatio2012 = MergeUtils.consolidateMap(PUPILS_RATIO_2012_PARAMS, PUPILS_RATIO_2012_PATH),
            consolidatedPupilsRatio2013 = MergeUtils.consolidateMap(PUPILS_RATIO_2013_PARAMS, PUPILS_RATIO_2013_PATH);
    private static final ArrayList<Map<String, Number>> pupilsRatioList = new ArrayList<>() {{
        add(consolidatedPupilsRatio2012);
        add(consolidatedPupilsRatio2013);
    }};

    private static final Map<String, Number>
            initDigitalSkillsRatio = Initializer.initConsolidatedMap(DIGITAL_SKILLS_PARAMS, DIGITAL_SKILLS_RATIO_PATH),
            initDropoutRatio = Initializer.initConsolidatedMap(DROPOUT_RATIO_PARAMS, DROPOUT_RATIO_PATH),
            initEarlyEducationRatio = Initializer.initConsolidatedMap(EARLY_EDUCATION_RATIO_PARAMS, EARLY_EDUCATION_RATIO_PATH),
            initEducationRatio = Initializer.initConsolidatedMap(EDUCATION_RATIO_PARAMS, EDUCATION_RATIO_PATH),
            initInactiveYoungRatio = Initializer.initConsolidatedMap(INACTIVE_YOUNG_RATIO_PARAMS, INACTIVE_YOUNG_RATIO_PATH),
            initNoKnownForeignLangRatio = Initializer.initConsolidatedMap(NO_KNOWN_FOREIGN_LANG_RATIO_PARAMS, NO_KNOWN_FOREIGN_LANG_RATIO_PATH),
            initPupilsRatio = Initializer.initConsolidatedMaps(pupilsRatioList),
            initTrainingLastMonthRatio = Initializer.initConsolidatedMap(TRAINING_LAST_MONTH_RATIO_PARAMS, TRAINING_LAST_MONTH_RATIO_PATH),
            initTrainingLastYearRatio = Initializer.initConsolidatedMap(TRAINING_LAST_YEAR_RATIO_PARAMS, TRAINING_LAST_YEAR_RATIO_PATH);

    private static final Map<String, Number>
            digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
            dropoutRatio = Preparation.prepareData(initDropoutRatio),
            earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
            educationRatio = Preparation.prepareData(initEducationRatio),
            inactiveYoungRatio = Preparation.prepareData(initInactiveYoungRatio),
            noKnownForeignLangRatio = Preparation.prepareData(initNoKnownForeignLangRatio),
            pupilsRatio = Preparation.prepareData(initPupilsRatio),
            trainingLastMonthRatio = Preparation.prepareData(initTrainingLastMonthRatio),
            trainingLastYearRatio = Preparation.prepareData(initTrainingLastYearRatio);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(DIGITAL_SKILLS_RATIO_FILE_NAME, Preparation.filterMap(initDigitalSkillsRatio));
        put(DROPOUT_RATIO_FILE_NAME, Preparation.filterMap(initDropoutRatio));
        put(EARLY_EDU_RATIO_FILE_NAME, Preparation.filterMap(initEarlyEducationRatio));
        put(EDU_RATIO_FILE_NAME, Preparation.filterMap(initEducationRatio)); // students
        put(INACTIVE_YOUNG_RATIO_FILE_NAME, Preparation.filterMap(initInactiveYoungRatio));
        put(NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME, Preparation.filterMap(initNoKnownForeignLangRatio));
        put(PUPILS_RATIO_FILE_NAME, Preparation.filterMap(initPupilsRatio));
        put(TRAINING_LAST_MONTH_RATIO_FILE_NAME, Preparation.filterMap(initTrainingLastMonthRatio));
        put(TRAINING_LAST_YEAR_RATIO_FILE_NAME, Preparation.filterMap(initTrainingLastYearRatio));
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(DIGITAL_SKILLS_RATIO_FILE_NAME, digitalSkillsRatio);
        put(DROPOUT_RATIO_FILE_NAME, dropoutRatio);
        put(EARLY_EDU_RATIO_FILE_NAME, earlyEducationRatio);
        put(EDU_RATIO_FILE_NAME, educationRatio); // students
        put(INACTIVE_YOUNG_RATIO_FILE_NAME, inactiveYoungRatio);
        put(NO_KNOWN_FOREIGN_LANG_RATIO_FILE_NAME, noKnownForeignLangRatio);
        put(PUPILS_RATIO_FILE_NAME, pupilsRatio);
        put(TRAINING_LAST_MONTH_RATIO_FILE_NAME, trainingLastMonthRatio);
        put(TRAINING_LAST_YEAR_RATIO_FILE_NAME, trainingLastYearRatio);
    }};

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double product = 1
                        * MathUtils.percentageSafetyDouble(digitalSkillsRatio, key)
                        * MathUtils.percentageSafetyDouble(earlyEducationRatio, key)
                        * MathUtils.percentageSafetyDouble(educationRatio, key)
                        * MathUtils.percentageSafetyDouble(pupilsRatio, key)
                        * MathUtils.percentageSafetyDouble(trainingLastMonthRatio, key)
                        * MathUtils.percentageSafetyDouble(trainingLastYearRatio, key)
                        * MathUtils.percentageSafetyDouble(dropoutRatio, key, true)
                        * MathUtils.percentageSafetyDouble(inactiveYoungRatio, key, true)
                        * MathUtils.percentageSafetyDouble(noKnownForeignLangRatio, key, true);

                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(pupilsRatio, true));
//        Print.print(initPupilsRatio, false);

        return consolidatedList;
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, EDUCATION_FILE_NAME, EU28_MEMBERS, seriesType, direction);
    }
}
