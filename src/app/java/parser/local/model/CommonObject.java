package app.java.parser.local.model;

import app.java.index.dao.LifeIndexDAO;
import app.java.commons.Constants;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class CommonObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    double avgNetSalary;
    long activePop, residents;

    public CommonObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.activePop = 0;
        this.avgNetSalary = 0;
        this.residents = 0;
    }

    public long getActivePop() {
        return activePop;
    }

    public void setActivePop(double activePop) {
        this.activePop = (long) (activePop * Constants.ACTIVE_POP_UM);
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

    public double getAvgNetSalary() {
        return avgNetSalary;
    }

    public void setAvgNetSalary(double avgNetSalary) {
        this.avgNetSalary = avgNetSalary;
    }

    public long getResidents() {
        return residents;
    }

    public void setResidents(long residents) {
        this.residents = residents;
    }

    /**
     * Get the average rate for all years by region and query type
     * @param allYearsList  The list for all years of ArrayList< EducationObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<CommonObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
        lifeIndexDAO.printDimensionMissing(year, region, Constants.DIMENSION.COMMON, queryType);

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

    private double getItemRate(ArrayList<CommonObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            CommonObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case ACTIVE_POP:
                        return (double) item.getActivePop();
                    case AVG_NET_SALARY:
                        return item.getAvgNetSalary();
                    case RESIDENT_POPULATION:
                        return (double) item.getResidents();
                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
