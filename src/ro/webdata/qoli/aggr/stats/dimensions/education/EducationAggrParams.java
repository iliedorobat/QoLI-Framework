package ro.webdata.qoli.aggr.stats.dimensions.education;

import java.util.HashMap;
import java.util.Map;

public class EducationAggrParams {
    public static final String EDUCATION = "education";

    public static final String DIGITAL_SKILLS_RATIO = "digitalSkillsRatio";
    public static final String DROPOUT_RATIO = "dropoutRatio";
    public static final String EARLY_EDU_RATIO = "earlyEducationRatio";
    public static final String EDUCATION_RATIO = "educationRatio";
    public static final String INACTIVE_YOUNG_RATIO = "inactiveYoungRatio";
    public static final String NO_KNOWN_FOREIGN_LANG_RATIO = "noKnownForeignLangRatio";
    public static final String PUPILS_RATIO = "pupilsRatio";
    public static final String TRAINING_LAST_MONTH_RATIO = "trainingLastMonthRatio";
    public static final String TRAINING_LAST_YEAR_RATIO = "trainingLastYearRatio";
    
    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
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

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
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
