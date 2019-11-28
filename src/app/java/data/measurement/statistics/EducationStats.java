package app.java.data.measurement.statistics;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
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

public class EducationStats {
    // The lists of queried values
    private static final String[]
            DIGITAL_SKILLS = {"I_DSK_BAB", "IND_TOTAL", "PC_IND"},
            EARLY_EDUCATION_RATIO = {"T", "PC"},
            EXCLUDED_RATIO = {"T", "Y18-24", "NO_FED_NFE", "NEMP", "PC"},
            NO_KNOWN_FOREIGN_LANG_RATIO = {"0", "PC", "Y25-64"},
            SCHOOL_DROPOUT_RATIO = {"T", "POP", "Y18-24", "PC"},
            STUDENTS_RATIO = {"PC", "T", "ED5-8", "Y15-64"},
            TRAINING_RATIO = {"PC", "TOTAL", "T", "Y25-64"},

            PUPILS_RATIO_2012 = {"ST1_1"},
            PUPILS_RATIO_2013 = {"RT", "ED1-3"};

    private static final String
            digitalSkillsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.DIGITAL_SKILLS_RATIO + Constants.JSON_EXTENSION,
            earlyEducationRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EARLY_EDU_RATIO + Constants.JSON_EXTENSION,
            excludedRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EXCLUDED_RATIO + Constants.JSON_EXTENSION,
            noKnownForeignLangRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.NO_KNOWN_FOREIGN_LANG_RATIO + Constants.JSON_EXTENSION,
            pupilsRatio2012Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2012 + Constants.JSON_EXTENSION,
            pupilsRatio2013Path = FilePathConst.EDUCATION_PATH + FileNameConst.PUPILS_RATIO_2013 + Constants.JSON_EXTENSION,
            schoolDropoutRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.SCHOOL_DROPOUT_RATIO + Constants.JSON_EXTENSION,
            studentsRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.EDU_RATIO + Constants.JSON_EXTENSION,
            trainingRatioPath = FilePathConst.EDUCATION_PATH + FileNameConst.TRAINING_RATIO + Constants.JSON_EXTENSION;

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
            initEarlyEducationRatio = Initializer.initConsolidatedMap(EARLY_EDUCATION_RATIO, earlyEducationRatioPath),
            initExcludedRatio = Initializer.initConsolidatedMap(EXCLUDED_RATIO, excludedRatioPath),
            initNoKnownForeignLangRatio = Initializer.initConsolidatedMap(NO_KNOWN_FOREIGN_LANG_RATIO, noKnownForeignLangRatioPath),
            initPupilsRatio = Initializer.initConsolidatedMaps(pupilsRatioList),
            initSchoolDropoutRatio = Initializer.initConsolidatedMap(SCHOOL_DROPOUT_RATIO, schoolDropoutRatioPath),
            initStudentsRatio = Initializer.initConsolidatedMap(STUDENTS_RATIO, studentsRatioPath),
            initTrainingRatio = Initializer.initConsolidatedMap(TRAINING_RATIO, trainingRatioPath);

    public static Map<String, Number> generateDimensionList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                digitalSkillsRatio = Preparation.prepareData(initDigitalSkillsRatio),
                earlyEducationRatio = Preparation.prepareData(initEarlyEducationRatio),
                excludedRatio = Preparation.prepareData(initExcludedRatio),
                noKnownForeignLangRatio = Preparation.prepareData(initNoKnownForeignLangRatio),
                pupilsRatio = Preparation.prepareData(initPupilsRatio),
                schoolDropoutRatio = Preparation.prepareData(initSchoolDropoutRatio),
                studentsRatio = Preparation.prepareData(initStudentsRatio),
                trainingRatio = Preparation.prepareData(initTrainingRatio);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double reversedExcludedRatio = MathUtils.percentageReverseRatio(excludedRatio, key),
                        reversedNoKnownForeignLangRatio = MathUtils.percentageReverseRatio(noKnownForeignLangRatio, key),
                        reversedSchoolDropoutRatio = MathUtils.percentageReverseRatio(schoolDropoutRatio, key);

                double product = 1
                        * MathUtils.percentageSafetyDouble(digitalSkillsRatio, key)
                        * MathUtils.percentageSafetyDouble(earlyEducationRatio, key)
                        * MathUtils.percentageSafetyDouble(pupilsRatio, key)
                        * MathUtils.percentageSafetyDouble(reversedExcludedRatio)
                        * MathUtils.percentageSafetyDouble(reversedNoKnownForeignLangRatio)
                        * MathUtils.percentageSafetyDouble(reversedSchoolDropoutRatio)
                        * MathUtils.percentageSafetyDouble(studentsRatio, key)
                        * MathUtils.percentageSafetyDouble(trainingRatio, key);
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(pupilsRatio, true));
//        Print.print(initPupilsRatio, false);

        return consolidatedList;
    }

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(Preparation.filterMap(initDigitalSkillsRatio));
            add(Preparation.filterMap(initEarlyEducationRatio));
            add(Preparation.filterMap(initExcludedRatio));
            add(Preparation.filterMap(initSchoolDropoutRatio));
            add(Preparation.filterMap(initPupilsRatio));
            add(Preparation.filterMap(initStudentsRatio));
            add(Preparation.filterMap(initTrainingRatio));
            add(Preparation.filterMap(initNoKnownForeignLangRatio));
        }};
    }
}
