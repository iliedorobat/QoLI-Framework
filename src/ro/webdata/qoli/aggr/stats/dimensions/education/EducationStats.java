package ro.webdata.qoli.aggr.stats.dimensions.education;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.MergeUtils;
import ro.webdata.qoli.aggr.data.stats.Preparation;
import ro.webdata.qoli.aggr.stats.Print;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.utils.StatsUtils;

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationAggrParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationPaths.*;

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

    public static Map<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(DIGITAL_SKILLS_RATIO, Preparation.filterMap(initDigitalSkillsRatio));
        put(DROPOUT_RATIO, Preparation.filterMap(initDropoutRatio));
        put(EARLY_EDU_RATIO, Preparation.filterMap(initEarlyEducationRatio));
        put(EDUCATION_RATIO, Preparation.filterMap(initEducationRatio));
        put(INACTIVE_YOUNG_RATIO, Preparation.filterMap(initInactiveYoungRatio));
        put(NO_KNOWN_FOREIGN_LANG_RATIO, Preparation.filterMap(initNoKnownForeignLangRatio));
        put(PUPILS_RATIO, Preparation.filterMap(initPupilsRatio));
        put(TRAINING_LAST_MONTH_RATIO, Preparation.filterMap(initTrainingLastMonthRatio));
        put(TRAINING_LAST_YEAR_RATIO, Preparation.filterMap(initTrainingLastYearRatio));
    }};

    public static final Map<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        put(DIGITAL_SKILLS_RATIO, digitalSkillsRatio);
        put(DROPOUT_RATIO, dropoutRatio);
        put(EARLY_EDU_RATIO, earlyEducationRatio);
        put(EDUCATION_RATIO, educationRatio);
        put(INACTIVE_YOUNG_RATIO, inactiveYoungRatio);
        put(NO_KNOWN_FOREIGN_LANG_RATIO, noKnownForeignLangRatio);
        put(PUPILS_RATIO, pupilsRatio);
        put(TRAINING_LAST_MONTH_RATIO, trainingLastMonthRatio);
        put(TRAINING_LAST_YEAR_RATIO, trainingLastYearRatio);
    }};

    public static Map<String, Number> generateStats(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        return StatsUtils.generateStats(aggrList, countryCodes, startYear, endYear, EDUCATION, ALLOWED_PARAMS, IS_REVERSED, preparedIndicators);
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        Print.printChartData(args, preparedIndicators, EDUCATION, Constants.EU28_MEMBERS, seriesType, direction);
    }

    public static void printDataAvailability(int targetYear, boolean indStatus) {
        Print.printDataAvailability(rawIndicators, EDUCATION, targetYear, indStatus);
    }
}
