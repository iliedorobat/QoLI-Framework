package app.java.parser.local.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class MainActivityObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    double empRate, unempRate, gdp;
    long researchers;

    public MainActivityObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.empRate = 0;
        this.unempRate = 0;
        this.gdp = 0;
        this.researchers = 0;
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

    public double getEmpRate() {
        return empRate;
    }

    public void setEmpRate(double empRate) {
        this.empRate = empRate;
    }

    public double getUnempRate() {
        return unempRate;
    }

    public void setUnempRate(double unempRate) {
        this.unempRate = unempRate;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

    public long getResearchers() {
        return researchers;
    }

    public void setResearchers(long researchers) {
        this.researchers = researchers;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<MainActivityObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<MainActivityObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< MainActivityObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<MainActivityObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.MAIN_ACTIVITY, queryType);

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
     * @param list          ArrayList with MainActivityObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<MainActivityObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            MainActivityObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case EMP_RATE:
                        return item.getEmpRate();
                    case UNEMP_RATE:
                        return item.getUnempRate();
                    case RESEARCHER:
                        return (double) item.getResearchers();
                    case GDP:
                        return item.getGdp();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
