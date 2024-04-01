package ro.webdata.qoli.aggr.stats.dimensions.materialLiving;

import java.util.HashMap;
import java.util.Map;

public class MaterialLivingAggrParams {
    public static final String LIVING_CONDITIONS = "livingConditions";

    public static final String DWELLING_ISSUES_RATIO = LIVING_CONDITIONS + ":dwellingIssuesRatio";
    public static final String END_MEET_INABILITY_D_RATIO = LIVING_CONDITIONS + ":endMeetInabilityDifficultyRatio";
    public static final String END_MEET_INABILITY_GD_RATIO = LIVING_CONDITIONS + ":endMeetInabilityGreatDifficultyRatio";
    public static final String END_MEET_INABILITY_RATIO = LIVING_CONDITIONS + ":endMeetInabilityRatio";
    public static final String FINANCIAL_SATISFACTION_RATIO = LIVING_CONDITIONS + ":financialSatisfactionRatio";
    public static final String GDP_PER_CAPITA_PPS_RATIO = LIVING_CONDITIONS + ":gdpPerCapitaPpsRatio";
    public static final String HIGH_INCOME_RATIO = LIVING_CONDITIONS + ":highIncomeRatio";
    public static final String INCOME_QUINTILE_RATIO = LIVING_CONDITIONS + ":incomeQuintileRatio";
    public static final String LACK_OF_BATHS_RATIO = LIVING_CONDITIONS + ":lackOfBathsRatio";
    public static final String LOW_WORK_INTENSITY_RATIO = LIVING_CONDITIONS + ":lowWorkIntensityRatio";
    public static final String MATERIAL_DEPRIVATION_RATIO = LIVING_CONDITIONS + ":materialDeprivationRatio";
    public static final String MEDIAN_INCOME_PPS_RATIO = LIVING_CONDITIONS + ":medianIncomePpsRatio";
    public static final String OVER_OCCUPIED_RATIO = LIVING_CONDITIONS + ":overOccupiedRatio";
    public static final String POVERTY_RISK_RATIO = LIVING_CONDITIONS + ":povertyRiskRatio";
    public static final String UNDER_OCCUPIED_RATIO = LIVING_CONDITIONS + ":underOccupiedRatio";

    public static final Map<String, String> ALLOWED_PARAMS = new HashMap<>() {{
        put(DWELLING_ISSUES_RATIO, "Dwelling Issues Ratio");
        put(END_MEET_INABILITY_RATIO, "End Meet Inability Ratio");
        put(FINANCIAL_SATISFACTION_RATIO, "Financial Satisfaction Ratio");
        put(GDP_PER_CAPITA_PPS_RATIO, "GDP per Capita in PPS Ratio");
        put(HIGH_INCOME_RATIO, "High Income Ratio");
        put(INCOME_QUINTILE_RATIO, "Income Quintile Ratio");
        put(LACK_OF_BATHS_RATIO, "Lack of Baths Ratio");
        put(LOW_WORK_INTENSITY_RATIO, "Low Work Intensity Ratio");
        put(MATERIAL_DEPRIVATION_RATIO, "Material Deprivation Ratio");
        put(MEDIAN_INCOME_PPS_RATIO, "Median Income in PPS Ratio");
        put(OVER_OCCUPIED_RATIO, "Over Occupied Ratio");
        put(POVERTY_RISK_RATIO, "Poverty Risk Ratio");
        put(UNDER_OCCUPIED_RATIO, "Under Occupied Ratio");
    }};

    public static final Map<String, Boolean> IS_REVERSED = new HashMap<>() {{
        put(DWELLING_ISSUES_RATIO, true);
        put(END_MEET_INABILITY_RATIO, true);
        put(FINANCIAL_SATISFACTION_RATIO, false);
        put(GDP_PER_CAPITA_PPS_RATIO, false);
        put(HIGH_INCOME_RATIO, false);
        put(INCOME_QUINTILE_RATIO, true);
        put(LACK_OF_BATHS_RATIO, true);
        put(LOW_WORK_INTENSITY_RATIO, true);
        put(MATERIAL_DEPRIVATION_RATIO, true);
        put(MEDIAN_INCOME_PPS_RATIO, false);
        put(OVER_OCCUPIED_RATIO, true);
        put(POVERTY_RISK_RATIO, true);
        put(UNDER_OCCUPIED_RATIO, false);
    }};
}
