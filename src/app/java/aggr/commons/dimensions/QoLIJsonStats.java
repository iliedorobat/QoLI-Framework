package app.java.aggr.commons.dimensions;

import app.java.aggr.commons.dimensions.education.EducationStats;
import app.java.aggr.commons.dimensions.environment.EnvironmentStats;
import app.java.aggr.commons.dimensions.gov.GovRightsStats;
import app.java.aggr.commons.dimensions.health.HealthStats;
import app.java.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import app.java.aggr.commons.dimensions.mainActivity.MainActivityStats;
import app.java.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.aggr.commons.dimensions.overall.OverallExperienceStats;
import app.java.aggr.commons.dimensions.safety.SafetyStats;
import app.java.aggr.commons.utils.JsonStatsUtils;

import static app.java.aggr.commons.constants.Constants.*;
import static app.java.aggr.commons.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static app.java.aggr.commons.dimensions.QoLIStats.DATA_BY_COUNTRIES;
import static app.java.aggr.commons.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static app.java.aggr.commons.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static app.java.aggr.commons.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static app.java.aggr.commons.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static app.java.aggr.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static app.java.aggr.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static app.java.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;
import static app.java.aggr.commons.dimensions.overall.OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME;
import static app.java.aggr.commons.dimensions.safety.SafetyPaths.SAFETY_FILE_NAME;

public class QoLIJsonStats {
    public static void writeDimensions(boolean calculateIndicators) {
        writeDataByCountries(calculateIndicators);
        writeDataByRegions(calculateIndicators);
    }

    private static void writeDataByCountries(boolean calculateIndicators) {
        writeData(EU28_MEMBERS, SERIES_TYPE_COUNTRY, calculateIndicators);
    }

    private static void writeDataByRegions(boolean calculateIndicators) {
        writeData(EU28_REGIONS, SERIES_TYPE_REGION, calculateIndicators);
    }

    private static void writeData(String[] membersList, String seriesType, boolean calculateIndicators) {
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(QOLI_FILE_NAME), membersList, seriesType, QOLI_FILE_NAME, null, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(EDUCATION_FILE_NAME), membersList, seriesType, EDUCATION_FILE_NAME, EducationStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(ENVIRONMENT_FILE_NAME), membersList, seriesType, ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(GOVERNANCE_FILE_NAME), membersList, seriesType, GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(HEALTH_FILE_NAME), membersList, seriesType, HEALTH_FILE_NAME, HealthStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, LeisureInteractStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(DATA_BY_COUNTRIES.get(SAFETY_FILE_NAME), membersList, seriesType, SAFETY_FILE_NAME, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
