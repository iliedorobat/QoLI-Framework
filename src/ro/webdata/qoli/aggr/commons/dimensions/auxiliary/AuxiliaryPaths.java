package app.java.aggr.commons.dimensions.auxiliary;

import app.java.aggr.commons.constants.Constants;

import java.io.File;

import static app.java.aggr.commons.constants.Constants.JSON_EXTENSION;

public class AuxiliaryPaths {
    public static final String AUXILIARY_FILE_NAME = "auxiliary";

    public static final String POPULATION_FILE_NAME = "population";

    public static final String AUXILIARY_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, AUXILIARY_FILE_NAME);

    public static final String
            POPULATION_PATH = AUXILIARY_RAW_PATH + File.separator + POPULATION_FILE_NAME + JSON_EXTENSION;
}
