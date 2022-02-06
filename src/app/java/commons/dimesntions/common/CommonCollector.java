package app.java.commons.dimesntions.common;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

public class CommonCollector {
    public static void dataCollector() {
        FileUtils.writeToJSONFile(getPopulation(), FilePathConst.DATASET_PATH, FileNameConst.POPULATION);
    }

    /**
     * Population on 1 January<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: count (number)<br/>
     * Dataset: demo_pjan<br/>
     * Years: 1960 - 2018
     *
     * @return
     */
    private static StringBuilder getPopulation() {
        return Fetcher.fetchData("demo_pjan", CommonParams.getPopulationParams());
    }
}
