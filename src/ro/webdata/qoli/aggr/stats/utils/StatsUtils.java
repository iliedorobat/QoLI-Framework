package ro.webdata.qoli.aggr.stats.utils;

import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StatsUtils {
    /**
     * Aggregate the result of the target indicators (aggrList) into a single value. <br/>
     * The target indicators (aggrList) are filtered out based on a specific series of indicators which describe
     * a dimension (allowedAggrList).
     *
     * @param aggrList List of aggregation params<br/>
     * E.g.:<br/>
     *      * predefined list: QoLIAggrParams.ALLOWED_PARAMS, EducationAggrParams.ALLOWED_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param countryCodes List of analyzed country codes (E.g.: ["AT", "BE", "RO"]).
     * @param startYear The year from which the analysis begins
     * @param endYear The year in which the analysis ends
     * @param mainAggregator The aggregator describing the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationAggrParams.EDUCATION, EnvironmentAggrParams.ENVIRONMENT, etc.
     * @param allowedAggrList List of aggregation params specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.ALLOWED_PARAMS, EducationAggrParams.ALLOWED_PARAMS, etc.
     * @param reversedMap Map containing specifications about which indicator describes a negative state.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.IS_REVERSED, EducationAggrParams.IS_REVERSED, etc.
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
     *      * predefined list: QoLIAggrParams.ALLOWED_PARAMS, EducationAggrParams.ALLOWED_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param countryCodes List of analyzed country codes (E.g.: ["AT", "BE", "RO"].
     * @param startYear The year from which the analysis begins
     * @param endYear The year in which the analysis ends
     * @param reversedMap Map containing specifications about which indicator describes a negative state.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.IS_REVERSED, EducationAggrParams.IS_REVERSED, etc.
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
                        product *= MathUtils.percentageSafetyDouble(values, key, isReversed);
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
     * @param membersList The list of countries/regions
     * @param seriesType The type of the aggregation (REGION or COUNTRY)
     * @param startYear The year the analysis starts
     * @param endYear The year the analysis ends
     * @return Filtered stats in a specific range
     */
    public static TreeMap<String, TreeMap<Integer, Number>> filterStats(
            Map<String, Number> entries,
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
                String key = code + "_" + year;
                Number value = data.get(key);
                itemStats.put(year, value);
            }
            stats.put(code, itemStats);
        }

        return stats;
    }

    /**
     * *** used on offences ratio ***<br/><br/>
     *
     * Aggregate values of the regions of the UK (UKC-L & UKM & UKN) into a single value (UK)
     * @param entries The map with target dimension data<br/>
     *                - (E.g.: EducationStats.generateStats())
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
     *                - (E.g.: EducationStats.generateStats())
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
     * Consolidate calculated values of EU countries into values of EU regions
     * @param entries The map with target dimension data<br/>
     *                - (E.g.: EducationStats.generateStats())
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
     * Filter the target indicators (aggrList) based on a specific series of indicators
     * which describe a dimension (allowedAggrList).
     @param aggrList List of aggregation params<br/>
     * E.g.:<br/>
     *      * predefined list: QoLIAggrParams.ALLOWED_PARAMS, EducationAggrParams.ALLOWED_PARAMS, etc.
     *      * custom list: ["digitalSkillsRatio", "dropoutRatio", "crimeRatio"]
     * @param mainAggregator The aggregator describing the target dimension.<br/>
     * E.g.:<br/>
     *      * EducationAggrParams.EDUCATION, EnvironmentAggrParams.ENVIRONMENT, etc.
     * @param allowedAggrList List of aggregation params specific to the target dimension.<br/>
     * E.g.:<br/>
     *      * QoLIAggrParams.ALLOWED_PARAMS, EducationAggrParams.ALLOWED_PARAMS, etc.
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
