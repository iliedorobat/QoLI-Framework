package app.java.relation.dao.impl;

import app.java.commons.Constants;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.local.dao.CommonDAO;
import app.java.parser.local.dao.impl.CommonDAOImpl;
import app.java.parser.local.model.CommonObject;
import app.java.relation.dao.CommonRelDAO;

import java.util.ArrayList;

public class CommonRelDAOImpl implements CommonRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static CommonDAO commonDAO = new CommonDAOImpl();

    public double getActivePop(String region, short year) {
        ArrayList<ArrayList<CommonObject>> lists = commonDAO.getCommonLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        CommonObject item = getCommonObject(region, year);
        double output = item.getActivePop();

        if (output == 0)
            output = item.getAvgRate(lists, region, Constants.TYPES.ACTIVE_POP);

        lifeIndexDAO.printDimensionInfo(item.getYear(), region,
                Constants.DIMENSION.COMMON, Constants.TYPES.ACTIVE_POP);

        return output;
    }

    public double getAvgNetSalary(String region, short year) {
        ArrayList<ArrayList<CommonObject>> lists = commonDAO.getCommonLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        CommonObject item = getCommonObject(region, year);
        double output = item.getAvgNetSalary();

        if (output == 0)
            output = item.getAvgRate(lists, region, Constants.TYPES.AVG_NET_SALARY);

        lifeIndexDAO.printDimensionInfo(item.getYear(), region,
                Constants.DIMENSION.COMMON, Constants.TYPES.AVG_NET_SALARY);

        return output;
    }

    public double getResidents(String region, short year) {
        ArrayList<ArrayList<CommonObject>> lists = commonDAO.getCommonLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        CommonObject item = getCommonObject(region, year);
        double output = item.getResidents();

        if (output == 0)
            output = item.getAvgRate(lists, region, Constants.TYPES.RESIDENT_POPULATION);

        lifeIndexDAO.printDimensionInfo(item.getYear(), region,
                Constants.DIMENSION.COMMON, Constants.TYPES.RESIDENT_POPULATION);

        return output;
    }

    private static CommonObject getCommonObject(String region, short year) {
        CommonObject output = null;
        CommonDAO commonDAO = new CommonDAOImpl();
        ArrayList<CommonObject> commonList = commonDAO.getCommonList(year);

        for (int i = 0; i < commonList.size(); i++) {
            CommonObject item = commonList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                output = item;
            }
        }

        return output;
    }
}
