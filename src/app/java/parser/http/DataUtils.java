package app.java.parser.http;

import app.java.commons.Errors;
import app.java.parser.ParserUtils;
import org.apache.commons.collections4.MultiValuedMap;

public class DataUtils {
    public static final String[] EU28_MEMBERS = {
            "EU28", // European Union - 28 countries
            "AT", // Austria
            "BE", // Belgium
            "BG", // Bulgaria
            "CY", // Cyprus
            "CZ", // Czechia
            "DE", // Germany (until 1990 former territory of the FRG)
            "DK", // Denmark
            "EE", // Estonia
            "EL", // Greece
            "ES", // Spain
            "FI", // Finland
            "FR", // France
            "HR", // Croatia
            "HU", // Hungary
            "IE", // Ireland
            "IT", // Italy
            "LT", // Lithuania
            "LU", // Luxembourg
            "LV", // Latvia
            "MT", // Malta
            "NL", // Netherlands
            "PL", // Poland
            "PT", // Portugal
            "RO", // Romania
            "SE", // Sweden
            "SI", // Slovenia
            "SK", // Slovakia
            "UK"  // United Kingdom
    };
    public static final String[] SATIS_LEVEL = {
            "HIGH",
            "MED",
            "LOW"
    };
    public static final String[] WEL_BEING_TYPE = {
            "ACCSAT",
            "COMSAT",
            "FINSAT",
            "JOBSAT",
            "GREENSAT",
            "LIFESAT",
            "LIVENVSAT",
            "MEANLIFE",
            "RELSAT",
            "TIMESAT"
    };
    public static final String[] ACTIVITIES_TYPE = {
            "AC41A",
            "AC42A",
            "AC43A"
    };
    public static final String[] SUPPORTIVE_API_NAMES = {
            "ilc_scp15",
            "ilc_scp17"
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
     * Get general parameters for consumption dataset (smokers; fruits and vegetables)
     *
     * @return
     */
    public static MultiValuedMap<String, String> getConsumptionParams() {
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
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
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
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
        MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
        params.put("age", "TOTAL");
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
     * @param satisLevel The satisfaction level:<br/>
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
    public static StringBuilder getSatisfactionRatio(String satisLevel, String wellBeing) {
        try {
            Errors.throwNewError(SATIS_LEVEL, satisLevel, "satisfaction levels");
            Errors.throwNewError(WEL_BEING_TYPE, wellBeing, "well being levels");

            MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
            params.put("age", "Y_GE16");
            params.put("indic_wb", wellBeing);
            params.put("isced11", "TOTAL");
            params.put("lev_satis", satisLevel);
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("ilc_pw05", params);
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

            MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
            ParserUtils.addParams(params, activities, "acl00");
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("sex", "T");
            params.put("unit", "PC");

            return DataFetcher.fetchData("ilc_scp19", params);
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

            MultiValuedMap<String, String> params = ParserUtils.getMainHttpParams();
            params.put("age", "Y_GE16");
            params.put("isced11", "TOTAL");
            params.put("set", "T");
            params.put("unit", "PC");
            return DataFetcher.fetchData(apiName, params);
        } catch (Exception e) {
            return null;
        }
    }
}
