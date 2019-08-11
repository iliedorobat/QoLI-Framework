package app.java.data.collect;

import app.java.commons.FileUtils;
import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.data.fetch.dao.GeneralDAO;
import app.java.data.fetch.dao.impl.GeneralDAOImpl;

public class GeneralCollector {
    private static GeneralDAO dao = new GeneralDAOImpl();

    public static void dataCollector() {
        StringBuilder sb = dao.getPopulation();
        FileUtils.writeToJSONFile(sb, FilePathConst.DATASET_PATH + FileNameConst.POPULATION);
    }
}
