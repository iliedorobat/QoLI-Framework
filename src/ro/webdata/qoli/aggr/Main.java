package ro.webdata.qoli.aggr;

import ro.webdata.qoli.aggr.commons.dimensions.QoLIAggrParams;
import ro.webdata.qoli.aggr.commons.dimensions.QoLICsvStats;
import ro.webdata.qoli.aggr.commons.dimensions.QoLIJsonStats;
import ro.webdata.qoli.aggr.commons.dimensions.QoLIStats;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationStats;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentStats;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsStats;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthStats;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityStats;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceStats;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyStats;
import ro.webdata.qoli.aggr.data.fetch.DataCollector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.StartupUtils.*;
import static ro.webdata.qoli.aggr.commons.dimensions.QoLIAggrParams.*;
import static ro.webdata.qoli.aggr.commons.dimensions.QoLIAggrParams.SAFETY;

public class Main {
    private static final HashMap<String, Map<String, Number>> dataByCountries = new HashMap<>(){{
        put(QOLI, QoLIStats.generateStats());
        put(EDUCATION, EducationStats.generateStats());
        put(ENVIRONMENT, EnvironmentStats.generateStats());
        put(GOVERNANCE, GovRightsStats.generateStats());
        put(HEALTH, HealthStats.generateStats());
        put(LEISURE_INTERACT, LeisureInteractStats.generateStats());
        put(LIVING_CONDITIONS, MaterialLivingStats.generateStats());
        put(MAIN_ACTIVITY, MainActivityStats.generateStats());
        put(OVERALL_EXPERIENCE, OverallExperienceStats.generateStats());
        put(SAFETY, SafetyStats.generateStats());
    }};

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
            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            String direction = getDirection(list);
            QoLICsvStats.writeDimensions(dataByCountries, direction, calculateIndicators);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
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
}
