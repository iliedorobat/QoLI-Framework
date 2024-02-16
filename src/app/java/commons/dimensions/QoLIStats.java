package app.java.commons.dimensions;

import app.java.commons.MapOrder;
import app.java.commons.constants.EnvConst;
import app.java.commons.dimensions.education.EducationStats;
import app.java.commons.dimensions.environment.EnvironmentStats;
import app.java.commons.dimensions.gov.GovRightsStats;
import app.java.commons.dimensions.health.HealthStats;
import app.java.commons.dimensions.interactions.InteractionsStats;
import app.java.commons.dimensions.leisure.LeisureStats;
import app.java.commons.dimensions.mainActivity.MainActivityStats;
import app.java.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.commons.dimensions.overall.OverallExperienceStats;
import app.java.commons.dimensions.safety.SafetyStats;
import app.java.commons.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static app.java.commons.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static app.java.commons.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static app.java.commons.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static app.java.commons.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static app.java.commons.dimensions.interactions.InteractionsPaths.INTERACTIONS_FILE_NAME;
import static app.java.commons.dimensions.leisure.LeisurePaths.LEISURE_FILE_NAME;
import static app.java.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static app.java.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;
import static app.java.commons.dimensions.overall.OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME;
import static app.java.commons.dimensions.safety.SafetyPaths.SAFETY_FILE_NAME;

public class QoLIStats {
    public static final HashMap<String, Map<String, Number>> DATA_BY_COUNTRIES = new HashMap<>(){{
        put(QOLI_FILE_NAME, QoLIStats.generateIndicatorList());
        put(EDUCATION_FILE_NAME, EducationStats.generateDimensionList());
        put(ENVIRONMENT_FILE_NAME, EnvironmentStats.generateDimensionList());
        put(GOVERNANCE_FILE_NAME, GovRightsStats.generateDimensionList());
        put(HEALTH_FILE_NAME, HealthStats.generateDimensionList());
        put(INTERACTIONS_FILE_NAME, InteractionsStats.generateDimensionList());
        put(LEISURE_FILE_NAME, LeisureStats.generateDimensionList());
        put(MAIN_ACTIVITY_FILE_NAME, MainActivityStats.generateDimensionList());
        put(LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.generateDimensionList());
        put(OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.generateDimensionList());
        put(SAFETY_FILE_NAME, SafetyStats.generateDimensionList());
    }};

    public static Map<String, Number> generateIndicatorList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                educationStats = EducationStats.generateDimensionList(),
                environmentStats = EnvironmentStats.generateDimensionList(),
                govRightsStats = GovRightsStats.generateDimensionList(),
                healthStats = HealthStats.generateDimensionList(),
                interactionsStats = InteractionsStats.generateDimensionList(),
                leisureStats = LeisureStats.generateDimensionList(),
                mainActivityStats = MainActivityStats.generateDimensionList(),
                materialLivingStats = MaterialLivingStats.generateDimensionList(),
                overallExperienceStats = OverallExperienceStats.generateDimensionList(),
                safetyStats = SafetyStats.generateDimensionList();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double education = educationStats.get(key).doubleValue();
                double environment = environmentStats.get(key).doubleValue();
                double govRights = govRightsStats.get(key).doubleValue();
                double health = healthStats.get(key).doubleValue();
                double interactions = interactionsStats.get(key).doubleValue();
                double leisure = leisureStats.get(key).doubleValue();
                double mainActivity = mainActivityStats.get(key).doubleValue();
                double materialLiving = materialLivingStats.get(key).doubleValue();
                double overallExperience = overallExperienceStats.get(key).doubleValue();
                double safety = safetyStats.get(key).doubleValue();

                double product = 1
                        * education
                        * environment
                        * govRights
                        * health
                        * interactions
                        * leisure
                        * mainActivity
                        * materialLiving
                        * overallExperience
                        * safety;
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

//        Print.printVariation(StatsUtils.generateVariation(consolidatedList, true));
//        Print.print(consolidatedList, false);

        return consolidatedList;
    }
}
