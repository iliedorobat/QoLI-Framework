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
        writeData(EU28_MEMBERS, SERIES_TYPE_COUNTRY);
        writeData(EU28_MEMBERS, SERIES_TYPE_REGION);
    }

    private static void writeData(String[] membersList, String seriesType) {
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

        JsonStatsUtils.writeJsonData(qoliList, membersList, seriesType, QOLI_DIR, null);
        JsonStatsUtils.writeJsonData(educationStats, membersList, seriesType, EDUCATION_DIR, EducationStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(environmentStats, membersList, seriesType, ENVIRONMENT_DIR, EnvironmentStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(govRightsStats, membersList, seriesType, GOVERNANCE_DIR, GovRightsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(healthStats, membersList, seriesType, HEALTH_DIR, HealthStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(interactionsStats, membersList, seriesType, INTERACTIONS_DIR, InteractionsStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(leisureStats, membersList, seriesType, LEISURE_DIR, LeisureStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(mainActivityStats, membersList, seriesType, MAIN_ACTIVITY_DIR, MainActivityStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(materialLivingStats, membersList, seriesType, LIVING_CONDITIONS_DIR, MaterialLivingStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(overallExperienceStats, membersList, seriesType, OVERALL_EXPERIENCE_DIR, OverallExperienceStats.preparedIndicators);
        JsonStatsUtils.writeJsonData(safetyStats, membersList, seriesType, SAFETY_DIR, SafetyStats.preparedIndicators);
    }
}
