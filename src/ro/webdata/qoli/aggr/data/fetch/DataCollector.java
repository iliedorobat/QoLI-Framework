package app.java.aggr.data.fetch;

import app.java.aggr.commons.dimensions.auxiliary.AuxiliaryCollector;
import app.java.aggr.commons.dimensions.education.EducationCollector;
import app.java.aggr.commons.dimensions.environment.EnvironmentCollector;
import app.java.aggr.commons.dimensions.gov.GovRightsCollector;
import app.java.aggr.commons.dimensions.health.HealthCollector;
import app.java.aggr.commons.dimensions.leisureInteract.LeisureInteractCollector;
import app.java.aggr.commons.dimensions.mainActivity.MainActivityCollector;
import app.java.aggr.commons.dimensions.materialLiving.MaterialLivingCollector;
import app.java.aggr.commons.dimensions.overall.OverallExperienceCollector;
import app.java.aggr.commons.dimensions.safety.SafetyCollector;

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
