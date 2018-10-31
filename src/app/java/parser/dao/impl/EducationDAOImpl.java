package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.dao.EducationDAO;
import app.java.parser.model.EducationObject;
import app.java.parser.model.InsseObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EducationDAOImpl implements EducationDAO {
    private static final String schoolsNoPath = Constants.EDUCATION_PATH +
            "SCL101B - Education units.csv";
    private static final String facultiesNoPath = Constants.EDUCATION_PATH +
            "SCL101E - Faculties.csv";
    private static final String teachersNoPath = Constants.EDUCATION_PATH +
            "SCL104A - Classroom teachers.csv";
    private static final String studentsNoPath = Constants.EDUCATION_PATH +
            "SCL103E - Enrolled population.csv";
    private static final String abandonRatePath = Constants.EDUCATION_PATH +
            "SCL113A - Abandon rate in pre-university education.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<EducationObject>> getEducationLists(int start, int end) {
        ArrayList<ArrayList<EducationObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getEducationList(year));
        }

        return lists;
    }

    public ArrayList<EducationObject> getEducationList(int year) {
        ArrayList<EducationObject> output = new ArrayList<>();
        HashMap<String, EducationObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> schoolsNoList = getEducationInsseObject(schoolsNoPath, Constants.PARAMS.SCHOOLS_COUNT, Constants.TOTAL);
        ArrayList<InsseObject> facultiesNoList = getEducationInsseObject(facultiesNoPath, Constants.PARAMS.FACULTIES_COUNT, Constants.TOTAL);
        ArrayList<InsseObject> teachersNoList = getEducationInsseObject(teachersNoPath, Constants.PARAMS.TEACHERS_COUNT, Constants.TOTAL);
        ArrayList<InsseObject> studentsNoList = getEducationInsseObject(studentsNoPath, Constants.PARAMS.STUDENTS_COUNT, Constants.TOTAL);

        ArrayList<InsseObject> abandonRateIList = getEducationInsseObject(abandonRatePath, Constants.PARAMS.ABANDON_RATE, Constants.EDUCATION_I);
        ArrayList<InsseObject> abandonRateIIList = getEducationInsseObject(abandonRatePath, Constants.PARAMS.ABANDON_RATE, Constants.EDUCATION_II);
        ArrayList<InsseObject> abandonRateIIIList = getEducationInsseObject(abandonRatePath, Constants.PARAMS.ABANDON_RATE, Constants.EDUCATION_III);

        setHashMapValues(hashMap, schoolsNoList, year, Constants.TYPES.SCHOOLS_COUNT);
        setHashMapValues(hashMap, facultiesNoList, year, Constants.TYPES.FACULTIES_COUNT);
        setHashMapValues(hashMap, teachersNoList, year, Constants.TYPES.TEACHERS_COUNT);
        setHashMapValues(hashMap, studentsNoList, year, Constants.TYPES.STUDENTS_COUNT);

        setHashMapValues(hashMap, abandonRateIList, year, Constants.TYPES.ABANDON_RATE_I);
        setHashMapValues(hashMap, abandonRateIIList, year, Constants.TYPES.ABANDON_RATE_II);
        setHashMapValues(hashMap, abandonRateIIIList, year, Constants.TYPES.ABANDON_RATE_III);

        for (int i = 0; i < regions.length; i++) {
            EducationObject educationObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (educationObject == null) {
                educationObject = new EducationObject(regions[i], year);
            }
            output.add(educationObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<EducationObject>> lists = getEducationLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<EducationObject> mainList = getEducationList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.TEACHERS_COUNT,
                Constants.TYPES.SCHOOLS_COUNT,
                Constants.TYPES.FACULTIES_COUNT,
                Constants.TYPES.ABANDON_RATE_I,
                Constants.TYPES.ABANDON_RATE_II,
                Constants.TYPES.ABANDON_RATE_III
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                EducationObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, EducationObject> hashMap,
                                 ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            EducationObject education = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (education == null) {
                    education = new EducationObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, education);
                }

                switch (queryType) {
                    case SCHOOLS_COUNT:
                        education.setSchoolsCount(Long.parseLong(item.getValue()));
                        break;
                    case FACULTIES_COUNT:
                        education.setFacultiesCount(Long.parseLong(item.getValue()));
                        break;
                    case TEACHERS_COUNT:
                        education.setTeachersCount(Long.parseLong(item.getValue()));
                        break;
                    case STUDENTS_COUNT:
                        education.setStudentsCount(Long.parseLong(item.getValue()));
                        break;
                    case ABANDON_RATE_I:
                        education.setAbandonRateI(Double.parseDouble(item.getValue()));
                        break;
                    case ABANDON_RATE_II:
                        education.setAbandonRateII(Double.parseDouble(item.getValue()));
                        break;
                    case ABANDON_RATE_III:
                        education.setAbandonRateIII(Double.parseDouble(item.getValue()));
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
    private ArrayList<InsseObject> getEducationInsseObject(String filePath, Constants.PARAMS queryType, String propertyType) {
        ArrayList<InsseObject> insseList = new ArrayList<>();
        String[] stringList = utils.getStringList(filePath);

        short typeIndex = -1;
        short regionIndex = 0;
        short yearIndex = 1;
        short descriptionIndex = 2;
        short valueIndex = 3;


        if (queryType == Constants.PARAMS.FACULTIES_COUNT ||
                queryType == Constants.PARAMS.ABANDON_RATE) {
            typeIndex = 0;
            regionIndex = 1;
            yearIndex = 2;
            descriptionIndex = 3;
            valueIndex = 4;
        }

        if (queryType == Constants.PARAMS.SCHOOLS_COUNT ||
                queryType == Constants.PARAMS.TEACHERS_COUNT ||
                queryType == Constants.PARAMS.STUDENTS_COUNT) {
            typeIndex = 0;
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

            if (propertyType.equals(type)) {
                utils.addInsseObject(insseList, elements, regionIndex, yearIndex, descriptionIndex, valueIndex);
            }
        }

        return insseList;
    }
}
