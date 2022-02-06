package app.java.data.fetch.dao;

/**
 * Health status<br/>
 * Data information: https://ec.europa.eu/eurostat/web/health/data
 */
public interface HealthDAO {
    /**
     * Daily alcohol consumption<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_al1b<br/>
     * Years: 2014<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @deprecated no dataset: FR
     * @return
     */
    StringBuilder getAlcoholicRatio();

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
    StringBuilder getBodyMassIndexRatio();

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
    StringBuilder getFVRatio();

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
    StringBuilder getHealthPersonnelRatio();

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
    StringBuilder getHealthyLifeRatio();

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
    StringBuilder getHealthyLifeYears();

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
    StringBuilder getHospitalBedsRatio();

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
    StringBuilder getLifeExpectancy();

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
    StringBuilder getLongHealthIssueRatio();

    /**
     * Proportion of the population aged 15 and over who did aerobic
     * and muscle-strengthening exercise<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: hlth_ehis_pe9e<br/>
     * Years: 2014
     *
     * @deprecated no dataset: BE; NL
     * @return
     */
    StringBuilder getPhysicalActivitiesRatio();

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
    StringBuilder getSmokersRatio();

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
    StringBuilder getUnmetDentalRatio();

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
    StringBuilder getUnmetMedicalRatio();

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
    StringBuilder getWorkAccidents();
}
