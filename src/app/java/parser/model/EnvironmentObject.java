package app.java.parser.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class EnvironmentObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    double greenSpaces;
    long waterAccess;

    public EnvironmentObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.greenSpaces = 0;
        this.waterAccess = 0;
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

    public double getGreenSpaces() {
        return greenSpaces;
    }

    public void setGreenSpaces(double greenSpaces) {
        this.greenSpaces = greenSpaces;
    }

    public long getWaterAccess() {
        return waterAccess;
    }

    public void setWaterAccess(long waterAccess) {
        this.waterAccess = waterAccess;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<EnvironmentObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<EnvironmentObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< EnvironmentObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<EnvironmentObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.ENVIRONMENT, queryType);

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

    private double getItemRate(ArrayList<EnvironmentObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            EnvironmentObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case GREEN_SPACE:
                        return item.getGreenSpaces();
                    case WATER_ACCESS:
                        return (double) item.getWaterAccess();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
