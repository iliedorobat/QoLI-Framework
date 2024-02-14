package app.java.commons.dimensions.aggrQoli;

import app.java.commons.MapOrder;
import app.java.commons.constants.Constants;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static app.java.commons.constants.Constants.EU28_MEMBERS;
import static app.java.commons.dimensions.aggrQoli.QoLIPaths.QOLI_FILE_NAME;
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

    public static final HashMap<String, Map<String, Number>> DATA_BY_REGIONS = new HashMap<>(){{
        put(QOLI_FILE_NAME, QoLIStats.aggregateRegions(QoLIStats.generateIndicatorList()));
        put(EDUCATION_FILE_NAME, QoLIStats.aggregateRegions(EducationStats.generateDimensionList()));
        put(ENVIRONMENT_FILE_NAME, QoLIStats.aggregateRegions(EnvironmentStats.generateDimensionList()));
        put(GOVERNANCE_FILE_NAME, QoLIStats.aggregateRegions((GovRightsStats.generateDimensionList())));
        put(HEALTH_FILE_NAME, QoLIStats.aggregateRegions(HealthStats.generateDimensionList()));
        put(INTERACTIONS_FILE_NAME, QoLIStats.aggregateRegions(InteractionsStats.generateDimensionList()));
        put(LEISURE_FILE_NAME, QoLIStats.aggregateRegions(LeisureStats.generateDimensionList()));
        put(MAIN_ACTIVITY_FILE_NAME, QoLIStats.aggregateRegions(MainActivityStats.generateDimensionList()));
        put(LIVING_CONDITIONS_FILE_NAME, QoLIStats.aggregateRegions(MaterialLivingStats.generateDimensionList()));
        put(OVERALL_EXPERIENCE_FILE_NAME, QoLIStats.aggregateRegions(OverallExperienceStats.generateDimensionList()));
        put(SAFETY_FILE_NAME, QoLIStats.aggregateRegions(SafetyStats.generateDimensionList()));
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

    public static Map<String, Number> aggregateRegions(Map<String, Number> entries) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            double easternCounter = 0,
                    northernCounter = 0,
                    southernCounter = 0,
                    westernCounter = 0;
            double easternSum = 0,
                    northernSum = 0,
                    southernSum = 0,
                    westernSum = 0;

            for (Map.Entry<String, Number> entry : entries.entrySet()) {
                String entryCode = MapUtils.getEntryCode(entry);
                Integer entryYear = MapUtils.getEntryYear(entry);
                Number entryValue = entry.getValue();

                if (entryYear == year) {
                    if (Arrays.asList(Constants.EU_EASTERN_MEMBERS).contains(entryCode)) {
                        easternSum += entryValue.doubleValue();
                        easternCounter++;
                    }
                    if (Arrays.asList(Constants.EU_NORTHERN_MEMBERS).contains(entryCode)) {
                        northernSum += entryValue.doubleValue();
                        northernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_SOUTHERN_MEMBERS).contains(entryCode)) {
                        southernSum += entryValue.doubleValue();
                        southernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_WESTERN_MEMBERS).contains(entryCode)) {
                        westernSum += entryValue.doubleValue();
                        westernCounter++;
                    }
                }
            }

            consolidatedList.put(MapUtils.generateKey("EU_EASTERN", year), easternSum / easternCounter);
            consolidatedList.put(MapUtils.generateKey("EU_NORTHERN", year), northernSum / northernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_SOUTHERN", year), southernSum / southernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_WESTERN", year), westernSum / westernCounter);
        }

        return consolidatedList;
    }
}
