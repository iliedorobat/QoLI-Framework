package app.java.parser.local.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class MaterialLifeObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    double avgTotalIncome, avgTotalSpend,
            relativePoverty, povertyRisk, deprivationRate;

    public MaterialLifeObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.avgTotalIncome = 0;
        this.avgTotalSpend = 0;
        this.relativePoverty = 0;
        this.povertyRisk = 0;
        this.deprivationRate = 0;
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

    public double getAvgTotalIncome() {
        return avgTotalIncome;
    }

    public void setAvgTotalIncome(double avgTotalIncome) {
        this.avgTotalIncome = avgTotalIncome;
    }

    public double getAvgTotalSpend() {
        return avgTotalSpend;
    }

    public void setAvgTotalSpend(double avgTotalSpend) {
        this.avgTotalSpend = avgTotalSpend;
    }

    public double getRelativePoverty() {
        return relativePoverty;
    }

    public void setRelativePoverty(double relativePoverty) {
        this.relativePoverty = relativePoverty;
    }

    public double getPovertyRisk() {
        return povertyRisk;
    }

    public void setPovertyRisk(double povertyRisk) {
        this.povertyRisk = povertyRisk;
    }

    public double getDeprivationRate() {
        return deprivationRate;
    }

    public void setDeprivationRate(double deprivationRate) {
        this.deprivationRate = deprivationRate;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<MaterialLifeObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<MaterialLifeObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< MaterialLifeObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<MaterialLifeObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.MATERIAL_LIFE, queryType);

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
     * @param list          ArrayList with MaterialLifeObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<MaterialLifeObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            MaterialLifeObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case AVG_TOTAL_SPEND:
                        return item.getAvgTotalSpend();
                    case AVG_TOTAL_INCOME:
                        return item.getAvgTotalIncome();
                    case RELATIVE_POVERTY:
                        return item.getRelativePoverty();
                    case POVERTY_RISK:
                        return item.getPovertyRisk();
                    case DEPRIVATION_RATE:
                        return item.getDeprivationRate();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
