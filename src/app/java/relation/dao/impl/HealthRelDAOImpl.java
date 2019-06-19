package app.java.relation.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.commons.Utils;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.HealthRelDAO;
import app.java.parser.local.model.HealthObject;
import app.java.commons.Constants;
import app.java.commons.MathUtils;
import app.java.parser.local.dao.HealthDAO;
import app.java.parser.local.dao.impl.HealthDAOImpl;

import java.util.ArrayList;

public class HealthRelDAOImpl implements HealthRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static HealthDAO healthDAO = new HealthDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();
    private static Utils utils = new Utils();

    public double getHealthRate(String region, short year) {
        ArrayList<ArrayList<HealthObject>> lists = healthDAO.getHealthLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<HealthObject> mainList = healthDAO.getHealthList(year);

        double residents = relation.getResidents(region, year);
        double health = 0;

        for (int i = 0; i < mainList.size(); i++) {
            HealthObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double infrastructureRate = getInfrastructureRate(lists, item, residents);
                double staffRate = getStaffRate(lists, item, residents);
                double foodConsRate = getFoodConsRate(lists, item);
                double reversedHurtsRate = getReversedHurtsRate(lists, item, residents);
                double naturalGrowthRate = getNatPopGrowthRate(lists, item);
                double lifeExpectancyRate = getLifeExpectancyRate(lists, item);

                // cu cat hurtsRate este mai mic, cu atat este mai bine
                health = infrastructureRate * staffRate * foodConsRate
                        * lifeExpectancyRate * reversedHurtsRate
                        * naturalGrowthRate;
            }
        }

        if (health == 0)
            System.err.println("Health Index: " + health);

        return math.getSquareValue(health, 6);
    }

    private double getInfrastructureRate(ArrayList<ArrayList<HealthObject>> lists,
                                         HealthObject item, double residents) {
        String itemRegion = item.getRegion();
        double totalGenMedCab = item.getTotalGenMedCab();
        double totalSpecMedCab = item.getTotalSpecializedMedCab();
        double totalDentalCab = item.getTotalDentalMedCab();
        double totalHospitals = item.getTotalHospitals();
        double totalHospitalsBeds = item.getTotalHospitalsBeds();
        double totalChildrenHospitalsBeds = item.getTotalChildrenHospitalsBeds();

        if (totalGenMedCab == 0)
            totalGenMedCab = item.getAvgRate(lists, itemRegion, Constants.TYPES.GENERAL_MED_CAB_TOTAL_COUNT);
        if (totalSpecMedCab == 0)
            totalSpecMedCab = item.getAvgRate(lists, itemRegion, Constants.TYPES.SPECIALIZED_MED_CAB_TOTAL_COUNT);
        if (totalDentalCab == 0)
            totalDentalCab = item.getAvgRate(lists, itemRegion, Constants.TYPES.DENTAL_MED_CAB_TOTAL_COUNT);
        if (totalHospitals == 0)
            totalHospitals = item.getAvgRate(lists, itemRegion, Constants.TYPES.HOSPITAL_TOTAL_COUNT);
        if (totalHospitalsBeds == 0)
            totalHospitalsBeds = item.getAvgRate(lists, itemRegion, Constants.TYPES.HOSPITAL_BED_TOTAL_COUNT);
        if (totalChildrenHospitalsBeds == 0)
            totalChildrenHospitalsBeds = item.getAvgRate(lists, itemRegion, Constants.TYPES.HOSPITAL_CHILDREN_BED_TOTAL_COUNT);

        double cabinets = totalGenMedCab + totalSpecMedCab + totalDentalCab;
        double hospitals = totalHospitals;
        double beds = totalHospitalsBeds + totalChildrenHospitalsBeds;

        double medicalUnits = (cabinets + hospitals) / residents * Constants.REPORT_NO_10;
        double bedsRate = beds / residents * Constants.REPORT_NO_10;

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.GENERAL_MED_CAB_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.SPECIALIZED_MED_CAB_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.DENTAL_MED_CAB_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.HOSPITAL_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.HOSPITAL_BED_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.HOSPITAL_CHILDREN_BED_TOTAL_COUNT);

        double infra = medicalUnits * bedsRate;

        return math.getSquareValue(infra, 2);
    }

    private double getStaffRate(ArrayList<ArrayList<HealthObject>> lists,
                                         HealthObject item, double residents) {
        String itemRegion = item.getRegion();
        double totalSecEduMedicalStaff = item.getTotalSecEduMedicalStaff();
        double totalHighEduNurses = item.getTotalNursesHighEdu();
        double totalDoctors = item.getTotalDoctors();
        double totalDentalDoctors = item.getTotalDentalMedCab();
        double totalPharmaStaff = item.getTotalPharmaStaff();

        if (totalSecEduMedicalStaff == 0)
            totalSecEduMedicalStaff = item.getAvgRate(lists, itemRegion, Constants.TYPES.SEC_EDU_STAFF_TOTAL_COUNT);
        if (totalHighEduNurses == 0)
            totalHighEduNurses = item.getAvgRate(lists, itemRegion, Constants.TYPES.NURSE_HIGH_EDU_TOTAL_COUNT);
        if (totalDoctors == 0)
            totalDoctors = item.getAvgRate(lists, itemRegion, Constants.TYPES.DOCTOR_TOTAL_COUNT);
        if (totalDentalDoctors == 0)
            totalDentalDoctors = item.getAvgRate(lists, itemRegion, Constants.TYPES.DENTIST_TOTAL_COUNT);
        if (totalPharmaStaff == 0)
            totalPharmaStaff = item.getAvgRate(lists, itemRegion, Constants.TYPES.PHARMA_STAFF_TOTAL_COUNT);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.SEC_EDU_STAFF_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.NURSE_HIGH_EDU_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.DOCTOR_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.DENTIST_TOTAL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.PHARMA_STAFF_TOTAL_COUNT);

        double staff = totalSecEduMedicalStaff + totalHighEduNurses
                + totalDoctors + totalDentalDoctors + totalPharmaStaff;

        return staff / residents * Constants.REPORT_NO_1;
    }

    // alcoholCons = cantitatile de bauturi alcoolice (MEDII LUNARE PE O PERSOANA - LITRI)
    // fruitsCons = cantitatile de fructe (MEDII LUNARE PE O PERSOANA - KG)
    // vegetablesCons = cantitatile de cereale si produse din cereale (MEDII LUNARE PE O PERSOANA - KG)
    private double getFoodConsRate(ArrayList<ArrayList<HealthObject>> lists,
                                   HealthObject item) {
        String itemRegion = item.getRegion();
        double alcoholCons = item.getAlcohol();
        double fruitsCons = item.getFruits();
        double vegetablesCons = item.getVegetables();

        if (alcoholCons == 0)
            alcoholCons = item.getAvgRate(lists, itemRegion, Constants.TYPES.ALCOHOL_COUNT);
        if (fruitsCons == 0)
            fruitsCons = item.getAvgRate(lists, itemRegion, Constants.TYPES.FRUIT_COUNT);
        if (vegetablesCons == 0)
            vegetablesCons = item.getAvgRate(lists, itemRegion, Constants.TYPES.VEGETABLE_COUNT);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.ALCOHOL_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.FRUIT_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.VEGETABLE_COUNT);

        return (fruitsCons + vegetablesCons) / alcoholCons * 100;
    }

    private double getReversedHurtsRate(ArrayList<ArrayList<HealthObject>> lists,
                                        HealthObject item, double residents) {
        String itemRegion = item.getRegion();
        double workHurts = item.getHurtsInWork();
        double trafficHurts = item.getHurtsInTraffic();

        if (workHurts == 0)
            workHurts = item.getAvgRate(lists, itemRegion, Constants.TYPES.HURT_IN_WORK_COUNT);
        if (trafficHurts == 0)
            trafficHurts = item.getAvgRate(lists, itemRegion, Constants.TYPES.HURT_IN_TRAFFIC_COUNT);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.HURT_IN_WORK_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.HURT_IN_TRAFFIC_COUNT);

        double hurts = (workHurts + trafficHurts) / residents * Constants.REPORT_NO_1;

        //TODO: reversed = 1/hurts
        return utils.getReversedRate(hurts);
    }

    private double getNatPopGrowthRate(ArrayList<ArrayList<HealthObject>> lists,
                                       HealthObject item) {
        String itemRegion = item.getRegion();
        double natPopGrowthRate = item.getNatPopGrowthRate();

        if (natPopGrowthRate == 0)
            natPopGrowthRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.NATURAL_POP_GROWTH_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.NATURAL_POP_GROWTH_RATE);

        // daca naturalGrowthRate > 0, influenteaza negativ (se calculeaza produsul)
        // altfel, influenteaza negativ (se divide health la naturalGrowthRate)
        //TODO: a better formula for negative numbers
        natPopGrowthRate = 5 + natPopGrowthRate;

        return natPopGrowthRate;
    }

    private double getLifeExpectancyRate(ArrayList<ArrayList<HealthObject>> lists,
                                         HealthObject item) {
        String itemRegion = item.getRegion();
        double lex = item.getAvgLifeExpectancy();

        if (lex == 0)
            lex = item.getAvgRate(lists, itemRegion, Constants.TYPES.AVG_LIFE_EXPECTANCY);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.HEALTH, Constants.TYPES.AVG_LIFE_EXPECTANCY);

        return lex;
    }
}
