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

import java.util.*;

import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;

public class QoLIStats {
    public static final Map<String, Map<String, Number>> preparedIndicators = new HashMap<>() {{
        putAll(EducationStats.preparedIndicators);
        putAll(EnvironmentStats.preparedIndicators);
        putAll(GovRightsStats.preparedIndicators);
        putAll(HealthStats.preparedIndicators);
        putAll(LeisureInteractStats.preparedIndicators);
        putAll(MainActivityStats.preparedIndicators);
        putAll(MaterialLivingStats.preparedIndicators);
        putAll(OverallExperienceStats.preparedIndicators);
        putAll(SafetyStats.preparedIndicators);
    }};

    public static Map<String, Number> generateStats(List<String> aggrs, List<String> countryCodes, int startYear, int endYear) {
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

                Number value = Math.pow(product, 1.0/counter);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }

    public static Map<String, Map<String, Number>> prepareDimensions(List<String> aggrList, List<String> countryCodes, int startYear, int endYear) {
        List<String> countryList = getCountryList(countryCodes);

        return new HashMap<>() {{
            put(EDUCATION, EducationStats.generateStats(aggrList, countryList , startYear, endYear));
            put(ENVIRONMENT, EnvironmentStats.generateStats(aggrList, countryList , startYear, endYear));
            put(GOVERNANCE, GovRightsStats.generateStats(aggrList, countryList , startYear, endYear));
            put(HEALTH, HealthStats.generateStats(aggrList, countryList , startYear, endYear));
            put(LEISURE_INTERACT, LeisureInteractStats.generateStats(aggrList, countryList , startYear, endYear));
            put(LIVING_CONDITIONS, MaterialLivingStats.generateStats(aggrList, countryList , startYear, endYear));
            put(MAIN_ACTIVITY, MainActivityStats.generateStats(aggrList, countryList , startYear, endYear));
            put(OVERALL_EXPERIENCE, OverallExperienceStats.generateStats(aggrList, countryList , startYear, endYear));
            put(SAFETY, SafetyStats.generateStats(aggrList, countryList , startYear, endYear));
        }};
    }

    public static List<String> getAggrList(List<String> aggrs) {
        return aggrs == null || aggrs.size() == 0
                ? QoLIAggrParams.AGGR_PARAMS
                : aggrs;
    }

    public static List<String> getCountryList(List<String> countryCodes) {
        return countryCodes == null || countryCodes.size() == 0
                ? Arrays.asList(Constants.EU28_MEMBERS)
                : countryCodes;
    }
}
