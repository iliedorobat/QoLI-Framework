package ro.webdata.qoli.aggr.commons.dimensions.auxiliary;

import ro.webdata.qoli.aggr.commons.constants.Constants;

import java.io.File;

public class AuxiliaryPaths {
    public static final String AUXILIARY_FILE_NAME = "auxiliary";

    public static final String POPULATION_FILE_NAME = "population";

    public static final String AUXILIARY_RAW_PATH = String.join(File.separator, Constants.RAW_DATASET_PATH, AUXILIARY_FILE_NAME);

    public static final String
            POPULATION_PATH = AUXILIARY_RAW_PATH + File.separator + POPULATION_FILE_NAME + Constants.JSON_EXTENSION;
}
