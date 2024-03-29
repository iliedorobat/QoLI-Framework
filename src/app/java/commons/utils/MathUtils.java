package app.java.commons.utils;

import app.java.commons.constants.Constants;

import java.util.Map;

public class MathUtils {
    private static final int HUNDRED_VALUE = 100;
    private static final int THOUSAND_VALUE = 1000;
    private static final int TEN_THOUSAND_VALUE = 10000;
    private static final int HUNDRED_THOUSAND_VALUE = 100000;

    /**
     * Returns the value of a specific square
     *
     * @param value The value of square extraction
     * @param order The square order
     * @return <b>double</b>
     */
    public static double getSquareValue(double value, double order) {
        if (order == 0)
            return Math.pow(value, 0);
        return Math.pow(value, 1 / order);
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

    /* ************** P E R C E N T A G E    O P E R A T I O N S ************** */
    /**
     * Get a safety value.<br/>
     * <b>A safety value</b> is a value that neutralize the effect of the "0" value
     * in the multiplication and division processes.<br/>
     *
     * E.g.:<br/>
     * - value = 0     =>   safetyValue = 0 + 101 = 101<br/>
     * - value = -100  =>   safetyValue = -100 + 101 = 1
     *
     * @param value The percentage value
     * @return The safety value
     */
    public static double percentageSafetyDouble(double value) {
        if (value < Constants.PERCENTAGE_MIN_VALUE) {
            throw new Error("The value (" + value + ") is lower than " + Constants.PERCENTAGE_MIN_VALUE
                    + " and the current safety threshold can not be applied.");
        }

        return value + Constants.PERCENTAGE_SAFETY_THRESHOLD;
    }

    /**
     * Get a safety value.<br/>
     * <b>A safety value</b> is a value that neutralize the effect of the "0" value
     * in the multiplication and division processes.<br/>
     *
     * E.g.:<br/>
     * - value = 0     =>   safetyValue = 0 + 101 = 101<br/>
     * - value = -100  =>   safetyValue = -100 + 101 = 1
     *
     * @param map The related map
     * @param key The key
     * @return The safety value
     */
    public static double percentageSafetyDouble(Map<String, Number> map, String key) {
        double value = map.get(key).doubleValue();
        return percentageSafetyDouble(value);
    }

    /**
     * Get a safety value.<br/>
     * <b>A safety value</b> is a value that neutralize the effect of the "0" value
     * in the multiplication and division processes.<br/>
     *
     * E.g.:<br/>
     * - value = 0     =>   safetyValue = 0 + 101 = 101<br/>
     * - value = -100  =>   safetyValue = -100 + 101 = 1
     *
     * @param map The related map
     * @param key The key
     * @param reversedImpact true/false specifying if the indicator has a negative impact
     *                       (e.g.: dropoutRatio, pollutionRatio, etc.)
     * @return The safety value
     */
    public static double percentageSafetyDouble(Map<String, Number> map, String key, boolean reversedImpact) {
        if (reversedImpact) {
            double reversedValue = MathUtils.percentageReverseRatio(map, key);
            return percentageSafetyDouble(reversedValue);
        }

        return percentageSafetyDouble(map, key);
    }

    /**
     * Get the differences between 100% and the current ratio.<br/>
     * E.g.: Dropout Ratio = 7%   =>   Graduating Ratio = 100% - 7% = 93%
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
     * E.g.: Gender pay gap = -3%   =>   100% + 3% = 103% (women earn 103% of what men earn)
     * E.g.: Gender pay gap = +8%   =>   100% - 8% = 92% (women earn 92% of what men earn)
     *
     * @param map The related map
     * @param key The key
     * @return The reversed ratio
     */
    public static double reverseGenderGap(Map<String, Number> map, String key) {
        Number number = map.get(key);
        if (number == null) {
            return 0;
        }

        double value = number.doubleValue();
        return value < 0
                ? 100 + Math.abs(value)
                : 100 - value;
    }
    /* ************** E O F    P E R C E N T A G E    O P E R A T I O N S ************** */

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
