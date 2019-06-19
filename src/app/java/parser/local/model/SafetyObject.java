package app.java.parser.local.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class SafetyObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    long familyAssists, socialAssists, socialDinnerGainers,
            convicts, policeOffenseSolved;
    double avgPension, offenceRate, crimeRate;

    public SafetyObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.familyAssists = 0;
        this.socialAssists = 0;
        this.socialDinnerGainers = 0;
        this.convicts = 0;
        this.policeOffenseSolved = 0;
        this.avgPension = 0;
        this.offenceRate = 0;
        this.crimeRate = 0;
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

    public long getFamilyAssists() {
        return familyAssists;
    }

    public void setFamilyAssists(long familyAssists) {
        this.familyAssists = familyAssists;
    }

    public long getSocialAssists() {
        return socialAssists;
    }

    public void setSocialAssists(long socialAssists) {
        this.socialAssists = socialAssists;
    }

    public long getSocialDinnerGainers() {
        return socialDinnerGainers;
    }

    public void setSocialDinnerGainers(long socialDinnerGainers) {
        this.socialDinnerGainers = socialDinnerGainers;
    }

    public long getConvicts() {
        return convicts;
    }

    public void setConvicts(long convicts) {
        this.convicts = convicts;
    }

    public long getPoliceOffenseSolved() {
        return policeOffenseSolved;
    }

    public void setPoliceOffenseSolved(long policeOffenseSolved) {
        this.policeOffenseSolved = policeOffenseSolved;
    }

    public double getAvgPension() {
        return avgPension;
    }

    public void setAvgPension(double avgPension) {
        this.avgPension = avgPension;
    }

    public double getOffenceRate() {
        return offenceRate;
    }

    public void setOffenceRate(double offenceRate) {
        this.offenceRate = offenceRate;
    }

    public double getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(double crimeRate) {
        this.crimeRate = crimeRate;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<SafetyObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<SafetyObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< SafetyObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<SafetyObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.SAFETY, queryType);

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
     * @param list          ArrayList with SafetyObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<SafetyObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            SafetyObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case FAMILY_ASSIST:
                        return (double) item.getFamilyAssists();
                    case SOCIAL_ASSIST:
                        return (double) item.getSocialAssists();
                    case SOCIAL_DINNER_GAINER:
                        return (double) item.getSocialDinnerGainers();
                    case AVG_PENSION:
                        return item.getAvgPension();
                    case CONVICTS:
                        return (double) item.getConvicts();
                    case CRIME_RATE:
                        return item.getCrimeRate();
                    case OFFENCE_RATE:
                        return item.getOffenceRate();
                    case POLICE_OFFENCE_SOLVED:
                        return (double) item.getPoliceOffenseSolved();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
