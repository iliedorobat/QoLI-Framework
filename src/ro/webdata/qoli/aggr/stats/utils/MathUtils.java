package ro.webdata.qoli.aggr.stats.utils;

import java.util.Map;

public class MathUtils {
    private static final int HUNDRED_VALUE = 100;
    private static final int THOUSAND_VALUE = 1000;
    private static final int TEN_THOUSAND_VALUE = 10000;
    private static final int HUNDRED_THOUSAND_VALUE = 100000;

    /**
     * Determine the result of applying of Math.log against a product of indicators.<br/>
     * Return 1 if the result of the product is 1, otherwise calculate the Math.log of the product.
     *
     * @param product The value of the product of a series of indicators
     * @return <b>double</b>
     */
    public static double getLogValue(double product) {
        if (product == 1)
            return product;
        return Math.log(product);
    }

    /**
     * Round a value with "places" decimals
     *
     * @param value The input value
     * @param places The number of decimals with which the value will be formatted
     * @return The value with "places" decimals
     */
    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        double preparedValue = value * factor;
        double roundedValue = Math.round(preparedValue);

        return roundedValue / factor;
    }

    /**
     * Get the differences between 100% and the current ratio.<br/>
     * E.g.: Dropout Ratio = 7%   =>   Graduating Ratio = 100% - 7% = 93%
     * E.g.: Gender pay gap = -3%   =>   100% + 3% = 103% (women earn 103% of what men earn)
     * E.g.: Gender pay gap = +8%   =>   100% - 8% = 92% (women earn 92% of what men earn)
     *
     * @param map The related map
     * @param key The key
     * @return The reversed ratio
     */
    public static double percentageReverseRatio(Map<String, Number> map, String key) {
        Number number = map.get(key);
        double value = number != null
            ? map.get(key).doubleValue()
            : 0;
        return 100 - value;
    }

    /**
     * Transform the value into a value per hundred inhabitants
     *
     * @param populationMap Total population (CommonStats.population)
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerHundredInhabitants(Map<String, Number> populationMap, String key, double value) {
        double population = populationMap.get(key).doubleValue();
        return value / population * HUNDRED_VALUE;
    }

    /**
     * Transform the value into a value per thousand inhabitants
     *
     * @param populationMap Total population (CommonStats.population)
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerThousandInhabitants(Map<String, Number> populationMap, String key, double value) {
        double population = populationMap.get(key).doubleValue();
        return value / population * THOUSAND_VALUE;
    }

    /**
     * Transform the value into a value per ten thousand inhabitants
     *
     * @param populationMap Total population (CommonStats.population)
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerTenThousandInhabitants(Map<String, Number> populationMap, String key, double value) {
        double population = populationMap.get(key).doubleValue();
        return value / population * TEN_THOUSAND_VALUE;
    }

    /**
     * Transform the value into a value per hundred thousand inhabitants
     *
     * @param populationMap Total population (CommonStats.population)
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerHundredThousandInhabitants(Map<String, Number> populationMap, String key, double value) {
        double population = populationMap.get(key).doubleValue();
        return value / population * HUNDRED_THOUSAND_VALUE;
    }
}
