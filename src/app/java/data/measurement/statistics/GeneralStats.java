package app.java.data.measurement.statistics;

import app.java.commons.constants.Constants;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.measurement.preparation.Initializer;
import app.java.data.measurement.preparation.Preparation;

import java.util.ArrayList;
import java.util.Map;

//TODO: change the "ratio" to "rate" in all over the app
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

    public static ArrayList<Map<String, Number>> getInitList() {
        return new ArrayList<>() {{
            add(initPopulation);
        }};
    }
}
