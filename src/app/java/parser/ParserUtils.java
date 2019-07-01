package app.java.parser;

import app.java.commons.Constants;

import java.util.HashMap;
import java.util.Map;

public class ParserUtils {
    public static Map<String, String> getGeneralHttpParams() {
        Map<String, String> params = new HashMap<>();
        params.put("lang", "en");

        if (Constants.IS_TESTING) {
            params.put("geo", "RO");
            params.put("time", "2015");
        }

        return params;
    }

    /**
     * Get general parameters for consumption dataset (smokers; fruits and vegetables)
     *
     * @return
     */
    public static Map<String, String> getConsumptionParams() {
        Map<String, String> params = getGeneralHttpParams();
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
    public static Map<String, String> getUnmetHealthParams() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
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
    public static Map<String, String> getWorkOccupationParams() {
        Map<String, String> params = ParserUtils.getGeneralHttpParams();
        params.put("age", "TOTAL");
        params.put("incgrp", "TOTAL");
        params.put("sex", "T");
        params.put("unit", "PC");
        return params;
    }
}
