package app.java.commons.dimensions.health;

import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

import static app.java.commons.dimensions.health.HealthParams.*;
import static app.java.commons.dimensions.health.HealthPaths.*;

public class HealthCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAlcoholicRatio(), HEALTH_RAW_PATH, ALCOHOLIC_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getBodyMassIndexRatio(), HEALTH_RAW_PATH, BMI_FILE_NAME);
        FileUtils.writeToJSONFile(getFVRatio(), HEALTH_RAW_PATH, FRUITS_VEGETABLES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getLifeExpectancy(), HEALTH_RAW_PATH, LIFE_EXPECTANCY_FILE_NAME);
        FileUtils.writeToJSONFile(getLongHealthIssuesRatio(), HEALTH_RAW_PATH, LONG_HEALTH_ISSUES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthPersonnelRatio(), HEALTH_RAW_PATH, HEALTH_PERSONNEL_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthyLifeRatio(), HEALTH_RAW_PATH, HEALTHY_LIFE_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getHealthyLifeYears(), HEALTH_RAW_PATH, HEALTHY_LIFE_YEARS_FILE_NAME);
        FileUtils.writeToJSONFile(getHospitalBedsRatio(), HEALTH_RAW_PATH, HOSPITAL_BEDS_FILE_NAME);
        FileUtils.writeToJSONFile(getPhysicalActivitiesRatio(), HEALTH_RAW_PATH, PHYSICAL_ACTIVITIES_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getSmokersRatio(), HEALTH_RAW_PATH, SMOKERS_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnmetDentalRatio(), HEALTH_RAW_PATH, UNMET_DENTAL_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getUnmetMedicalRatio(), HEALTH_RAW_PATH, UNMET_MEDICAL_RATIO_FILE_NAME);
        FileUtils.writeToJSONFile(getWorkAccidents(), HEALTH_RAW_PATH, WORK_ACCIDENTS_FILE_NAME);
    }

    /**
     * Daily alcohol consumption<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_al1b<br/>
     * Years: 2014; 2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getAlcoholicRatio() {
        return Fetcher.fetchData("hlth_ehis_al1b", ALCOHOLIC_RATIO_PARAMS);
    }

    /**
     * Overweight and obese people<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_bm1i<br/>
     * Years: 2014; 2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getBodyMassIndexRatio() {
        return Fetcher.fetchData("hlth_ehis_bm1i", BMI_PARAMS);
    }

    /**
     * Daily consumption of fruit and vegetables<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_fv3i<br/>
     * Years: 2014; 2019
     *
     * @return
     */
    private static StringBuilder getFVRatio() {
        return Fetcher.fetchData("hlth_ehis_fv3i", FRUITS_VEGETABLES_RATIO_PARAMS);
    }

    /**
     * Health personnel: Medical doctors; Nurses and midwives; Dentists; Pharmacists; Physiotherapists<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Dataset: hlth_rs_prsrg<br/>
     * Value type: per hundred thousand inhabitants (number)<br/>
     * Years: 1993-2021
     *
     * @return
     */
    private static StringBuilder getHealthPersonnelRatio() {
        return Fetcher.fetchData("hlth_rs_prsrg", HEALTH_PERSONNEL_PARAMS);
    }

    /**
     * Self-perceived very-good or good health (people aged 16 years or over)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_10<br/>
     * Years: 2005-2022
     *
     * @return
     */
    private static StringBuilder getHealthyLifeRatio() {
        return Fetcher.fetchData("hlth_silc_10", HEALTHY_LIFE_RATIO_PARAMS);
    }

    /**
     * Health expectancy at birth<br/>
     *
     * Aggregation: country<br/>
     * Data type: year (number)<br/>
     * Dataset: hlth_hlye<br/>
     * Years: 2004-2021
     *
     * @return Male & Female statistics
     */
    private static StringBuilder getHealthyLifeYears() {
        return Fetcher.fetchData("hlth_hlye", HEALTHY_LIFE_YEARS_PARAMS);
    }

    /**
     * Hospital beds<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: per hundred thousand inhabitants (number)<br/>
     * Dataset: hlth_rs_bds<br/>
     * Note: Hospital beds provide information on health care capacities,
     *      i.e. on the maximum number of patients who can be treated by hospitals<br/>
     * Years: 1960-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => hlth_rs_bdsrg
     *
     * @return
     */
    private static StringBuilder getHospitalBedsRatio() {
        return Fetcher.fetchData("hlth_rs_bds", HOSPITAL_BEDS_PARAMS);
    }

    /**
     * Life expectancy at birth (the number of remaining years a person
     * is expected to live at birth or at a certain age)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: years (number)<br/>
     * Dataset: demo_mlexpec<br/>
     * Years: 1960-2022<br/><br/>
     *
     * Comments: NUTS 2 regions => demo_r_mlifexp
     *
     * @return
     */
    private static StringBuilder getLifeExpectancy() {
        return Fetcher.fetchData("demo_mlexpec", LIFE_EXPECTANCY_PARAMS);
    }

    /**
     * People (aged 16 years or over) having a long-standing illness or health problem<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_11<br/>
     * Years: 2008-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLongHealthIssuesRatio() {
        return Fetcher.fetchData("hlth_silc_11", LONG_HEALTH_ISSUE_RATIO_PARAMS);
    }

    /**
     * Proportion of the population aged 15 and over who did aerobic
     * and muscle-strengthening exercise<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_pe9e<br/>
     * Years: 2014; 2019<br/><br/>
     *
     * @return
     */
    private static StringBuilder getPhysicalActivitiesRatio() {
        return Fetcher.fetchData("hlth_ehis_pe9e", PHYSICAL_ACTIVITIES_RATIO_PARAMS);
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
        return Fetcher.fetchData("hlth_ehis_sk3i", SMOKERS_RATIO_PARAMS);
    }

    /**
     * Self-reported unmet needs for dental examination (people aged 16 years or over)<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_09<br/>
     * Years: 2008-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetDentalRatio() {
        return Fetcher.fetchData("hlth_silc_09", UNMET_DENTAL_RATIO_PARAMS);
    }

    /**
     * Self-reported unmet needs for medical examination (people aged 16 years or over)<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_08<br/>
     * Years: 2008-2022<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetMedicalRatio() {
        return Fetcher.fetchData("hlth_silc_08", UNMET_MEDICAL_RATIO_PARAMS);
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
        return Fetcher.fetchData("hsw_mi07", WORK_ACCIDENTS_PARAMS);
    }
}
