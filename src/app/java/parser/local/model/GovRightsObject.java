package app.java.parser.local.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class GovRightsObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    double maleEmpRate, femaleEmpRate, voteRate;

    public GovRightsObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.maleEmpRate = 0;
        this.femaleEmpRate = 0;
        this.voteRate = 0;
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

    public double getMaleEmpRate() {
        return maleEmpRate;
    }

    public void setMaleEmpRate(double maleEmpRate) {
        this.maleEmpRate = maleEmpRate;
    }

    public double getFemaleEmpRate() {
        return femaleEmpRate;
    }

    public void setFemaleEmpRate(double femaleEmpRate) {
        this.femaleEmpRate = femaleEmpRate;
    }

    public double getVoteRate() {
        return voteRate;
    }

    public void setVoteRate(double voteRate) {
        this.voteRate = voteRate;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<GovRightsObject>> allYearsList,
                               String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<GovRightsObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< GovRightsObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<GovRightsObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.GOV_RIGHTS, queryType);

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
     * @param list          ArrayList with GovRightsObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<GovRightsObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            GovRightsObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case MALE_EMP_RATE:
                        return item.getMaleEmpRate();
                    case FEMALE_EMP_RATE:
                        return item.getFemaleEmpRate();
                    case VOTE_RATE:
                        return item.getVoteRate();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
