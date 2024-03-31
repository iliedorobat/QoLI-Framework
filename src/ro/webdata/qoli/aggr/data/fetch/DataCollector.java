package ro.webdata.qoli.aggr.data.fetch;

import ro.webdata.qoli.aggr.stats.dimensions.auxiliary.AuxiliaryCollector;
import ro.webdata.qoli.aggr.stats.dimensions.education.EducationCollector;
import ro.webdata.qoli.aggr.stats.dimensions.environment.EnvironmentCollector;
import ro.webdata.qoli.aggr.stats.dimensions.gov.GovRightsCollector;
import ro.webdata.qoli.aggr.stats.dimensions.health.HealthCollector;
import ro.webdata.qoli.aggr.stats.dimensions.leisureInteract.LeisureInteractCollector;
import ro.webdata.qoli.aggr.stats.dimensions.mainActivity.MainActivityCollector;
import ro.webdata.qoli.aggr.stats.dimensions.materialLiving.MaterialLivingCollector;
import ro.webdata.qoli.aggr.stats.dimensions.overall.OverallExperienceCollector;
import ro.webdata.qoli.aggr.stats.dimensions.safety.SafetyCollector;

/**
 * Collect the data from Eurostat portal and save it to json files
 */
public class DataCollector {
    public static void collectData() {
        System.out.println("Starting the data collection process...\n");

        AuxiliaryCollector.fetchData();
        EducationCollector.fetchData();
        EnvironmentCollector.fetchData();
        GovRightsCollector.fetchData();
        HealthCollector.fetchData();
        LeisureInteractCollector.fetchData();
        MainActivityCollector.fetchData();
        MaterialLivingCollector.fetchData();
        OverallExperienceCollector.fetchData();
        SafetyCollector.fetchData();

        System.out.println("\nThe data collection process has been successfully completed!");
    }
}
