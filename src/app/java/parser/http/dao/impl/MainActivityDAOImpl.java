package app.java.parser.http.dao.impl;

import app.java.parser.http.DataFetcher;
import app.java.parser.http.dao.MainActivityDAO;

import java.util.Map;

public class MainActivityDAOImpl implements MainActivityDAO {
    //TODO: Income (wage) and benefits
    //TODO: Health and safety at work (accidental injuries at work)

    public StringBuilder getAvgPopulationJSON(Map<String, String> params) {
        return DataFetcher.fetchData("nama_10r_3popgdp", params);
    }

    //TODO: avg free hours per week (Work-life balance)
    public StringBuilder getAvgWorkHoursJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfe2ehour", params);
    }

    public StringBuilder getActivePopulationJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfp2act", params);
    }

    public StringBuilder getEmplymentJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfe2emp", params);
    }

    public StringBuilder getEmploymentRateJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfe2emprt", params);
    }

    //TODO: The long-term unemployment rate = the ratio of people who have been
    // unemployed for at least a year to the total size of the labour force
    // = getLongTermUnmployment / getActivePopulation * 100
    public StringBuilder getLongTermUnmploymentJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfu2ltu", params);
    }

    public StringBuilder getUnmploymentRateJSON(Map<String, String> params) {
        return DataFetcher.fetchData("lfst_r_lfu3rt", params);
    }

    //TODO: GDP per capita = getGDP / getAvgPopulation
    public StringBuilder getGDPJSON(Map<String, String> params) {
        return DataFetcher.fetchData("nama_10r_2gdp", params);
    }
}
