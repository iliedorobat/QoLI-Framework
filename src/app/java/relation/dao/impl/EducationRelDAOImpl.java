package app.java.relation.dao.impl;

import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.EducationRelDAO;
import app.java.parser.dao.impl.EducationDAOImpl;
import app.java.commons.Constants;
import app.java.commons.MathUtils;
import app.java.parser.dao.EducationDAO;
import app.java.parser.model.EducationObject;

import java.util.ArrayList;

public class EducationRelDAOImpl implements EducationRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static EducationDAO educationDAO = new EducationDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();
    private static Utils utils = new Utils();

    public double getEducationRate(String region, short year) {
        ArrayList<ArrayList<EducationObject>> lists = educationDAO.getEducationLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<EducationObject> mainList = educationDAO.getEducationList(year);

        double residents = relation.getResidents(region, year);
        double education = 0;

        for (int i = 0; i < mainList.size(); i++) {
            EducationObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double reversedAbandonRate = getReversedAbandonRate(lists, item);
                double infrastructureRate = getInfrastructureRate(lists, item, residents);
                double teachersRate = getTeachersRate(lists, item);
                education = reversedAbandonRate * infrastructureRate * teachersRate;
            }
        }

        if (education == 0)
            System.err.println("Education Index: " + education);

        return math.getSquareValue(education, 3);
    }

    // teachersRate = profersori la 1000 elevi
    private double getTeachersRate(ArrayList<ArrayList<EducationObject>> lists,
                                         EducationObject item) {
        String itemRegion = item.getRegion();
        double teachers = item.getTeachersCount();

        if (teachers == 0)
            teachers = item.getAvgRate(lists, itemRegion, Constants.TYPES.TEACHERS_COUNT);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.TEACHERS_COUNT);

        double teachersRate = teachers / item.getStudentsCount() * Constants.REPORT_NO_1;

        return teachersRate;
    }

    // infraRate = unitati scolare la 1000 persoane
    private double getInfrastructureRate(ArrayList<ArrayList<EducationObject>> lists,
                                   EducationObject item, double residents) {
        String itemRegion = item.getRegion();
        double schools = item.getSchoolsCount();
        double faculties = item.getFacultiesCount();

        if (schools == 0)
            schools = item.getAvgRate(lists, itemRegion, Constants.TYPES.SCHOOLS_COUNT);
        if (faculties == 0)
            faculties = item.getAvgRate(lists, itemRegion, Constants.TYPES.FACULTIES_COUNT);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.SCHOOLS_COUNT);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.FACULTIES_COUNT);

        double infraRate = (schools + faculties) / residents * Constants.REPORT_NO_1;

        return infraRate;
    }

    // abandon rate = (elevi la inceput de an - elevi la sfarsit de an) / elevi la inceput de an * 100
    private double getReversedAbandonRate(ArrayList<ArrayList<EducationObject>> lists,
                                          EducationObject item) {
        String itemRegion = item.getRegion();
        double abandonI = item.getAbandonRateI();
        double abandonII = item.getAbandonRateII();
        double abandonIII = item.getAbandonRateIII();

        if (abandonI == 0)
            abandonI = item.getAvgRate(lists, itemRegion, Constants.TYPES.ABANDON_RATE_I);
        if (abandonII == 0)
            abandonII = item.getAvgRate(lists, itemRegion, Constants.TYPES.ABANDON_RATE_II);
        if (abandonIII == 0)
            abandonIII = item.getAvgRate(lists, itemRegion, Constants.TYPES.ABANDON_RATE_III);

        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.ABANDON_RATE_I);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.ABANDON_RATE_II);
        lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                Constants.DIMENSION.EDUCATION, Constants.TYPES.ABANDON_RATE_III);

        double abandon = (abandonI + abandonII + abandonIII) / 3;

        return utils.getReversedRate(abandon);
    }
}
