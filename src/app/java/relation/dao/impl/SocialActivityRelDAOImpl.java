package app.java.relation.dao.impl;

import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.relation.dao.CommonRelDAO;
import app.java.relation.dao.SocialActivityRelDAO;
import app.java.commons.MathUtils;
import app.java.parser.dao.SocialActivityDAO;
import app.java.parser.dao.impl.SocialActivityDAOImpl;
import app.java.parser.model.SocialActivityObject;
import app.java.commons.Constants;

import java.util.ArrayList;

public class SocialActivityRelDAOImpl implements SocialActivityRelDAO {
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();
    private static SocialActivityDAO socialDAO = new SocialActivityDAOImpl();
    private static CommonRelDAO relation = new CommonRelDAOImpl();
    private static MathUtils math = new MathUtils();

    // museumsRate = numarul de muzee la 10000 locuitori
    // showsRate = numarul de spectacole la 10000 locuitori (pe luna)
    // sportsRate = numarul de sectii sportive la 10000 locuitori
    // athletesRate = numarul de atleti la 10000 locuitori
    public double getSocialActivityRate(String region, short year) {
        ArrayList<ArrayList<SocialActivityObject>> lists = socialDAO.getSocialActivityLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<SocialActivityObject> mainList = socialDAO.getSocialActivityList(year);

        double residents = relation.getResidents(region, year);
        double social = 0;

        for (int i = 0; i < mainList.size(); i++) {
            SocialActivityObject item = mainList.get(i);
            String itemRegion = item.getRegion();

            if (region.equals(itemRegion)) {
                double museums = item.getMuseums();
                double shows = item.getCinemaShows();
                double sports = item.getSportsGrounds();

                if (museums == 0)
                    museums = item.getAvgRate(lists, itemRegion, Constants.TYPES.MUSEUM_COUNT);
                if (shows == 0)
                    shows = item.getAvgRate(lists, itemRegion, Constants.TYPES.CINEMA_SHOW_COUNT);
                if (sports == 0)
                    sports = item.getAvgRate(lists, itemRegion, Constants.TYPES.SPORT_GROUP_COUNT);

                lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                        Constants.DIMENSION.SOCIAL_ACTIVITY, Constants.TYPES.MUSEUM_COUNT);
                lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                        Constants.DIMENSION.SOCIAL_ACTIVITY, Constants.TYPES.CINEMA_SHOW_COUNT);
                lifeIndexDAO.printDimensionInfo(item.getYear(), itemRegion,
                        Constants.DIMENSION.SOCIAL_ACTIVITY, Constants.TYPES.SPORT_GROUP_COUNT);

                double museumsRate = museums / residents * Constants.REPORT_NO_10;
                double showsRate = shows / residents / 12 * Constants.REPORT_NO_10;
                double sportsRate = sports / residents * Constants.REPORT_NO_10;
                social = museumsRate + showsRate + sportsRate;
            }
        }

        if (social == 0)
            System.err.println("Social Activity Index: " + social);

        return social;
    }
}
