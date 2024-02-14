package app.java.commons.dimensions.common;

import app.java.commons.constants.FilePathConst;

import java.io.File;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class CommonPaths {
    public static final String AUXILIARY_FILE_NAME = "auxiliary";

    public static final String POPULATION_FILE_NAME = "population";

    public static final String AUXILIARY_RAW_PATH = String.join(File.separator, FilePathConst.RAW_DATASET_PATH, AUXILIARY_FILE_NAME);

    public static final String
            POPULATION_PATH = AUXILIARY_RAW_PATH + File.separator + CommonPaths.POPULATION_FILE_NAME + JSON_EXTENSION;
}
