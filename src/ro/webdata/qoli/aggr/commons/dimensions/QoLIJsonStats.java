package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.commons.utils.JsonStatsUtils;
import ro.webdata.qoli.aggr.commons.constants.Constants;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationPaths;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentPaths;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsPaths;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthPaths;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperiencePaths;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyPaths;

import static ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;

public class QoLIJsonStats {
    public static void writeDimensions(boolean calculateIndicators) {
        writeDataByCountries(calculateIndicators);
        writeDataByRegions(calculateIndicators);
    }

    private static void writeDataByCountries(boolean calculateIndicators) {
        writeData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, calculateIndicators);
    }

    private static void writeDataByRegions(boolean calculateIndicators) {
        writeData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, calculateIndicators);
    }

    private static void writeData(String[] membersList, String seriesType, boolean calculateIndicators) {
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(QoLIPaths.QOLI_FILE_NAME), membersList, seriesType, QoLIPaths.QOLI_FILE_NAME, null, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(EducationPaths.EDUCATION_FILE_NAME), membersList, seriesType, EducationPaths.EDUCATION_FILE_NAME, EducationStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(EnvironmentPaths.ENVIRONMENT_FILE_NAME), membersList, seriesType, EnvironmentPaths.ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(GovRightsPaths.GOVERNANCE_FILE_NAME), membersList, seriesType, GovRightsPaths.GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(HealthPaths.HEALTH_FILE_NAME), membersList, seriesType, HealthPaths.HEALTH_FILE_NAME, HealthStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(LEISURE_INTERACT_FILE_NAME), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, LeisureInteractStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(MAIN_ACTIVITY_FILE_NAME), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(LIVING_CONDITIONS_FILE_NAME), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME), membersList, seriesType, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(QoLIStats.DATA_BY_COUNTRIES.get(SafetyPaths.SAFETY_FILE_NAME), membersList, seriesType, SafetyPaths.SAFETY_FILE_NAME, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
