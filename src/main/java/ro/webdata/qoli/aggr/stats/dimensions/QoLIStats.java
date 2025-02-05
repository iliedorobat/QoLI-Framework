package ro.webdata.qoli.aggr.stats.dimensions;

import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.stats.utils.MapUtils;
import ro.webdata.qoli.aggr.stats.utils.MathUtils;

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;

public class QoLIStats {
    public static final Map<String, Map<String, Number>> rawIndicators = new HashMap<>() {{
        putAll(EducationStats.rawIndicators);
        putAll(EnvironmentStats.rawIndicators);
        putAll(GovRightsStats.rawIndicators);
        putAll(HealthStats.rawIndicators);
        putAll(LeisureInteractStats.rawIndicators);
        putAll(MainActivityStats.rawIndicators);
        putAll(MaterialLivingStats.rawIndicators);
        putAll(OverallExperienceStats.rawIndicators);
        putAll(SafetyStats.rawIndicators);
    }};

    public static final Map<String, ArrayList<Integer>> rawIndicatorsTimeRange = extractMultisetTimeRange(rawIndicators);

    public static final Map<String, Map<String, Number>> aggrIndicators = new HashMap<>() {{
        putAll(EducationStats.aggrIndicators);
        putAll(EnvironmentStats.aggrIndicators);
        putAll(GovRightsStats.aggrIndicators);
        putAll(HealthStats.aggrIndicators);
        putAll(LeisureInteractStats.aggrIndicators);
        putAll(MainActivityStats.aggrIndicators);
        putAll(MaterialLivingStats.aggrIndicators);
        putAll(OverallExperienceStats.aggrIndicators);
        putAll(SafetyStats.aggrIndicators);
    }};

    public static final Map<String, Map<String, Number>> baseIndicators = new HashMap<>() {{
        putAll(EducationStats.baseIndicators);
        putAll(EnvironmentStats.baseIndicators);
        putAll(GovRightsStats.baseIndicators);
        putAll(HealthStats.baseIndicators);
        putAll(LeisureInteractStats.baseIndicators);
        putAll(MainActivityStats.baseIndicators);
        putAll(MaterialLivingStats.baseIndicators);
        putAll(OverallExperienceStats.baseIndicators);
        putAll(SafetyStats.baseIndicators);
    }};

    public static Map<String, Number> generateAggrStats(List<String> aggrs, List<String> countryCodes, int startYear, int endYear) {
        List<String> aggrList = getAggrList(aggrs);
        List<String> countryList = getCountryList(countryCodes);

        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Map<String, Number>> stats = prepareDimensions(aggrList, countryList, startYear, endYear);

        for (int year = startYear; year <= endYear; year++) {
            for (String code : countryList) {
                String key = MapUtils.generateKey(code, year);
                double product = 1;
                int counter = 0;

                for (Map.Entry<String, Map<String, Number>> entry : stats.entrySet()) {
                    Map<String, Number> dimensionValues = entry.getValue();
                    String dimensionName = entry.getKey();

                    boolean exists = aggrList.stream()
                            .anyMatch(indicatorName -> {
                                // E.g.: "education"
                                boolean isDimension = indicatorName.equals(dimensionName);
                                // E.g.: "education:dropoutRatio"
                                boolean isIndicator = indicatorName.startsWith(dimensionName + ":");

                                return isDimension || isIndicator;
                            });

                    if (exists) {
                        counter++;
                        product *= dimensionValues.get(key).doubleValue();
                    }
                }

                Number value = MathUtils.getLogValue(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    public static Map<String, Map<String, Number>> prepareDimensions(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        List<String> countryList = getCountryList(countryCodes);

        return new HashMap<>() {{
            put(EDUCATION, EducationStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(ENVIRONMENT, EnvironmentStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(GOVERNANCE, GovRightsStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(HEALTH, HealthStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(LEISURE_INTERACT, LeisureInteractStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(LIVING_CONDITIONS, MaterialLivingStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(MAIN_ACTIVITY, MainActivityStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(OVERALL_EXPERIENCE, OverallExperienceStats.generateAggrStats(aggrList, countryList , startYear, endYear));
            put(SAFETY, SafetyStats.generateAggrStats(aggrList, countryList , startYear, endYear));
        }};
    }

    public static Map<String, Map<String, Number>> prepareExtendedDimensions(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        Map<String, Map<String, Number>> dataByCountries = prepareDimensions(aggrList, countryCodes, startYear, endYear);
        dataByCountries.put(QOLI, QoLIStats.generateAggrStats(aggrList, countryCodes, startYear, endYear));
        return dataByCountries;
    }

    public static List<String> getAggrList(List<String> aggrs) {
        return aggrs == null || aggrs.isEmpty()
                ? QoLIAggrParams.AGGR_PARAMS
                : aggrs;
    }

    public static List<String> getCountryList(List<String> countryCodes) {
        return countryCodes == null || countryCodes.isEmpty()
                ? Arrays.asList(Constants.EU28_MEMBERS)
                : countryCodes;
    }

    public static Map<String, ArrayList<Integer>> extractMultisetTimeRange(Map<String, Map<String, Number>> entries) {
        Map<String, ArrayList<Integer>> timeRanges = new HashMap<>();

        for (Map.Entry<String, Map<String, Number>> entry : entries.entrySet()) {
            timeRanges.put(entry.getKey(), extractTimeRange(entry.getValue()));
        }

        return timeRanges;
    }

    public static ArrayList<Integer> extractTimeRange(Map<String, Number> entries) {
        Set<Integer> set = new HashSet<>();

        for (Map.Entry<String, Number> entry : entries.entrySet()) {
            String key = entry.getKey();
            Number value = entry.getValue();

            if (value != null) {
                String[] pair = key.split("_");
                String year = pair[1];
                set.add(Integer.parseInt(year));
            }
        }

        ArrayList<Integer> timeRange = new ArrayList<>(set);
        Collections.sort(timeRange);
        Collections.reverse(timeRange);

        return timeRange;
    }
}
