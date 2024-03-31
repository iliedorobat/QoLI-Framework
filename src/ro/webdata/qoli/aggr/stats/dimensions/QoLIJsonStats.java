package ro.webdata.qoli.aggr.stats.dimensions;

import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.stats.utils.JsonStatsUtils;

import java.util.HashMap;
import java.util.Map;

import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.QoLIPaths.QOLI_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.education.EducationPaths.EDUCATION_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentPaths.ENVIRONMENT_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsPaths.GOVERNANCE_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.health.HealthPaths.HEALTH_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractPaths.LEISURE_INTERACT_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityPaths.MAIN_ACTIVITY_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME;
import static ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyPaths.SAFETY_FILE_NAME;

public class QoLIJsonStats {
    public static void writeDimensions(HashMap<String, Map<String, Number>> dataByCountries, boolean calculateIndicators) {
        writeDataByCountries(dataByCountries, calculateIndicators);
        writeDataByRegions(dataByCountries, calculateIndicators);
    }

    private static void writeDataByCountries(HashMap<String, Map<String, Number>> dataByCountries, boolean calculateIndicators) {
        writeData(Constants.EU28_MEMBERS, Constants.SERIES_TYPE_COUNTRY, dataByCountries, calculateIndicators);
    }

    private static void writeDataByRegions(HashMap<String, Map<String, Number>> dataByCountries, boolean calculateIndicators) {
        writeData(Constants.EU28_REGIONS, Constants.SERIES_TYPE_REGION, dataByCountries, calculateIndicators);
    }

    private static void writeData(String[] membersList, String seriesType, HashMap<String, Map<String, Number>> dataByCountries, boolean calculateIndicators) {
        JsonStatsUtils.writeJsonData(dataByCountries.get(QOLI), membersList, seriesType, QOLI_FILE_NAME, null, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(EDUCATION), membersList, seriesType, EDUCATION_FILE_NAME, EducationStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(ENVIRONMENT), membersList, seriesType, ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(GOVERNANCE), membersList, seriesType, GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(HEALTH), membersList, seriesType, HEALTH_FILE_NAME, HealthStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(LEISURE_INTERACT), membersList, seriesType, LEISURE_INTERACT_FILE_NAME, LeisureInteractStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(LIVING_CONDITIONS), membersList, seriesType, LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(MAIN_ACTIVITY), membersList, seriesType, MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(OVERALL_EXPERIENCE), membersList, seriesType, OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators, calculateIndicators);
        JsonStatsUtils.writeJsonData(dataByCountries.get(SAFETY), membersList, seriesType, SAFETY_FILE_NAME, SafetyStats.preparedIndicators, calculateIndicators);
    }
}
