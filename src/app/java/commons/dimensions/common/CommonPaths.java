package app.java.commons.dimensions.common;

import app.java.commons.constants.FilePathConst;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

public class CommonPaths {
    public static final String POPULATION_FILE_NAME = "population";

    public static final String
            POPULATION_PATH = FilePathConst.DATASET_PATH + CommonPaths.POPULATION_FILE_NAME + JSON_EXTENSION;
}
