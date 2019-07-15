package app.java.data.collect;

import app.java.data.fetch.dao.EnvironmentDAO;
import app.java.data.fetch.dao.impl.EnvironmentDAOImpl;

public class EnvironmentCollector {
    private static EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();

    public static void dataCollector() {
        StringBuilder pollutionRatio = environmentDAO.getPollutionRatio(),
                airPollutionRatio = environmentDAO.getAirPollutionRatio(),
                noisePollutionRatio = environmentDAO.getNoisePollutionRatio(),
                waterSupplyRatio = environmentDAO.getWaterSupplyRatio();
    }
}
