package ro.webdata.qoli.aggr.stats.dimensions.health;

import ro.webdata.qoli.aggr.stats.utils.FileUtils;
import ro.webdata.qoli.aggr.data.fetch.Fetcher;

public class HealthCollector {
    public static void fetchData() {
        Fetcher.sleep(100);
        FileUtils.writeToJSONFile(getBodyMassIndexRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.BMI_FILE_NAME);
        FileUtils.writeToJSONFile(getDepressiveRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.DEPRESSIVE_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLifeExpectancy(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.LIFE_EXPECTANCY_FILE_NAME);
        FileUtils.writeToJSONFile(getLongHealthIssuesRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.LONG_HEALTH_ISSUES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthPersonnelRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.HEALTH_PERSONNEL_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthyLifeRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.HEALTHY_LIFE_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthyLifeYears(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.HEALTHY_LIFE_YEARS_FILE_NAME);
        FileUtils.writeToJSONFile(getHospitalBedsRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.HOSPITAL_BEDS_FILE_NAME);
        FileUtils.writeToJSONFile(getNonAlcoholicRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.NON_ALCOHOLIC_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getNonFruitsVegetablesRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.NON_FRUITS_VEGETABLES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getPhysicalActivitiesRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.PHYSICAL_ACTIVITIES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getSmokersRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.SMOKERS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnmetDentalRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.UNMET_DENTAL_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnmetMedicalRatio(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.UNMET_MEDICAL_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWorkAccidents(), HealthPaths.HEALTH_RAW_PATH, HealthPaths.WORK_ACCIDENTS_FILE_NAME);
    }

    /**
     * People having body mass within normal limits<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_bm1i<br/>
     * Years: 2014; 2019
     *
     * @return
     */
    private static StringBuilder getBodyMassIndexRatio() {
        return Fetcher.fetchData("hlth_ehis_bm1i", HealthParams.BMI_PARAMS);
    }

    /**
     * Share of people who faced depressive symptoms<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_mh1i<br/>
     * Years: 2014; 2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getDepressiveRatio() {
        return Fetcher.fetchData("hlth_ehis_mh1i", HealthParams.DEPRESSIVE_RATIO_PARAMS);
    }

    /**
     * Health personnel: medical doctors; nurses and midwives; dentists; pharmacists; physiotherapists<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Dataset: hlth_rs_prs2<br/>
     * Value type: per hundred thousand inhabitants (number)<br/>
     * Years: 1960-2022
     *
     * @return
     */
    private static StringBuilder getHealthPersonnelRatio() {
        return Fetcher.fetchData("hlth_rs_prs2", HealthParams.HEALTH_PERSONNEL_PARAMS);
    }

    /**
     * Self-perceived very-good or good health (people aged 16 years or over)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_10<br/>
     * Years: 2005-2023
     *
     * @return
     */
    private static StringBuilder getHealthyLifeRatio() {
        return Fetcher.fetchData("hlth_silc_10", HealthParams.HEALTHY_LIFE_RATIO_PARAMS);
    }

    /**
     * Health expectancy at birth (the number of remaining years that a person of specific
     * age is expected to live without any severe or moderate health problems)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: year (number)<br/>
     * Dataset: hlth_hlye<br/>
     * Years: 2004-2021
     *
     * @return
     */
    private static StringBuilder getHealthyLifeYears() {
        return Fetcher.fetchData("hlth_hlye", HealthParams.HEALTHY_LIFE_YEARS_PARAMS);
    }

    /**
     * Hospital beds<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: per hundred thousand inhabitants (number)<br/>
     * Dataset: hlth_rs_bds1<br/>
     * Note: Hospital beds provide information on health care capacities,
     *      i.e. on the maximum number of patients who can be treated by hospitals<br/>
     * Years: 1960-2022<br/><br/>
     *
     * @return
     */
    private static StringBuilder getHospitalBedsRatio() {
        return Fetcher.fetchData("hlth_rs_bds1", HealthParams.HOSPITAL_BEDS_PARAMS);
    }

    /**
     * Life expectancy at birth (the number of remaining years a person
     * is expected to live at birth or at a certain age)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: years (number)<br/>
     * Dataset: demo_mlexpec<br/>
     * Years: 1960-2023<br/><br/>
     *
     * Comments: NUTS 2 regions => demo_r_mlifexp
     *
     * @return
     */
    private static StringBuilder getLifeExpectancy() {
        return Fetcher.fetchData("demo_mlexpec", HealthParams.LIFE_EXPECTANCY_PARAMS);
    }

    /**
     * People (aged 16 years or over) having a long-standing illness or health problem<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_11<br/>
     * Years: 2008-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLongHealthIssuesRatio() {
        return Fetcher.fetchData("hlth_silc_11", HealthParams.LONG_HEALTH_ISSUE_RATIO_PARAMS);
    }

    /**
     * Share of the population who have never or in the last 12 months had heavy episodic drinking<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_al3i<br/>
     * Years: 2014; 2019
     *
     * @return
     */
    private static StringBuilder getNonAlcoholicRatio() {
        return Fetcher.fetchData("hlth_ehis_al3i", HealthParams.NON_ALCOHOLIC_RATIO_PARAMS);
    }

    /**
     * Share of people who don't consume fruit and vegetables daily<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_fv3i<br/>
     * Years: 2014; 2019
     *
     * @return
     */
    private static StringBuilder getNonFruitsVegetablesRatio() {
        return Fetcher.fetchData("hlth_ehis_fv3i", HealthParams.NON_FRUITS_VEGETABLES_RATIO_PARAMS);
    }

    /**
     * Proportion of the population aged 15 and over who did aerobic
     * and muscle-strengthening exercise<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_pe9e<br/>
     * Years: 2014; 2019
     *
     * @return
     */
    private static StringBuilder getPhysicalActivitiesRatio() {
        return Fetcher.fetchData("hlth_ehis_pe9e", HealthParams.PHYSICAL_ACTIVITIES_RATIO_PARAMS);
    }

    /**
     * Daily smokers of cigarettes<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_sk3i<br/>
     * Years: 2014; 2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getSmokersRatio() {
        return Fetcher.fetchData("hlth_ehis_sk3i", HealthParams.SMOKERS_RATIO_PARAMS);
    }

    /**
     * Self-reported unmet needs for dental examination (people aged 16 years or over)<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_09<br/>
     * Years: 2008-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetDentalRatio() {
        return Fetcher.fetchData("hlth_silc_09", HealthParams.UNMET_DENTAL_RATIO_PARAMS);
    }

    /**
     * Self-reported unmet needs for medical examination (people aged 16 years or over)<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_08<br/>
     * Years: 2008-2023<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetMedicalRatio() {
        return Fetcher.fetchData("hlth_silc_08", HealthParams.UNMET_MEDICAL_RATIO_PARAMS);
    }

    /**
     * Accidents at work requiring 4 or more days of medical care<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: number<br/>
     * Dataset: hsw_mi07<br/>
     * Years: 2008-2021<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getWorkAccidents() {
        return Fetcher.fetchData("hsw_mi07", HealthParams.WORK_ACCIDENTS_PARAMS);
    }
}
