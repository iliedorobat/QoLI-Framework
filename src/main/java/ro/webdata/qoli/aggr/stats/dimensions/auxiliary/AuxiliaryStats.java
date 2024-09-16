package ro.webdata.qoli.aggr.stats.dimensions.auxiliary;

import ro.webdata.qoli.aggr.data.stats.Initializer;
import ro.webdata.qoli.aggr.data.stats.Preparation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryPaths.POPULATION_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryPaths.POPULATION_PATH;

public class AuxiliaryStats {
    private static final Map<String, Number>
            initPopulation = Initializer.initConsolidatedMap(AuxiliaryParams.POPULATION_PARAMS, POPULATION_PATH);

    public static final Map<String, Number>
            population = Preparation.prepareData(initPopulation);

    public static TreeMap<String, Map<String, Number>> rawIndicators = new TreeMap<>() {{
        put(POPULATION_FILE_NAME, initPopulation);
    }};

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>(){{
        put(POPULATION_FILE_NAME, population);
    }};
}
