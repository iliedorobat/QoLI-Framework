package app.java.data.measurement;

import app.java.commons.MapOrder;
import app.java.commons.Print;
import app.java.commons.Statistics;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.commons.utils.MapUtils;
import app.java.data.measurement.dao.*;
import app.java.data.measurement.dao.impl.*;

import java.util.Map;
import java.util.TreeMap;

public class QoLI {
    private static EducationStatsDAO educationStatsDAO = new EducationStatsImpl();
    private static EnvironmentStatsDAO environmentStatsDAO= new EnvironmentStatsImpl();
    private static GovRightsStatsDAO govRightsStatsDAO = new GovRightsStatsImpl();
    private static HealthStatsDAO healthStatsDAO = new HealthStatsImpl();
    private static MainActivityStatsDAO mainActivityStatsDAO = new MainActivityStatsImpl();
    private static MaterialLivingStatsDAO materialLivingStatsDAO = new MaterialLivingStatsImpl();
    private static OverallExperienceStatsDAO overallExperienceStatsDAO = new OverallExperienceStatsImpl();
    private static SafetyStatsDAO safetyStatsDAO = new SafetyStatsImpl();
    private static SocialActivityStatsDAO socialActivityStatsDAO = new SocialActivityStatsImpl();

    public static Map<String, Number> generateIndicatorList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                educationStats = educationStatsDAO.generateDimensionList(),
                environmentStats = environmentStatsDAO.generateDimensionList(),
                govRightsStats = govRightsStatsDAO.generateDimensionList(),
                healthStats = healthStatsDAO.generateDimensionList(),
                mainActivityStats = mainActivityStatsDAO.generateDimensionList(),
                materialLivingStats = materialLivingStatsDAO.generateDimensionList(),
                overallExperienceStats = overallExperienceStatsDAO.generateDimensionList(),
                safetyStats = safetyStatsDAO.generateDimensionList(),
                socialActivityStats = socialActivityStatsDAO.generateDimensionList();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (int i = 0; i < Constants.EU28_MEMBERS.length; i++) {
                String code = Constants.EU28_MEMBERS[i];
                String key = MapUtils.generateKey(code, year);

                double education = educationStats.get(key).doubleValue();
                double environment = environmentStats.get(key).doubleValue();
                double govRights = govRightsStats.get(key).doubleValue();
                double health = healthStats.get(key).doubleValue();
                double mainActivity = mainActivityStats.get(key).doubleValue();
                double materialLiving = materialLivingStats.get(key).doubleValue();
                double overallExperience = overallExperienceStats.get(key).doubleValue();
                double safety = safetyStats.get(key).doubleValue();
                double socialActivity = socialActivityStats.get(key).doubleValue();

                double product = 1
                        * education
                        * environment
                        * govRights
                        * health
                        * mainActivity
                        * materialLiving
                        * overallExperience
                        * safety
                        * socialActivity;
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(Statistics.generateVariation(consolidatedList, true));
//        Print.print(consolidatedList, false);

        return consolidatedList;
    }
}
