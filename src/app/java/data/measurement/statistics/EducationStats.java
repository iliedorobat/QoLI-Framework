package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.MapUtils;
import app.java.commons.utils.MathUtils;
import app.java.data.measurement.MeasureUtils;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class EducationStats {
    // Queried params values
    private static final String[]
            DIGITAL_SKILLS = {"I_DSK_BAB", "IND_TOTAL", "PC_IND"},
            DROPOUT_RATIO = {"T", "POP", "Y18-24", "PC"},
            EARLY_EDUCATION_RATIO = {"T", "PC"},
            INACTIVE_YOUNG_RATIO = {"T", "Y18-24", "NO_FE_NO_NFE", "NEMP", "PC"},
            NO_KNOWN_FOREIGN_LANG_RATIO = {"0", "PC", "Y25-64"},
            STUDENTS_RATIO = {"PC", "T", "ED5-8", "Y15-64"},
            TRAINING_RATIO = {"PC", "TOTAL", "T", "Y25-64"},

            PUPILS_RATIO_2012 = {"ST1_1"},
            PUPILS_RATIO_2013 = {"RT", "ED1-3"};

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
            consolidatedPupilsRatio2012 = MeasureUtils.consolidateMap(PUPILS_RATIO_2012, pupilsRatio2012Path),
            consolidatedPupilsRatio2013 = MeasureUtils.consolidateMap(PUPILS_RATIO_2013, pupilsRatio2013Path);
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

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
                dropoutRatio = Preparation.prepareData(initDropoutRatio),
                earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
                inactiveYoungRatio = Preparation.prepareData(initInactiveYoungRatio),
                noKnownForeignLangRatio = Preparation.prepareData(initNoKnownForeignLangRatio),
                pupilsRatio = Preparation.prepareData(initPupilsRatio),
                studentsRatio = Preparation.prepareData(initStudentsRatio),
                trainingRatio = Preparation.prepareData(initTrainingRatio);

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

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initDigitalSkillsRatio));
            add(Preparation.filterMap(initDropoutRatio));
            add(Preparation.filterMap(initEarlyEducationRatio));
            add(Preparation.filterMap(initInactiveYoungRatio));
            add(Preparation.filterMap(initNoKnownForeignLangRatio));
            add(Preparation.filterMap(initPupilsRatio));
            add(Preparation.filterMap(initStudentsRatio));
            add(Preparation.filterMap(initTrainingRatio));
        }};
    }
}
