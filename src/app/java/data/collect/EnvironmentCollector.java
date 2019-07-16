package app.java.data.collect;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.FileUtils;
import app.java.data.fetch.dao.EnvironmentDAO;
import app.java.data.fetch.dao.impl.EnvironmentDAOImpl;

public class EnvironmentCollector {
    private static EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();

    public static void dataCollector() {
        StringBuilder pollutionRatio = environmentDAO.getPollutionRatio(),
                airPollutionRatio = environmentDAO.getAirPollutionRatio(),
                noisePollutionRatio = environmentDAO.getNoisePollutionRatio(),
                waterSupplyRatio = environmentDAO.getWaterSupplyRatio();

        FileUtils.writeToJSONFile(pollutionRatio, FilePathConst.ENVIRONMENT_PATH + FileNameConst.POLLUTION_RATIO);
        FileUtils.writeToJSONFile(airPollutionRatio, FilePathConst.ENVIRONMENT_PATH + FileNameConst.AIR_POLLUTION_RATIO);
        FileUtils.writeToJSONFile(noisePollutionRatio, FilePathConst.ENVIRONMENT_PATH + FileNameConst.NOISE_POLLUTION_RATIO);
        FileUtils.writeToJSONFile(waterSupplyRatio, FilePathConst.ENVIRONMENT_PATH + FileNameConst.WATER_SUPPLY_RATIO);
    }
}
