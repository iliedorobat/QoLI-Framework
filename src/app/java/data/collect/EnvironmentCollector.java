package app.java.data.collect;

import app.java.commons.Constants;
import app.java.commons.TextUtils;
import app.java.data.fetch.dao.EnvironmentDAO;
import app.java.data.fetch.dao.impl.EnvironmentDAOImpl;

public class EnvironmentCollector {
    private static EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();

    public static void dataCollector() {
        StringBuilder pollutionRatio = environmentDAO.getPollutionRatio(),
                airPollutionRatio = environmentDAO.getAirPollutionRatio(),
                noisePollutionRatio = environmentDAO.getNoisePollutionRatio(),
                waterSupplyRatio = environmentDAO.getWaterSupplyRatio();

        TextUtils.writeToJSONFile(pollutionRatio, Constants.ENVIRONMENT_PATH + "pollutionRatio");
        TextUtils.writeToJSONFile(airPollutionRatio, Constants.ENVIRONMENT_PATH + "airPollutionRatio");
        TextUtils.writeToJSONFile(noisePollutionRatio, Constants.ENVIRONMENT_PATH + "noisePollutionRatio");
        TextUtils.writeToJSONFile(waterSupplyRatio, Constants.ENVIRONMENT_PATH + "waterSupplyRatio");
    }
}
