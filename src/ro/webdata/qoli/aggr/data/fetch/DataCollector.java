package ro.webdata.qoli.aggr.data.fetch;

import ro.webdata.qoli.aggr.commons.dimensions.auxiliary.AuxiliaryCollector;
import ro.webdata.qoli.aggr.commons.dimensions.education.EducationCollector;
import ro.webdata.qoli.aggr.commons.dimensions.environment.EnvironmentCollector;
import ro.webdata.qoli.aggr.commons.dimensions.gov.GovRightsCollector;
import ro.webdata.qoli.aggr.commons.dimensions.health.HealthCollector;
import ro.webdata.qoli.aggr.commons.dimensions.leisureInteract.LeisureInteractCollector;
import ro.webdata.qoli.aggr.commons.dimensions.mainActivity.MainActivityCollector;
import ro.webdata.qoli.aggr.commons.dimensions.materialLiving.MaterialLivingCollector;
import ro.webdata.qoli.aggr.commons.dimensions.overall.OverallExperienceCollector;
import ro.webdata.qoli.aggr.commons.dimensions.safety.SafetyCollector;

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
