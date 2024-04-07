package ro.webdata.qoli.aggr.stats.dimensions.education;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PERCENT;
import static ro.webdata.qoli.aggr.stats.constants.ParamsUnits.PUPILS_PER_TEACHER;

public class EducationAggrParams {
    public static final String EDUCATION = "education";

    public static final String DIGITAL_SKILLS_RATIO = EDUCATION + ":digitalSkillsRatio";
    public static final String DROPOUT_RATIO = EDUCATION + ":dropoutRatio";
    public static final String EARLY_EDU_RATIO = EDUCATION + ":earlyEducationRatio";
    public static final String EDUCATION_RATIO = EDUCATION + ":educationRatio";
    public static final String INACTIVE_YOUNG_RATIO = EDUCATION + ":inactiveYoungRatio";
    public static final String NO_KNOWN_FOREIGN_LANG_RATIO = EDUCATION + ":noKnownForeignLangRatio";
    public static final String PUPILS_RATIO = EDUCATION + ":pupilsRatio";
    public static final String TRAINING_LAST_MONTH_RATIO = EDUCATION + ":trainingLastMonthRatio";
    public static final String TRAINING_LAST_YEAR_RATIO = EDUCATION + ":trainingLastYearRatio";
    
    public static final Map<String, String> AGGR_PARAMS_LABELS = new HashMap<>() {{
        put(DIGITAL_SKILLS_RATIO, "Digital Skills Ratio");
        put(DROPOUT_RATIO, "Dropout Ratio");
        put(EARLY_EDU_RATIO, "Early Education Ratio");
        put(EDUCATION_RATIO, "Education Ratio");
        put(INACTIVE_YOUNG_RATIO, "Inactive Young People Ratio");
        put(NO_KNOWN_FOREIGN_LANG_RATIO, "Zero Foreign Language Ratio");
        put(PUPILS_RATIO, "Pupils Ratio");
        put(TRAINING_LAST_MONTH_RATIO, "Training Ratio (last 4 weeks)");
        put(TRAINING_LAST_YEAR_RATIO, "Training Ratio (last year)");
    }};

    public static final Map<String, String> AGGR_PARAMS_UNITS = new HashMap<>() {{
        put(DIGITAL_SKILLS_RATIO, PERCENT);
        put(DROPOUT_RATIO, PERCENT);
        put(EARLY_EDU_RATIO, PERCENT);
        put(EDUCATION_RATIO, PERCENT);
        put(INACTIVE_YOUNG_RATIO, PERCENT);
        put(NO_KNOWN_FOREIGN_LANG_RATIO, PERCENT);
        put(PUPILS_RATIO, PUPILS_PER_TEACHER);
        put(TRAINING_LAST_MONTH_RATIO, PERCENT);
        put(TRAINING_LAST_YEAR_RATIO, PERCENT);
    }};

    public static final List<String> AGGR_PARAMS = List.copyOf(AGGR_PARAMS_LABELS.keySet());

    public static final Map<String, Boolean> AGGR_REVERSED_STATE = new HashMap<>() {{
        put(DIGITAL_SKILLS_RATIO, false);
        put(DROPOUT_RATIO, true);
        put(EARLY_EDU_RATIO, false);
        put(EDUCATION_RATIO, false);
        put(INACTIVE_YOUNG_RATIO, true);
        put(NO_KNOWN_FOREIGN_LANG_RATIO, true);
        put(PUPILS_RATIO, true);
        put(TRAINING_LAST_MONTH_RATIO, false);
        put(TRAINING_LAST_YEAR_RATIO, false);
    }};
}
