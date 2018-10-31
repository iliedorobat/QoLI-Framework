package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.dao.HealthDAO;
import app.java.parser.model.HealthObject;
import app.java.parser.model.InsseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthDAOImpl implements HealthDAO {
    private static final String alcoholPath = Constants.HEALTH_PATH + "consumtion/" +
            "BUF113J - HBS The average alcoholic drinks consumtion by MACROREGIONS.csv";
    private static final String fruitPath = Constants.HEALTH_PATH + "consumtion/" +
            "BUF113J - HBS The average fruits consumtion by MACROREGIONS.csv";
    private static final String vegetablePath = Constants.HEALTH_PATH + "consumtion/" +
            "BUF113J - HBS The average vegetables consumtion by MACROREGIONS.csv";

    private static final String generalMedCabPath = Constants.HEALTH_PATH + "infrastructure/" +
            "SAN101A - General medical centers.csv";
    private static final String specializedMedCabPath = Constants.HEALTH_PATH + "infrastructure/" +
            "SAN101A - Speciality medical centers.csv";
    private static final String dentalCabPath = Constants.HEALTH_PATH + "infrastructure/" +
            "SAN101A - Dental centers.csv";
    private static final String hospitalPath = Constants.HEALTH_PATH + "infrastructure/" +
            "SAN101A - Hospitals.csv";
    private static final String hospitalBedsPath = Constants.HEALTH_PATH + "infrastructure/" +
            "SAN102B - Number of the beds from sanitary units.csv";

    private static final String secEduStaffPath = Constants.HEALTH_PATH + "medical-staff/" +
            "SAN104A - Secondary education medical staff.csv";
    private static final String nurseHightEduPath = Constants.HEALTH_PATH + "medical-staff/" +
            "SAN104A - Nurses with higher education.csv";
    private static final String doctorPath = Constants.HEALTH_PATH + "medical-staff/" +
            "SAN104A - Physicians (dentists excluded).csv";
    private static final String dentistPath = Constants.HEALTH_PATH + "medical-staff/" +
            "SAN104A - Dentists.csv";
    private static final String pharmaStaffPath = Constants.HEALTH_PATH + "medical-staff/" +
            "SAN104A - Pharmaceutical chemists.csv";

    private static final String natPopGrowthRatePath = Constants.HEALTH_PATH +
            "POP215A - Rate of natural increase of the population.csv";
    private static final String avgLifeExpectancyPath = Constants.HEALTH_PATH +
            "POP217A - Life expectancy.csv";
    private static final String hurtInWorkPath = Constants.HEALTH_PATH +
            "ACC102B - Injured persons by type of accidents at work.csv";
    private static final String hurtInTrafficPath = Constants.HEALTH_PATH +
            "TRN105B - Casualties in road traffic injury accidents.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<HealthObject>> getHealthLists(int start, int end) {
        ArrayList<ArrayList<HealthObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getHealthList(year));
        }

        return lists;
    }

    public ArrayList<HealthObject> getHealthList(int year) {
        ArrayList<HealthObject> output = new ArrayList<>();
        HashMap<String, HealthObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> alcoholList = getHealthInsseObject(alcoholPath, Constants.PARAMS.COUNT, Constants.TOTAL);
        ArrayList<InsseObject> fruitList = getHealthInsseObject(fruitPath, Constants.PARAMS.COUNT, Constants.TOTAL);
        ArrayList<InsseObject> vegetableList = getHealthInsseObject(vegetablePath, Constants.PARAMS.COUNT, Constants.TOTAL);

        ArrayList<InsseObject> totalGeneralMedCabList = getHealthInsseObject(generalMedCabPath, Constants.PARAMS.MEDICAL_CAB, Constants.TOTAL);
        ArrayList<InsseObject> totalSpecializedMedCabList = getHealthInsseObject(specializedMedCabPath, Constants.PARAMS.MEDICAL_CAB, Constants.TOTAL);
        ArrayList<InsseObject> totalDentalMedCabList = getHealthInsseObject(dentalCabPath, Constants.PARAMS.MEDICAL_CAB, Constants.TOTAL);
        ArrayList<InsseObject> totalHospitalList = getHealthInsseObject(hospitalPath, Constants.PARAMS.MEDICAL_CAB, Constants.TOTAL);
        ArrayList<InsseObject> totalHospitalBedList = getHealthInsseObject(hospitalBedsPath, Constants.PARAMS.MEDICAL_CAB, Constants.TOTAL);

        ArrayList<InsseObject> totalSecEduStaffList = getHealthInsseObject(secEduStaffPath, Constants.PARAMS.MEDICAL_STAFF, Constants.TOTAL);
        ArrayList<InsseObject> totalNurseHighEduList = getHealthInsseObject(nurseHightEduPath, Constants.PARAMS.MEDICAL_STAFF, Constants.TOTAL);
        ArrayList<InsseObject> totalDoctorList = getHealthInsseObject(doctorPath, Constants.PARAMS.MEDICAL_STAFF, Constants.TOTAL);
        ArrayList<InsseObject> totalDentistList = getHealthInsseObject(dentistPath, Constants.PARAMS.MEDICAL_STAFF, Constants.TOTAL);
        ArrayList<InsseObject> totalPharmaStaffList = getHealthInsseObject(pharmaStaffPath, Constants.PARAMS.MEDICAL_STAFF, Constants.TOTAL);

        ArrayList<InsseObject> naturalPopGrowthRateList = utils.getInsseObject(natPopGrowthRatePath, Constants.PARAMS.MEDICAL);
        ArrayList<InsseObject> avgLifeExpectancyList = utils.getInsseObject(avgLifeExpectancyPath, Constants.PARAMS.COUNT);
        ArrayList<InsseObject> hurtInWorkList = utils.getInsseObject(hurtInWorkPath, Constants.PARAMS.MEDICAL);
        ArrayList<InsseObject> hurtInTrafficList = utils.getInsseObject(hurtInTrafficPath, Constants.PARAMS.MEDICAL);

        setHashMapValues(hashMap, alcoholList, year, Constants.TYPES.ALCOHOL_COUNT);
        setHashMapValues(hashMap, fruitList, year, Constants.TYPES.FRUIT_COUNT);
        setHashMapValues(hashMap, vegetableList, year, Constants.TYPES.VEGETABLE_COUNT);

        setHashMapValues(hashMap, totalGeneralMedCabList, year, Constants.TYPES.GENERAL_MED_CAB_TOTAL_COUNT);
        setHashMapValues(hashMap, totalSpecializedMedCabList, year, Constants.TYPES.SPECIALIZED_MED_CAB_TOTAL_COUNT);
        setHashMapValues(hashMap, totalDentalMedCabList, year, Constants.TYPES.DENTAL_MED_CAB_TOTAL_COUNT);
        setHashMapValues(hashMap, totalHospitalList, year, Constants.TYPES.HOSPITAL_TOTAL_COUNT);
        setHashMapValues(hashMap, totalHospitalBedList, year, Constants.TYPES.HOSPITAL_BED_TOTAL_COUNT);

        setHashMapValues(hashMap, totalSecEduStaffList, year, Constants.TYPES.SEC_EDU_STAFF_TOTAL_COUNT);
        setHashMapValues(hashMap, totalNurseHighEduList, year, Constants.TYPES.NURSE_HIGH_EDU_TOTAL_COUNT);
        setHashMapValues(hashMap, totalDoctorList, year, Constants.TYPES.DOCTOR_TOTAL_COUNT);
        setHashMapValues(hashMap, totalDentistList, year, Constants.TYPES.DENTIST_TOTAL_COUNT);
        setHashMapValues(hashMap, totalPharmaStaffList, year, Constants.TYPES.PHARMA_STAFF_TOTAL_COUNT);

        setHashMapValues(hashMap, naturalPopGrowthRateList, year, Constants.TYPES.NATURAL_POP_GROWTH_RATE);
        setHashMapValues(hashMap, avgLifeExpectancyList, year, Constants.TYPES.AVG_LIFE_EXPECTANCY);
        setHashMapValues(hashMap, hurtInWorkList, year, Constants.TYPES.HURT_IN_WORK_COUNT);
        setHashMapValues(hashMap, hurtInTrafficList, year, Constants.TYPES.HURT_IN_TRAFFIC_COUNT);

        for (int i = 0; i < regions.length; i++) {
            HealthObject healthObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (healthObject == null) {
                healthObject = new HealthObject(regions[i], year);
            }
            output.add(healthObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<HealthObject>> lists = getHealthLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<HealthObject> mainList = getHealthList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.ALCOHOL_COUNT,
                Constants.TYPES.FRUIT_COUNT,
                Constants.TYPES.VEGETABLE_COUNT,

                Constants.TYPES.GENERAL_MED_CAB_TOTAL_COUNT,
                Constants.TYPES.SPECIALIZED_MED_CAB_TOTAL_COUNT,
                Constants.TYPES.DENTAL_MED_CAB_TOTAL_COUNT,
                Constants.TYPES.HOSPITAL_TOTAL_COUNT,
                Constants.TYPES.HOSPITAL_CHILDREN_BED_TOTAL_COUNT,
                Constants.TYPES.HOSPITAL_BED_TOTAL_COUNT,

                Constants.TYPES.SEC_EDU_STAFF_TOTAL_COUNT,
                Constants.TYPES.NURSE_HIGH_EDU_TOTAL_COUNT,
                Constants.TYPES.DOCTOR_TOTAL_COUNT,
                Constants.TYPES.DENTIST_TOTAL_COUNT,
                Constants.TYPES.PHARMA_STAFF_TOTAL_COUNT,

                Constants.TYPES.NATURAL_POP_GROWTH_RATE,
                Constants.TYPES.AVG_LIFE_EXPECTANCY,
                Constants.TYPES.HURT_IN_WORK_COUNT,
                Constants.TYPES.HURT_IN_TRAFFIC_COUNT
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                HealthObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, HealthObject> hashMap,
                                  ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            HealthObject health = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (health == null) {
                    health = new HealthObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, health);
                }

                switch (queryType) {
                    case ALCOHOL_COUNT:
                        health.setAlcohol(Double.parseDouble(item.getValue()));
                        break;
                    case FRUIT_COUNT:
                        health.setFruits(Double.parseDouble((item.getValue())));
                        break;
                    case VEGETABLE_COUNT:
                        health.setVegetables(Double.parseDouble((item.getValue())));
                        break;

                    case GENERAL_MED_CAB_TOTAL_COUNT:
                        health.setTotalGenMedCab(Long.parseLong(item.getValue()));
                        break;
                    case SPECIALIZED_MED_CAB_TOTAL_COUNT:
                        health.setTotalSpecializedMedCab(Long.parseLong(item.getValue()));
                        break;
                    case DENTAL_MED_CAB_TOTAL_COUNT:
                        health.setTotalDentalMedCab(Long.parseLong(item.getValue()));
                        break;
                    case HOSPITAL_TOTAL_COUNT:
                        health.setTotalHospitals(Long.parseLong(item.getValue()));
                        break;
                    case HOSPITAL_CHILDREN_BED_TOTAL_COUNT:
                        health.setTotalChildrenHospitalsBeds(Long.parseLong(item.getValue()));
                        break;
                    case HOSPITAL_BED_TOTAL_COUNT:
                        health.setTotalHospitalsBeds(Long.parseLong(item.getValue()));
                        break;

                    case SEC_EDU_STAFF_TOTAL_COUNT:
                        health.setTotalSecEduMedicalStaff(Long.parseLong(item.getValue()));
                        break;
                    case NURSE_HIGH_EDU_TOTAL_COUNT:
                        health.setTotalNursesHighEdu(Long.parseLong(item.getValue()));
                        break;
                    case DOCTOR_TOTAL_COUNT:
                        health.setTotalDoctors(Long.parseLong(item.getValue()));
                        break;
                    case DENTIST_TOTAL_COUNT:
                        health.setTotalDentists(Long.parseLong(item.getValue()));
                        break;
                    case PHARMA_STAFF_TOTAL_COUNT:
                        health.setTotalPharmaStaff(Long.parseLong(item.getValue()));
                        break;

                    case NATURAL_POP_GROWTH_RATE:
                        health.setNatPopGrowthRate(Double.parseDouble(item.getValue()));
                        break;
                    case AVG_LIFE_EXPECTANCY:
                        health.setAvgLifeExpectancy(Double.parseDouble(item.getValue()));
                        break;
                    case HURT_IN_WORK_COUNT:
                        health.setHurtsInWork(Long.parseLong(item.getValue()));
                        break;
                    case HURT_IN_TRAFFIC_COUNT:
                        health.setHurtsInTraffic(Long.parseLong(item.getValue()));
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
    public ArrayList<InsseObject> getHealthInsseObject(String filePath, Constants.PARAMS queryType, String propertyType) {
        ArrayList<InsseObject> insseList = new ArrayList<>();
        String[] stringList = utils.getStringList(filePath);

        short typeIndex = 1;
        short regionIndex = 2;
        short yearIndex = 3;
        short descriptionIndex = 4;
        short valueIndex = 5;

        if (queryType == Constants.PARAMS.MEDICAL_STAFF) {
            regionIndex = 3;
            yearIndex = 4;
            descriptionIndex = 5;
            valueIndex = 6;
        }

        // The first element is the file description
        for (int i = 1; i < stringList.length; i++) {
            String[] elements = stringList[i].split(",");
            String type = elements[typeIndex].trim().toLowerCase();

            if (propertyType.equals(type)) {
                utils.addInsseObject(insseList, elements, regionIndex, yearIndex, descriptionIndex, valueIndex);
            }
        }

        return insseList;
    }
}
