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
            Map<String, Map<String, Number>> dataByCountries = QoLIStats.prepareExtendedDimensions(aggr, null, startYear, endYear);
            String direction = getDirection(list);

            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            QoLICsvStats.writeDimensions(dataByCountries, direction, calculateIndicators, startYear, endYear);
            QoLIJsonStats.writeDimensions(dataByCountries, calculateIndicators, startYear, endYear);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
                Map<String, Map<String, Number>> dataByCountries = QoLIStats.prepareExtendedDimensions(aggr, null, startYear, endYear);

                // 4. Print the QoLI and the QoLI dimensions values
                QoLICsvStats.printDimensions(list, seriesType, dataByCountries, direction);

                // 5. Print a specific indicator
                EducationStats.printAggrIndicators(list, seriesType, direction);
                EnvironmentStats.printAggrIndicators(list, seriesType, direction);
                GovRightsStats.printAggrIndicators(list, seriesType, direction);
                HealthStats.printAggrIndicators(list, seriesType, direction);
                LeisureInteractStats.printAggrIndicators(list, seriesType, direction);
                MainActivityStats.printAggrIndicators(list, seriesType, direction);
                MaterialLivingStats.printAggrIndicators(list, seriesType, direction);
                OverallExperienceStats.printAggrIndicators(list, seriesType, direction);
                SafetyStats.printAggrIndicators(list, seriesType, direction);
            }
        }
    }
}
