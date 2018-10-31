package app.java.relation.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.GovRightsRelDAO;
import app.java.commons.MathUtils;
import app.java.parser.dao.GovRightsDAO;
import app.java.parser.model.GovRightsObject;
import app.java.commons.Constants;
import app.java.parser.dao.impl.GovRightsDAOImpl;

import java.util.ArrayList;

public class GovRightsRelDAOImpl implements GovRightsRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static GovRightsDAO govRightsDAO = new GovRightsDAOImpl();
    private static MathUtils math = new MathUtils();

    public double getGovRightsRate(String region, short year) {
        ArrayList<ArrayList<GovRightsObject>> lists = govRightsDAO.getGovRightsLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<GovRightsObject> mainList = govRightsDAO.getGovRightsList(year);

        double govRights = 0;

        for (int i = 0; i < mainList.size(); i++) {
            GovRightsObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double genderEmpRate = getGenderEmpRate(lists, item);
                double voteRate = getVoteRate(lists, item);
                govRights = genderEmpRate * voteRate;
            }
        }

        if (govRights == 0)
            System.err.println("Governance and Basic Rights Index: " + govRights);

        return math.getSquareValue(govRights, 2);
    }

    private double getGenderEmpRate(ArrayList<ArrayList<GovRightsObject>> lists,
                                    GovRightsObject item) {
        String itemRegion = item.getRegion();
        double maleEmpRate = item.getMaleEmpRate();
        double femaleEmpRate = item.getFemaleEmpRate();

        if (maleEmpRate == 0)
            maleEmpRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.MALE_EMP_RATE);
        if (femaleEmpRate == 0)
            femaleEmpRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.FEMALE_EMP_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.GOV_RIGHTS, Constants.TYPES.MALE_EMP_RATE);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.GOV_RIGHTS, Constants.TYPES.FEMALE_EMP_RATE);

        return femaleEmpRate / maleEmpRate * 100;
    }

    private double getVoteRate(ArrayList<ArrayList<GovRightsObject>> lists,
                               GovRightsObject item) {
        String itemRegion = item.getRegion();
        double voteRate = item.getVoteRate();

        if (voteRate == 0)
            voteRate = item.getAvgRate(lists, itemRegion, Constants.TYPES.VOTE_RATE);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.GOV_RIGHTS, Constants.TYPES.VOTE_RATE);

        return voteRate;
    }
}
