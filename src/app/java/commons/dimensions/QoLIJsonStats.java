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

public class QoLIJsonStats {
    public static void writeDimensions() {
        writeCountries();
        writeRegions();
    }

    private static void writeCountries() {
        Map<String, Number>
                qoliList = QoLICsvStats.generateIndicatorList(),
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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "QoLI");
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Education");
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Environment");
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "GBR");
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Health");
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Interactions");
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Leisure");
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "PMA");
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "MLC");
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Overall Exp");
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "Safety");
    }

    private static void writeRegions() {
        Map<String, Number>
                qoliList = QoLICsvStats.generateIndicatorList(),
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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_REGION, "QoLI");
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Education");
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Environment");
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_REGION, "GBR");
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Health");
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Interactions");
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Leisure");
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_REGION, "PMA");
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_REGION, "MLC");
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Overall Exp");
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_REGION, "Safety");
    }
}
