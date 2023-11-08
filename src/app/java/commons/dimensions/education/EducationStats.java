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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.education.EducationParams.*;
import static app.java.commons.dimensions.education.EducationPaths.*;

public class EducationStats {
    // Intermediate data which will be grouped into a single indicator
    private static final Map<String, Number>
            consolidatedPupilsRatio2012 = MergeUtils.consolidateMap(PUPILS_RATIO_2012_PARAMS, PUPILS_RATIO_2012_PATH),
            consolidatedPupilsRatio2013 = MergeUtils.consolidateMap(PUPILS_RATIO_2013_PARAMS, PUPILS_RATIO_2013_PATH);
    private static final ArrayList<Map<String, Number>> pupilsRatioList = new ArrayList<>();
    static {
        pupilsRatioList.add(consolidatedPupilsRatio2012);
        pupilsRatioList.add(consolidatedPupilsRatio2013);
    }

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

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double
                        reversedDropoutRatio = MathUtils.percentageReverseRatio(dropoutRatio, key),
                        reversedInactiveYoungRatio = MathUtils.percentageReverseRatio(inactiveYoungRatio, key),
                        reversedNoKnownForeignLangRatio = MathUtils.percentageReverseRatio(noKnownForeignLangRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(digitalSkillsRatio, key)
                        * MathUtils.percentageSafetyDouble(earlyEducationRatio, key)
                        * MathUtils.percentageSafetyDouble(educationRatio, key)
                        * MathUtils.percentageSafetyDouble(pupilsRatio, key)
                        * MathUtils.percentageSafetyDouble(trainingRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedDropoutRatio)
                        * MathUtils.percentageSafetyDouble(reversedInactiveYoungRatio)
                        * MathUtils.percentageSafetyDouble(reversedNoKnownForeignLangRatio);

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
        if (args.contains("--dimension=" + DimensionNames.EDUCATION)) {
            if (args.contains("--indicator=" + IndicatorNames.DIGITAL_SKILLS_RATIO))
                Print.printChartData(digitalSkillsRatio, EU28_MEMBERS, seriesType, IndicatorNames.DIGITAL_SKILLS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.DROPOUT_RATIO))
                Print.printChartData(dropoutRatio, EU28_MEMBERS, seriesType, IndicatorNames.DROPOUT_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.EARLY_EDUCATION_RATIO))
                Print.printChartData(earlyEducationRatio, EU28_MEMBERS, seriesType, IndicatorNames.EARLY_EDUCATION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.EDUCATION_RATIO))
                Print.printChartData(educationRatio, EU28_MEMBERS, seriesType, IndicatorNames.EDUCATION_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.INACTIVE_YOUNG_RATIO))
                Print.printChartData(inactiveYoungRatio, EU28_MEMBERS, seriesType, IndicatorNames.INACTIVE_YOUNG_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NO_KNOWN_FOREIGN_LANG_RATIO))
                Print.printChartData(noKnownForeignLangRatio, EU28_MEMBERS, seriesType, IndicatorNames.NO_KNOWN_FOREIGN_LANG_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.PUPILS_RATIO))
                Print.printChartData(pupilsRatio, EU28_MEMBERS, seriesType, IndicatorNames.PUPILS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.TRAINING_RATIO))
                Print.printChartData(trainingRatio, EU28_MEMBERS, seriesType, IndicatorNames.TRAINING_RATIO, direction);
        }
    }
}
