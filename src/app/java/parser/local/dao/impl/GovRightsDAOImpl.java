package app.java.parser.local.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.local.dao.GovRightsDAO;
import app.java.parser.local.model.GovRightsObject;
import app.java.parser.local.model.InsseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GovRightsDAOImpl implements GovRightsDAO {
    private static final String empRatePath = Constants.GOV_RIGHTS_PATH +
            "AMG156F - AMIGO - Employment rate.csv";
    private static final String voteRatePath = Constants.GOV_RIGHTS_PATH +
            "Rate of attendance at parliamentary elections.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<GovRightsObject>> getGovRightsLists(int start, int end) {
        ArrayList<ArrayList<GovRightsObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getGovRightsList(year));
        }

        return lists;
    }

    public ArrayList<GovRightsObject> getGovRightsList(int year) {
        ArrayList<GovRightsObject> output = new ArrayList<>();
        HashMap<String, GovRightsObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> maleEmpRateList = getGovRightsInsseObject(empRatePath, Constants.PARAMS.RATE, Constants.SEX_MASCULIN);
        ArrayList<InsseObject> femaleEmpRateList = getGovRightsInsseObject(empRatePath, Constants.PARAMS.RATE, Constants.SEX_FEMININ);
        ArrayList<InsseObject> voteRateList = getGovRightsInsseObject(voteRatePath, Constants.PARAMS.GENERAL, Constants.PARLIAMENTARY);

        setHashMapValues(hashMap, maleEmpRateList, year, Constants.TYPES.MALE_EMP_RATE);
        setHashMapValues(hashMap, femaleEmpRateList, year, Constants.TYPES.FEMALE_EMP_RATE);
        setHashMapValues(hashMap, voteRateList, year, Constants.TYPES.VOTE_RATE);

        for (int i = 0; i < regions.length; i++) {
            GovRightsObject govRightsObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (govRightsObject == null) {
                govRightsObject = new GovRightsObject(regions[i], year);
            }
            output.add(govRightsObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<GovRightsObject>> lists = getGovRightsLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<GovRightsObject> mainList = getGovRightsList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.MALE_EMP_RATE,
                Constants.TYPES.FEMALE_EMP_RATE,
                Constants.TYPES.VOTE_RATE
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                GovRightsObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, GovRightsObject> hashMap,
                                  ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            GovRightsObject govRights = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (govRights == null) {
                    govRights = new GovRightsObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, govRights);
                }

                switch (queryType) {
                    case MALE_EMP_RATE:
                        govRights.setMaleEmpRate(Double.parseDouble(item.getValue()));
                        break;
                    case FEMALE_EMP_RATE:
                        govRights.setFemaleEmpRate(Double.parseDouble(item.getValue()));
                        break;
                    case VOTE_RATE:
                        govRights.setVoteRate(Double.parseDouble(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Get the list with the processed data
     * @param filePath <b>String</b>: the path to the desired file
     * @param queryType <b>Constants.PARAMS</b>: a flag
     * @param propertyType <b>String</b>: a special property
     * @return <b>ArrayList<InsseObject></b>
     */
    public ArrayList<InsseObject> getGovRightsInsseObject(String filePath, Constants.PARAMS queryType, String propertyType) {
        ArrayList<InsseObject> insseList = new ArrayList<>();
        String[] stringList = utils.getStringList(filePath);

        short typeIndex = -1;
        short regionIndex = 0;
        short yearIndex = 1;
        short descriptionIndex = 2;
        short valueIndex = 3;

        if (queryType == Constants.PARAMS.RATE) {
            typeIndex = 1;
            regionIndex = 2;
            yearIndex = 3;
            descriptionIndex = 4;
            valueIndex = 5;
        }

        // The first element is the file description
        for (int i = 1; i < stringList.length; i++) {
            String[] elements = stringList[i].split(",");
            String type = "";

            if (typeIndex > -1)
                type = elements[typeIndex].trim().toLowerCase();

            if (propertyType.equals(Constants.PARLIAMENTARY))
                type = Constants.PARLIAMENTARY;

            if (propertyType.equals(type)) {
                utils.addInsseObject(insseList, elements, regionIndex, yearIndex, descriptionIndex, valueIndex);
            }
        }

        return insseList;
    }
}
