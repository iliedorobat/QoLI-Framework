package app.java.data.fetch;

import app.java.commons.Errors;
import app.java.commons.constants.Constants;
import app.java.commons.constants.EnvConst;
import app.java.data.fetch.dao.impl.MainActivityDAOImpl;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

public class FetcherUtils {
    private static final String[] EU28_MEMBERS = Constants.EU28_MEMBERS;

    public static final String[] ACTIVITIES_TYPE = {
            "AC41A", // Formal voluntary activities
            "AC42A", // Informal voluntary activities
            "AC43A"  // Active citizenship
    };
    public static final String[] SATISFACTION_LEVELS = {
            "HIGH",
            "MED",
            "LOW"
    };
    public static final String[] SUPPORTIVE_API_NAMES = {
            "ilc_scp15",
            "ilc_scp17"
    };
    public static final String[] WEL_BEING_TYPES = {
            "ACCSAT",    // Satisfaction with accommodation
            "COMSAT",    // Satisfaction with commuting time
            "FINSAT",    // Satisfaction with financial situation
            "JOBSAT",    // Job satisfaction
            "GREENSAT",  // Satisfaction with recreational and green areas
            "LIFESAT",   // Overall life satisfaction
            "LIVENVSAT", // Satisfaction with living environment
            "MEANLIFE",  // Meaning of lifE
            "RELSAT",    // Satisfaction with personal relationships
            "TIMESAT"    // Satisfaction with time use
    };

    public static String getGeoParams() {
        String output = "";

        for (int i = 0; i < EU28_MEMBERS.length; i++) {
            output += "geo=" + EU28_MEMBERS[i];

            if (i < EU28_MEMBERS.length - 1) {
                output += "&";
            }
        }

        return output;
    }

    /**
     * Add new parameters into the params list
     * @param params The parameters list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     * @deprecated
     * TODO: remove
     */
    public static void addParams(MultiValuedMap<String, String> params, String[] values, String propertyName) {
        for (int i = 0; i < values.length; i++) {
            params.put(propertyName, values[i]);
        }
    }


    /**
     * Add new parameters into the params list
     * @param params The parameters list
     * @param values The list with values that should be added
     * @param propertyName The name of the added property
     */
    public static void addParams(MultiValuedMap<String, String> params, String propertyName, String[] values) {
        for (int i = 0; i < values.length; i++) {
            params.put(propertyName, values[i]);
        }
    }

    /**
     * Get main parameters
     *
     * @return
     */
    public static MultiValuedMap<String, String> getMainHttpParams() {
        MultiValuedMap<String, String> params = new HashSetValuedHashMap<>();
        params.put("lang", "en");

        if (EnvConst.IS_TESTING) {
            params.put("geo", "RO");
            params.put("time", "2015");
        } else {
            addParams(params, Constants.EU28_MEMBERS, "geo");
            for (int i = EnvConst.MIN_YEAR; i <= EnvConst.MAX_YEAR; i++) {
                params.put("time", String.valueOf(i));
            }
        }

        return params;
    }

