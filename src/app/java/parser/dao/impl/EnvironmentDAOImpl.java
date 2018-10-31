package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.dao.EnvironmentDAO;
import app.java.parser.model.EnvironmentObject;
import app.java.parser.model.InsseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EnvironmentDAOImpl implements EnvironmentDAO {
    //TODO: PMI109A - Population connected to waste water collecting systems and waste water treatment plants
    private static final String greenSpacesPath = Constants.ENVIRONMENT_PATH +
            "GOS103B - Verdure spots area in municipalities and towns.csv";
    private static final String waterPath = Constants.ENVIRONMENT_PATH +
            "PMI109B - Population connected to public water supply.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<EnvironmentObject>> getEnvironmentLists(int start, int end) {
        ArrayList<ArrayList<EnvironmentObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getEnvironmentList(year));
        }

        return lists;
    }

    public ArrayList<EnvironmentObject> getEnvironmentList(int year) {
        ArrayList<EnvironmentObject> output = new ArrayList<>();
        HashMap<String, EnvironmentObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> greenSpacesList = utils.getInsseObject(greenSpacesPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> waterList = utils.getInsseObject(waterPath, Constants.PARAMS.GENERAL);

        setHashMapValues(hashMap, greenSpacesList, year, Constants.TYPES.GREEN_SPACE);
        setHashMapValues(hashMap, waterList, year, Constants.TYPES.WATER_ACCESS);

        for (int i = 0; i < regions.length; i++) {
            EnvironmentObject environmentObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (environmentObject == null) {
                environmentObject = new EnvironmentObject(regions[i], year);
            }
            output.add(environmentObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<EnvironmentObject>> lists = getEnvironmentLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<EnvironmentObject> mainList = getEnvironmentList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.GREEN_SPACE,
                Constants.TYPES.WATER_ACCESS
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                EnvironmentObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, EnvironmentObject> hashMap,
                                 ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            EnvironmentObject environment = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (environment == null) {
                    environment = new EnvironmentObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, environment);
                }

                switch (queryType) {
                    case GREEN_SPACE:
                        environment.setGreenSpaces(Long.parseLong(item.getValue()));
                        break;
                    case WATER_ACCESS:
                        environment.setWaterAccess(Long.parseLong(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
