package app.java.commons.dimensions.education;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.constants.DimensionNames;
import app.java.commons.constants.IndicatorNames;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.stats.Initializer;
import app.java.data.stats.MergeUtils;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.*;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EducationStats {
    // Queried params values
    private static final MultiValuedMap<String, String>
            DIGITAL_SKILLS = EducationParams.getDigitalSkillsParams(),
            DROPOUT_RATIO = EducationParams.getDropoutParams(),
            EARLY_EDUCATION_RATIO = EducationParams.getEarlyEducationParams(),
            INACTIVE_YOUNG_RATIO = EducationParams.getInactiveYoungParams(),
            NO_KNOWN_FOREIGN_LANG_RATIO = EducationParams.getNoKnownForeignLangParams(),
            PUPILS_RATIO_2012 = EducationParams.getPupilsParams2012(),
            PUPILS_RATIO_2013 = EducationParams.getPupilsParams2013(),
            STUDENTS_RATIO = EducationParams.getEducationParams(),
            TRAINING_RATIO = EducationParams.getTrainingParams();

    private static final String
            digitalSkillsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.DIGITAL_SKILLS_RATIO + JSON_EXTENSION,
            dropoutRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.DROPOUT_RATIO + JSON_EXTENSION,
            earlyEducationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EARLY_EDU_RATIO + JSON_EXTENSION,
            inactiveYoungRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.INACTIVE_YOUNG_RATIO + JSON_EXTENSION,
            noKnownForeignLangRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.NO_KNOWN_FOREIGN_LANG_RATIO + JSON_EXTENSION,
            pupilsRatio2012Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2012 + JSON_EXTENSION,
            pupilsRatio2013Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2013 + JSON_EXTENSION,
            studentsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EDU_RATIO + JSON_EXTENSION,
            trainingRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.TRAINING_RATIO + JSON_EXTENSION;

    // Intermediate data which should be consolidated into a single indicator
    private static final Map<String, Number>
            consolidatedPupilsRatio2012 = MergeUtils.consolidateMap(PUPILS_RATIO_2012, pupilsRatio2012Path),
            consolidatedPupilsRatio2013 = MergeUtils.consolidateMap(PUPILS_RATIO_2013, pupilsRatio2013Path);
    private static final ArrayList<Map<String, Number>> pupilsRatioList = new ArrayList<>();
    static {
        pupilsRatioList.add(consolidatedPupilsRatio2012);
        pupilsRatioList.add(consolidatedPupilsRatio2013);
    }

    private static final Map<String, Number>
            initDigitalSkillsRatio = Initializer.initConsolidatedMap(DIGITAL_SKILLS, digitalSkillsRatioPath),
            initDropoutRatio = Initializer.initConsolidatedMap(DROPOUT_RATIO, dropoutRatioPath),
            initEarlyEducationRatio = Initializer.initConsolidatedMap(EARLY_EDUCATION_RATIO, earlyEducationRatioPath),
            initInactiveYoungRatio = Initializer.initConsolidatedMap(INACTIVE_YOUNG_RATIO, inactiveYoungRatioPath),
            initNoKnownForeignLangRatio = Initializer.initConsolidatedMap(NO_KNOWN_FOREIGN_LANG_RATIO, noKnownForeignLangRatioPath),
            initPupilsRatio = Initializer.initConsolidatedMaps(pupilsRatioList),
            initStudentsRatio = Initializer.initConsolidatedMap(STUDENTS_RATIO, studentsRatioPath),
            initTrainingRatio = Initializer.initConsolidatedMap(TRAINING_RATIO, trainingRatioPath);

    public static final Map<String, Number>
            digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
            dropoutRatio = Preparation.prepareData(initDropoutRatio),
            earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
            inactiveYoungRatio = Preparation.prepareData(initInactiveYoungRatio),
            noKnownForeignLangRatio = Preparation.prepareData(initNoKnownForeignLangRatio),
            pupilsRatio = Preparation.prepareData(initPupilsRatio),
            studentsRatio = Preparation.prepareData(initStudentsRatio),
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
                        * MathUtils.percentageSafetyDouble(pupilsRatio, key)
                        * MathUtils.percentageSafetyDouble(studentsRatio, key)
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
            put("Students Ratio", Preparation.filterMap(initStudentsRatio));
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

            if (args.contains("--indicator=" + IndicatorNames.INACTIVE_YOUNG_RATIO))
                Print.printChartData(inactiveYoungRatio, EU28_MEMBERS, seriesType, IndicatorNames.INACTIVE_YOUNG_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.NO_KNOWN_FOREIGN_LANG_RATIO))
                Print.printChartData(noKnownForeignLangRatio, EU28_MEMBERS, seriesType, IndicatorNames.NO_KNOWN_FOREIGN_LANG_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.PUPILS_RATIO))
                Print.printChartData(pupilsRatio, EU28_MEMBERS, seriesType, IndicatorNames.PUPILS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.STUDENTS_RATIO))
                Print.printChartData(studentsRatio, EU28_MEMBERS, seriesType, IndicatorNames.STUDENTS_RATIO, direction);

            if (args.contains("--indicator=" + IndicatorNames.TRAINING_RATIO))
                Print.printChartData(trainingRatio, EU28_MEMBERS, seriesType, IndicatorNames.TRAINING_RATIO, direction);
        }
    }
}
