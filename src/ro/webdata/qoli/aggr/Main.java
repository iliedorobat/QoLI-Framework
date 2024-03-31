package ro.webdata.qoli.aggr;

import ro.webdata.qoli.aggr.data.fetch.DataCollector;
import ro.webdata.qoli.aggr.stats.constants.EnvConst;
import ro.webdata.qoli.aggr.stats.dimensions.QoLICsvStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIJsonStats;
import ro.webdata.qoli.aggr.stats.dimensions.QoLIStats;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyStats;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.ParamsUtils.*;
import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);

        boolean collect = args.length == 0 || contains(list, "--collect");
        boolean indStatus = contains(list, "--showIndicatorStatus");
        boolean calculate = args.length == 0 || contains(list, "--calculate");
        boolean calculateIndicators = args.length == 0 || contains(list, "--calculateIndicators");
        boolean print = args.length == 0 || contains(list, "--print");
        int statusYear = getTargetYear(list);
        List<String> aggr = getAggregations(list);

        if (collect) {
            // 1. Collect the datasets;
            DataCollector.collectData();
        }

        if (statusYear > -1) {
            // 2. (OPTIONAL) Print the data inconsistencies (available dataset and expected dataset)
            EducationStats.printDataAvailability(statusYear, indStatus);
            EnvironmentStats.printDataAvailability(statusYear, indStatus);
            GovRightsStats.printDataAvailability(statusYear, indStatus);
            HealthStats.printDataAvailability(statusYear, indStatus);
            LeisureInteractStats.printDataAvailability(statusYear, indStatus);
            MainActivityStats.printDataAvailability(statusYear, indStatus);
            MaterialLivingStats.printDataAvailability(statusYear, indStatus);
            OverallExperienceStats.printDataAvailability(statusYear, indStatus);
            SafetyStats.printDataAvailability(statusYear, indStatus);
        }

        if (calculate) {
            HashMap<String, Map<String, Number>> dataByCountries = generateData(aggr);
            String direction = getDirection(list);

            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            QoLICsvStats.writeDimensions(dataByCountries, direction, calculateIndicators, EnvConst.MIN_YEAR, EnvConst.MAX_YEAR);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators, EnvConst.MIN_YEAR, EnvConst.MAX_YEAR);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
                HashMap<String, Map<String, Number>> dataByCountries = generateData(aggr);

                // 4. Print the QoLI and the QoLI dimensions values
                QoLICsvStats.printDimensions(list, seriesType, dataByCountries, direction);

                // 5. Print a specific indicator
                EducationStats.printIndicators(list, seriesType, direction);
                EnvironmentStats.printIndicators(list, seriesType, direction);
                GovRightsStats.printIndicators(list, seriesType, direction);
                HealthStats.printIndicators(list, seriesType, direction);
                LeisureInteractStats.printIndicators(list, seriesType, direction);
                MainActivityStats.printIndicators(list, seriesType, direction);
                MaterialLivingStats.printIndicators(list, seriesType, direction);
                OverallExperienceStats.printIndicators(list, seriesType, direction);
                SafetyStats.printIndicators(list, seriesType, direction);
            }
        }
    }

    private static HashMap<String, Map<String, Number>> generateData(List<String> aggr) {
        return new HashMap<>(){{
            put(QOLI, QoLIStats.generateStats(aggr));
            put(EDUCATION, EducationStats.generateStats(aggr));
            put(ENVIRONMENT, EnvironmentStats.generateStats(aggr));
            put(GOVERNANCE, GovRightsStats.generateStats(aggr));
            put(HEALTH, HealthStats.generateStats(aggr));
            put(LEISURE_INTERACT, LeisureInteractStats.generateStats(aggr));
            put(LIVING_CONDITIONS, MaterialLivingStats.generateStats(aggr));
            put(MAIN_ACTIVITY, MainActivityStats.generateStats(aggr));
            put(OVERALL_EXPERIENCE, OverallExperienceStats.generateStats(aggr));
            put(SAFETY, SafetyStats.generateStats(aggr));
        }};
    }
}
