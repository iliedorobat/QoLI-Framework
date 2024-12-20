package ro.webdata.qoli.aggr.stats.utils;

import ro.webdata.qoli.EnvState;
import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class StatsUtils {
    /**
     * Aggregate the result of the target indicators (aggrList) into a single value. <br/>
     * The target indicators (aggrList) are filtered out based on a specific series of indicators which describe
     * a dimension (allowedAggrList).
     *
     * @param aggrList List of aggregation params<br/>
     * E.g.:<br/>
     *      * predefined list: QoLIAggrParams.AGGR_PARAMS, EducationAggrParams.AGGR_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param countryCodes List of analyzed country codes (E.g.: ["AT", "BE", "RO"]).
     * @param startYear The year from which the analysis begins
     * @param endYear The year in which the analysis ends
     * @param mainAggregator The aggregator describing the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationAggrParams.EDUCATION, EnvironmentAggrParams.ENVIRONMENT, etc.
     * @param allowedAggrList List of aggregation params specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.AGGR_PARAMS, EducationAggrParams.AGGR_PARAMS, etc.
     * @param reversedMap Map containing specifications about which indicator describes a negative state.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.AGGR_REVERSED_STATES, EducationAggrParams.AGGR_REVERSED_STATES, etc.
     * @param preparedIndicators Map that contains the prepared indicators specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationStats.preparedIndicators, etc.
     * @return Sorted map with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    public static Map<String, Number> generateStats(
            List<String> aggrList,
            List<String> countryCodes,
            int startYear,
            int endYear,
            String mainAggregator,
            List<String> allowedAggrList,
            Map<String, Boolean> reversedMap,
            Map<String, Map<String, Number>> preparedIndicators
    ) {
        List<String> filteredAggrList = filterAggrList(aggrList, mainAggregator, allowedAggrList);
        return generateStats(filteredAggrList, countryCodes, startYear, endYear, reversedMap, preparedIndicators);
    }

    /**
     * Aggregate the result of the target indicators (aggrList) into a single value.
     *
     * @param aggrList List of aggregation params<br/>
     * E.g.:<br/>
     *      * predefined list: QoLIAggrParams.AGGR_PARAMS, EducationAggrParams.AGGR_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param countryCodes List of analyzed country codes (E.g.: ["AT", "BE", "RO"].
     * @param startYear The year from which the analysis begins
     * @param endYear The year in which the analysis ends
     * @param reversedMap Map containing specifications about which indicator describes a negative state.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.AGGR_REVERSED_STATES, EducationAggrParams.AGGR_REVERSED_STATES, etc.
     * @param preparedIndicators Map that contains the prepared indicators specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationStats.preparedIndicators, etc.
     * @return Sorted map with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    public static Map<String, Number> generateStats(
            List<String> aggrList,
            List<String> countryCodes,
            int startYear,
            int endYear,
            Map<String, Boolean> reversedMap,
            Map<String, Map<String, Number>> preparedIndicators
    ) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = startYear; year <= endYear; year++) {
            for (String code : countryCodes) {
                String key = MapUtils.generateKey(code, year);
                double product = 1;

                for (Map.Entry<String, Map<String, Number>> entry : preparedIndicators.entrySet()) {
                    Map<String, Number> values = entry.getValue();
                    String indicatorName = entry.getKey();

                    String aggr = aggrList.stream()
                            .filter(item -> item.equals(indicatorName))
                            .findFirst()
                            .orElse(null);

                    if (aggr != null && aggr.equals(indicatorName)) {
                        if (!reversedMap.containsKey(indicatorName)) {
                            System.err.println("reversed map does not contains " + indicatorName);
                            continue;
                        }
                        boolean isReversed = reversedMap.get(indicatorName);
                        product *= isReversed
                                ? MathUtils.percentageReverseRatio(values, key)
                                : values.get(key).doubleValue();
                    }
                }

                Number value = MathUtils.getLogValue(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Filter the generated stats in a range.
     * @param entries The map containing prepared data for a specific dimension
     * @param entriesTimeRange: [OPTIONAL] The list of allowed time range
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Filtered stats in a specific range
     */
    public static TreeMap<String, TreeMap<Integer, Number>> filterStats(
            Map<String, Number> entries,
            ArrayList<Integer> entriesTimeRange,
            String[] membersList,
            String seriesType,
            int startYear,
            int endYear
    ) {
        TreeMap<String, TreeMap<Integer, Number>> stats = new TreeMap<>();
        Map<String, Number> data = getEntries(entries, seriesType, startYear, endYear);

        for (String code : membersList) {
            TreeMap<Integer, Number> itemStats = new TreeMap<>();

            for (int year = startYear; year <= endYear; year++) {
                if (entriesTimeRange == null || entriesTimeRange.contains(year)) {
                    String key = code + "_" + year;
                    Number value = data.get(key);

                    if (value != null)
                        itemStats.put(year, value);
                }
            }

            if (!itemStats.isEmpty())
                stats.put(code, itemStats);
        }

        return stats;
    }

    /**
     * *** used on offences ratio ***<br/><br/>
     *
     * Aggregate values of the regions of the UK (UKC-L & UKM & UKN) into a single value (UK)
     * @param entries The map with target dimension data<br/>
     *                - (E.g.: EducationStats.generateAggrStats())
     * @param code The country code for which the aggregation is done
     * @param year The year for which the aggregation is done
     * @return Prepared value
     */
    public static Number generateJsonValue(Map<String, Number> entries, String code, int year) {
        String key = code + "_" + year;
        Number value = entries.get(key);

        // Handle offences data
        if (value == null && code.equals("UK")) {
            double ukSum = 0;

            ukSum += entries.get("UKC-L" + "_" + year).doubleValue();
            ukSum += entries.get("UKM" + "_" + year).doubleValue();
            ukSum += entries.get("UKN" + "_" + year).doubleValue();

            value = ukSum;
        }

        return value;
    }

    /**
     * Get countries data or regions data based on "seriesType"
     * @param entries The map with target dimension data<br/>
     *                - (E.g.: EducationStats.generateAggrStats())
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Countries/regions data
     */
    public static Map<String, Number> getEntries(Map<String, Number> entries, String seriesType, int startYear, int endYear) {
        return seriesType.equals(Constants.SERIES_TYPE_REGION)
                ? StatsUtils.aggregateRegions(entries, startYear, endYear)
                : entries;
    }

    /**
     * Consolidate the values using the geometric mean
     * @param mapsList The list of maps
     * @return The consolidated list of maps
     */
    public static Map<String, Number> calculateGeometricMean(List<Map<String, Number>> mapsList) {
        return calculateGeometricMean(mapsList, 1.0);
    }

    /**
     * Consolidate the values using the geometric mean
     * @param mapsList The list of maps
     * @param multiplicationFactor A value used to adjust the original value
     *      - E.g.: population trust params must be multiplied by 10 to obtain values between 0 - 100.
     * @return The consolidated list of maps
     */
    public static Map<String, Number> calculateGeometricMean(List<Map<String, Number>> mapsList, double multiplicationFactor) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                double product = 1;

                for (Map<String, Number> map : mapsList) {
                    double value = getProdSafeValue(map, code, year) * multiplicationFactor;
                    product *= value;
                }

                Number value = Math.pow(product, 1.0/mapsList.size());
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    /**
     * Consolidate the values by summing them up
     * @param mapsList The list of maps
     * @return The consolidated list of maps
     */
    public static Map<String, Number> calculateSum(List<Map<String, Number>> mapsList) {
        return calculateSum(mapsList, 1.0);
    }

    /**
     * Consolidate the values by summing them up
     * @param mapsList The list of maps
     * @param multiplicationFactor A value used to adjust the original value
     *      - E.g.: health personnel params must be divided by 10 (multiplied by 1/10) to transform the value
     *              expressed "per hundred thousand" inhabitants into "per million" inhabitants.
     * @return The consolidated list of maps
     */
    public static Map<String, Number> calculateSum(List<Map<String, Number>> mapsList, double multiplicationFactor) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = EnvState.MIN_YEAR; year <= EnvState.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
                String key = MapUtils.generateKey(code, year);
                double sum = 0;

                for (Map<String, Number> map : mapsList) {
                    double value = map.get(key).doubleValue();
                    sum += value;
                }

                sum *= multiplicationFactor;
                consolidatedList.put(key, sum);
            }
        }

        return consolidatedList;
    }

    /**
     * Consolidate calculated values of EU countries into values of EU regions
     * @param entries The map with target dimension data<br/>
     *                - (E.g.: EducationStats.generateAggrStats())
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Regions data prepared to be exported as a JSON
     */
    private static Map<String, Number> aggregateRegions(Map<String, Number> entries, int startYear, int endYear) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());

        for (int year = startYear; year <= endYear; year++) {
            double easternCounter = 0,
                    northernCounter = 0,
                    southernCounter = 0,
                    westernCounter = 0;
            double easternSum = 0,
                    northernSum = 0,
                    southernSum = 0,
                    westernSum = 0;

            for (Map.Entry<String, Number> entry : entries.entrySet()) {
                String entryCode = MapUtils.getEntryCode(entry);
                Integer entryYear = MapUtils.getEntryYear(entry);
                Number entryValue = entry.getValue();

                if (entryYear != null && entryYear == year) {
                    if (Arrays.asList(Constants.EU_EASTERN_MEMBERS).contains(entryCode)) {
                        easternSum += entryValue.doubleValue();
                        easternCounter++;
                    }
                    if (Arrays.asList(Constants.EU_NORTHERN_MEMBERS).contains(entryCode)) {
                        northernSum += entryValue.doubleValue();
                        northernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_SOUTHERN_MEMBERS).contains(entryCode)) {
                        southernSum += entryValue.doubleValue();
                        southernCounter++;
                    }
                    if (Arrays.asList(Constants.EU_WESTERN_MEMBERS).contains(entryCode)) {
                        westernSum += entryValue.doubleValue();
                        westernCounter++;
                    }
                }
            }

            consolidatedList.put(MapUtils.generateKey("EU_EASTERN", year), easternSum / easternCounter);
            consolidatedList.put(MapUtils.generateKey("EU_NORTHERN", year), northernSum / northernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_SOUTHERN", year), southernSum / southernCounter);
            consolidatedList.put(MapUtils.generateKey("EU_WESTERN", year), westernSum / westernCounter);
        }

        return consolidatedList;
    }

    /**
     * Get the safe value. A safe value is considered to be either the neutral value (1)
     * or a value greater than the neutral value.
     * @param entries The map containing prepared data for a specific indicator
     * @param code The country code for which the calculation is done
     * @param year The year for which the calculation is done
     * @return
     */
    private static double getProdSafeValue(Map<String, Number> entries, String code, int year) {
        String inputKey = MapUtils.generateKey(code, year);
        double value = entries.get(inputKey).doubleValue();
        if (value != 0)
            return value;

        // Get the value recorded to the nearest year which is greater than 0.0 for cases where
        // the datasets contain the value "0.0" instead of "null".
        // This preparation prevents corruption of the result of the product of values.
        // E.g.: "Getting together with friends" for the year 2022 (data retrieved on June 11, 2024)
        for (int crrYear = year; crrYear >= EnvState.MIN_YEAR; crrYear--) {
            String key = MapUtils.generateKey(code, crrYear);
            value = entries.get(key).doubleValue();

            if (value != 0)
                return value;
        }

        return 1;
    }

    /**
     * Filter the target indicators (aggrList) based on a specific series of indicators
     * which describe a dimension (allowedAggrList).
     @param aggrList List of aggregation params<br/>
     * E.g.:<br/>
     *      * predefined list: QoLIAggrParams.AGGR_PARAMS, EducationAggrParams.AGGR_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param mainAggregator The aggregator describing the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationAggrParams.EDUCATION, EnvironmentAggrParams.ENVIRONMENT, etc.
     * @param allowedAggrList List of aggregation params specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.AGGR_PARAMS, EducationAggrParams.AGGR_PARAMS, etc.
     * @return Sorted map with COUNTRY-CODE_YEAR as key (e.g.: AT_2010; RO_2015 etc.)
     */
    private static List<String> filterAggrList(List<String> aggrList, String mainAggregator, List<String> allowedAggrList) {
        if (aggrList == null || aggrList.size() == 0)
            return allowedAggrList;

        if (aggrList.contains(mainAggregator))
            return allowedAggrList;

        return aggrList.stream()
                .filter(allowedAggrList::contains)
                .collect(Collectors.toList());
    }
}
