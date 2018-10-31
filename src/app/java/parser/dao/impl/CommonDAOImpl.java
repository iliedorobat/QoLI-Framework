package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.parser.dao.CommonDAO;
import app.java.parser.model.CommonObject;
import app.java.parser.model.InsseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonDAOImpl implements CommonDAO {
    private static final String activePopPath = Constants.DATASET_PATH +
            "FOM102A - Civil economically active population.csv";
    private static final String avgNetSalaryPath = Constants.DATASET_PATH +
            "FOM106E - Average monthly nominal net earnings.csv";
    private static final String residentPath = Constants.DATASET_PATH +
            "POP108B - Domicile population at July 1st.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();

    public ArrayList<ArrayList<CommonObject>> getCommonLists(int start, int end) {
        ArrayList<ArrayList<CommonObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getCommonList(year));
        }

        return lists;
    }

    public ArrayList<CommonObject> getCommonList(int year) {
        ArrayList<CommonObject> output = new ArrayList<>();
        HashMap<String, CommonObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> activePopList = getActivePopInsseObject(activePopPath, Constants.TOTAL);
        ArrayList<InsseObject> avgNetSalaryList = utils.getInsseObject(avgNetSalaryPath, Constants.PARAMS.COUNT);
        ArrayList<InsseObject> residentList = utils.getInsseObject(residentPath, Constants.PARAMS.RESIDENT_POPULATION);

        setHashMapValues(hashMap, activePopList, year, Constants.TYPES.ACTIVE_POP);
        setHashMapValues(hashMap, avgNetSalaryList, year, Constants.TYPES.AVG_NET_SALARY);
        setHashMapValues(hashMap, residentList, year, Constants.TYPES.RESIDENT_POPULATION);

        for (int i = 0; i < regions.length; i++) {
            CommonObject commonObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (commonObject == null) {
                commonObject = new CommonObject(regions[i], year);
            }
            output.add(commonObject);
        }

        return output;
    }

    private void setHashMapValues(HashMap<String, CommonObject> hashMap,
                                 ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            CommonObject common = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (common == null) {
                    common = new CommonObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, common);
                }

                switch (queryType) {
                    case ACTIVE_POP:
                        common.setActivePop(Double.parseDouble(item.getValue()));
                        break;
                    case AVG_NET_SALARY:
                        common.setAvgNetSalary(Double.parseDouble(item.getValue()));
                        break;
                    case RESIDENT_POPULATION:
                        common.setResidents(Long.parseLong(item.getValue()));
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
     * @param propertyType <b>String</b>: a special property
     * @return <b>ArrayList<InsseObject></b>
     */
    public ArrayList<InsseObject> getActivePopInsseObject(String filePath, String propertyType) {
        ArrayList<InsseObject> insseList = new ArrayList<>();
        String[] stringList = utils.getStringList(filePath);

        short genderIndex = 0;
        short regionIndex = 1;
        short yearIndex = 2;
        short descriptionIndex = 3;
        short valueIndex = 4;

        for (int i = 1; i < stringList.length; i++) {
            String[] elements = stringList[i].split(",");
            String gender = elements[genderIndex].trim().toLowerCase();

            if (propertyType.equals(gender)) {
                utils.addInsseObject(insseList, elements, regionIndex, yearIndex, descriptionIndex, valueIndex);
            }

        }

        return insseList;
    }
}
