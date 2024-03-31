package ro.webdata.qoli.aggr.commons.dimensions;

import ro.webdata.qoli.aggr.commons.MapOrder;
import ro.webdata.qoli.aggr.commons.constants.Constants;
import ro.webdata.qoli.aggr.commons.constants.EnvConst;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.commons.utils.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QoLIStats {
    public static Map<String, Number> generateStats() {
        return generateStats(QoLIAggrParams.ALLOWED_PARAMS);
    }

    public static Map<String, Number> generateStats(List<String> aggrList) {
        Map<String, Number> consolidatedList = new TreeMap<>(new MapOrder());
        Map<String, Number>
                educationStats = EducationStats.generateStats(aggrList),
                environmentStats = EnvironmentStats.generateStats(aggrList),
                govRightsStats = GovRightsStats.generateStats(aggrList),
                healthStats = HealthStats.generateStats(aggrList),
                leisureInteractStats = LeisureInteractStats.generateStats(aggrList),
                mainActivityStats = MainActivityStats.generateStats(aggrList),
                materialLivingStats = MaterialLivingStats.generateStats(aggrList),
                overallExperienceStats = OverallExperienceStats.generateStats(aggrList),
                safetyStats = SafetyStats.generateStats(aggrList);

        for (int year = EnvConst.MIN_YEAR; year <= EnvConst.MAX_YEAR; year++) {
            for (String code : Constants.EU28_MEMBERS) {
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
                Number value = Math.log(product);
                consolidatedList.put(key, value);
            }
        }

        return consolidatedList;
    }
}
