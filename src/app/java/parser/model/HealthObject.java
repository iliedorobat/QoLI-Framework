package app.java.parser.model;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;

import java.util.ArrayList;

public class HealthObject {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    String region;
    int year;
    long totalGenMedCab, totalSpecializedMedCab, totalDentalMedCab,
            totalHospitals, totalHospitalsBeds, totalChildrenHospitalsBeds,
            totalSecEduMedicalStaff, totalNursesHighEdu, totalDoctors,
            totalDentists, totalPharmaStaff,
            hurtsInWork, hurtsInTraffic;
    double alcohol, fruits, vegetables,
            natPopGrowthRate, avgLifeExpectancy;

    public HealthObject(String region, int year) {
        this.region = region;
        this.year = year;
        initialize();
    }

    private void initialize() {
        this.hurtsInWork = 0;
        this.hurtsInTraffic = 0;
        this.alcohol = 0;
        this.fruits = 0;
        this.vegetables = 0;
        this.natPopGrowthRate = 0;
        this.avgLifeExpectancy = 0;
        this.totalGenMedCab = 0;
        this.totalSpecializedMedCab = 0;
        this.totalDentalMedCab = 0;
        this.totalHospitals = 0;
        this.totalChildrenHospitalsBeds = 0;
        this.totalHospitalsBeds = 0;
        this.totalSecEduMedicalStaff = 0;
        this.totalNursesHighEdu = 0;
        this.totalDoctors = 0;
        this.totalDentists = 0;
        this.totalPharmaStaff = 0;
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

    public long getHurtsInWork() {
        return hurtsInWork;
    }

    public void setHurtsInWork(long hurtsInWork) {
        this.hurtsInWork = hurtsInWork;
    }

    public long getHurtsInTraffic() {
        return hurtsInTraffic;
    }

    public void setHurtsInTraffic(long hurtsInTraffic) {
        this.hurtsInTraffic = hurtsInTraffic;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getFruits() {
        return fruits;
    }

    public void setFruits(double fruits) {
        this.fruits = fruits;
    }

    public double getVegetables() {
        return vegetables;
    }

    public void setVegetables(double vegetables) {
        this.vegetables = vegetables;
    }

    public double getNatPopGrowthRate() {
        return natPopGrowthRate;
    }

    public void setNatPopGrowthRate(double natPopGrowthRate) {
        this.natPopGrowthRate = natPopGrowthRate;
    }

    public double getAvgLifeExpectancy() {
        return avgLifeExpectancy;
    }

    public void setAvgLifeExpectancy(double avgLifeExpectancy) {
        this.avgLifeExpectancy = avgLifeExpectancy;
    }

    public long getTotalGenMedCab() {
        return totalGenMedCab;
    }

    public void setTotalGenMedCab(long totalGenMedCab) {
        this.totalGenMedCab = totalGenMedCab;
    }

    public long getTotalSpecializedMedCab() {
        return totalSpecializedMedCab;
    }

    public void setTotalSpecializedMedCab(long totalSpecializedMedCab) {
        this.totalSpecializedMedCab = totalSpecializedMedCab;
    }

    public long getTotalDentalMedCab() {
        return totalDentalMedCab;
    }

    public void setTotalDentalMedCab(long totalDentalMedCab) {
        this.totalDentalMedCab = totalDentalMedCab;
    }

    public long getTotalHospitals() {
        return totalHospitals;
    }

    public void setTotalHospitals(long totalHospitals) {
        this.totalHospitals = totalHospitals;
    }

    public long getTotalChildrenHospitalsBeds() {
        return totalChildrenHospitalsBeds;
    }

    public void setTotalChildrenHospitalsBeds(long totalChildrenHospitalsBeds) {
        this.totalChildrenHospitalsBeds = totalChildrenHospitalsBeds;
    }

    public long getTotalHospitalsBeds() {
        return totalHospitalsBeds;
    }

    public void setTotalHospitalsBeds(long totalHospitalsBeds) {
        this.totalHospitalsBeds = totalHospitalsBeds;
    }

    public long getTotalSecEduMedicalStaff() {
        return totalSecEduMedicalStaff;
    }

    public void setTotalSecEduMedicalStaff(long totalSecEduMedicalStaff) {
        this.totalSecEduMedicalStaff = totalSecEduMedicalStaff;
    }

    public long getTotalNursesHighEdu() {
        return totalNursesHighEdu;
    }

    public void setTotalNursesHighEdu(long totalNursesHighEdu) {
        this.totalNursesHighEdu = totalNursesHighEdu;
    }

    public long getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(long totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public long getTotalDentists() {
        return totalDentists;
    }

    public void setTotalDentists(long totalDentists) {
        this.totalDentists = totalDentists;
    }

    public long getTotalPharmaStaff() {
        return totalPharmaStaff;
    }

    public void setTotalPharmaStaff(long totalPharmaStaff) {
        this.totalPharmaStaff = totalPharmaStaff;
    }

    // relative amplitude = amplitude / average * 100
    @SuppressWarnings("Duplicates")
    public double getRelativeAmplitude(ArrayList<ArrayList<HealthObject>> allYearsList,
                                       String region, Constants.TYPES queryType) {
        double avg = getAvgRate(allYearsList, region, queryType);
        double amplitude = getAmplitude(allYearsList, region, queryType);
        double relativeAmplitude = amplitude / avg * 100;

        return relativeAmplitude;
    }

    // amplitude = max - min
    @SuppressWarnings("Duplicates")
    public double getAmplitude(ArrayList<ArrayList<HealthObject>> allYearsList,
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
     * @param allYearsList  The list for all years of ArrayList< HealthObject >
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    @SuppressWarnings("Duplicates")
    public double getAvgRate(ArrayList<ArrayList<HealthObject>> allYearsList,
                             String region, Constants.TYPES queryType) {
//        System.err.println("HEALTH: " + queryType + " " + region + " " + year);

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
     * @param list          ArrayList with HealthObject objects
     * @param year          The target year
     * @param region        The target region
     * @param queryType     The type of query
     * @return <b>double</b>
     */
    private double getItemRate(ArrayList<HealthObject> list, int year,
                               String region, Constants.TYPES queryType) {
        for (int i = 0; i < list.size(); i++) {
            HealthObject item = list.get(i);
            if (year == item.getYear() && region.equals(item.getRegion())) {
                switch (queryType) {
                    case GENERAL_MED_CAB_TOTAL_COUNT:
                        return (double) item.getTotalGenMedCab();
                    case SPECIALIZED_MED_CAB_TOTAL_COUNT:
                        return (double) item.getTotalSpecializedMedCab();
                    case DENTAL_MED_CAB_TOTAL_COUNT:
                        return (double) item.getTotalDentalMedCab();
                    case HOSPITAL_TOTAL_COUNT:
                        return (double) item.getTotalHospitals();
                    case HOSPITAL_CHILDREN_BED_TOTAL_COUNT:
                        return (double) item.getTotalChildrenHospitalsBeds();
                    case HOSPITAL_BED_TOTAL_COUNT:
                        return (double) item.getTotalHospitalsBeds();

                    case SEC_EDU_STAFF_TOTAL_COUNT:
                        return (double) item.getTotalSecEduMedicalStaff();
                    case NURSE_HIGH_EDU_TOTAL_COUNT:
                        return (double) item.getTotalNursesHighEdu();
                    case DOCTOR_TOTAL_COUNT:
                        return (double) item.getTotalDoctors();
                    case DENTIST_TOTAL_COUNT:
                        return (double) item.getTotalDentists();

                    case ALCOHOL_COUNT:
                        return item.getAlcohol();
                    case FRUIT_COUNT:
                        return item.getFruits();
                    case VEGETABLE_COUNT:
                        return item.getVegetables();

                    case HURT_IN_WORK_COUNT:
                        return (double) item.getHurtsInWork();
                    case HURT_IN_TRAFFIC_COUNT:
                        return (double) item.getHurtsInTraffic();

                    case NATURAL_POP_GROWTH_RATE:
                        return item.getNatPopGrowthRate();
                    case AVG_LIFE_EXPECTANCY:
                        return item.getAvgLifeExpectancy();

                    default:
                        return 0;
                }
            }
        }

        return 0;
    }
}
