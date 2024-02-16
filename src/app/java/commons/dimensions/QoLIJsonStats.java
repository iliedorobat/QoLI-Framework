package app.java.commons.dimensions;

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
import app.java.commons.utils.JsonStatsUtils;

import java.util.HashMap;
import java.util.Map;

import static app.java.commons.constants.Constants.*;
import static app.java.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static app.java.commons.dimensions.QoLIStats.DATA_BY_COUNTRIES;
import static app.java.commons.dimensions.QoLIStats.DATA_BY_REGIONS;
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

public class QoLIJsonStats {
    public static void writeDimensions(boolean calculateIndicators) {
        writeDataByCountries(calculateIndicators);
        writeDataByRegions(calculateIndicators);
    }

    private static void writeDataByCountries(boolean calculateIndicators) {
        writeData(DATA_BY_COUNTRIES, EU28_MEMBERS, SERIES_TYPE_COUNTRY, calculateIndicators);
    }

    private static void writeDataByRegions(boolean calculateIndicators) {
        writeData(DATA_BY_REGIONS, EU28_REGIONS, SERIES_TYPE_REGION, calculateIndicators);
    }

    private static void writeData(HashMap<String, Map<String, Number>> indicators, String[] membersList, String seriesType, boolean calculateIndicators) {
        JsonStatsUtils.writeJsonData(indicators.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, null, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, EducationStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, HealthStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(INTERACTIONS_FILE_NAME), membersList, seriesType, INTERACTIONS_FILE_NAME, InteractionsStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(LEISURE_FILE_NAME), membersList, seriesType, LEISURE_FILE_NAME, LeisureStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(indicators.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
