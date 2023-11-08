package app.java.commons.dimensions.materialLiving;

import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.materialLiving.MaterialLivingPaths.*;

public class MaterialLivingCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getDwellingIssuesRatio(), FilePathConst.MATERIAL_LIVING_PATH, DWELLING_ISSUES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getEndMeetInabilityRatio(), FilePathConst.MATERIAL_LIVING_PATH, END_MEET_INABILITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getFinancialSatisfaction(), FilePathConst.MATERIAL_LIVING_PATH, FINANCIAL_SATISFACTION_FILE_NAME);
        FileUtils.writeToJSONFile(getHighIncomeRatio(), FilePathConst.MATERIAL_LIVING_PATH, HIGH_INCOME_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getIncomeQuintileRatio(), FilePathConst.MATERIAL_LIVING_PATH, INCOME_QUINTILE_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLackOfBathsRatio(), FilePathConst.MATERIAL_LIVING_PATH, LACK_OF_BATHS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLowWorkIntensityRatio(), FilePathConst.MATERIAL_LIVING_PATH, LOW_WORK_INTENSITY_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getMaterialDeprivationRatio(), FilePathConst.MATERIAL_LIVING_PATH, MATERIAL_DEPRIVATION_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getMedianIncome(), FilePathConst.MATERIAL_LIVING_PATH, MEDIAN_INCOME_FILE_NAME);
        FileUtils.writeToJSONFile(getOverOccupiedRatio(), FilePathConst.MATERIAL_LIVING_PATH, OVER_OCCUPIED_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPovertyRiskRatio(), FilePathConst.MATERIAL_LIVING_PATH, POVERTY_RISK_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnderOccupiedRatio(), FilePathConst.MATERIAL_LIVING_PATH, UNDER_OCCUPIED_RATIO_FILE_NAME);
    }

    /**
     * Population living in a dwelling with a leaking roof, damp walls, floors
     * or foundation, or rot in window frames of floor<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho01<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getDwellingIssuesRatio() {
        return Fetcher.fetchData("ilc_mdho01", MaterialLivingParams.getDwellingIssuesParams());
    }

    /**
     * Inability to make ends meet<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of the population living in households, by difficulty of making ends meet (%)<br/>
     * Dataset: ilc_mdes09<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getEndMeetInabilityRatio() {
        return Fetcher.fetchData("ilc_mdes09", MaterialLivingParams.getEndMeetInabilityParams());
    }

    /**
     * Percentage of the population rating their financial satisfaction as high<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013; 2018
     *
     * @return
     */
    private static StringBuilder getFinancialSatisfaction() {
        return Fetcher.fetchSatisfactionRatio(MaterialLivingParams.getFinancialSatisfactionParams());
    }

    /**
     * Proportion of the population having income of 130% of median income or more<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_di20<br/>
     * Years: 2003-2022
     *
     * @return
     */
    private static StringBuilder getHighIncomeRatio() {
        return Fetcher.fetchData("ilc_di20", MaterialLivingParams.getHighIncomeParams());
    }

    /**
     * Inequality of income distribution (S80/S20 income quintile share ratio) by selected age group<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: income quintile share ratio (%)<br/>
     * Dataset: ilc_di11<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getIncomeQuintileRatio() {
        return Fetcher.fetchData("ilc_di11", MaterialLivingParams.getIncomeQuintileParams());
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
        return Fetcher.fetchData("ilc_mdho05", MaterialLivingParams.getLackOfBathsParams());
    }

    /**
     * People living in households with very low work intensity<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: proportion of total population aged 0 to 59 years (%)<br/>
     * Dataset: ilc_lvhl11<br/>
     * Years: 2003-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => ilc_lvhl21<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLowWorkIntensityRatio() {
        return Fetcher.fetchData("ilc_lvhl11", MaterialLivingParams.getLowWorkIntensityParams());
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
        return Fetcher.fetchData("ilc_mddd11", MaterialLivingParams.getMaterialDeprivationParams());
    }

    /**
     * Median income<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: Purchasing Power Standard (number)<br/>
     * Dataset: ilc_di03<br/>
     * Note: EU-SILC and ECHP surveys</br/>
     * Years: 1995-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => nama_10r_2hhinc
     *
     * @return
     */
    private static StringBuilder getMedianIncome() {
        return Fetcher.fetchData("ilc_di03", MaterialLivingParams.getMedianIncome());
    }

    /**
     * Overcrowding rate<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho05a<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getOverOccupiedRatio() {
        return Fetcher.fetchData("ilc_lvho05a", MaterialLivingParams.getOverOccupiedParams());
    }

    /**
     * At-risk-of-poverty rate (cut-off point: 60% of median equivalised income after social transfers)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_li03<br/>
     * Note: EU-SILC and ECHP surveys<br/>
     * Years: 1995-2022<br/><br/>
     *
     * Comments: NUTS regions => ilc_li41<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getPovertyRiskRatio() {
        return Fetcher.fetchData("ilc_li03", MaterialLivingParams.getPovertyRiskParams());
    }

    /**
     * Share of people living in under-occupied dwellings<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_lvho50a<br/>
     * Note: EU-SILC survey<br/>
     * Years: 2003-2022
     *
     * @return
     */
    private static StringBuilder getUnderOccupiedRatio() {
        return Fetcher.fetchData("ilc_lvho50a", MaterialLivingParams.getUnderOccupiedParams());
    }
}
