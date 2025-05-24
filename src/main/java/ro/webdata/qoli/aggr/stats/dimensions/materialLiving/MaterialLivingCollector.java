package ro.webdata.qoli.aggr.stats.dimensions.materialLiving;

import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;

import static ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingParams.*;
import static ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingPaths.*;

public class MaterialLivingCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getDwellingIssuesRatio(), LIVING_CONDITIONS_RAW_PATH, DWELLING_ISSUES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEndMeetInabilityRatio(), LIVING_CONDITIONS_RAW_PATH, END_MEET_INABILITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getFinancialSatisfaction(), LIVING_CONDITIONS_RAW_PATH, FINANCIAL_SATISFACTION_FILE_NAME);
        FileUtils.writeToJSONFile(getGdpPerCapitaPpsRatio(), LIVING_CONDITIONS_RAW_PATH, GDP_PER_CAPITA_PPS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHighIncomeRatio(), LIVING_CONDITIONS_RAW_PATH, HIGH_INCOME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getIncomeQuintileRatio(), LIVING_CONDITIONS_RAW_PATH, INCOME_QUINTILE_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLackOfBathsRatio(), LIVING_CONDITIONS_RAW_PATH, LACK_OF_BATHS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLowWorkIntensityRatio(), LIVING_CONDITIONS_RAW_PATH, LOW_WORK_INTENSITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getMaterialDeprivationRatio(), LIVING_CONDITIONS_RAW_PATH, MATERIAL_DEPRIVATION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getMedianIncome(), LIVING_CONDITIONS_RAW_PATH, MEDIAN_INCOME_PPS_FILE_NAME);
        FileUtils.writeToJSONFile(getOverOccupiedRatio(), LIVING_CONDITIONS_RAW_PATH, OVER_OCCUPIED_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPovertyRiskRatio(), LIVING_CONDITIONS_RAW_PATH, POVERTY_RISK_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnderOccupiedRatio(), LIVING_CONDITIONS_RAW_PATH, UNDER_OCCUPIED_RATIO_FILE_NAME);
    }

    /**
     * Population living in a dwelling with a leaking roof, damp walls, floors
     * or foundation, or rot in window frames of floor<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho01<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2020; 2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getDwellingIssuesRatio() {
        return Fetcher.fetchData("ilc_mdho01", DWELLING_ISSUES_RATIO_PARAMS);
    }

    /**
     * Inability to make ends meet<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of the population living in households, by difficulty of making ends meet (%)<br/>
     * Dataset: ilc_mdes09<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getEndMeetInabilityRatio() {
        return Fetcher.fetchData("ilc_mdes09", END_MEET_INABILITY_RATIO_PARAMS);
    }

    /**
     * Percentage of the population rating their financial satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05b<br/>
     * Years: 2013; 2018; 2022
     *
     * @return
     */
    private static StringBuilder getFinancialSatisfaction() {
        return Fetcher.fetchSatisfactionRatio(FINANCIAL_SATISFACTION_PARAMS);
    }

    /**
     * GDP per capita in PPS<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: tec00114<br/>
     * Years: 2011-2023
     *
     * @return
     */
    private static StringBuilder getGdpPerCapitaPpsRatio() {
        return Fetcher.fetchData("tec00114", GDP_PER_CAPITA_PPS_RATIO_PARAMS);
    }

    /**
     * Proportion of the population having income of 130% of median income or more<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_di20<br/>
     * Years: 2003-2023
     *
     * @return
     */
    private static StringBuilder getHighIncomeRatio() {
        return Fetcher.fetchData("ilc_di20", HIGH_INCOME_RATIO_PARAMS);
    }

    /**
     * Inequality of income distribution (S80/S20 income quintile share ratio) by selected age group<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: income quintile share ratio (%)<br/>
     * Dataset: ilc_di11<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getIncomeQuintileRatio() {
        return Fetcher.fetchData("ilc_di11", INCOME_QUINTILE_RATIO_PARAMS);
    }

    /**
     * Population having neither a bath, nor a shower, nor indoor flushing toilet in their household<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho05<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLackOfBathsRatio() {
        return Fetcher.fetchData("ilc_mdho05", LACK_OF_BATHS_RATIO_PARAMS);
    }

    /**
     * People living in households with very low work intensity<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of total population aged 0 to 59 years (%)<br/>
     * Dataset: ilc_lvhl11<br/>
     * Years: 2003-2023<br/><br/>
     *
     * Comments: NUTS 2 regions => ilc_lvhl21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLowWorkIntensityRatio() {
        return Fetcher.fetchData("ilc_lvhl11", LOW_WORK_INTENSITY_RATIO_PARAMS);
    }

    /**
     * Severe material deprivation rate<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mddd11<br/>
     * Years: 2003-2020<br/><br/>
     *
     * Comments: NUTS regions => ilc_mddd21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getMaterialDeprivationRatio() {
        return Fetcher.fetchData("ilc_mddd11", MATERIAL_DEPRIVATION_RATIO_PARAMS);
    }

    /**
     * Median income<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: Purchasing Power Standard (number)<br/>
     * Dataset: ilc_di03<br/>
     * Note: EU-SILC and ECHP surveys</br/>
     * Years: 1995-2023<br/><br/>
     *
     * Comments: NUTS 2 regions => nama_10r_2hhinc
     *
     * @return
     */
    private static StringBuilder getMedianIncome() {
        return Fetcher.fetchData("ilc_di03", MEDIAN_INCOME_PPS_PARAMS);
    }

    /**
     * Overcrowding rate<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho05a<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getOverOccupiedRatio() {
        return Fetcher.fetchData("ilc_lvho05a", OVER_OCCUPIED_RATIO_PARAMS);
    }

    /**
     * At-risk-of-poverty rate (cut-off point: 60% of median equivalised income after social transfers)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_li03<br/>
     * Note: EU-SILC and ECHP surveys<br/>
     * Years: 1995-2023<br/><br/>
     *
     * Comments: NUTS regions => ilc_li41<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPovertyRiskRatio() {
        return Fetcher.fetchData("ilc_li03", POVERTY_RISK_RATIO_PARAMS);
    }

    /**
     * Share of people living in under-occupied dwellings<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho50a<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2023
     *
     * @return
     */
    private static StringBuilder getUnderOccupiedRatio() {
        return Fetcher.fetchData("ilc_lvho50a", UNDER_OCCUPIED_RATIO_PARAMS);
    }
}
