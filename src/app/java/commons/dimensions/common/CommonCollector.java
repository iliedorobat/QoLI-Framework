package app.java.commons.dimensions.common;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.common.CommonParams.POPULATION_PARAMS;

public class CommonCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getPopulation(), FilePathConst.COMMON_RAW_PATH, CommonPaths.POPULATION_FILE_NAME);
    }

    /**
     * Population on 1 January<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: demo_pjan<br/>
     * Years: 1960-2022
     *
     * @return
     */
    private static StringBuilder getPopulation() {
        return Fetcher.fetchData("demo_pjan", POPULATION_PARAMS);
    }
}
