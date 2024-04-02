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
import ro.webdata.qoli.aggr.stats.utils.MathUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QoLIStats {
    public static Map<String, Number> generateStats(List<String> aggrList, List<String> countryCodes) {
        List<String> countryList = countryCodes == null || countryCodes.size() == 0
                ? Arrays.asList(Constants.EU28_MEMBERS)
                : countryCodes;
        
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                educationStats = EducationStats.generateStats(aggrList, countryList),
                environmentStats = EnvironmentStats.generateStats(aggrList, countryList),
                govRightsStats = GovRightsStats.generateStats(aggrList, countryList),
                healthStats = HealthStats.generateStats(aggrList, countryList),
                leisureInteractStats = LeisureInteractStats.generateStats(aggrList, countryList),
                mainActivityStats = MainActivityStats.generateStats(aggrList, countryList),
                materialLivingStats = MaterialLivingStats.generateStats(aggrList, countryList),
                overallExperienceStats = OverallExperienceStats.generateStats(aggrList, countryList),
                safetyStats = SafetyStats.generateStats(aggrList, countryList);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : countryList) {
                String key = MapUtils.generateKey(code, year);

                double education = educationStats.get(key).doubleValue();
                double environment = environmentStats.get(key).doubleValue();
                double govRights = govRightsStats.get(key).doubleValue();
                double health = healthStats.get(key).doubleValue();
                double leisureInteract = leisureInteractStats.get(key).doubleValue();
                double mainActivity = mainActivityStats.get(key).doubleValue();
                double materialLiving = materialLivingStats.get(key).doubleValue();
                double overallExperience = overallExperienceStats.get(key).doubleValue();
                double safety = safetyStats.get(key).doubleValue();

                double product = 1
                        * education
                        * environment
                        * govRights
                        * health
                        * leisureInteract
                        * mainActivity
                        * materialLiving
                        * overallExperience
                        * safety;
                Number value = MathUtils.getLogValue(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
