package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.commons.utils.MapUtils;
import ro.webdata.qoli.aggr.commons.constants.Constants;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationPaths;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentPaths;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsPaths;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthPaths;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperiencePaths;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyPaths;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static ro.webdata.qoli.aggr.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;

public class QoLIStats {
    public static final HashMap<String, Map<String, Number>> DATA_BY_COUNTRIES = new HashMap<>(){{
        put(QOLI_FILE_NAME, QoLIStats.generateIndicatorList());
        put(EducationPaths.EDUCATION_FILE_NAME, EducationStats.generateDimensionList());
        put(EnvironmentPaths.ENVIRONMENT_FILE_NAME, EnvironmentStats.generateDimensionList());
        put(GovRightsPaths.GOVERNANCE_FILE_NAME, GovRightsStats.generateDimensionList());
        put(HealthPaths.HEALTH_FILE_NAME, HealthStats.generateDimensionList());
        put(LEISURE_INTERACT_FILE_NAME, LeisureInteractStats.generateDimensionList());
        put(MAIN_ACTIVITY_FILE_NAME, MainActivityStats.generateDimensionList());
        put(LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.generateDimensionList());
        put(OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.generateDimensionList());
        put(SafetyPaths.SAFETY_FILE_NAME, SafetyStats.generateDimensionList());
    }};

    public static Map<String, Number> generateIndicatorList() {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                educationStats = EducationStats.generateDimensionList(),
                environmentStats = EnvironmentStats.generateDimensionList(),
                govRightsStats = GovRightsStats.generateDimensionList(),
                healthStats = HealthStats.generateDimensionList(),
                leisureInteractStats = LeisureInteractStats.generateDimensionList(),
                mainActivityStats = MainActivityStats.generateDimensionList(),
                materialLivingStats = MaterialLivingStats.generateDimensionList(),
                overallExperienceStats = OverallExperienceStats.generateDimensionList(),
                safetyStats = SafetyStats.generateDimensionList();

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);

                double education = educationStats.get(key).doubleValue();
                double environment = environmentStats.get(key).doubleValue();
                double govRights = govRightsStats.get(key).doubleValue();
                double health = healthStats.get(key).doubleValue();
                double leisureInteract = leisureInteractStats.get(key).doubleValue();
                double mainActivity = mainActivityStats.get(key).doubleValue();
                double materialLiving = materialLivingStats.get(key).doubleValue();
                double overallExperience = overallExperienceStats.get(key).doubleValue();
                double safety = safetyStats.get(key).doubleValue();

                double product = 1
                        * education
                        * environment
                        * govRights
                        * health
                        * leisureInteract
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