    /**
     * Get general parameters for consumption dataset (smokers; fruits and vegetables)
     *
     * @return
     */
    public static MultiValuedMap<String, String> getConsumptionParams() {
        MultiValuedMap<String, String> params = getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("quantile", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return params;
    }

    /**
     * Get general parameters for self-reported unmet needs for medical/dental examination
     *
     * @return
     */
    public static MultiValuedMap<String, String> getUnmetHealthParams() {
        MultiValuedMap<String, String> params = getMainHttpParams();
        params.put("age", "Y_GE16");
        params.put("quantile", "TOTAL");
        params.put("reason", "TOOEFW");
        params.put("sex", "T");
        params.put("unit", "PC");
        return params;
    }

    /**
     * Get general parameters for work occupation (under/over occupied ratio)
     *
     * @return
     */
    public static MultiValuedMap<String, String> getWorkOccupationParams() {
        MultiValuedMap<String, String> params = getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return params;
    }

    /**
     * Get general parameters for home conditions
     *
     * @return
     */
    public static MultiValuedMap<String, String> getHomeConditionsParams() {
        MultiValuedMap<String, String> params = getMainHttpParams();
        params.put("age", "TOTAL");
        params.put("hhtyp", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return params;
    }

    /**
     * Percentage of the population rating their satisfaction as high, medium or low<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_pw05<br/>
     * Years: 2013
     *
     * @param satisfactionLevel The satisfaction level:<br/>
     *                   - HIGH: high;<br/>
     *                   - MED: medium;<br/>
     *                   - LOW: low;
     *
     * @param wellBeing The type of calculated well being:<br/>
     *                  - ACCSAT: Satisfaction with accommodation;<br/>
     *                  - COMSAT: Satisfaction with commuting time;<br/>
     *                  - FINSAT: Satisfaction with financial situation;<br/>
     *                  - GREENSAT: Satisfaction with recreational and green areas;<br/>
     *                  - JOBSAT: Job satisfaction;<br/>
     *                  - LIFESAT: Overall life satisfaction;<br/>
     *                  - LIVENVSAT: Satisfaction with living environment;<br/>
     *                  - MEANLIFE: Meaning of life;<br/>
     *                  - RELSAT: Satisfaction with personal relationships;<br/>
     *                  - TIMESAT: Satisfaction with time use;
     *
     * @return
     */
    public static StringBuilder getSatisfactionRatio(String satisfactionLevel, String wellBeing) {
        try {
            Errors.throwNewError(SATISFACTION_LEVELS, satisfactionLevel, "satisfaction levels");
            Errors.throwNewError(WEL_BEING_TYPES, wellBeing, "well being levels");

            MultiValuedMap<String, String> params = getMainHttpParams();
            params.put("age", "Y_GE16");
            params.put("indic_wb", wellBeing);
            params.put("isced11", "TOTAL");
            params.put("lev_satis", satisfactionLevel);
            params.put("sex", "T");
            params.put("unit", "PC");

            return Fetcher.fetchData("ilc_pw05", params);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Participation in formal or informal voluntary activities or active citizenship<br/>
     * People aged 16 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp19<br/>
     * Years: 2015
     *
     * @param activities The activity types:<br/>
     *                 - AC41A: Formal volontary activities;<br/>
     *                 - AC42A: Informal volontary activities;<br/>
     *                 - AC43A: Active citizenship.
     *
     * @return
     */
    public static StringBuilder getActivePeopleRatio(String[] activities) {
        try {
            Errors.throwNewError(ACTIVITIES_TYPE, activities, "type of people activities");

            MultiValuedMap<String, String> params = getMainHttpParams();
            addParams(params, activities, "acl00");
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("sex", "T");
            params.put("unit", "PC");

            return Fetcher.fetchData("ilc_scp19", params);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get the ratio of persons (16 years or over) who have someone to ask for help
     * or to discuss personal matters<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: percentage (%)<br/>
     * Dataset: ilc_scp15 / ilc_scp17<br/>
     * Years: 2013; 2015
     *
     * @param apiName The API name:<br/>
     *                - ilc_scp15;<br/>
     *                - ilc_scp17.
     * @return
     */
    public static StringBuilder getSupportiveRatio(String apiName) {
        try {
            Errors.throwNewError(SUPPORTIVE_API_NAMES, apiName, "API names");

            MultiValuedMap<String, String> params = getMainHttpParams();
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("sex", "T");
            params.put("unit", "PC");
            return Fetcher.fetchData(apiName, params);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Average number of usual weekly hours of work in main job
     * worked by full-time employed persons aged 15 years or over<br/><br/>
     *
     * Aggregation: country<br/>
     * Data type: hours (number)<br/>
     * Dataset: lfsa_ewhuna | lfsa_ewhun2<br/>
     * Years: 1983-2018<br/><br/>
     *
     * Comments: NUTS 2 regions => lfst_r_lfe2ehour<br/><br/>
     *
     * <b>GREATER IS WORSE!</b>
     *
     * @param dataset The dataset name<br/>
     *                 - lfsa_ewhuna: for years between 1983-2008;<br/>
     *                 - lfsa_ewhun2: for years between 2008-2018.
     *
     * @return
     */
    public static StringBuilder getAvgWorkHours(String dataset) {
        try {
            Errors.throwNewError(
                    MainActivityDAOImpl.WORK_DATASET,
                    dataset,
                    "dataset name"
            );

            String activity = "";
            if (dataset.equals(MainActivityDAOImpl.WORK_DATASET[0]))
                activity = MainActivityDAOImpl.WORK_ACTIVITIES[0];
            if (dataset.equals(MainActivityDAOImpl.WORK_DATASET[1]))
                activity = MainActivityDAOImpl.WORK_ACTIVITIES[1];

            MultiValuedMap<String, String> params = FetcherUtils.getMainHttpParams();
            params.put(activity, "TOTAL");
            params.put("age", "Y15-64");
            params.put("sex", "T");
            params.put("unit", "HR");
            params.put("worktime", "FT");
            params.put("wstatus", "EMP");
            return Fetcher.fetchData(dataset, params);
        } catch (Exception e) {
            return null;
        }
    }
}
