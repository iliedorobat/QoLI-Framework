package app.java.commons.dimesntions.common;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.ArrayList;
import java.util.Map;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

//TODO: change the "ratio" to "rate" all over the app
public class CommonStats {
    private static final String[]
            POPULATION = {"TOTAL", "T", "NR"};

    private static final String JSON_EXT = JSON_EXTENSION;
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
