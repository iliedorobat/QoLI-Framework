package ro.webdata.qoli.aggr.commons.dimensions.education;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public static final List<String> ALLOWED_PARAMS = new ArrayList<>() {{
        add(DIGITAL_SKILLS_RATIO);
        add(DROPOUT_RATIO);
        add(EARLY_EDU_RATIO);
        add(EDUCATION_RATIO);
        add(INACTIVE_YOUNG_RATIO);
        add(NO_KNOWN_FOREIGN_LANG_RATIO);
        add(PUPILS_RATIO);
        add(TRAINING_LAST_MONTH_RATIO);
        add(TRAINING_LAST_YEAR_RATIO);
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
