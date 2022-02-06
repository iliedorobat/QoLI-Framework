package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.FetcherUtils;

public class GeneralCollector {
    public static void dataCollector() {
        StringBuilder population = FetcherUtils.getPopulation();
        FileUtils.writeToJSONFile(population, FilePathConst.DATASET_PATH, FileNameConst.POPULATION);
    }
}
