package app.java.commons.constants;

import java.io.File;

public class FilePathConst {
    public static final String PREPARED_DATASET_PATH = String.join(File.separator, "files", "prepared");
    public static final String RAW_DATASET_PATH = String.join(File.separator, "files", "raw", "json", "countries");

    public static final String AUXILIARY_DIR = "auxiliary";
    public static final String EDUCATION_DIR = "education";
    public static final String ENVIRONMENT_DIR = "environment";
    public static final String GOVERNANCE_DIR = "governance";
    public static final String HEALTH_DIR = "health";
    public static final String INTERACTIONS_DIR = "interactions";
    public static final String LEISURE_DIR = "leisure";
    public static final String LIVING_CONDITIONS_DIR = "livingConditions";
    public static final String MAIN_ACTIVITY_DIR = "mainActivity";
    public static final String OVERALL_EXPERIENCE_DIR = "overallExperience";
    public static final String QOLI_DIR = "qoli";
    public static final String SAFETY_DIR = "safety";

    public static final String COMMON_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, AUXILIARY_DIR);
    public static final String EDUCATION_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, EDUCATION_DIR);
    public static final String ENVIRONMENT_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, ENVIRONMENT_DIR);
    public static final String GOV_RIGHTS_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, GOVERNANCE_DIR);
    public static final String HEALTH_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, HEALTH_DIR);
    public static final String INTERACTIONS_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, INTERACTIONS_DIR);
    public static final String LEISURE_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, LEISURE_DIR);
    public static final String LIVING_CONDITIONS_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, LIVING_CONDITIONS_DIR);
    public static final String MAIN_ACTIVITY_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, MAIN_ACTIVITY_DIR);
    public static final String OVERALL_EXPERIENCE_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, OVERALL_EXPERIENCE_DIR);
    public static final String SAFETY_RAW_PATH = String.join(File.separator, RAW_DATASET_PATH, SAFETY_DIR);
}
