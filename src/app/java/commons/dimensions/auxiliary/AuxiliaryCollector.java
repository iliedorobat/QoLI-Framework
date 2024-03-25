package app.java.commons.dimensions.auxiliary;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.auxiliary.AuxiliaryParams.POPULATION_PARAMS;
import static app.java.commons.dimensions.auxiliary.AuxiliaryPaths.AUXILIARY_RAW_PATH;

public class AuxiliaryCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getPopulation(), AUXILIARY_RAW_PATH, AuxiliaryPaths.POPULATION_FILE_NAME);
    }

    /**
     * Population on 1 January<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: demo_pjan<br/>
     * Years: 1960-2023
     *
     * @return
     */
    private static StringBuilder getPopulation() {
        return Fetcher.fetchData("demo_pjan", POPULATION_PARAMS);
    }
}
