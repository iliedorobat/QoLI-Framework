package app.java.commons.utils;

import app.java.commons.constants.Constants;
import app.java.commons.dimesntions.common.CommonStats;

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
    /* ************** E O F    P E R C E N T A G E    O P E R A T I O N S ************** */

    /**
     * Transform the value into a value per hundred inhabitants
     *
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerHundredInhabitants(String key, double value) {
        double population = CommonStats.population.get(key).doubleValue();
        return value / population * HUNDRED_VALUE;
    }

    /**
     * Transform the value into a value per thousand inhabitants
     *
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerThousandInhabitants(String key, double value) {
        double population = CommonStats.population.get(key).doubleValue();
        return value / population * THOUSAND_VALUE;
    }

    /**
     * Transform the value into a value per ten thousand inhabitants
     *
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerTenThousandInhabitants(String key, double value) {
        double population = CommonStats.population.get(key).doubleValue();
        return value / population * TEN_THOUSAND_VALUE;
    }

    /**
     * Transform the value into a value per hundred thousand inhabitants
     *
     * @param key The key used to extract the total population
     * @param value The initial value
     * @return The value per thousand inhabitants
     */
    public static Number generatePerHundredThousandInhabitants(String key, double value) {
        double population = CommonStats.population.get(key).doubleValue();
        return value / population * HUNDRED_THOUSAND_VALUE;
    }
}
