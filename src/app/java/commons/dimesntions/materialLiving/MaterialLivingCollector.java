package app.java.commons.dimesntions.materialLiving;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

public class MaterialLivingCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getDwellingIssuesRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.DWELLING_ISSUES_RATIO);
        FileUtils.writeToJSONFile(getEndMeetInabilityRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.END_MEET_INABILITY_RATIO);
        FileUtils.writeToJSONFile(getFinancialSatisfaction(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.FINANCIAL_SATISFACTION);
        FileUtils.writeToJSONFile(getHighIncomeRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.HIGH_INCOME_RATIO);
        FileUtils.writeToJSONFile(getIncomeQuintileRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.INCOME_QUINTILE_RATIO);
        FileUtils.writeToJSONFile(getLackOfBathsRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.LACK_OF_BATHS_RATIO);
        FileUtils.writeToJSONFile(getLowWorkIntensityRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.LOW_WORK_INTENSITY_RATIO);
        FileUtils.writeToJSONFile(getMaterialDeprivationRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.MATERIAL_DEPRIVATION_RATIO);
        FileUtils.writeToJSONFile(getMedianIncome(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.MEDIAN_INCOME);
        FileUtils.writeToJSONFile(getOverOccupiedRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.OVER_OCCUPIED_RATIO);
        FileUtils.writeToJSONFile(getPovertyRiskRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.POVERTY_RISK_RATIO);
        FileUtils.writeToJSONFile(getUnderOccupiedRatio(), FilePathConst.MATERIAL_LIVING_PATH, FileNameConst.UNDER_OCCUPIED_RATIO);
    }

    /**
     * Population living in a dwelling with a leaking roof, damp walls, floors
     * or foundation, or rot in window frames of floor<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_mdho01<br/>
     * Years: 2003-2020<br/><br/>
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
     * Years: 2003-2020<br/><br/>
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
     * Years: 2003-2020
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
     * Years: 1995-2020<br/><br/>
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
     * Years: 2003-2020<br/><br/>
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
     * Years: 1995-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => nama_10r_2hhinc
     *
     * @deprecated deprecated in favour of getHighIncomeRatio
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
     * Years: 2003-2020<br/><br/>
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
     * Years: 1995-2020<br/><br/>
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
     * Years: 2003-2020
     *
     * @return
     */
    private static StringBuilder getUnderOccupiedRatio() {
        return Fetcher.fetchData("ilc_lvho50a", MaterialLivingParams.getUnderOccupiedParams());
    }
}
