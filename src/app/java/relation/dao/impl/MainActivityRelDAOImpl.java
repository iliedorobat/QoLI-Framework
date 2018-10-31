package app.java.relation.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.MainActivityRelDAO;
import app.java.parser.dao.MainActivityDAO;
import app.java.commons.Constants;
import app.java.commons.MathUtils;
import app.java.parser.dao.impl.MainActivityDAOImpl;
import app.java.parser.model.MainActivityObject;

import java.util.ArrayList;

public class MainActivityRelDAOImpl implements MainActivityRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static MainActivityDAO mainActivityDAO = new MainActivityDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();

    // researchersRate = numarul de cercetatori la 1000 locuitori
    // pib = milioane lei PIB la 1000 locuitori
    // employeeRate = sqrt(empRate / unempRate, 2)
    public double getMainActivityRate(String region, short year) {
        ArrayList<ArrayList<MainActivityObject>> lists = mainActivityDAO.getMainActivityLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<MainActivityObject> mainList = mainActivityDAO.getMainActivityList(year);

        double residents = relation.getResidents(region, year);
        double mainActivity = 0;

        for (int i = 0; i < mainList.size(); i++) {
            MainActivityObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double researchersRate = getResearchersRate(lists, item, residents);
                double gdpRate = getPibRate(lists, item, residents);
                double employeeRate = getEmployeeRate(lists, item);
                mainActivity = researchersRate * gdpRate * employeeRate;
            }
        }

        if (mainActivity == 0)
            System.err.println("Main Activity Index: " + mainActivity);

        return math.getSquareValue(mainActivity, 3);
    }

    private double getResearchersRate(ArrayList<ArrayList<MainActivityObject>> lists,
                                      MainActivityObject item, double residents) {
        String itemRegion = item.getRegion();
        double researchers = (double) item.getResearchers();

        if (researchers == 0)
            researchers = item.getAvgRate(lists, itemRegion, Constants.TYPES.RESEARCHER);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MAIN_ACTIVITY, Constants.TYPES.RESEARCHER);

        return researchers / residents * Constants.REPORT_NO_1;
    }


    private double getPibRate(ArrayList<ArrayList<MainActivityObject>> lists,
                                      MainActivityObject item, double residents) {
        String itemRegion = item.getRegion();
        double pib = item.getGdp();

        if (pib == 0)
            pib = item.getAvgRate(lists, itemRegion, Constants.TYPES.GDP);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MAIN_ACTIVITY, Constants.TYPES.GDP);

        return pib / residents * Constants.REPORT_NO_1;
    }

    // empRate = populatia activa / total populatie
    // unempRate = someri inregistrati / pop activa * 100
    // regularizarea (ajustarea/corectarea) empRate si unempRate
    private double getEmployeeRate(ArrayList<ArrayList<MainActivityObject>> lists,
                                   MainActivityObject item) {
        String itemRegion = item.getRegion();
        double empRate = item.getEmpRate();
        double unempRate = item.getUnempRate();

        if (empRate == 0)
            empRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.EMP_RATE);
        if (unempRate == 0)
            unempRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.UNEMP_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MAIN_ACTIVITY, Constants.TYPES.EMP_RATE);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.MAIN_ACTIVITY, Constants.TYPES.UNEMP_RATE);

        return empRate / unempRate;
    }
}
