package app.java.parser.dao.impl;

import app.java.commons.Constants;
import app.java.commons.Utils;
import app.java.index.dao.LifeIndexDAO;
import app.java.index.dao.impl.LifeIndexDAOImpl;
import app.java.parser.dao.SocialActivityDAO;
import app.java.parser.model.InsseObject;
import app.java.parser.model.SocialActivityObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SocialActivityDAOImpl implements SocialActivityDAO {
    private static final String cinemaPath = Constants.SOCIAL_ACTIVITY_PATH +
            "ART109B - Number of cinema screenings.csv";
    private static final String museumsPath = Constants.SOCIAL_ACTIVITY_PATH +
            "ART104B - Museums and public collections.csv";
    private static final String sportsGroupsPath = Constants.SOCIAL_ACTIVITY_PATH +
            "CSP101B - Sport section.csv";

    private static Utils utils = new Utils();
    private static String[] regions = utils.getRegions();
    private static LifeIndexDAO lifeIndexDAO = new LifeIndexDAOImpl();

    public ArrayList<ArrayList<SocialActivityObject>> getSocialActivityLists(int start, int end) {
        ArrayList<ArrayList<SocialActivityObject>> lists = new ArrayList<>();

        for (int year = start; year <= end; year++) {
            lists.add(getSocialActivityList(year));
        }

        return lists;
    }

    public ArrayList<SocialActivityObject> getSocialActivityList(int year) {
        ArrayList<SocialActivityObject> output = new ArrayList<>();
        HashMap<String, SocialActivityObject> hashMap = new HashMap<>();

        ArrayList<InsseObject> cinemaList = utils.getInsseObject(cinemaPath, Constants.PARAMS.SOCIAL_ACTIVITY);
        ArrayList<InsseObject> museumsList = utils.getInsseObject(museumsPath, Constants.PARAMS.GENERAL);
        ArrayList<InsseObject> sportsGroupsList = utils.getInsseObject(sportsGroupsPath, Constants.PARAMS.GENERAL);

        setHashMapValues(hashMap, cinemaList, year, Constants.TYPES.CINEMA_SHOW_COUNT);
        setHashMapValues(hashMap, museumsList, year, Constants.TYPES.MUSEUM_COUNT);
        setHashMapValues(hashMap, sportsGroupsList, year, Constants.TYPES.SPORT_GROUP_COUNT);

        for (int i = 0; i < regions.length; i++) {
            SocialActivityObject socialActivityObject = hashMap.get(regions[i]);
            // If there is no data, generate an empty object
            if (socialActivityObject == null) {
                socialActivityObject = new SocialActivityObject(regions[i], year);
            }
            output.add(socialActivityObject);
        }

        return output;
    }

    public void printRelativeAmplitude() {
        ArrayList<ArrayList<SocialActivityObject>> lists = getSocialActivityLists(Constants.MIN_YEAR, Constants.MAX_YEAR);
        ArrayList<SocialActivityObject> mainList = getSocialActivityList(Constants.MAX_YEAR);
        Constants.TYPES[] queryType = {
                Constants.TYPES.CINEMA_SHOW_COUNT,
                Constants.TYPES.MUSEUM_COUNT,
                Constants.TYPES.SPORT_GROUP_COUNT
        };

        for (int i = 0; i < queryType.length; i++) {
            for (int j = 0; j < mainList.size(); j++) {
                SocialActivityObject item = mainList.get(j);
                String region = item.getRegion();
                double relativeAmplitude = item.getRelativeAmplitude(lists, region, queryType[i]);
                double average = item.getAvgRate(lists, region, queryType[i]);
                lifeIndexDAO.printVariation(region, queryType[i], relativeAmplitude, average);
            }
        }
    }

    private void setHashMapValues(HashMap<String, SocialActivityObject> hashMap,
                                  ArrayList<InsseObject> arrayList, int year, Constants.TYPES queryType) {
        for (int i = 0; i < arrayList.size(); i++) {
            InsseObject item = arrayList.get(i);
            String region = utils.getRegion(regions, item);
            SocialActivityObject conditions = hashMap.get(region);

            if (Integer.parseInt(item.getYear()) == year) {
                if (conditions == null) {
                    conditions = new SocialActivityObject(region, Integer.parseInt(item.getYear()));
                    hashMap.put(region, conditions);
                }

                switch (queryType) {
                    case CINEMA_SHOW_COUNT:
                        conditions.setCinemaShows(Long.parseLong(item.getValue()));
                        break;
                    case MUSEUM_COUNT:
                        conditions.setMuseums(Long.parseLong(item.getValue()));
                        break;
                    case SPORT_GROUP_COUNT:
                        conditions.setSportsGrounds(Long.parseLong(item.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
