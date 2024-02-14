package app.java.commons.dimensions;

import app.java.commons.dimensions.aggrQoli.QoLIPaths;
import app.java.commons.dimensions.aggrQoli.QoLIStats;
import app.java.commons.dimensions.education.EducationPaths;
import app.java.commons.dimensions.education.EducationStats;
import app.java.commons.dimensions.environment.EnvironmentPaths;
import app.java.commons.dimensions.environment.EnvironmentStats;
import app.java.commons.dimensions.gov.GovRightsPaths;
import app.java.commons.dimensions.gov.GovRightsStats;
import app.java.commons.dimensions.health.HealthPaths;
import app.java.commons.dimensions.health.HealthStats;
import app.java.commons.dimensions.interactions.InteractionsPaths;
import app.java.commons.dimensions.interactions.InteractionsStats;
import app.java.commons.dimensions.leisure.LeisurePaths;
import app.java.commons.dimensions.leisure.LeisureStats;
import app.java.commons.dimensions.mainActivity.MainActivityPaths;
import app.java.commons.dimensions.mainActivity.MainActivityStats;
import app.java.commons.dimensions.materialLiving.MaterialLivingPaths;
import app.java.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.commons.dimensions.overall.OverallExperiencePaths;
import app.java.commons.dimensions.overall.OverallExperienceStats;
import app.java.commons.dimensions.safety.SafetyPaths;
import app.java.commons.dimensions.safety.SafetyStats;
import app.java.commons.utils.JsonStatsUtils;

import java.util.Map;

import static app.java.commons.constants.Constants.*;

public class QoLIJsonStats {
    public static void writeDimensions() {
        writeDataByCountries();
        writeDataByRegions();
    }

    private static void writeDataByCountries() {
        Map<String, Number>
                qoliList = QoLIStats.generateIndicatorList(),
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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, QoLIPaths.QOLI_FILE_NAME, null);
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EducationPaths.EDUCATION_FILE_NAME, EducationStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EnvironmentPaths.ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, GovRightsPaths.GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, HealthPaths.HEALTH_FILE_NAME, HealthStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, InteractionsPaths.INTERACTIONS_FILE_NAME, InteractionsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LeisurePaths.LEISURE_FILE_NAME, LeisureStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MainActivityPaths.MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, SafetyPaths.SAFETY_FILE_NAME, SafetyStats.preparedIndicators);
    }

    private static void writeDataByRegions() {
        Map<String, Number>
                qoliList = QoLIStats.aggregateRegions(QoLIStats.generateIndicatorList()),
                educationStats = QoLIStats.aggregateRegions(EducationStats.generateDimensionList()),
                environmentStats = QoLIStats.aggregateRegions(EnvironmentStats.generateDimensionList()),
                govRightsStats = QoLIStats.aggregateRegions(GovRightsStats.generateDimensionList()),
                healthStats = QoLIStats.aggregateRegions(HealthStats.generateDimensionList()),
                interactionsStats = QoLIStats.aggregateRegions(InteractionsStats.generateDimensionList()),
                leisureStats = QoLIStats.aggregateRegions(LeisureStats.generateDimensionList()),
                mainActivityStats = QoLIStats.aggregateRegions(MainActivityStats.generateDimensionList()),
                materialLivingStats = QoLIStats.aggregateRegions(MaterialLivingStats.generateDimensionList()),
                overallExperienceStats = QoLIStats.aggregateRegions(OverallExperienceStats.generateDimensionList()),
                safetyStats = QoLIStats.aggregateRegions(SafetyStats.generateDimensionList());

        JsonStatsUtils.writeJsonData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, QoLIPaths.QOLI_FILE_NAME, null);
        JsonStatsUtils.writeJsonData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, EducationPaths.EDUCATION_FILE_NAME, EducationStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, EnvironmentPaths.ENVIRONMENT_FILE_NAME, EnvironmentStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, GovRightsPaths.GOVERNANCE_FILE_NAME, GovRightsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, HealthPaths.HEALTH_FILE_NAME, HealthStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, InteractionsPaths.INTERACTIONS_FILE_NAME, InteractionsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, LeisurePaths.LEISURE_FILE_NAME, LeisureStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, MainActivityPaths.MAIN_ACTIVITY_FILE_NAME, MainActivityStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, MaterialLivingPaths.LIVING_CONDITIONS_FILE_NAME, MaterialLivingStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, OverallExperiencePaths.OVERALL_EXPERIENCE_FILE_NAME, OverallExperienceStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, SafetyPaths.SAFETY_FILE_NAME, SafetyStats.preparedIndicators);
    }
}
