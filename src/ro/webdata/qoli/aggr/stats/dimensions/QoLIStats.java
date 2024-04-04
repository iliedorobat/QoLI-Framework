package ro.webdata.qoli.aggr.stats.dimensions;

import ro.webdata.qoli.aggr.stats.MapOrder;
import ro.webdata.qoli.aggr.stats.constants.Constants;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
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
    public static Map<String, Number> generateStats(List<String> aggrList, List<String> countryCodes) {
        List<String> countryList = countryCodes == null || countryCodes.size() == 0
                ? Arrays.asList(Constants.EU28_MEMBERS)
                : countryCodes;
        
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Map<String, Number>> stats = new HashMap<>() {{
            put(EDUCATION, EducationStats.generateStats(aggrList, countryList));
            put(ENVIRONMENT, EnvironmentStats.generateStats(aggrList, countryList));
            put(GOVERNANCE, GovRightsStats.generateStats(aggrList, countryList));
            put(HEALTH, HealthStats.generateStats(aggrList, countryList));
            put(LEISURE_INTERACT, LeisureInteractStats.generateStats(aggrList, countryList));
            put(LIVING_CONDITIONS, MaterialLivingStats.generateStats(aggrList, countryList));
            put(MAIN_ACTIVITY, MainActivityStats.generateStats(aggrList, countryList));
            put(OVERALL_EXPERIENCE, OverallExperienceStats.generateStats(aggrList, countryList));
            put(SAFETY, SafetyStats.generateStats(aggrList, countryList));
        }};

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
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
}
