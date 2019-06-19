package app.java.parser.local.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.local.dao.MaterialLifeDAO;
import app.java.parser.local.model.InsseObject;
import app.java.parser.local.model.MaterialLifeObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MaterialLifeDAOImpl implements MaterialLifeDAO {
    private static final String avgTotalIncomePath = Constants.MATERIAL_LIFE_PATH +
            "BUF104J - HBS Monthly averages of total income per household.csv";
    private static final String avgTotalSpendPath = Constants.MATERIAL_LIFE_PATH +
            "BUF106J - HBS Monthly averages of total expenditure per household.csv";
    private static final String relativePovertyPath = Constants.MATERIAL_LIFE_PATH +
            "SAR102D - Relative at-risk-of-poverty rate.csv";
    private static final String povertyRiskPath = Constants.MATERIAL_LIFE_PATH +
            "SAR111C - AROPE-at risk of poverty or social exclusion rate.csv";
    private static final String severeMaterialDeprivationRatePath = Constants.MATERIAL_LIFE_PATH +
            "SAR112C - Severe material deprivation rate.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<MaterialLifeObject>> getMaterialLifeLists(int start, int end) {
        ArrayList<ArrayList<MaterialLifeObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getMaterialLifeList(year));
        }

        return lists;
    }

    public ArrayList<MaterialLifeObject> getMaterialLifeList(int year) {
        ArrayList<MaterialLifeObject> output = new ArrayList<>();
        HashMap<String, MaterialLifeObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> incomeList = utils.getInsseObject(avgTotalIncomePath, Constants.PARAMS.MATERIAL_LIFE_MONEY);
        ArrayList<InsseObject> spentList = utils.getInsseObject(avgTotalSpendPath, Constants.PARAMS.MATERIAL_LIFE_MONEY);
        ArrayList<InsseObject> relativePovertyList = utils.getInsseObject(relativePovertyPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> povertyRiskList = utils.getInsseObject(povertyRiskPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> depravationRateList = utils.getInsseObject(severeMaterialDeprivationRatePath, Constants.PARAMS.GENERAL);

        setHashMapValues(hashMap, incomeList, year, Constants.TYPES.AVG_TOTAL_INCOME);
        setHashMapValues(hashMap, spentList, year, Constants.TYPES.AVG_TOTAL_SPEND);
        setHashMapValues(hashMap, relativePovertyList, year, Constants.TYPES.RELATIVE_POVERTY);
        setHashMapValues(hashMap, povertyRiskList, year, Constants.TYPES.POVERTY_RISK);
        setHashMapValues(hashMap, depravationRateList, year, Constants.TYPES.DEPRIVATION_RATE);

        for (int i = 0; i < regions.length; i++) {
            MaterialLifeObject materialLifeObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (materialLifeObject == null) {
                materialLifeObject = new MaterialLifeObject(regions[i], year);
            }
            output.add(materialLifeObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<MaterialLifeObject>> lists = getMaterialLifeLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<MaterialLifeObject> mainList = getMaterialLifeList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.AVG_TOTAL_INCOME,
                Constants.TYPES.AVG_TOTAL_SPEND,
                Constants.TYPES.DEPRIVATION_RATE,
                Constants.TYPES.RELATIVE_POVERTY,
                Constants.TYPES.POVERTY_RISK
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                MaterialLifeObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, MaterialLifeObject> hashMap,
                                  ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            MaterialLifeObject conditions = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (conditions == null) {
                    conditions = new MaterialLifeObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, conditions);
                }

                switch (queryType) {
                    case AVG_TOTAL_INCOME:
                        conditions.setAvgTotalIncome(Double.parseDouble(item.getValue()));
                        break;
                    case AVG_TOTAL_SPEND:
                        conditions.setAvgTotalSpend(Double.parseDouble(item.getValue()));
                        break;
                    case DEPRIVATION_RATE:
                        conditions.setDeprivationRate(Double.parseDouble(item.getValue()));
                        break;
                    case RELATIVE_POVERTY:
                        conditions.setRelativePoverty(Double.parseDouble(item.getValue()));
                        break;
                    case POVERTY_RISK:
                        conditions.setPovertyRisk(Double.parseDouble(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
