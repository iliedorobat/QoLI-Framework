package app.java.aggr;

import app.java.aggr.commons.dimensions.QoLICsvStats;
import app.java.aggr.commons.dimensions.QoLIJsonStats;
import app.java.aggr.commons.dimensions.education.EducationStats;
import app.java.aggr.commons.dimensions.environment.EnvironmentStats;
import app.java.aggr.commons.dimensions.gov.GovRightsStats;
import app.java.aggr.commons.dimensions.health.HealthStats;
import app.java.aggr.commons.dimensions.leisureInteract.LeisureInteractStats;
import app.java.aggr.commons.dimensions.mainActivity.MainActivityStats;
import app.java.aggr.commons.dimensions.materialLiving.MaterialLivingStats;
import app.java.aggr.commons.dimensions.overall.OverallExperienceStats;
import app.java.aggr.commons.dimensions.safety.SafetyStats;
import app.java.aggr.data.fetch.DataCollector;

import java.util.Arrays;
import java.util.List;

import static app.java.aggr.StartupUtils.*;

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
            // 3. Calculate and write the QoLI and the QoLI dimensions values to disk
            String direction = getDirection(list);
            QoLICsvStats.writeDimensions(direction, calculateIndicators);
            QoLIJsonStats.writeDimensions(calculateIndicators);
        }

        if (print) {
            String seriesType = getSeriesType(list);
            String direction = getDirection(list);

            if (seriesType != null) {
                // 4. Print the QoLI and the QoLI dimensions values
                QoLICsvStats.printDimensions(list, seriesType, direction);

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
