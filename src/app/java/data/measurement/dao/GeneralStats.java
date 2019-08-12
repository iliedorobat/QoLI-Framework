package app.java.data.measurement.dao;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.Map;

public class GeneralStats {
    private static final String[]
            POPULATION = {"TOTAL", "T", "NR"};

    private static final String JSON_EXT = Constants.JSON_EXTENSION;
    private static final String
            populationPath = FilePathConst.DATASET_PATH + FileNameConst.POPULATION + JSON_EXT;

    private static final Map<String, Number>
            initPopulation = Initializer.initConsolidatedMap(POPULATION, populationPath);

    public static final Map<String, Number>
            population = Preparation.prepareData(initPopulation);
}
