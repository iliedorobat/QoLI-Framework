package app.java.commons.dimensions.common;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;
import org.apache.commons.collections4.MultiValuedMap;

import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.JSON_EXTENSION;

//TODO: change the "ratio" to "rate" all over the app
public class CommonStats {
    private static final MultiValuedMap<String, String>
            POPULATION = CommonParams.getPopulationParams();

    private static final String
            populationPath = FilePathConst.DATASET_PATH + FileNameConst.POPULATION + JSON_EXTENSION;

    private static final Map<String, Number>
            initPopulation = Initializer.initConsolidatedMap(POPULATION, populationPath);

    public static final Map<String, Number>
            population = Preparation.prepareData(initPopulation);

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Population", initPopulation);
        }};
    }
}
