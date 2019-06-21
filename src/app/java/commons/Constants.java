package app.java.commons;

public class Constants {
    public static final boolean IS_TESTING = true;

    public static final String DATASET_PATH = "files/dataset/";
    public static final String MATERIAL_LIFE_PATH = DATASET_PATH + "material-life/";
    public static final String MAIN_ACTIVITY_PATH = DATASET_PATH + "main-activity/";
    public static final String HEALTH_PATH = DATASET_PATH + "health/";
    public static final String EDUCATION_PATH = DATASET_PATH + "education/";
    public static final String SOCIAL_ACTIVITY_PATH = DATASET_PATH + "social-activity/";
    public static final String SAFETY_PATH = DATASET_PATH + "safety/";
    public static final String GOV_RIGHTS_PATH = DATASET_PATH + "gov-rights/";
    public static final String ENVIRONMENT_PATH = DATASET_PATH + "environment/";

    public static final boolean PRINT_DIMENSION_IFO = false;
    public static final boolean PRINT_DIMENSION_VALUE = true;
    public static final boolean PRINT_DIMENSION_MISSING = false; //true;
    public static final boolean PRINT_AMPLITUDE = true;

    public static final double VARIATION_THRESHOLD = 20;
    public static final double REPORT_NO_1 = 1000;
    public static final double REPORT_NO_10 = 10000;
    public static final double REPORT_NO_100 = 100000;
    public static final double HA_TO_M = 10000;

    public static final short MIN_YEAR = 2007;
    public static final short MAX_YEAR = 2017;

    public static final String B_IF_REGION = "Regiunea BUCURESTI - ILFOV";
    public static final String CENTER_REGION = "Regiunea CENTRU";
    public static final String NE_REGION = "Regiunea NORD-EST";
    public static final String NV_REGION = "Regiunea NORD-VEST";
    public static final String SE_REGION = "Regiunea SUD-EST";
    public static final String S_M_REGION = "Regiunea SUD-MUNTENIA";
    public static final String SV_REGION = "Regiunea SUD-VEST OLTENIA";
    public static final String V_REGION = "Regiunea VEST";

    public static final String PRIVATE = "proprietate privata";
    public static final String PUBLIC = "proprietate publica";

    public static final String SEX_MASCULIN = "masculin";
    public static final String SEX_FEMININ = "feminin";
    public static final String TOTAL = "total";
    public static final String PARLIAMENTARY = "alegeri parlamentare";

    public static final String EDUCATION_I = "invatamant primar si gimnazial";
    public static final String EDUCATION_II = "invatamantul secundar ciclul 2 (liceal si profesional)";
    public static final String EDUCATION_III = "invatamant postliceal si de maistri";

    // Extras din titlul coloanei (mii persoane)
    public static final int ACTIVE_POP_UM = 1000;

    public enum DIMENSION {
        COMMON,
        EDUCATION,
        ENVIRONMENT,
        GOV_RIGHTS,
        HEALTH,
        MAIN_ACTIVITY,
        MATERIAL_LIFE,
        SAFETY,
        SOCIAL_ACTIVITY
    }

    public enum PARAMS {
        FACULTIES_COUNT,
        SCHOOLS_COUNT,
        TEACHERS_COUNT,
        STUDENTS_COUNT,
        ABANDON_RATE,

        COUNT,
        GENERAL,
        MATERIAL_LIFE_MONEY,
        MEDICAL,
        MEDICAL_CAB,
        MEDICAL_STAFF,
        RATE,
        RESEARCHER,
        RESIDENT_POPULATION,
        SAFETY,
        SOCIAL_ACTIVITY
    }

    public enum TYPES {
        // Commons
        ACTIVE_POP,
        AVG_NET_SALARY,
        RESIDENT_POPULATION,

        // 1. Material and living conditions
        AVG_TOTAL_INCOME,
        AVG_TOTAL_SPEND,
        AVG_GROSS_SALARY,
        DEPRIVATION_RATE,
        RELATIVE_POVERTY,
        POVERTY_RISK,

        // 2. Productive or main activity
        EMP_RATE,
        GDP,
        RESEARCHER,
        UNEMP_HIGH_EDU_COUNT,
        UNEMP_RATE,
        UNEMP_SEC_EDU_COUNT,

        // 3. Health
        ALCOHOL_COUNT,
        FRUIT_COUNT,
        VEGETABLE_COUNT,
        NATURAL_POP_GROWTH_RATE,
        AVG_LIFE_EXPECTANCY,
        HURT_IN_WORK_COUNT,
        HURT_IN_TRAFFIC_COUNT,
        GENERAL_MED_CAB_TOTAL_COUNT,
        SPECIALIZED_MED_CAB_TOTAL_COUNT,
        DENTAL_MED_CAB_TOTAL_COUNT,
        HOSPITAL_TOTAL_COUNT,
        HOSPITAL_CHILDREN_BED_TOTAL_COUNT,
        HOSPITAL_BED_TOTAL_COUNT,
        SEC_EDU_STAFF_TOTAL_COUNT,
        NURSE_HIGH_EDU_TOTAL_COUNT,
        DOCTOR_TOTAL_COUNT,
        DENTIST_TOTAL_COUNT,
        PHARMA_STAFF_TOTAL_COUNT,

        // 4. Education
        ABANDON_RATE_I,
        ABANDON_RATE_II,
        ABANDON_RATE_III,
        SCHOOLS_COUNT,
        FACULTIES_COUNT,
        TEACHERS_COUNT,
        STUDENTS_COUNT,
        POST_SEC_EDU_STUD,

        // 5. Leisure and social interactions (social activity)
        CINEMA_SHOW_COUNT,
        MUSEUM_COUNT,
        ATHLETES_COUNT,
        SPORT_GROUP_COUNT,

        // 6. Economic and physical safety
        FAMILY_ASSIST,
        SOCIAL_ASSIST,
        SOCIAL_DINNER_GAINER,
        AVG_PENSION,
        CONVICTS,
        POLICE_OFFENCE_SOLVED,
        OFFENCE_RATE,
        CRIME_RATE,

        // 7. Governance and basic rights
        MALE_EMP_RATE,
        FEMALE_EMP_RATE,
        VOTE_RATE,

        // 8. Natural and living environment
        GREEN_SPACE,
        WATER_ACCESS
    }
}
