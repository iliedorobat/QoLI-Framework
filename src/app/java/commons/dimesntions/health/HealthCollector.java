package app.java.commons.dimesntions.health;

import app.java.commons.constants.FileNameConst;
import app.java.commons.constants.FilePathConst;
import app.java.commons.utils.FileUtils;
import app.java.data.fetch.Fetcher;

public class HealthCollector {
    public static void fetchData() {
        FileUtils.writeToJSONFile(getAlcoholicRatio(), FilePathConst.HEALTH_PATH, FileNameConst.ALCOHOLIC_RATIO);
        FileUtils.writeToJSONFile(getBodyMassIndexRatio(), FilePathConst.HEALTH_PATH, FileNameConst.BODY_MASS_INDEX);
        FileUtils.writeToJSONFile(getFVRatio(), FilePathConst.HEALTH_PATH, FileNameConst.FRUITS_VEGETABLES_RATIO);
        FileUtils.writeToJSONFile(getLifeExpectancy(), FilePathConst.HEALTH_PATH, FileNameConst.LIFE_EXPECTANCY);
        FileUtils.writeToJSONFile(getLongHealthIssuesRatio(), FilePathConst.HEALTH_PATH, FileNameConst.LONG_HEALTH_ISSUES_RATIO);
        FileUtils.writeToJSONFile(getHealthPersonnelRatio(), FilePathConst.HEALTH_PATH, FileNameConst.HEALTH_PERSONNEL);
        FileUtils.writeToJSONFile(getHealthyLifeRatio(), FilePathConst.HEALTH_PATH, FileNameConst.HEALTHY_LIFE_RATIO);
        FileUtils.writeToJSONFile(getHealthyLifeYears(), FilePathConst.HEALTH_PATH, FileNameConst.HEALTHY_LIFE_YEARS);
        FileUtils.writeToJSONFile(getHospitalBedsRatio(), FilePathConst.HEALTH_PATH, FileNameConst.HOSPITAL_BEDS);
        FileUtils.writeToJSONFile(getPhysicalActivitiesRatio(), FilePathConst.HEALTH_PATH, FileNameConst.PHYSICAL_ACTIVITIES_RATIO);
        FileUtils.writeToJSONFile(getSmokersRatio(), FilePathConst.HEALTH_PATH, FileNameConst.SMOKERS_RATIO);
        FileUtils.writeToJSONFile(getUnmetDentalRatio(), FilePathConst.HEALTH_PATH, FileNameConst.UNMET_DENTAL_RATIO);
        FileUtils.writeToJSONFile(getUnmetMedicalRatio(), FilePathConst.HEALTH_PATH, FileNameConst.UNMET_MEDICAL_RATIO);
        FileUtils.writeToJSONFile(getWorkAccidents(), FilePathConst.HEALTH_PATH, FileNameConst.WORK_ACCIDENTS);
    }

    /**
     * Daily alcohol consumption<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_al1b<br/>
     * Years: 2014<br/><br/>
     *
     * <b>Lack of data: FR</b><br/><br/>
     * <b>GREATER IS WORSE!</b>
     * @return
     */
    private static StringBuilder getAlcoholicRatio() {
        return Fetcher.fetchData("hlth_ehis_al1b", HealthParams.getAlcoholicParams());
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
        return Fetcher.fetchData("hlth_ehis_bm1i", HealthParams.getBodyMassIndexParams());
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
        return Fetcher.fetchData("hlth_ehis_fv3i", HealthParams.getFVParams());
    }

    /**
     * Health personnel: Medical doctors; Nurses and midwives; Dentists; Pharmacists; Physiotherapists<br/><br/>
     *
     * Aggregation: NUTS 2 regions<br/>
     * Dataset: hlth_rs_prsrg<br/>
     * Value type: per hundred thousand inhabitants (number)<br/>
     * Years: 1993-2020
     *
     * @return
     */
    private static StringBuilder getHealthPersonnelRatio() {
        return Fetcher.fetchData("hlth_rs_prsrg", HealthParams.getHealthPersonnelParams());
    }

    /**
     * Self-perceived very-good or good health<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_10<br/>
     * Years: 2005-2020
     *
     * @return
     */
    private static StringBuilder getHealthyLifeRatio() {
        return Fetcher.fetchData("hlth_silc_10", HealthParams.getHealthyLifeParams());
    }

    /**
     * Health expectancy at birth<br/>
     *
     * Aggregation: country<br/>
     * Data type: year (number)<br/>
     * Dataset: hlth_hlye<br/>
     * Years: 2004-2019
     *
     * @return Male & Female statistics
     */
    private static StringBuilder getHealthyLifeYears() {
        return Fetcher.fetchData("hlth_hlye", HealthParams.getHealthyLifeYearsParams());
    }

    /**
     * Hospital beds<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: per hundred thousand inhabitants (number)<br/>
     * Dataset: hlth_rs_bds<br/>
     * Info: Hospital beds provide information on health care capacities,
     * i.e. on the maximum number of patients who can be treated by hospitals<br/>
     * Years: 1960-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => hlth_rs_bdsrg
     *
     * @return
     */
    private static StringBuilder getHospitalBedsRatio() {
        return Fetcher.fetchData("hlth_rs_bds", HealthParams.getHospitalBedsParams());
    }

    /**
     * Life expectancy at birth (the number of remaining years a person
     * is expected to live at birth or at a certain age)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: years (number)<br/>
     * Dataset: demo_mlexpec<br/>
     * Years: 1960-2020<br/><br/>
     *
     * Comments: NUTS 2 regions => demo_r_mlifexp
     *
     * @return
     */
    private static StringBuilder getLifeExpectancy() {
        return Fetcher.fetchData("demo_mlexpec", HealthParams.getLifeExpectancyParams());
    }

    /**
     * People (aged 16 years or over) having a long-standing illness or health problem<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_11<br/>
     * Years: 2008-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getLongHealthIssuesRatio() {
        return Fetcher.fetchData("hlth_silc_11", HealthParams.getLongHealthIssuesParams());
    }

    /**
     * Proportion of the population aged 15 and over who did aerobic
     * and muscle-strengthening exercise<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_pe9e<br/>
     * Years: 2014<br/><br/>
     *
     * <b>Lack of data: BE; NL</b>
     * @return
     */
    private static StringBuilder getPhysicalActivitiesRatio() {
        return Fetcher.fetchData("hlth_ehis_pe9e", HealthParams.getPhysicalActivitiesParams());
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
        return Fetcher.fetchData("hlth_ehis_sk3i", HealthParams.getSmokersParams());
    }

    /**
     * Self-reported unmet needs for dental examination<br/>
     * People aged 16 years or over<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_09<br/>
     * Years: 2008-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetDentalRatio() {
        return Fetcher.fetchData("hlth_silc_09", HealthParams.getUnmetDentalParams());
    }

    /**
     * Self-reported unmet needs for medical examination<br/>
     * People aged 16 years or over<br/>
     * Reasons: Too expensive or too far to travel or waiting list<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_silc_08<br/>
     * Years: 2008-2020<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getUnmetMedicalRatio() {
        return Fetcher.fetchData("hlth_silc_08", HealthParams.getUnmetMedicalParams());
    }

    /**
     * Accidents at work (severity = 4 days or over)<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: number<br/>
     * Dataset: hsw_mi07<br/>
     * Years: 2008-2019<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @return
     */
    private static StringBuilder getWorkAccidents() {
        return Fetcher.fetchData("hsw_mi07", HealthParams.getWorkAccidentsParams());
    }
}
