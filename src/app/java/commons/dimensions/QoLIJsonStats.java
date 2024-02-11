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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "qoli");
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "education");
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "environment");
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "governance");
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "health");
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "interactions");
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "leisure");
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "mainActivity");
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "livingConditions");
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "overallExperience");
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_COUNTRY, "safety");
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

        JsonStatsUtils.writeJsonData(qoliList, EU28_MEMBERS, SERIES_TYPE_REGION, "qoli");
        JsonStatsUtils.writeJsonData(educationStats, EU28_MEMBERS, SERIES_TYPE_REGION, "education");
        JsonStatsUtils.writeJsonData(environmentStats, EU28_MEMBERS, SERIES_TYPE_REGION, "environment");
        JsonStatsUtils.writeJsonData(govRightsStats, EU28_MEMBERS, SERIES_TYPE_REGION, "governance");
        JsonStatsUtils.writeJsonData(healthStats, EU28_MEMBERS, SERIES_TYPE_REGION, "health");
        JsonStatsUtils.writeJsonData(interactionsStats, EU28_MEMBERS, SERIES_TYPE_REGION, "interactions");
        JsonStatsUtils.writeJsonData(leisureStats, EU28_MEMBERS, SERIES_TYPE_REGION, "leisure");
        JsonStatsUtils.writeJsonData(mainActivityStats, EU28_MEMBERS, SERIES_TYPE_REGION, "mainActivity");
        JsonStatsUtils.writeJsonData(materialLivingStats, EU28_MEMBERS, SERIES_TYPE_REGION, "livingConditions");
        JsonStatsUtils.writeJsonData(overallExperienceStats, EU28_MEMBERS, SERIES_TYPE_REGION, "overallExperience");
        JsonStatsUtils.writeJsonData(safetyStats, EU28_MEMBERS, SERIES_TYPE_REGION, "safety");
    }
}
