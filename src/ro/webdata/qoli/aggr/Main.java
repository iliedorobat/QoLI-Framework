package ro.webdata.qoli.aggr;

import ro.webdata.qoli.aggr.data.fetch.DataCollector;
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
import java.util.List;
import java.util.Map;

import static ro.webdata.qoli.aggr.ParamsUtils.*;
import static ro.webdata.qoli.aggr.stats.dimensions.QoLIAggrParams.QOLI;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);

        boolean collect = args.length == 0 || contains(list, "--collect");
        boolean indStatus = contains(list, "--showIndicatorStatus");
        boolean calculate = args.length == 0 || contains(list, "--calculate");
        boolean calculateIndicators = args.length == 0 || contains(list, "--calculateIndicators");
        boolean print = args.length == 0 || contains(list, "--print");
        int statusYear = getYear(list, "--dataStatus");
        int startYear = getYear(list, "--startYear");
        int endYear = getYear(list, "--endYear");
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
            Map<String, Map<String, Number>> dataByCountries = prepareDimensions(aggr, startYear, endYear);
            String direction = getDirection(list);

            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            QoLICsvStats.writeDimensions(dataByCountries, direction, calculateIndicators, startYear, endYear);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators, startYear, endYear);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
                Map<String, Map<String, Number>> dataByCountries = prepareDimensions(aggr, startYear, endYear);

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

    private static Map<String, Map<String, Number>> prepareDimensions(List<String> aggr, int startYear, int endYear) {
        Map<String, Map<String, Number>> dataByCountries = QoLIStats.prepareDimensions(aggr, null, startYear, endYear);
        dataByCountries.put(QOLI, QoLIStats.generateStats(aggr, null, startYear, endYear));
        return dataByCountries;
    }
}
