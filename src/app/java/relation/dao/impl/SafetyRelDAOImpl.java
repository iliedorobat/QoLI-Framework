package app.java.relation.dao.impl;

import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.SafetyRelDAO;
import app.java.commons.MathUtils;
import app.java.parser.dao.SafetyDAO;
import app.java.parser.dao.impl.SafetyDAOImpl;
import app.java.parser.model.SafetyObject;
import app.java.commons.Constants;

import java.util.ArrayList;

public class SafetyRelDAOImpl implements SafetyRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static SafetyDAO safetyDAO = new SafetyDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();
    private static Utils utils = new Utils();

    public double getSafetyRate(String region, short year) {
        ArrayList<ArrayList<SafetyObject>> lists = safetyDAO.getSafetyLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<SafetyObject> mainList = safetyDAO.getSafetyList(year);

        double avgNetSalary = relation.getAvgNetSalary(region, year);
        double residents = relation.getResidents(region, year);
        double safety = 0;

        for (int i = 0; i < mainList.size(); i++) {
            SafetyObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double economicRate = getEconomicSafetyRate(lists, item, avgNetSalary, residents);
                double physicalRate = getPhysicalSafetyRate(lists, item, residents);
                safety = economicRate * physicalRate;
            }
        }

        if (safety == 0)
            System.err.println("Safety Index: " + safety);

        return math.getSquareValue(safety, 2);
    }

    // familyAssistsRate = numarul mediu de beneficiari de alocatie pentru familie la 10000 locuitori
    // socialAssistsRate = numarul mediu de beneficiari de ajutoare sociale la 10000 locuitori
    // dinnerGainersRate = numarul mediu de beneficiari ai cantinelor sociale la 10000 locuitori
    // pensionRate = pensia medie in raport cu salariul mediu net
    private double getEconomicSafetyRate(ArrayList<ArrayList<SafetyObject>> lists,
                                         SafetyObject item, double avgNetSalary, double residents) {
        String itemRegion = item.getRegion();
        double familyAssists = item.getFamilyAssists();
        double socialAssists = item.getSocialAssists();
        double dinnerGainers = item.getSocialDinnerGainers();
        double pension = item.getAvgPension();

        if (familyAssists == 0)
            familyAssists = item.getAvgRate(lists, itemRegion, Constants.TYPES.FAMILY_ASSIST);
        if (socialAssists == 0)
            socialAssists = item.getAvgRate(lists, itemRegion, Constants.TYPES.SOCIAL_ASSIST);
        if (dinnerGainers == 0)
            dinnerGainers = item.getAvgRate(lists, itemRegion, Constants.TYPES.SOCIAL_DINNER_GAINER);
        if (pension == 0)
            pension = item.getAvgRate(lists, itemRegion, Constants.TYPES.AVG_PENSION);

        double familyAssistsRate = familyAssists / residents * Constants.REPORT_NO_1;
        double socialAssistsRate = socialAssists / residents * Constants.REPORT_NO_1;
        double dinnerGainersRate = dinnerGainers / residents * Constants.REPORT_NO_1;

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.FAMILY_ASSIST);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.SOCIAL_ASSIST);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.SOCIAL_DINNER_GAINER);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.AVG_PENSION);

        double pensionRate = pension / avgNetSalary * 100;

        double safety = utils.getReversedRate(familyAssistsRate)
                * utils.getReversedRate(socialAssistsRate)
                * utils.getReversedRate(dinnerGainersRate)
                * pensionRate;

        return math.getSquareValue(safety, 4);
    }

    // convictsRate = numarul de persoane condamnate definitiv la 100000 locuitori
    // crimeRate = numarul persoanelor condamnate definitiv la 100000 locuitori
    // offenceRate = numarul infractiunilor inregistrate si solutionate la 100000 locuitori
    // solvedRate = principalele tipuri de infractiuni cercetate de politie in anul de referinta la 100000 locuitori
    private double getPhysicalSafetyRate(ArrayList<ArrayList<SafetyObject>> lists,
                                         SafetyObject item, double residents) {
        String itemRegion = item.getRegion();
        double convicts = item.getConvicts();
        double policeSolved = item.getPoliceOffenseSolved();

        if (convicts == 0)
            convicts = item.getAvgRate(lists, itemRegion, Constants.TYPES.CONVICTS);
        if (policeSolved == 0)
            policeSolved = item.getAvgRate(lists, itemRegion, Constants.TYPES.POLICE_OFFENCE_SOLVED);

        double convictsRate = convicts / residents * Constants.REPORT_NO_100;
        double crimeRate = item.getCrimeRate();
        double offenceRate = item.getOffenceRate();
        double solvedRate = policeSolved / residents * Constants.REPORT_NO_100;

        if (crimeRate == 0)
            crimeRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.CRIME_RATE);
        if (offenceRate == 0)
            offenceRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.OFFENCE_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.CONVICTS);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.POLICE_OFFENCE_SOLVED);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.CRIME_RATE);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.SAFETY, Constants.TYPES.OFFENCE_RATE);

        double safety = solvedRate / (convictsRate * crimeRate * offenceRate);

        return safety * Constants.REPORT_NO_100;
    }
}
