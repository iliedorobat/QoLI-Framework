package app.java.aggr.commons.dimensions.auxiliary;

import app.java.aggr.data.stats.Initializer;
import app.java.aggr.data.stats.Preparation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static app.java.aggr.commons.dimensions.auxiliary.AuxiliaryParams.POPULATION_PARAMS;
import static app.java.aggr.commons.dimensions.auxiliary.AuxiliaryPaths.POPULATION_FILE_NAME;
import static app.java.aggr.commons.dimensions.auxiliary.AuxiliaryPaths.POPULATION_PATH;

//TODO: change the "ratio" to "rate" all over the app
public class AuxiliaryStats {
    private static final Map<String, Number>
            initPopulation = Initializer.initConsolidatedMap(POPULATION_PARAMS, POPULATION_PATH);

    public static final Map<String, Number>
            population = Preparation.prepareData(initPopulation);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(POPULATION_FILE_NAME, initPopulation);
    }};

    public static final HashMap<String, Map<String, Number>> preparedIndicators = new HashMap<>(){{
        put(POPULATION_FILE_NAME, population);
    }};
}
