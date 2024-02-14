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

import java.util.Map;

import static app.java.commons.constants.Constants.*;
import static app.java.commons.constants.FilePathConst.*;

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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, QOLI_DIR, null);
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, EDUCATION_DIR, EducationStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, ENVIRONMENT_DIR, EnvironmentStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, GOVERNANCE_DIR, GovRightsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, HEALTH_DIR, HealthStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, INTERACTIONS_DIR, InteractionsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LEISURE_DIR, LeisureStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, MAIN_ACTIVITY_DIR, MainActivityStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, LIVING_CONDITIONS_DIR, MaterialLivingStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, OVERALL_EXPERIENCE_DIR, OverallExperienceStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, SAFETY_DIR, SafetyStats.preparedIndicators);
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

        JsonStatsUtils.writeJsonData(qoliList, EU28_REGIONS, SERIES_TYPE_REGION, QOLI_DIR, null);
        JsonStatsUtils.writeJsonData(educationStats, EU28_REGIONS, SERIES_TYPE_REGION, EDUCATION_DIR, EducationStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(environmentStats, EU28_REGIONS, SERIES_TYPE_REGION, ENVIRONMENT_DIR, EnvironmentStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_REGIONS, SERIES_TYPE_REGION, GOVERNANCE_DIR, GovRightsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(healthStats, EU28_REGIONS, SERIES_TYPE_REGION, HEALTH_DIR, HealthStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_REGIONS, SERIES_TYPE_REGION, INTERACTIONS_DIR, InteractionsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(leisureStats, EU28_REGIONS, SERIES_TYPE_REGION, LEISURE_DIR, LeisureStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_REGIONS, SERIES_TYPE_REGION, MAIN_ACTIVITY_DIR, MainActivityStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_REGIONS, SERIES_TYPE_REGION, LIVING_CONDITIONS_DIR, MaterialLivingStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_REGIONS, SERIES_TYPE_REGION, OVERALL_EXPERIENCE_DIR, OverallExperienceStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(safetyStats, EU28_REGIONS, SERIES_TYPE_REGION, SAFETY_DIR, SafetyStats.preparedIndicators);
    }
}
