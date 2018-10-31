package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.dao.MainActivityDAO;
import app.java.parser.model.InsseObject;
import app.java.parser.model.MainActivityObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivityDAOImpl implements MainActivityDAO {
    private static final String gdpPath = Constants.MAIN_ACTIVITY_PATH +
            "CON103I - GDP, calculated according CANE Rev.2 - ESA 2010.csv";
    private static final String researcherPath = Constants.MAIN_ACTIVITY_PATH +
            "CDP102E - Employees from research.csv";
    private static final String empRatePath = Constants.MAIN_ACTIVITY_PATH +
            "FOM116A - Employment rate of labour resources.csv";
    private static final String unempRatePath = Constants.MAIN_ACTIVITY_PATH +
            "SOM103A - Unemployment rate.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<MainActivityObject>> getMainActivityLists(int start, int end) {
        ArrayList<ArrayList<MainActivityObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getMainActivityList(year));
        }

        return lists;
    }

    public ArrayList<MainActivityObject> getMainActivityList(int year) {
        ArrayList<MainActivityObject> output = new ArrayList<>();
        HashMap<String, MainActivityObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> gdpList = utils.getInsseObject(gdpPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> researcherList = utils.getInsseObject(researcherPath, Constants.PARAMS.RESEARCHER);
        ArrayList<InsseObject> empRateList = utils.getInsseObject(empRatePath, Constants.PARAMS.RATE);
        ArrayList<InsseObject> unempRateList = utils.getInsseObject(unempRatePath, Constants.PARAMS.RATE);

        setHashMapValues(hashMap, gdpList, year, Constants.TYPES.GDP);
        setHashMapValues(hashMap, researcherList, year, Constants.TYPES.RESEARCHER);
        setHashMapValues(hashMap, empRateList, year, Constants.TYPES.EMP_RATE);
        setHashMapValues(hashMap, unempRateList, year, Constants.TYPES.UNEMP_RATE);

        for (int i = 0; i < regions.length; i++) {
            MainActivityObject mainActivityObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (mainActivityObject == null) {
                mainActivityObject = new MainActivityObject(regions[i], year);
            }
            output.add(mainActivityObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<MainActivityObject>> lists = getMainActivityLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<MainActivityObject> mainList = getMainActivityList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.GDP,
                Constants.TYPES.RESEARCHER,
                Constants.TYPES.EMP_RATE,
                Constants.TYPES.UNEMP_RATE
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                MainActivityObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, MainActivityObject> hashMap,
                                 ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            MainActivityObject mainActivity = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (mainActivity == null) {
                    mainActivity = new MainActivityObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, mainActivity);
                }

                switch (queryType) {
                    case GDP:
                        mainActivity.setGdp(Double.parseDouble(item.getValue()));
                        break;
                    case RESEARCHER:
                        mainActivity.setResearchers(Long.parseLong(item.getValue()));
                        break;
                    case EMP_RATE:
                        mainActivity.setEmpRate(Double.parseDouble(item.getValue()));
                        break;
                    case UNEMP_RATE:
                        mainActivity.setUnempRate(Double.parseDouble(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
