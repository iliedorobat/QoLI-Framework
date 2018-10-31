package app.java.parser.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class EducationObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    long schoolsCount, facultiesCount;
    long teachersCount, studentsCount;
    double abandonRateI, abandonRateII, abandonRateIII;

    public EducationObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.schoolsCount = 0;
        this.facultiesCount = 0;
        this.teachersCount = 0;
        this.studentsCount = 0;
        this.abandonRateI = 0;
        this.abandonRateII = 0;
        this.abandonRateIII = 0;
    }

    public long getSchoolsCount() {
        return schoolsCount;
    }

    public void setSchoolsCount(long schoolsCount) {
        this.schoolsCount = schoolsCount;
    }

    public long getFacultiesCount() {
        return facultiesCount;
    }

    public void setFacultiesCount(long facultiesCount) {
        this.facultiesCount = facultiesCount;
    }

    public long getTeachersCount() {
        return teachersCount;
    }

    public void setTeachersCount(long teachersCount) {
        this.teachersCount = teachersCount;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAbandonRateI() {
        return abandonRateI;
    }

    public void setAbandonRateI(double abandonRateI) {
        this.abandonRateI = abandonRateI;
    }

    public double getAbandonRateII() {
        return abandonRateII;
    }

    public void setAbandonRateII(double abandonRateII) {
        this.abandonRateII = abandonRateII;
    }

    public double getAbandonRateIII() {
        return abandonRateIII;
    }

    public void setAbandonRateIII(double abandonRateIII) {
        this.abandonRateIII = abandonRateIII;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<EducationObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<EducationObject>> allYearsList,
                               String region, Constants.TYPES queryType) {
        double min = 0;
        double max = 0;

        for (int year = Constants.MIN_YEAR, i = 0; year <= Constants.MAX_YEAR; year++, i++) {
            double value = getItemRate(allYearsList.get(i), year, region, queryType);
            if (min == 0) min = value;
            if (max == 0) max = value;
            if (min > value) min = value;
            if (max < value) max = value;
        }

        return max - min;
    }

    /**
     * Get the average rate for all years by region and query type
     * @param allYearsList  The list for all years of ArrayList< EducationObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<EducationObject>> allYearsList,
                              String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.EDUCATION, queryType);

        short divided = 0;
        double sum = 0;

        for (int year = Constants.MIN_YEAR; year <= Constants.MAX_YEAR; year++) {
            for (int i = 0; i < allYearsList.size(); i++) {
                sum = sum + getItemRate(allYearsList.get(i), year, region, queryType);
            }

            if (sum > 0)
                divided++;
        }

        if (divided == 0)
            return sum;

        return sum / divided;
    }

    /**
     * Get the item rate by year, region and type
     * @param list          ArrayList with EducationObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<EducationObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            EducationObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case SCHOOLS_COUNT:
                        return (double) item.getSchoolsCount();
                    case FACULTIES_COUNT:
                        return (double) item.getFacultiesCount();
                    case TEACHERS_COUNT:
                        return (double) item.getTeachersCount();
                    case STUDENTS_COUNT:
                        return (double) item.getStudentsCount();
                    case ABANDON_RATE_I:
                        return item.getAbandonRateI();
                    case ABANDON_RATE_II:
                        return item.getAbandonRateII();
                    case ABANDON_RATE_III:
                        return item.getAbandonRateIII();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
