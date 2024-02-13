package app.java.commons.dimensions.education;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.IndicatorNames;
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
            initTrainingRatio = Initializer.initConsolidatedMap(TRAINING_RATIO_PARAMS, TRAINING_RATIO_PATH);

    public static final Map<String, Number>
            digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
            dropoutRatio = Preparation.prepareData(initDropoutRatio),
            earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
            educationRatio = Preparation.prepareData(initEducationRatio),
            inactiveYoungRatio = Preparation.prepareData(initInactiveYoungRatio),
            noKnownForeignLangRatio = Preparation.prepareData(initNoKnownForeignLangRatio),
            pupilsRatio = Preparation.prepareData(initPupilsRatio),
            trainingRatio = Preparation.prepareData(initTrainingRatio);

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put("digitalSkillsRatio", digitalSkillsRatio);
        put("dropoutRatio", dropoutRatio);
        put("earlyEducationRatio", earlyEducationRatio);
        put("educationRatio", educationRatio);
        put("inactiveYoungRatio", inactiveYoungRatio);
        put("zeroForeignLangRatio", noKnownForeignLangRatio);
        put("pupilsRatio", pupilsRatio);
        put("trainingRatio", trainingRatio);
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
                        * MathUtils.percentageSafetyDouble(trainingRatio, key)
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

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Digital Skills Ratio", Preparation.filterMap(initDigitalSkillsRatio));
            put("Dropout Ratio", Preparation.filterMap(initDropoutRatio));
            put("Early Education Ratio", Preparation.filterMap(initEarlyEducationRatio));
            put("Inactive Young Ratio", Preparation.filterMap(initInactiveYoungRatio));
            put("No Known Foreign Lang Ratio", Preparation.filterMap(initNoKnownForeignLangRatio));
            put("Pupils Ratio", Preparation.filterMap(initPupilsRatio));
            put("Students Ratio", Preparation.filterMap(initEducationRatio));
            put("Trainings Ratio", Preparation.filterMap(initTrainingRatio));
        }};
    }

    public static void printIndicators(List<String> args, String seriesType, String direction) {
        HashMap<String, Map<String, Number>> indicators = new HashMap<>() {{
            put(IndicatorNames.DIGITAL_SKILLS_RATIO, digitalSkillsRatio);
            put(IndicatorNames.DROPOUT_RATIO, dropoutRatio);
            put(IndicatorNames.EARLY_EDUCATION_RATIO, earlyEducationRatio);
            put(IndicatorNames.EDUCATION_RATIO, educationRatio);
            put(IndicatorNames.INACTIVE_YOUNG_RATIO, inactiveYoungRatio);
            put(IndicatorNames.NO_KNOWN_FOREIGN_LANG_RATIO, noKnownForeignLangRatio);
            put(IndicatorNames.PUPILS_RATIO, pupilsRatio);
            put(IndicatorNames.TRAINING_RATIO, trainingRatio);
        }};

        Print.printChartData(args, indicators, DimensionNames.EDUCATION, EU28_MEMBERS, seriesType, direction);
    }
}
