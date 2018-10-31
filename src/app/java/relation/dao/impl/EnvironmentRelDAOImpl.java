package app.java.relation.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.EnvironmentRelDAO;
import app.java.commons.Constants;
import app.java.commons.MathUtils;
import app.java.parser.dao.EnvironmentDAO;
import app.java.parser.dao.impl.EnvironmentDAOImpl;
import app.java.parser.model.EnvironmentObject;

import java.util.ArrayList;

public class EnvironmentRelDAOImpl implements EnvironmentRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();

    // greenSpaces = suprafata spatiilor verzi amenajate sub forma de parcuri, gradini publice sau scuaruri publice,
    // parcele cu pomi si flori, paduri, cimitirele, terenurile bazelor si amenajarilor sportive in cadrul
    // perimetrelor construibile ale localitatilor
    // waterAccess = reteaua publica de distributie centralizata
    public double getEnvironmentRate(String region, short year) {
        ArrayList<ArrayList<EnvironmentObject>> lists = environmentDAO.getEnvironmentLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<EnvironmentObject> mainList = environmentDAO.getEnvironmentList(year);

        double residents = relation.getResidents(region, year);
        double environment = 0;

        for (int i = 0; i < mainList.size(); i++) {
            EnvironmentObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double greenSpacesRate = getGreenSpacesRate(lists, item, residents);
                double waterAccessRate = getWaterAccessRate(lists, item, residents);
                environment = greenSpacesRate * waterAccessRate;
            }
        }

        if (environment == 0)
            System.err.println("Environment Index: " + environment);

        return math.getSquareValue(environment, 2);
    }

    private double getGreenSpacesRate(ArrayList<ArrayList<EnvironmentObject>> lists,
                                    EnvironmentObject item, double residents) {
        String itemRegion = item.getRegion();
        double greenSpaces = item.getGreenSpaces();

        if (greenSpaces == 0)
            greenSpaces = item.getAvgRate(lists, itemRegion, Constants.TYPES.GREEN_SPACE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.ENVIRONMENT, Constants.TYPES.GREEN_SPACE);

        return greenSpaces / residents * Constants.HA_TO_M;
    }

    private double getWaterAccessRate(ArrayList<ArrayList<EnvironmentObject>> lists,
                                      EnvironmentObject item, double residents) {
        String itemRegion = item.getRegion();
        double waterAccess = item.getWaterAccess();

        if (waterAccess == 0)
            waterAccess = item.getAvgRate(lists, itemRegion, Constants.TYPES.WATER_ACCESS);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.ENVIRONMENT, Constants.TYPES.WATER_ACCESS);

        return waterAccess / residents * 100;
    }
}
