package app.java.commons.dimensions.common;

import app.java.data.stats.Initializer;
import app.java.data.stats.Preparation;

import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.dimensions.common.CommonParams.POPULATION_PARAMS;
import static app.java.commons.dimensions.common.CommonPaths.POPULATION_PATH;

//TODO: change the "ratio" to "rate" all over the app
public class CommonStats {
    private static final Map<String, Number>
            initPopulation = Initializer.initConsolidatedMap(POPULATION_PARAMS, POPULATION_PATH);

    public static final Map<String, Number>
            population = Preparation.prepareData(initPopulation);

    public static TreeMap<String, Map<String, Number>> getInitList() {
        return new TreeMap<>() {{
            put("Population", initPopulation);
        }};
    }
}
