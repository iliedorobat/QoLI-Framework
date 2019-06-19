package app.java.parser.local.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.local.dao.SafetyDAO;
import app.java.parser.local.model.InsseObject;
import app.java.parser.local.model.SafetyObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SafetyDAOImpl implements SafetyDAO {
    private static final String familyAssistPath = Constants.SAFETY_PATH +
            "ASS118D - Allowance for family support.csv";
    private static final String socialAssistPath = Constants.SAFETY_PATH +
            "ASS118F - Social allowances assuring the minimum guaranteed income.csv";
    private static final String socialDinnerGainerPath = Constants.SAFETY_PATH +
            "ASS119A - Public social care canteens beneficiary.csv";
    private static final String avgPensionPath = Constants.SAFETY_PATH +
            "PNS102D - Monthly average pension.csv";

    private static final String convictsPath = Constants.SAFETY_PATH +
            "JUS104C - Persons definitively convicted by the courts of justice.csv";
    private static final String policeOffenceSolvedPath = Constants.SAFETY_PATH +
            "JUS109A - Offences solved by the police and offences solved by the Prosecutors.csv";
    private static final String offenceRatePath = Constants.SAFETY_PATH +
            "JUS107B - Offence rate.csv";
    private static final String crimeRatePath = Constants.SAFETY_PATH +
            "JUS107A - Criminality rate.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<SafetyObject>> getSafetyLists(int start, int end) {
        ArrayList<ArrayList<SafetyObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getSafetyList(year));
        }

        return lists;
    }

    public ArrayList<SafetyObject> getSafetyList(int year) {
        ArrayList<SafetyObject> output = new ArrayList<>();
        HashMap<String, SafetyObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> familyAssistList = utils.getInsseObject(familyAssistPath, Constants.PARAMS.SAFETY);
        ArrayList<InsseObject> socialAssistList = utils.getInsseObject(socialAssistPath, Constants.PARAMS.SAFETY);
        ArrayList<InsseObject> socialDinnerGainerList = utils.getInsseObject(socialDinnerGainerPath, Constants.PARAMS.SAFETY);
        ArrayList<InsseObject> avgPensionList = utils.getInsseObject(avgPensionPath, Constants.PARAMS.SAFETY);

        ArrayList<InsseObject> convictsList = utils.getInsseObject(convictsPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> policeOffenceSolvedList = utils.getInsseObject(policeOffenceSolvedPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> offenceRateList = utils.getInsseObject(offenceRatePath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> crimeRateList = utils.getInsseObject(crimeRatePath, Constants.PARAMS.GENERAL);

        setHashMapValues(hashMap, familyAssistList, year, Constants.TYPES.FAMILY_ASSIST);
        setHashMapValues(hashMap, socialAssistList, year, Constants.TYPES.SOCIAL_ASSIST);
        setHashMapValues(hashMap, socialDinnerGainerList, year, Constants.TYPES.SOCIAL_DINNER_GAINER);
        setHashMapValues(hashMap, avgPensionList, year, Constants.TYPES.AVG_PENSION);

        setHashMapValues(hashMap, convictsList, year, Constants.TYPES.CONVICTS);
        setHashMapValues(hashMap, policeOffenceSolvedList, year, Constants.TYPES.POLICE_OFFENCE_SOLVED);
        setHashMapValues(hashMap, offenceRateList, year, Constants.TYPES.OFFENCE_RATE);
        setHashMapValues(hashMap, crimeRateList, year, Constants.TYPES.CRIME_RATE);

        for (int i = 0; i < regions.length; i++) {
            SafetyObject safetyObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (safetyObject == null) {
                safetyObject = new SafetyObject(regions[i], year);
            }
            output.add(safetyObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<SafetyObject>> lists = getSafetyLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<SafetyObject> mainList = getSafetyList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.FAMILY_ASSIST,
                Constants.TYPES.SOCIAL_ASSIST,
                Constants.TYPES.SOCIAL_DINNER_GAINER,
                Constants.TYPES.AVG_PENSION,

                Constants.TYPES.CONVICTS,
                Constants.TYPES.POLICE_OFFENCE_SOLVED,
                Constants.TYPES.OFFENCE_RATE,
                Constants.TYPES.CRIME_RATE,
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                SafetyObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, SafetyObject> hashMap,
                                  ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            SafetyObject conditions = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (conditions == null) {
                    conditions = new SafetyObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, conditions);
                }

                switch (queryType) {
                    case FAMILY_ASSIST:
                        conditions.setFamilyAssists(Long.parseLong(item.getValue()));
                        break;
                    case SOCIAL_ASSIST:
                        conditions.setSocialAssists(Long.parseLong(item.getValue()));
                        break;
                    case SOCIAL_DINNER_GAINER:
                        conditions.setSocialDinnerGainers((long) Double.parseDouble(item.getValue()));
                        break;
                    case AVG_PENSION:
                        conditions.setAvgPension(Double.parseDouble(item.getValue()));
                        break;

                    case CONVICTS:
                        conditions.setConvicts(Long.parseLong(item.getValue()));
                        break;
                    case POLICE_OFFENCE_SOLVED:
                        conditions.setPoliceOffenseSolved(Long.parseLong(item.getValue()));
                        break;
                    case OFFENCE_RATE:
                        conditions.setOffenceRate(Double.parseDouble(item.getValue()));
                        break;
                    case CRIME_RATE:
                        conditions.setCrimeRate(Double.parseDouble(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
